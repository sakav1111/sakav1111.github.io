---
date: "2021-08-11 08:00:00 Z"
title: Jenkins – Jenkins Pipeline for SpringBoot Application JFrog + Docker + Kubernetes + Azure ACS & AKS
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


# Jenkins – Jenkins Pipeline for SpringBoot Application JFrog + Docker + Kubernetes + Azure ACS & AKS

![](media/29744ec9e49013476f817de70c392361.png)

![](media/2dabd9bb5c761830b85b6e4d0aa7a92c.png)


<br>



## 1.Configure SpringBoot Project

We have SpringBoot application in Github.

<https://github.com/smlcodes/Docker-SpringBoot-EmployeeServices.git>

we have removed MySQL dependency from docker because we do not require separate
MySQL instance for each Image.

Build & Run the GitHub code.

![](media/f7375fd89a43acaf32a522cc7de1a57e.png)

<br>



## 2.Create MySQL Database in Azure

<https://docs.microsoft.com/en-us/azure/mysql/quickstart-create-mysql-server-database-using-azure-portal>

<br>



## 3.Manual Docker Image for SpringBoot Project

To containerize an application, we enclose our application inside a container
image and publish that image to a shared registry. The container runtime pulls
this image from the registry, unpacks the image, and runs the application inside
it.

<br>



### a. Building a Container Image the Conventional Way

**1.Creating a** `Dockerfile`

A `Dockerfile` is a plain text file that contains instructions from which a
Docker image is built. Create a `Dockerfile` in the project’s root folder.

A `Dockerfile` contains the commands below.

-   **FROM**: directive sets the image on which the container will be based on.

-   **RUN**: executes commands in the container.

-   **COPY**: creates a copy of files from the file system in the container.

-   **CMD**: sets the executable commands within the container.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Image based on JAVA Container
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Docker-EmployeeServices-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/Docker-EmployeeServices-1.0.jar"]
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>



**2.Building the Application**

We first build the application with Maven. This creates an executable JAR of the
application. We need to convert this executable JAR into a Docker image for
running in a Docker engine.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
mvn clean package
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>



**3. Building the Container Image**  
We need to create Docker image from jar file by running `docker build -t
Repository:TAGVERSION`

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
docker build -t employeeservices:latest .
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/0ba3cebce783b846e4f6ec13549db8b8.png)

<br>



We can see our image listed with the command:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
docker images
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/e8b01f2b5d79cc5c6b678ea28609385d.png)

<br>



4.**Viewing the Layers Inside the Container Image**  
We will use the [dive tool](https://github.com/wagoodman/dive) to view check
layers inside the image. using `dive Repository:TAGVERSION`

![](media/45f2dba23ee6005ba77db8e64f3d259e.png)

<br>



5.Running Docker Image

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
docker run --name employeeservices -d -p 8080:8080 employeeservices:latest
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>



6.Execute the command below to list all the running Docker containers.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
docker container ps
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>



7.Access Running application by docker URL

![](media/f2934eb49fcd90d03c2cc07d508f016c.png)


<br>



<br>




### b. Building Using Maven

 
By using Maven, we have multiple options.

1.  Using the integrated Spring Boot **build-image** goal.

2.  Using the **jib-maven-plugin** from Google.

3.  Using the **dockerfile-maven-plugin** from Spotify.

#### 1.Using the integrated Spring Boot build-image goal.

Spring Boot comes pre-shipped with its own plugin for building Docker images and
you don’t need to make any changes. It is available through the standard
**spring-boot-starter-parent** that is included in your **pom.xml.**

you **do not need to write a Dockerfile** either and the plugin takes care of
Spring-recommended security, memory, and performance optimizations. if there is
a **Dockerfile** located within your source code repository, **it will be
ignored**.

<br>


To create Docker image, just run

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
mvn spring-boot:build-image
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

make sure project name must be on small letters & no special symbols

![](media/0a31ee570319456a1cf4cf4cf73b5085.png)

![](media/2da7982027a1ac935bb67648dd9472ff.png)

<br>



#### 2. Using the jib-maven-plugin from Google

Jib is a Maven and Gradle plugin for creating Docker images for Java
applications.

-   **it does not require Docker to be installed locally**

-   **it does not require you to have written a Dockerfile**

-   Best Choice for Continuous Integration / build server

-   [jib-maven-plugin](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin)
    will build and push the image straight to the Docker registry of choice.

<u>**Using Local System**</u>  
Since we are not pushing to a Docker registry, we will tell Jib to use our local
Docker installation by using the **dockerBuild** goal.

pom.xml

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
<plugin>
	<groupId>com.google.cloud.tools</groupId>
	<artifactId>jib-maven-plugin</artifactId>
	<version>2.6.0</version>
	<configuration>
		<from>
			<image>gcr.io/distroless/java:8</image>
		</from>
		<to>
			<image>docker-employeeservices:${project.version}</image>
		</to>
	</configuration>
</plugin>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Builds to a container image and pushes registry.
$ mvn compile jib:build

# Builds to a Docker daemon in local system.
$ mvn compile jib:dockerBuild

# This builds and saves your image as tarball to target/jib-image.tar
$ mvn compile jib:buildTar

# you can load tar Image into docker with
docker load --input target/jib-image.tar
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ 
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/e25a5822a8b231f93d0adedf246c2879.png)

