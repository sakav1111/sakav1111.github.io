---
title: XAMPP - How to change the root password for MySQL in XAMPP
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

# XAMPP -How to change the root password for MySQL in XAMPP?


In Older versions of XAMPP we can change root password by using
<http://localhost/security/ >**(**<http://localhost:8888/security/>**)
directly.**

For newer versions the above given url is not working.

For newer versions open XAMPP Shell & run below command to change root password

![http://localhost:6666/sml/wp-content/uploads/2016/12/How-to-change-the-root-password-for-MySQL-in-XAMPP.png](media/29603c79f68db2f22d644038a12c0bbd.png)

```dos
mysqladmin.exe -u root password mypassword
```
This sets the root password to **‘mypassword’**

 

To connect with **MySQL Command-line** from **windows Command-line**

Go to **xampp\\mysql\\bin** & run below command, it will asks for account
password enter it
```dos
mysql -u root --password
```


 
