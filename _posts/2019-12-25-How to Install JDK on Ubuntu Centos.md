---
title: Ubuntu - How to Install Java JDK on Ubuntu & CentOS
date: 2017-12-01 00:00:00 Z
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



# Install Java JDK on Ubuntu

## OpenJDK Installation

### Installing the Default OpenJDK (Java 11) 

```bash
#First, update the apt package index with:
sudo apt update


#install the default Java OpenJDK package with:
sudo apt install default-jdk


#Verify the installation
java -version
```



### Installing OpenJDK 8 
```bash
sudo apt update
sudo apt install openjdk-8-jdk
```



## Installing Oracle Java

```bash
# Install the dependencies necessary to add a new repository:
sudo apt install software-properties-common

# Enable the Linux Uprising PPA 
sudo add-apt-repository ppa:linuxuprising/java

# update the packages list and install the oracle-java11-installer
sudo apt update
sudo apt install oracle-java11-installer

# accept the Oracle license & Verify the installation.
java -version
```



### Set the Default Java Version

If you have multiple Java installations to change the default version, use the update-alternatives tool
```bash
sudo update-alternatives --config java

#To change the default Java version just enter the version number (the number in the Selection column) and press Enter.
```




### Set the JAVA_HOME Environment Variable

To set the `JAVA_HOME` environment variable, first, you need to find out the Java installation paths using the update-alternatives command
```bash
sudo update-alternatives --config java
```

```bash
# Copy the installation path & open the /etc/environment file:
sudo nano /etc/environment

# Add the following line, at the end of the file:
JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64"

# Run 'source' command to apply the changes
source /etc/environment

# To verify that the JAVA_HOME run echo command:
echo $JAVA_HOME
```



if you want to **uninstall** the openjdk-8-jdk package run:
```bash
sudo apt remove openjdk-8-jdk
```




# Install JDK on CentOS



### Installing OpenJDK 11

```bash
#install java11
sudo yum install java-11-openjdk-devel

#verify
java -version

# To install only the headless OpenJDK 11 type(minimal Java runtime):
sudo yum install java-11-openjdk-headless
```




### Installing OpenJDK 8

```bash
# install 1.8
sudo yum install java-1.8.0-openjdk-devel

# Verify the installation
java -version

# Setting the Default Java Version
sudo alternatives --config java
```





