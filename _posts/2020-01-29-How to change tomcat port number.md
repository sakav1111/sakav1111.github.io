---
title: How to change tomcat port number & GUI User
date: 2019-01-29 0:03:00 Z
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

# How to change tomcat port number & GUI User

## How to change tomcat port number

- Go to `tomcat>conf` folder

- Edit `server.xml`

- Search "`Connector port`"

- Replace "`8080`" by your port number

- Restart tomcat server.


## How to change default user and password for Tomcat
- Edit `conf/tomcat_users.xml`.

- Uncomment the roles and users block. Define a role named `admin` (if tomcat6) or `manager-gui` (tomcat7). 

- Then, define a user name & password with role `admin` or `manager-gui` assigned to it.
    ```xml
    <role rolename="tomcat"/>
    <role rolename="role1"/>
    <role rolename="admin"/>
    <role rolename="manager-gui"/>
    <user username="tomcat" password="<must-be-changed>" roles="tomcat"/>
    <user username="admin" password="admin" roles="tomcat,role1,admin,manager-gui"/>
    <user username="root" password="root" roles="tomcat,role1,admin,manager-gui"/>
    <user username="satya" password="satya" roles="tomcat,role1,admin,manager-gui"/>
    <user username="role1" password="role" roles="role1"/>
    ```


- Restart your tomcat. Test.
