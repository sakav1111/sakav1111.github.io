---
title: XAMPP -How to Change MySQL port number in XAMPP 
date: 2017-11-11 00:00:00 Z
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

# XAMPP -How to Change MySQL port number in XAMPP



1.Open xampp root folder locate my.ini file in **C:\\xampp\\mysql\\bin folder**



2.We can found default password 3306 in following lines
```dos
[client] 
# password       = your_password 
port            = 3306 
socket          = "/xampp/mysql/mysql.sock"

# The MySQL server
[mysqld]
port= 3306
socket = "/xampp/mysql/mysql.sock"
basedir = "/xampp/mysql
```

 

3.Change port=3306 to some other port like port= 3336
```dos
[client] 
# password       = your_password 
port            = 3336 
socket          = "/xampp/mysql/mysql.sock"

# The MySQL server
[mysqld]
port= 3336
socket = "/xampp/mysql/mysql.sock"
basedir = "/xampp/mysql
```


4.Restart XAMPP MySQL Server.