The only configuration we needed is to provide was \<to\>\<image\>, which is the
name of our Docker image. We can now use that plugin to create a Docker image
that will be available on our local Docker daemon.

To build the Docker image, run the command, without maven dependency in pom.xml

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
mvn compile com.google.cloud.tools:jib-maven-plugin:2.3.0:dockerBuild
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>



<u>**Using DockerHub**</u>

1.add registry credentials to the Maven **Maven\\conf\\settings.xml** file

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	<server>
	  <id>registry.hub.docker.com</id>
		<username>smlcodes</username>
		<password>passw0rd@</password>
	</server>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

2.Update pom.xml

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
<plugin>
	<groupId>com.google.cloud.tools</groupId>
	<artifactId>jib-maven-plugin</artifactId>
	<version>2.6.0</version>
	<configuration>
		<from>
			<image>gcr.io/distroless/java:8</image>
		</from>
		<to>
			<image>smlcodes/docker-employeeservices:${project.version}</image>
		</to>
	</configuration>
</plugin>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

-   Typically, you don’t need to provide **\<from\>** as by default, as it uses
    a distro-less Java 8 image. However, I have used Java 11, so I have
    explicitly mentioned that here. Moreover, depending on your use case, you
    may want to use a different base image

-   **\<image\>** This refers to the target image that will pushed to the
    container registry**.**

**To Build & Publish docker image**

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
mvn compile jib:build
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/8751c9abca4abe6e88c8826794792c1d.png)

**Check Docker HUB online**

![](media/6f2307bc886834e7608361d2eb83ec52.png)

I have used a Docker registry, but you can use any cloud provider's (ECS, GCR,
ACR) container registry. For other options available for use with the plugin,
you can refer to the
[documentation](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin).

<br>



#### 3.Using the dockerfile-maven-plugin

Add your Dockerfile into your root directory alongside your pom.xml.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
<plugin>
	<groupId>com.spotify</groupId>
	<artifactId>dockerfile-maven-plugin</artifactId>
	<version>1.4.13</version>
	<executions>
		<execution>
			<id>default</id>
			<goals>
				<goal>build</goal>
				<goal>push</goal>
			</goals>
		</execution>
	</executions>
	<configuration>
		<repository>${project.artifactId}</repository>
		<tag>${project.version}</tag>
		<buildArgs>
            <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
	       </buildArgs>
</configuration>
</plugin>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>



<br>



## 4.Jenkins Configuration

