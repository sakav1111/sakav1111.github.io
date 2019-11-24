---
title: DevOps - SonarQube Ant Project Configuration
date: 2017-011-11 00:00:00 Z
categories:
- DevOps
tags:
- DevOps
layout: article
cover: /assets/logo/devops.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---


# **SonarQube Ant Project Configuration**



1.Install & Configure [apache ant](http://ant.apache.org/bindownload.cgi) on
your local system



2.[Download](https://mvnrepository.com/artifact/org.codehaus.sonar-plugins/sonar-ant-task/2.1)
**Sonar-ant-task.jar** and place the jar file in **apache-ant-1.10.0\\lib**
folder



3.Chose the Java ant Project for Analyzing Source Code. Ex: **LoginAnt** as
below

![http://localhost:6666/sml/wp-content/uploads/2017/03/SonarQube-Tutorial-SmlCodes-10.png](media/bc77e0be54264c52f2f602f9c1c15335.png)



4.Open **Build.xml** & Configure sonar details as below
```xml
<target name="sonar">
    <taskdef uri="antlib:org.sonar.ant" resource="org/sonar/ant/antlib.xml">        
        <!-- Update the following line, or put the "sonar-ant-task-*.jar" file in your "$HOME/.ant/lib" folder -->        
        <classpath  path="<Path of sonar-ant-task-1.3.jar>" />    
    </taskdef>    
    <property name="sonar.jdbc.username" value="sonar" />
    <property name="sonar.jdbc.password" value="sonar" />
    <property name="sonar.host.url" value="http://localhost:9000" />
<!-- Define the SonarQube project properties -->
    <property name="sonar.projectKey" value="<PackageName>" /> 
    <property name="sonar.projectName" value="<ProjectName>" />
    <property name="sonar.projectVersion" value="1.0" />
    <property name="sonar.language" value="java" />   
    <property name="sonar.binaries" value="<Path of sonar-3.7.1/lib" /> 
    <property name="sonar.sources" value="<Path of Java Source file>" />
    <property name="sonar.projectBaseDir" value="<Path of Project Directory>" />
<!-- Define the SonarQube target -->
<!-- Execute Sonar -->    
<sonar:sonar />
</target>
```


5.Run SonarQube by Starting
**C:\\DevOps\\sonarqube\\bin\\windows-x86-64\\StartSonar.bat**



6.Open SonarQube Dashboard on [http://localhost:9000](http://localhost:9000/)



7.Navigate to Project root directory/build directory & run below command

**ant sonar**

![http://localhost:6666/sml/wp-content/uploads/2017/03/SonarQube-Tutorial-SmlCodes-11.png](media/5aaec19a6791f551f70c0db2aa5d40aa.png)



8.Now the complete SONAR Report will be displayed on
[http://localhost:9000](http://localhost:9000/)


