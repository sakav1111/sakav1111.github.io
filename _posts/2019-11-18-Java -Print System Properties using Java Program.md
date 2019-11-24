---
title: Java -Print System Properties  
date: 2019-11-11 00:00:00 Z
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

# Java -Print System Properties using Java Program

*Print System Properties using Java Program -Java Example*

Program : Print System Properties using Java Program
```java
import java.io.IOException;
public class SysPropDemo {
    public static void main(String[] argv) throws IOException {
        if (argv.length == 0)
            // BEGIN sysprops
            System.getProperties().list(System.out);
            // END sysprops
        else {
            for (String s : argv) {
                System.out.println(s + " = " + 
                    System.getProperty(s));
            }
        }
    }
}
```

```dos
C:\Java>javac SysPropDemo.java
C:\Java>java SysPropDemo
-- listing properties --
java.runtime.name=Java(TM) SE Runtime Environment
sun.boot.library.path=C:\Program Files\Java\jdk1.8.0_101\jr...
java.vm.version=25.101-b13
java.vm.vendor=Oracle Corporation
java.vendor.url=http://java.oracle.com/
path.separator=;
java.vm.name=Java HotSpot(TM) 64-Bit Server VM
file.encoding.pkg=sun.io
user.script=
user.country=US
sun.java.launcher=SUN_STANDARD
sun.os.patch.level=
java.vm.specification.name=Java Virtual Machine Specification
user.dir=D:\SmlCodes\workspace\JavaExamples
java.runtime.version=1.8.0_101-b13
java.awt.graphicsenv=sun.awt.Win32GraphicsEnvironment
java.endorsed.dirs=C:\Program Files\Java\jdk1.8.0_101\jr...
```