Downoad & Start Jenkins from [**here**](https://www.jenkins.io/download/)

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
cd C:\Docker\Jenkins
 
java -jar jenkins.war --httpPort=2222

echo "Jenkins Started . . . "
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


<br>



**Login to Jenkins**  

<http://localhost:2222/>

admin/admin

<br>



<br>



## 5.Docker Image PUSH to JFrog Artifactory 

Go to JFrog Website: <https://jfrog.com/start-free> create an account, login

On your system cmdline

`docker login satyajohnny.jfrog.io`

![](media/1d51e74cbc204b5e6cd91061e38faa6b.png)

From next time onwards it won’t ask for Credentials

Go to Administration \> Add Repository \> Local \> Select : Docker \> Name :
**jenkins-docker-images** ![](media/e2d0a3c744399ed3bf8d8e0383dbc0b8.png)

To Integrate with Jenkins, follow
<http://satyacodes.ml/jenkins/jfrog-integration>

![](media/177c6ae40de0841e443fbbca9df43e67.png)

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ go
pipeline {
    agent 
 
   	tools {
   	//Java, Maven Configured in Globol tools configuration. and here the names we provided for configuration
   	 	 'Java8'
    	 'Maven'
  	}
    stages {
        stage ('Clone') {
            steps {
            //github-creds are github login creds, configured in Credencial Manager 
            	git branch: 'main',
    				credentialsId: 'github-creds',
    				url: 'https://github.com/smlcodes/Docker-SpringBoot-EmployeeServices.git'
                
            }
        }
 
             /* 
                    Artifactory-1 - similarly like Globol tool configuration. 
                    				But we are configured manually, passed as ServerID
                    				
                    rtMavenResolver - To get Jars of Pom.xml	
                    rtMavenDeployer - To Deploy Newly created jars on Build			
            */
        stage ('Artifactory Configuration') {
            steps {

					rtServer (
					    : 'Artifactory-1',
					    url: "https://satyajohnny.jfrog.io/artifactory",
                    	credentialsId: "jfrog-creds"
					)
				
				rtMavenResolver (
                    : 'maven-resolver',
                    serverId: 'Artifactory-1',
                    releaseRepo: 'libs-release-libs-release',
                    snapshotRepo: 'libs-release-libs-snapshot'
                )  
                 
                rtMavenDeployer (
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
                rtMavenRun (
                    tool: 'Maven',
                    pom: 'pom.xml',
                    goals: '-X clean install',
                    deployerId: "maven-deployer",
                    resolverId: "maven-resolver"
                )
            }
        }
    }
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

After Running this New jars added to JFrog
Artifactory![](media/a75d5558bf6c56d6c352641c060a2410.png)

## 5.Docker Image Push to Docker HUB

First, create a environment to save docker image information’s.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
dockerImage = ''
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Change the build stage to save build information in environment.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
dockerImage = docker.build registry + ":$BUILD_NUMBER"
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ go
pipeline {

    environment {
        registry = "smlcodes/docker-employeeservices"
        registryCredential = 'docker-creds'
        dockerImage = ''
    }
    agent 

    tools {
         'Java8'
         'Maven'
    }
    
    
    
    stages {
        stage('Clone') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-creds',
                    url: 'https://github.com/smlcodes/docker-springboot-employeeservices.git'

            }
        }

        /* 
                    Artifactory-1 - similarly like Globol tool configuration. 
                    				But we are configured manually, passed as ServerID
                    				
                    rtMavenResolver - To get Jars of Pom.xml	
                    rtMavenDeployer - To Deploy Newly created jars on Build			
            */
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

        stage('Build Docker Image') {
            steps {

                 "\n \n \n \n ***************************************"
                 "         Build Docker Image"
                 "***************************************\n \n \n"

                script {
                    docker.build("satyajohnny.jfrog.io/" + "docker-employeeservices:2.0.${env.BUILD_NUMBER}")
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }


        stage('Push Image to DockerHub') {
            steps {
                script {

                     "\n \n \n \n ***************************************"
                     "         Push Image to DockerHub"
                     "*********************************\n \n \n"
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
         
    } //Stages End

} //Pipeline End
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/4afb89e9c6f5b3aaa8d66aa28e01b342.png)

![](media/b2ea3326a03ee99de345a43719267574.png)

<br>



<br>



## 5.Docker Image Push to Azure Container Registry 

To Quick start Azure Container Registry follow

<https://docs.microsoft.com/en-us/azure/container-registry/container-registry-get-started-portal>

Select **Create a resource** \> **Containers** \> **Container Registry**.
![](media/61234f437de957d4afb0dcc797b5d396.png)

![](media/1fbe7e7d51477d24b262e1cc7137e237.png)

Go to Access Keys in Container Registry and enable the admin user, then use the
autogenerated credentials to login via
Docker![](media/6539a81f70b2caa63f6dbbf5d58e8fd8.png)

Configure these credentials in Jenkins with name `'azurecontainer-creds'`

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ go
pipeline {

    environment {    	
        dockerregistry = "smlcodes/docker-employeeservices"
        dockercreds = 'docker-creds'
        dockerHubImage = ''
        
        //Go to Access Keys in Container Registry and enable the admin user, then use the autogenerated credentials to login via Docker
        azureregistry = "satyajohnnycontainer.azurecr.io"
        azurecreds = 'azurecontainer-creds'
        azureImage = '' 
             
    }
    agent 

    tools {
         'Java8'
         'Maven'
    }
    
    
    
    stages {
        stage('Clone') {
            steps {
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

        stage('Build Docker Image') {
            steps {

                script {
azureImage = docker.build("satyajohnnycontainer.azurecr.io/" + "docker-employeeservices:2.0.${env.BUILD_NUMBER}")
                }
            }
        }
        
           stage('Push Image to Azure Container Registry') {
            steps {
                script {
                    docker.withRegistry('https://satyajohnnycontainer.azurecr.io', azurecreds) {
                        azureImage.push()
                    }
                }
            }
        }
          

        stage ('Publish Build Info') {
            steps {
                rtPublishBuildInfo (
                    serverId: "Artifactory-1"
                )
            }
        }

    } //Stages End

} //Pipeline End
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/a687c543b5f3e15d06cd1045ec3853b2.png)

Now go to Azure Container Registry \> Services \> Repository , see Docker Image
published

![](media/f27f28c84fea1e132558683c95da31ff.png)

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#Login 
az acr login --name satyajohnnycontainer

#List images in registry
az acr repository list --name satyajohnnycontainer --output table
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/8ce8580fc277ac45cbf66c2de07dce52.png)

<br>



<br>



## 6.AKS – Azure Kubernetes Service 

AKS is a managed Kubernetes service that lets you quickly deploy and manage
clusters. We will see how to create AKS cluster in Azure cloud using Azure CLI.

**Pre-requisites:**

-   Azure CLI is installed on your local machine.

-   Account setup in Azure.

![](media/a84f47b0c89cfb41cbb6e96781d90c64.png)

### Create Azure Container Registry

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#Login to Azure
az login

#Create a resource group first.
az group create --name myResourceGroup --location eastus
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>



Run the below command to create your own private container registry using
**Azure Container Registry**

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
az acr create --resource-group myResourceGroup --name satyajohnnycontainer --sku Standard --location eastus
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


<br>




**Create AKS cluster with 2 worker nodes**

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
az aks create --resource-group myResourceGroup --name  --node-count 2 --enable-addons monitoring --generate-ssh-keys
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
C:\Windows\system32> --resource-group myResourceGroup --name  --node-count 2 --enable-addons monitoring --generate-ssh-keys

SSH key files 'C:\Users\kavetis\.ssh\id_rsa' and 'C:\Users\kavetis\.ssh\id_rsa.pub' have been generated under ~/.ssh to allow SSH access to the VM. If using machines without permanent storage like Azure Cloud Shell without an attached file share, back up your keys to a safe location
Resource provider 'Microsoft.OperationalInsights' used by this operation is not registered. We are registering for you.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>



Once Above cmd run completes, we can see many no. of services are created which
are required by AKS. ![](media/95fb5d03b8a6b939c7936ed28407fcbd.png)

We can see 2 Nodes inside AKS Cluster
![](media/c979f38ed6fe76a2ae4d94a76cf0ebbf.png)

<br>



### Connect to the cluster

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
az aks get-credentials --resource-group myResourceGroup --name  --overwrite-existing
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

To verify the connection to your cluster, use the kubectl get command to return
a list of the cluster nodes.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
kubectl get nodes
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/dbb44d092d3718d5674d343a8073e68f.png)

\# List all deployments in a specific namespace

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
kubectl get deployments --all-namespaces=true
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/aca9fd734279e2fa038326e8f52d31bf.png)

### Update Jenkins Pipeline

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ go
pipeline {
    agent 
    environment {        
        registry = "smlcodes/docker-employeeservices"
        registryCredential = 'docker-creds'
        dockerImage = ''
    }
    stages {

        stage('checkout') {
    		 steps {
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

<br>



`Kubernetes.yaml` – For Docker HUB

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

<br>



Build Pipeline

![](media/dd1af48fa8d0a9afc4c624f55758d484.png)

<br>



Build Is Success, But if you see AKS Cluster it is unable to Pull the
Image![](media/d033cc8415f503b5540dc56e62d81ac1.png)

<br>



Here we can see that AKS is not able to pull image from ACR. Let’s describe the
pod and look for more information.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
kubectl get pods
docker-employeeservices-acs-86b4879d-7h5x5        0/1     ImagePullBackOff   0          16h
docker-employeeservices-acs-86b4879d-b4zc2        0/1     ImagePullBackOff   0          16h
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>



**kubectl describe pods** **docker-employeeservices-acs-86b4879d-7h5x5**
![](media/69e43dbdda954ef0aad4e205f9d3ec08.png)

Here the Problem is ACS & AKS Integration. For that we need to run below command

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
az aks update -n myAKSCluster -g myResourceGroup --attach-acr <acr-name>

az aks update -n satyajohnnyAKSCluster -g myResourceGroup --attach-acr satyajohnnycontainer
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/f240928f64974ca8d0fa39aa39973151.png)

<br>



When we attach ACR to AKS, it actually grants the ‘acrpull’ role assignment to
ACR specified by name or resource ID. Check Integration Status

`az aks check-acr --name satyajohnnyAKSCluster --resource-group myResourceGroup
\--acr satyajohnnycontainer.azurecr.io`

![](media/3e70337ecde081d871e6429fe36a2bc9.png)

Re-Run Pipeline Again.

<br>



<br>



## 7. Verify deployments to K8S

`kubectl get pods`

![](media/a087ae8840870f8c392b363a3c864feb.png)

`kubectl get deployments`

![](media/476c26c601e608e7494da8f6110c782f.png)

`kubectl get services`

![](media/d056ca955c1654ddb3d8f4e1cc085f69.png)

Here **External-IP** is the Public IP Where we can access Our Application. You
can also get from UI, Go to AKS Portal in below Image

![](media/f8ddbde9a0ae00c53692fb9c346da84c.png)

Check Pods are Running![](media/3001ee30dbc45dcba94bc02ed0c37de2.png)

open public IP **without port**

![](media/8a4fc1b8c7d37f3898d82711e359e100.png)

<br>



<br>



## 8. Final Working Pipeline Code

// Dockerfile

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ go
# Image based on JAVA Container
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Docker-EmployeeServices-2.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/Docker-EmployeeServices-2.0.jar"]
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 <br>

// Jenkinsfile
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ go

pipeline {

    environment {
    	
        dockerregistry = "smlcodes/docker-employeeservices"
        dockercreds = 'docker-creds'
        dockerHubImage = ''
        
        //Go to Access Keys in Container Registry and enable the admin user, then use the autogenerated credentials to login via Docker
        azureregistry = "satyajohnnycontainer.azurecr.io"
        azurecreds = 'azurecontainer-creds'
        azureImage = ''              
    }
    
    
    agent 

    tools {
        //Java, Maven Configured in Globol tools configuration. and here the names we provided for configuration
         'Java8'
         'Maven'
    }
    
    
    
    stages {
        stage('Clone') {
            steps {
                //github-creds are github login creds, configured in Credencial Manager 
                git branch: 'main',
                    credentialsId: 'github-creds',
                    url: 'https://github.com/smlcodes/docker-springboot-employeeservices.git'

            }
        }

        /* 
                    Artifactory-1 - similarly like Globol tool configuration. 
                    				But we are configured manually, passed as ServerID
                    				
                    rtMavenResolver - To get Jars of Pom.xml	
                    rtMavenDeployer - To Deploy Newly created jars on Build			
            */
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

        stage('Build Docker Image') {
            steps {

                 "\n \n \n \n ******************************************************************************"
                 "         Build Docker Image"
                 "******************************************************************************\n \n \n"

                script {
                    //docker.build("satyajohnny.jfrog.io/" + "docker-employeeservices:2.0.${env.BUILD_NUMBER}")
                    dockerHubImage = docker.build dockerregistry + ":$BUILD_NUMBER"
                    azureImage = docker.build("satyajohnnycontainer.azurecr.io/" + "docker-employeeservices:${env.BUILD_NUMBER}")
                }
            }
        }


        
           stage('Push Image to Azure Container Registry') {
            steps {
                script {

                     "\n \n \n \n ******************************************************************************"
                     "         Push Image to Azure Container Registry"
                     "******************************************************************************\n \n \n"
                    docker.withRegistry('https://satyajohnnycontainer.azurecr.io', azurecreds) {
                        azureImage.push()
                        azureImage.push('latest')
                    }
                }
            }
        }
      
       // Uploading Docker images into Docker Hub
        stage('Push Image to Docker HUB') {
            steps {
                script {
                    docker.withRegistry('', dockercreds) {
                        dockerHubImage.push()
                        dockerHubImage.push('latest')
                    }
                }
            }
        }
        
        
       stage('Remove Unused docker image') {
            steps {
                 "docker rmi $dockerregistry:$BUILD_NUMBER"
            }
        }
        
        // Deploy To Azure Kubernetes Cluster
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
		        
		        

		        

	/*
	Push Image to Artfifactory Not Woking
	serverId : Configred Artfifactory Server Name
	host : docker daemon host where  Docker currenlty running system/localhost  
	targetRepo :  Repo name in Artfifactory
	*/
 
 /*    
       stage ('Push Image to Artifactory') {
            steps {
			echo "\n \n \n \n ******************************************************************************"
            echo "       Not Working on LocalHost :   Push Docker Image to Artifactory"
            echo "******************************************************************************\n \n \n"
                rtDockerPush(
                    serverId: "Artifactory-1",
                    image: "satyajohnny.jfrog.io/" + "docker-employeeservices:2.0.${env.BUILD_NUMBER}",
					 host: 'tcp://thedockerhost:2375/',
                    targetRepo: 'jenkins-docker-images',
                    properties: 'project-name=docker-employeeservices:2.0;status=stable'
                )
          }
        }
  */      

/*
        stage('Push Image to DockerHub') {
            steps {
                script {

                    echo "\n \n \n \n ******************************************************************************"
                    echo "         Working - Push Image to DockerHub"
                    echo "******************************************************************************\n \n \n"
                    docker.withRegistry('', dockercreds) {
                        dockerHubImage.push()
                    }
                }
            }
        }
  */      





    } //Stages End

} //Pipeline End

always {
    // remove built docker image and prune system
    print 'Cleaning up the Docker system.'
     'docker system prune -f'
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 <br>

Kubernetes.yaml

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: docker-employeeservices-acs
spec:
  replicas: 2
  selector:
    matchLabels:
      app: docker-employeeservices-acs
  template:
    metadata:
      labels:
        app: docker-employeeservices-acs
    spec:
      containers:
      - name: docker-employeeservices-acs       
        image: satyajohnnycontainer.azurecr.io/docker-employeeservices:latest
        #image: smlcodes/docker-employeeservices
---
apiVersion: v1
kind: Service
metadata:
  name: docker-employeeservices-acs-service
spec:
  type: LoadBalancer
  ports:
  - port: 80
    targetPort: 8080
  selector:
    app: docker-employeeservices-acs
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 <br>

## 9. Delete Cluster 

To avoid Azure charges, you should clean up unneeded resources. When the cluster
is no longer needed, use the az group delete command to remove the resource
group, container service, and all related resources.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
az group delete --name myResourceGroup --yes --no-wait
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

## Ref.

-   <https://www.coachdevops.com/2020/06/deploy-python-app-into-kubernetes.html>

-   <https://docs.microsoft.com/en-us/azure/mysql/quickstart-create-mysql-server-database-using-azure-portal>

-   <https://reflectoring.io/spring-boot-docker/>

-   <https://medium.com/swlh/build-a-docker-image-using-maven-and-spring-boot-58147045a400>

-   <https://medium.com/@gustavo.guss/jenkins-building-docker-image-and-sending-to-registry-64b84ea45ee9>

-   <https://docs.microsoft.com/en-us/azure/container-registry/container-registry-get-started-portal>

-   <https://jfrog.com/blog/ci-cd-side-by-side-jenkins-and-jfrog-pipelines/>

-   <https://www.jfrog.com/confluence/display/JFROG/Declarative+Pipeline+Syntax>

-   <https://technology.amis.nl/languages/java-languages/jenkins-building-java-and-deploying-to-kubernetes/>

-   <https://github.com/talitz/spring-petclinic-ci-cd-k8s-example/blob/master/Jenkinsfile>

-   <https://dzone.com/articles/building-docker-images-to-docker-hub-using-jenkins>

-   <https://stackoverflow.com/questions/58203820/jenkins-with-jfrog-artifactory-push-docker-images>

-   <https://stackoverflow.com/questions/50022357/can-not-push-docker-image-to-artifactory-in-jenkins-pipeline-using-artifactory-p>

-   <https://github.com/dumindarw/reactive-eventservice/blob/master/Jenkinsfile>

-   <https://github.com/learnk8s/free-kubernetes>

-   <https://docs.microsoft.com/en-us/azure/architecture/solution-ideas/articles/container-cicd-using-jenkins-and-kubernetes-on-azure-container-service>

-   <https://ystatit.medium.com/azure-configure-acr-integration-for-existing-aks-cluster-c551c678fede>

-   <https://github.com/dumindarw/reactive-eventservice/blob/master/Jenkinsfile>

-   <https://github.com/learnk8s/free-kubernetes>

-   <https://docs.microsoft.com/en-us/azure/architecture/solution-ideas/articles/container-cicd-using-jenkins-and-kubernetes-on-azure-container-service>
