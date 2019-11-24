---
title: XAMPP - How to find and kill a process running on a port
date: 2019-11-11 00:00:00 Z
categories:
- XAMPP
tags:
- XAMPP
layout: article
cover: /assets/logo/xampp.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

XAMPP -How to find and kill a process running on a port
-----------------------

Use command 
netstat -ano

It will list all the Services which are running on ports



Per perticular Port 
netstat -ano|findstr "PID :8081"

```

C:\Users\Kavetis>netstat -ano

Active Connections

  Proto  Local Address          Foreign Address        State           PID
  TCP    0.0.0.0:80             0.0.0.0:0              LISTENING       4
  TCP    0.0.0.0:135            0.0.0.0:0              LISTENING       1128
  TCP    0.0.0.0:445            0.0.0.0:0              LISTENING       4
  TCP    0.0.0.0:1947           0.0.0.0:0              LISTENING       4576
  TCP    0.0.0.0:2179           0.0.0.0:0              LISTENING       5696
  TCP    0.0.0.0:2701           0.0.0.0:0              LISTENING       2340
  TCP    0.0.0.0:3306           0.0.0.0:0              LISTENING       7384
  TCP    0.0.0.0:6001           0.0.0.0:0              LISTENING       5276
  TCP    0.0.0.0:49664          0.0.0.0:0              LISTENING       764
  TCP    0.0.0.0:49665          0.0.0.0:0              LISTENING       1692
  TCP    0.0.0.0:49666          0.0.0.0:0              LISTENING       1784
  TCP    0.0.0.0:49667          0.0.0.0:0              LISTENING       3844
  TCP    0.0.0.0:49669          0.0.0.0:0              LISTENING       932
  
```


To kill this process (the /f is force):
taskkill /pid 1128 /f  