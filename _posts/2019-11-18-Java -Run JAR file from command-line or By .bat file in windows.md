---
title: Java - Run JAR file from command-line 
date: 2019-011-11 00:00:00 Z
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

# Run JAR file from command-line or By .bat file in windows

**Run JAR file from command-line:** If we are working on Java programming some
where  we need to Run our implemented JAR Files from command line.  Sometimes
Double clicking on JAR file is not work in windows .

Because, windows has no Idea about what file your are trying to open or Java is
not installed properly , there are so many reasons why Windows not opening JAR
file.

In this type of situation we can JAR file in windows by below methods

 

**1.Using CMD prompt**

Open CMD Prompt , navigate to the .jar file location run **java -jar
\<filename.jar\>**

**D:\\Codes\>java -jar "jd-gui-1.4.0.jar"**

 

**2.Using .bat file**

Typing command every time for launching .jar file may irritate us. for that just
copy above command in notepad & save it in .bat extension

Notepad -\>  Enter **java -jar "jd-gui-1.4.0.jar"** save as "run.bat"
