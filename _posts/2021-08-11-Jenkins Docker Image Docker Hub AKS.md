---
date: "2021-08-11 07:00:00 Z"
title: Jenkins – Docker Image + Docker Hub + AKS (Azure Kubernetes Service)
categories:
- Jenkins
tags:
- Jenkins
layout: article
cover: /assets/logo/jenkins.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

# Jenkins – Docker Image + Docker Hub + AKS (Azure Kubernetes Service)

## 1. Install below Plugins 

-   Docker

-   Docker pipeline

-   Kubernetes Deploy plug-ins

![](media/1e18fde2aa1f1cb35c7c42cf16734795.png)

## 2. Update Docker Hub credentials in Jenkins \> Manage Credentials 

![](media/13b87adc8693b6f1f23ae0dcef118441.png)

## 3. Update AKS Credentials in Jenkins

Go to `C:\Users\<USER_NAME>\.kube\config` & Copy complete file content starting
from apiVersion: v1

Go to Manage Credentials \> Add Credentials, In content – paste copied content
from **config** file. ![](media/b415023024c89d9cfa86c42cf9dbbbcd.png)

## 4. Create Jenkins Pipeline

Create new Jenkins Item \> Type as Pipeline
![](media/3bcfac6d8be3a895b9832a97aa7117d5.png)

Update Pipeline Code : `Jenkinsfile`

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ go
pipeline {
    agent 
    environment {
        //once you sign up for Docker hub, use that user_id here
        registry = "smlcodes/docker-employeeservices"
            //- update your credentials ID after creating credentials for connecting to Docker Hub
        registryCredential = 'docker-creds'
        dockerImage = ''
    }
    stages {

        stage('checkout') {
    		 steps {
                //github-creds are github login creds, configured in Credencial Manager 
                git branch: 'main',
                    credentialsId: 'github-creds',
                    url: 'https://github.com/smlcodes/docker-springboot-employeeservices.git'
            }
        }


        stage('Artifactory Configuration') {
            steps {

                rtServer(
                    : 'Artifactory-1',
                    url: "https://satyajohnny.jfrog.io/artifactory",
                    credentialsId: "jfrog-creds"
                )

                rtMavenResolver(
                    : 'maven-resolver',
                    serverId: 'Artifactory-1',
                    releaseRepo: 'libs-release-libs-release',
                    snapshotRepo: 'libs-release-libs-snapshot'
                )

                rtMavenDeployer(
                    : 'maven-deployer',
                    serverId: 'Artifactory-1',
                    releaseRepo: 'libs-release-libs-release-local',
                    snapshotRepo: 'libs-release-libs-snapshot-local',
                    threads: 6
                )
            }
        }

        stage('Build Maven Project') {
            steps {
                rtMavenRun(
                    tool: 'Maven',
                    pom: 'pom.xml',
                    goals: '-U clean install',
                    deployerId: "maven-deployer",
                    resolverId: "maven-resolver"
                )
            }
        }
        
        stage('Build Docker image') {
            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }

        // Uploading Docker images into Docker Hub
        stage('Upload Image to Docker HUB') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                }
            }
        }

        stage('Remove Unused docker image') {
            steps {
                 "docker rmi $registry:$BUILD_NUMBER"
            }
        }

        stage('K8S Deploy') {
            steps {
                script {
                    kubernetesDeploy(
                        configs: 'Kubernetes.yaml',
                        kubeconfigId: 'aks-creds',
                        enableConfigSubstitution: true
                    )

                }
            }
        }

    }
}

always {
    // remove built docker image and prune system
    print 'Cleaning up the Docker system.'
     'docker system prune -f'
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Kubernetes.yaml

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: docker-employeeservices-manual
spec:
  replicas: 2
  selector:
    matchLabels:
      app: docker-employeeservices-manual
  template:
    metadata:
      labels:
        app: docker-employeeservices-manual
    spec:
      containers:
      - name: docker-employeeservices-manual
        image: smlcodes/docker-employeeservices
        # image: wingtiptoysregistry.azurecr.io/gs-spring-boot-docker:latest
---
apiVersion: v1
kind: Service
metadata:
  name: docker-employeeservices-manual-service
spec:
  type: LoadBalancer
  ports:
  - port: 80
    targetPort: 8080
  selector:
    app: docker-employeeservices-manual
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

## 5. Run Pipeline

![](media/bdfa4ae67fb7b3bc65f0659084d2253f.png)

## 6.Validate UI

We need Public IP of AKS cluster. Go to AKS Portal in below Image

![](media/f8ddbde9a0ae00c53692fb9c346da84c.png)

Check Pods are Running![](media/3001ee30dbc45dcba94bc02ed0c37de2.png)

open public IP **without port**

![](media/8a4fc1b8c7d37f3898d82711e359e100.png)
