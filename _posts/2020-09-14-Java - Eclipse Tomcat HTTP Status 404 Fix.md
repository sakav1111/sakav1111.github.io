---
date: "2020-09-14 00:00:00 Z"
title: Java - Eclipse Tomcat HTTP Status 404 Fix
categories:
- Java
tags:
- Java
layout: article
cover: /assets/logo/java.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

Java - Eclipse Tomcat HTTP Status 404 Fix
-----------------------------------------

```

INFO: Initializing Coyote HTTP/1.1 on http-8080
mai 02, 2013 4:05:13 PM org.apache.catalina.startup.Catalina load
INFO: Initialization processed in 604 ms
mai 02, 2013 4:05:14 PM org.apache.catalina.core.StandardService start
INFO: Starting service Catalina
mai 02, 2013 4:05:14 PM org.apache.catalina.core.StandardEngine start
INFO: Starting Servlet Engine: Apache Tomcat/6.0.36
mai 02, 2013 4:05:14 PM org.apache.coyote.http11.Http11Protocol start
INFO: Starting Coyote HTTP/1.1 on http-8080
mai 02, 2013 4:05:14 PM org.apache.jk.common.ChannelSocket init
INFO: JK: ajp13 listening on /0.0.0.0:8009
mai 02, 2013 4:05:14 PM org.apache.jk.server.JkMain start
INFO: Jk running ID=0 time=0/22  config=null
mai 02, 2013 4:05:14 PM org.apache.catalina.startup.Catalina start
INFO: Server startup in 493 ms
```


### To fix this

Make sure you have created atleast one .jsp page

Then in Eclipse

1.  Click on **Window \> Show view \> Server or right click on the server in
    "Servers" view, select "Properties".**

2.  In the "General" panel, click on the "Switch Location" button.

3.  The "Location: [workspace metadata]" should replace by something else.

4.  Open the Overview screen for the server by double clicking it.

5.  In the Server locations tab , select "Use Tomcat location".

6.  Save the configurations and restart the Server.
