---
title: How To - Install Apache Tomcat On Ubuntu
date: 2017-11-26 00:00:00 Z
categories:
- HowTo
tags:
- HowTo
layout: article
cover: /assets/logo/howto.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---


## How To Install Apache Tomcat On Ubuntu

### Install JDK



Check if Java is Installed
```java
sudo apt update
sudo apt install openjdk-8-jdk

java –version
```

### Download Tomcat 9
1. Download the latest binary Tomcat release navigate to the official Apache Tomcat Download page.

2. On it, find the Binary Distributions > Core list and the tar.gz link in it. Copy the link of the file.
https://phoenixnap.com/kb/how-to-install-tomcat-ubuntu


### 1. Create Tomcat User and Group
For security reasons, do not run Tomcat under the root user. Create a new group and system user to run the Apache Tomcat service from the /opt/tomcat directory.
```
mkdir /opt/tomcat
cd /opt/tomcat
sudo groupadd tomcat
sudo useradd -s /bin/false -g tomcat -d /opt/tomcat tomcat  
cd /opt && sudo chown -R tomcat tomcat/
```

### 2.Install Tomcat
Go to https://tomcat.apache.org/download-90.cgi

Get tar.gz URL :: http://apache.mirrors.ionfish.org/tomcat/tomcat-8/v8.5.5/bin/apache-tomcat-8.5.5.tar.gz

Next, change to the /tmp directory on your server
`cd /tmp`

Use curl to download from Link
`curl -O http://apachemirror.wuchna.com/tomcat/tomcat-8/v8.5.56/bin/apache-tomcat-8.5.56.tar.gz`


We will install Tomcat to the /opt/tomcat directory. Create the directory, then extract the archive to it with these commands:
```
sudo mkdir /opt/tomcat
sudo tar xzvf apache-tomcat-8*tar.gz -C /opt/tomcat --strip-components=1
```

Update Permissions
Give the tomcat group ownership over the entire installation directory:
```
cd /opt/tomcat
sudo chgrp -R tomcat /opt/tomcat
sudo chmod -R g+r conf
sudo chmod g+x conf
```

Make the tomcat user the owner of the webapps, work, temp, and logs directories:
`sudo chown -R tomcat webapps/ work/ temp/ logs/`


### 3.Setup systemd Service

Tomcat needs to know where Java is installed. To Set JAVA_HOME
`sudo update-java-alternatives -l`

Create File, tomcat.service
`sudo vi /etc/systemd/system/tomcat.service`

Paste the following contents into your service file. Modify the value of JAVA_HOME if necessary to match the value you found on your system. You may also want to modify the memory allocation settings that are specified in CATALINA_OPTS:

/etc/systemd/system/tomcat.service
```
[Unit]
Description=Apache Tomcat Web Application Container
After=network.target

[Service]
Type=forking

Environment=JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
Environment=CATALINA_PID=/opt/tomcat/temp/tomcat.pid
Environment=CATALINA_HOME=/opt/tomcat
Environment=CATALINA_BASE=/opt/tomcat
Environment='CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC'
Environment='JAVA_OPTS=-Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom'

ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh

User=tomcat
Group=tomcat
UMask=0007
RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target
```


Next, reload the systemd daemon so that it knows about our service file:
`sudo systemctl daemon-reload`


Start the Tomcat service by typing:
`sudo systemctl start tomcat`
sudo systemctl enable tomcat


### Adjust the Firewall and Test the Tomcat Server
Tomcat uses port 8080 to accept conventional requests. Allow traffic to that port by typing:  
`sudo ufw allow 8080`



### Allow webapp
`sudo vi /opt/tomcat/conf/tomcat-users.xml`

```
  <role rolename="tomcat"/>
  <role rolename="role1"/>
  <role rolename="admin"/>
  <role rolename="manager-gui"/>
  <user username="tomcat" password="tomact" roles="tomcat"/>
  <user username="admin" password="admin" roles="tomcat,role1,admin,manager-gui"/>
  <user username="root" password="root" roles="tomcat,role1,admin,manager-gui"/>
  <user username="satya" password="satya" roles="tomcat,role1,admin,manager-gui"/>
  <user username="role1" password="role" roles="role1"/>
```


By default, newer versions of Tomcat restrict access to the Manager and Host Manager apps to connections coming from the server itself.

Since we are installing on a remote machine, you will probably want to remove or alter this restriction. To change the IP address restrictions on these, open the appropriate context.xml files.

For the Manager app, type:
```java
sudo vi /opt/tomcat/webapps/manager/META-INF/context.xml
```

For the Host Manager app, type:
```java
sudo vi /opt/tomcat/webapps/host-manager/META-INF/context.xml
```

Inside, comment out the IP address restriction to allow connections from anywhere. Alternatively, if you would like to allow access only to connections coming from your own IP address, you can add your public IP address to the list:

`context.xml` files for Tomcat webapps
```java
<Context antiResourceLocking="false" privileged="true" >
  <!--<Valve className="org.apache.catalina.valves.RemoteAddrValve"
         allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1" />-->
</Context>
```




To put our changes into effect, restart the Tomcat service:
sudo systemctl restart tomcat


REf: https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-8-on-ubuntu-16-04