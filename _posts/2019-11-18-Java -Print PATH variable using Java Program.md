---
title: Java - Print PATH variable using Java Program
date: 2017-011-11 00:00:00 Z
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

# Print PATH variable using Java Program

*Print PATH variable using Java Program -Java Example*


1.  Environment Setup : [How to install Java & set PATH
    variables](http://localhost:6666/sml/java/install-java-set-path-variable-windows-7810/)

2.  Refer Tutorial for more Details: [Introduction to Java -Core Java Complete
    Tutorial](http://localhost:6666/sml/java/introduction-java-core-java-complete-tutorial/)
```java
For Compiling >javac filename.java
For Running >java classname
```

<br>


Program : Print PATH variable using Java Program
```java
public class GetEnv {
    public static void main(String[] argv) {
        System.out.println("System.getenv(\"PATH\") = " + System.getenv("PATH"));
    }
}
C:\Java>javac GetEnv.java
C:\Java>java GetEnv
System.getenv("PATH") = C:/Program Files/Java/jre1.8.0_77/bin/server;
C:/Program Files/Java/jre1.8.0_77/bin;C:/Program Files/Java/jre1.8.0_77/lib/amd64;
C:\ProgramData\Oracle\Java\javapath;C:\windows\system32;C:\windows;
C:\windows\System32\Wbem;C:\windows\System32\WindowsPowerShell\v1.0\;
C:\Program Files\Git\cmd;C:\Program Files\nodejs\;D:\MongoDB\bin;
D:\apache-maven-3.3.9\bin;C:\Program Files\Java\jdk1.8.0_101\bin;;D:\Softwares\eclipse;
```
