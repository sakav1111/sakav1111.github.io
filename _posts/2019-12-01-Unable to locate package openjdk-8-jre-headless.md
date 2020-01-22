---
title: Ubuntu - Unable to locate package openjdk-8-jre-headless 
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



Jenkins - Ubuntu: Unable to locate package openjdk-8-jre-headless
======================
I have Jenkins running on my Ubuntu 14.04, and i am trying to do upgrade for jenkins using normal apt-get install jenkins, but when i try to do it, i keep getting the error.
```bash
Found an incorrect Java version
Java version found:
java version "1.7.0_131"
OpenJDK Runtime Environment (IcedTea 2.6.9) (7u131-2.6.9-0ubuntu0.14.04.2)
OpenJDK 64-Bit Server VM (build 24.131-b00, mixed mode)

Aborting
invoke-rc.d: initscript jenkins, action "start" failed.
dpkg: error processing package jenkins (--configure):
 subprocess installed post-installation script returned error exit status 1
Errors were encountered while processing:
 jenkins
E: Sub-process /usr/bin/dpkg returned an error code (1)
```


## Solution
 
I'm using Java 7, but Jenkins needs Java 8.You need JDK 8 to run jenkins.

If i run this i got another error
```bash
root@vagrant-ubuntu-trusty-64:/home/vagrant# sudo apt install openjdk-8-jre-headless
Reading package lists... Done
Building dependency tree
Reading state information... Done
E: Unable to locate package openjdk-8-jre-headless
```

<br>

**Add the webupd8 repo to your system**:
```bash
sudo add-apt-repository ppa:webupd8team/java
sudo -E add-apt-repository ppa:openjdk-r/ppa 
sudo apt-get update
sudo apt-get install openjdk-8-jdk
```

You can also install multiple version of jdk, mixing openjdk and oracle versions. Then you can use the command update-java-alternatives to switch between installed version:

```bash
# list available jdk
update-java-alternatives --list

# use jdk7
sudo update-java-alternatives --set java-7-oracle

# use jdk8
sudo update-java-alternatives --set java-8-oracle
```


## how to switch between different versions

run the follwing command from the terminal:
```bash
sudo update-alternatives --config java
```


There are 2 choices for the alternative java (providing /usr/bin/java).

```bash
Selection    Path                                            Priority   Status
------------------------------------------------------------
  0            /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/java   1071      auto mode
  1            /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/java   1071      manual mode
* 2            /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java   1069      manual mode
```


- Press enter to keep the current choice[*], or type selection number:
As you can see I'm running open jdk 8. 

- To switch to to jdk 7, press 1 and hit the Enter key. Do the same for javac as well with, `sudo update-alternatives --config javac`.

Check versions to confirm the change:  
`java -version and javac -version.`