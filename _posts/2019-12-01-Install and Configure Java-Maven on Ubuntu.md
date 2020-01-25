---
title: Ubuntu - Install & Configure Java, Maven on Ubuntu
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



Ubuntu: Install & Configure Java, Maven on Ubuntu
======================


# Java Installation

Open Terminal - Execute below commands
```bash
sudo add-apt-repository ppa:webupd8team/java
sudo -E add-apt-repository ppa:openjdk-r/ppa 
sudo apt-get update
sudo apt-get install openjdk-8-jdk
```

Some times we have muliple JDK's / we need to use specific version of java. for that 
```bash
# list available jdk
update-java-alternatives --list

# use jdk7
sudo update-java-alternatives --set java-7-oracle

# use jdk8
sudo update-java-alternatives --set java-8-oracle
```


To switch between different versions
```bash
# Press enter to keep the current choice[*], or type selection number: As you can see I’m running open jdk 8.
sudo update-alternatives --config java
```

#### Temporory WAY


```bash
# Then open /etc/environment any text editor
sudo nano /etc/environment
```

```bash
# /etc/environment : At the end of this file, add the following line
JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64/bin/"
```

```bash
# Reload file to apply the changes.
source /etc/environment
```
Other users will need to execute the command source /etc/environment or log out and log back in to apply this setting.

#### Peramanent WAY
Get all java versions installed in you machine
```bash
sudo update-alternatives --config java
```



Copy the Path you want save it permanetly - and do, `export` 
```bash
# /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java
```


To check whether your JAVA_HOME path has been successfully saved, enter the following command to check.
```bash
echo $JAVA_HOME
```


# Maven Installation


Maven 3.3+ requires JDK 1.7 or above to be installed

### 1. Install OpenJDK
```bash
sudo apt update
sudo apt install default-jdk

# verfiy Version
java --version
```

## Option 1 : with 'sudo apt'
```bash
# Install the Maven via apt command.
sudo apt install maven

# The apt command installed Maven in the following location
ls -lsa /usr/share/maven

ls -lsa /etc/maven
```


## Option 2 : Manual way

Download Apache Maven in the `/tmp` directory  
```bash
wget wget http://www-eu.apache.org/dist/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.tar.gz -P /tmp
```
  

Once the download is completed, extract the archive in the /opt directory:
```bash
sudo tar xf /tmp/apache-maven-*.tar.gz -C /opt
```
  

create a symbolic link maven
```bash
sudo ln -s /opt/apache-maven-3.6.0 /opt/maven
```


create a new file named `mavenenv.sh` inside of the `/etc/profile.d/` directory.
```bash
sudo vi /etc/profile.d/maven.sh
```
```bash
#Paste Below PATHS
/etc/profile.d/maven.sh
export JAVA_HOME=/usr/lib/jvm/default-java
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}

#Make the script executable with chmod:
sudo chmod +x /etc/profile.d/maven.sh
```

Verify Install
```bash
mvn -version
```


Optional - Verify PATH variable
```bash
echo $PATH
```
