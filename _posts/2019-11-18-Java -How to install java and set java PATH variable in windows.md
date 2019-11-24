---
title: Java - set java PATH variable in windows 
date: 2017-11-11 00:00:00 Z
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

# Java -How to install java and set java PATH variable in windows 7/8/10

**Downloading the Java Software Development Kit**

[](http://localhost:6666/sml/wp-content/uploads/2016/12/Install_java_inwindows_smlcodes-1.png)1.Go
to Oracle official website choose the version by following link
<http://www.oracle.com/technetwork/java/javase/downloads/index.html>

![http://localhost:6666/sml/wp-content/uploads/2016/12/Install_java_inwindows_smlcodes-1.png](media/21f2aef6bc8d72bbb685231daba929bc.png)

 

2.Double-click the installer file you downloaded

3.it will ask for PATH to be install. Give the location it will completes the
Installation

 

## **How to set java_home on Windows 7/8/10?**

1.Go to advanced system settings

![http://localhost:6666/sml/wp-content/uploads/2016/12/Install_java_inwindows_smlcodes-2.png](media/404faa15a082af5bf2100dc6e5a754f4.png)

[](http://localhost:6666/sml/wp-content/uploads/2016/12/Install_java_inwindows_smlcodes-2.png)

 

2.Go to advanced tab & then environment variables
![http://localhost:6666/sml/wp-content/uploads/2016/12/Install_java_inwindows_smlcodes-3.png](media/c78d231ae84725a0a0b1bec7b5506525.png)

 

3.In System variables, add a new **JAVA_HOME** variable and point it to the JDK
installed folder.

![http://localhost:6666/sml/wp-content/uploads/2016/12/Install_java_inwindows_smlcodes-4.png](media/49387fc0247aec03e6c0b40da3e68730.png)

 
Don’t include the \\bin folder, just the JDK path. For example
```dos
Correct – C:\Program Files\Java\jdk1.8.0_60
Wrong – C:\Program Files\Java\jdk1.8.0_60\bin
```


 

4.In System variables, find **`PATH`**, clicks edit and append this
**`%JAVA_HOME%\bin`** to the end.

![http://localhost:6666/sml/wp-content/uploads/2016/12/Install_java_inwindows_smlcodes-5.png](media/ecc3dfe7d04f09c07f06f9aed13005b9.png)

`JAVA_HOME\bin` in PATH make the Java’s commands are accessible from everywhere.

 

5.Open Command prompt test PATH is set correctly or not by giving `java –version`
command

![http://localhost:6666/sml/wp-content/uploads/2016/12/Install_java_inwindows_smlcodes-6.png](media/0e0f8fa200ef56594e2b1b9e67b5dd3a.png)
