---
title: DevOps - Hygieia Jenkins Collector Configuration
date: 2019-11-11 00:00:00 Z
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

**1. Get Jenkins Build Tool Server Details**  
In this tutorial we are configuring Jenkins with Hygieia Dashboard. To configure
Jenkins we must the details like Jenkins server **URL**, **username**, **API
Token** etc.



Here I configured Jenkins server in my local machine, its URL is
**http://localhost:9051/.**

![http://localhost:6666/sml/wp-content/uploads/2017/09/Hygieia-Jenkins-Collector-Configuration-1.png](media/5b87b8560a4081bf2238debba1a87915.png)



For API Token, Go to **Jenkins Home → Right Top Click on Username
→Configure →Show API Token**

![http://localhost:6666/sml/wp-content/uploads/2017/09/Hygieia-Jenkins-Collector-Configuration-2.png](media/7b718332c41ceeeaa7998fed0fc1a74d.png)

 

So, my Jenkins server details are like,

-   Server URL: **http://localhost:9051**

-   Username: **root**

-   API Token : **07ef71a4e84fa29e28e2812f8f5b2582**

Make sure Jenkins server is running before we configure with Hygieia.

To configure Jenkins, [Please follow this Tutorial for more
details.](http://localhost:6666/sml/devops/jenkins-tutorial-devops-tool/)

 



**2. Start API**

-   Run Hygieia Dashboard API & Make Sure it is running.



-   To Run API from Eclipse, API
    [→](https://www.toptal.com/designers/htmlarrows/arrows/right-arrow/) Right
    Click [→](https://www.toptal.com/designers/htmlarrows/arrows/right-arrow/)
    **→Run as →Java Application →Choose main class :
    com.capitalone.dashboard.Application**

To configure API, [Please follow this Tutorial & Go to API
Section.](http://localhost:6666/sml/tutorials/hygieia-dashboard-tutorial/)



 

**3. Start UI**  
For Starting UI, Open nodejs command prompt, navigate to UI folder location &
run **gulp serve** command

![http://localhost:6666/sml/wp-content/uploads/2017/09/Hygieia-Jenkins-Collector-Configuration-3.png](media/598e32e5ea267ffcd622a3f06d3c9775.png)



Make Sure API is connected with UI. On Successful Connection UI **shows Green
Arrow**

![http://localhost:6666/sml/wp-content/uploads/2017/09/Hygieia-Jenkins-Collector-Configuration-4.png](media/880343de2025a9121ed76556418e042f.png)

To configure UI, [Please follow this Tutorial & Go to UI
Section.](http://localhost:6666/sml/tutorials/hygieia-dashboard-tutorial/)

 

**4. Configure Jenkins Build Collector**  
Go to Jenkins-Build-Collector root folder, create **application.properties**
file if not exist and provide your MongoDB Connection details & Jenkins server
details as below



Also you can find the sample application.properties file from
<https://github.com/capitalone/Hygieia/tree/master/collectors/build/jenkins>



Run Jenkins Build Collector by Right-click on Project **→Run as →Java
Application →Choose main class : com.capitalone.dashboard.Application**

![http://localhost:6666/sml/wp-content/uploads/2017/09/Hygieia-Jenkins-Collector-Configuration-5.png](media/0c0cd263b2e1558a9d64a70cb2605477.png)

On Successful running it will show the above Output on the console

 

We can do authentication any of these to methods
```dockerfile
# If using username/token for api authentication
#   (required for Cloudbees Jenkins Ops Center) see sample
#jenkins.servers[1]=http://username:token@jenkins.company.com
jenkins.servers[1]=http://root:07ef71a4e84fa29e28e2812f8f5b2582@localhost:9051

# Another option: If using same username/password Jenkins auth,
#   set username/apiKey to use HTTP Basic Auth (blank=no auth)
jenkins.usernames[0]=root
jenkins.apiKeys[0]=07ef71a4e84fa29e28e2812f8f5b2582
```




 

**5. Configure Build Widget**  
Open UI, create a sample Dashboard. To configure Dashboard, [Please follow this
Tutorial & Go to UI
Section.](http://localhost:6666/sml/tutorials/hygieia-dashboard-tutorial/)

-   Click on Configure Build Widget

-   Choose the Build Job from the List

-   Save the Widget

![http://localhost:6666/sml/wp-content/uploads/2017/09/Hygieia-Jenkins-Collector-Configuration-6.png](media/f23ac03bf225126dae1b6c644bb7b3dc.png)

After some time it will show the results on the Dashboard as below

![http://localhost:6666/sml/wp-content/uploads/2017/09/Hygieia-Jenkins-Collector-Configuration-7-1024x497.png](media/422aa0fbeaaf888312ab81804911bd36.png)

 

**Errors & Solutions**
```dockerfile
# If using username/token for api authentication
#   (required for Cloudbees Jenkins Ops Center) see sample
#jenkins.servers[1]=http://username:token@jenkins.company.com
jenkins.servers[1]=http://root:07ef71a4e84fa29e28e2812f8f5b2582@localhost:9051

# Another option: If using same username/password Jenkins auth,
#   set username/apiKey to use HTTP Basic Auth (blank=no auth)
jenkins.usernames[0]=root
jenkins.apiKeys[0]=07ef71a4e84fa29e28e2812f8f5b2582
```

If got above error, make sure your Jenkins username/password or credentials are
correct or not.
