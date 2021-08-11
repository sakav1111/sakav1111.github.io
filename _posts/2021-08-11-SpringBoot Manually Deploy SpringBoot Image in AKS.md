---
date: "2021-08-11 06:00:00 Z"
title: Spring Boot - Manually Deploy SpringBoot Image in Azure Kubernetes Service (AKS)
categories:
- SpringBoot
tags:
- SpringBoot
layout: article
cover: /assets/logo/springboot.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---


# SpringBoot – Manually Deploy SpringBoot Image in Azure Kubernetes Service (AKS)

1.  Select **Add** from any of the resource views (Namespace, Workloads,
    Services and ingresses, Storage, or Configuration).

    ![Kubernetes resources view](media/677a55e034c2a0327f239bbf8a2ab89e.png)



2.  Paste in the following YAML:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: docker-employeeservices-manual
spec:
  replicas: 1
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



3.  Select **Add** at the bottom of the YAML editor to deploy the application.

    ![Kubernetes resources view, add
    resource](media/6d6a792a7c95daf95851ad6f6c0cbc13.png)



4.  Once the YAML file is added, the resource viewer shows your Spring Boot
    application. The external service includes a linked external IP address so
    you can easily view the application in your browser.

    ![Kubernetes resources view, services
    list](media/aa06f52eaefa466328e4ed8305c2917a.png)

    ![Kubernetes resources view, services list, external endpoints
    highlighted](media/924d967a3c2c4c175a4c2c6f8f030b6e.png)



5.  Select **External IP**. You will then see your Spring Boot application
    running on Azure.

    ![Browse Sample App on Azure](media/7ba671c727768610c28fafc89ae96e61.png)

## Ref.

<https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/deploy-spring-boot-java-app-on-kubernetes>

<https://www.cloudiqtech.com/deploy-spring-boot-application-in-kubernetes-pod/>

<https://youtu.be/TPMUxsRI1OA>
<https://piotrminkowski.com/2020/11/10/continuous-integration-with-jenkins-on-kubernetes/>

<https://katharharshal1.medium.com/deploy-spring-boot-application-into-aws-eks-using-jenkins-cicd-2ced0e0d894c>

<https://www.youtube.com/watch?v=5C6kzqeO4Ew> (BEST)

<https://www.coachdevops.com/2020/06/deploy-python-app-into-kubernetes.html>

<https://www.youtube.com/watch?v=Dxmmh1lW-rA>

<https://www.youtube.com/watch?v=3UaqJGXMp8w>

<https://dzone.com/articles/dockerizing-jenkins-2-setup-and-using-it-along-wit>

<https://jfrog.com/blog/ci-cd-side-by-side-jenkins-and-jfrog-pipelines/>
