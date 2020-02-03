---
title: MySQL - change xampp mysql root password
date: 2019-01-29 0:03:00 Z
categories:
- MySQL
tags:
- MySQL
layout: article
cover: /assets/logo/devops.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

## Way 1 - Using MySQL Console

Open XAMPP Control > Open Shell, Run below comand 
Default Fisrt, password is empty - So it will ask for New Password

Next, type below one , it will ask for new one
```
mysqladmin -u root password
```


```
mysqladmin -u root password
New password: ****
Confirm new password: ****
```


After password SET, login 
```
# mysql -u root -p
Enter password: ****
```


## Way 2 - Using init_file system variable

Stop MySQL service
Start > Control Panel > Administrative Tools > Services. Find the MySQL service in the list and stop it.


Create a text file containing below statement, Save the file `(C:\xampp\mysql\pwd.txt)'
```
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';  //here 'root' is new password
```


Go TO MySQL bin location via cmdline, and Start MySQL server with the init_file system variable set to name the file (notice that the backslash in the option value is doubled):
```
C:\> cd "C:\xampp\mysql\bin"
C:\> mysqld --init-file=C:\\xampp\\mysql\\pwd.txt
```

Restart MySQWL
After the server has started successfully, delete C:\mysql-init.txt.


## Way 3 - reset XAMPP MySQL root password through SQL update:
1.	Start the Apache Server and MySQL instances from the XAMPP control panel.

2.	After the server started, open any web browser and give http://localhost/phpmyadmin/ 
 
3.	In the phpMyAdmin window, select SQL tab from the right panel. This will open the SQL tab where we can run the SQL queries.

4.	Now type the following query in the textarea and click Go
```
UPDATE mysql.user SET Password=PASSWORD('password') WHERE User='root'; FLUSH PRIVILEGES;
```
5.	Now you will see a message saying that the query has been executed successfully.

6.	If you refresh the page, you will be getting a error message. This is because the phpMyAdmin configuration file is not aware of our newly set root passoword. To do this we have to modify the phpMyAdmin config file.

7.	Open the file [XAMPP Installation Path] `/phpmyadmin /config.inc.php` in your favorite text editor.

8.	Search for the string `$cfg\['Servers'\]\[$i\]['password'] = '';`

9. Change it to like this, `$cfg\['Servers'\]\[$i\]['password'] = 'password';` Here the ‘password’ is what we set to the root user using the SQL query.

9.	Now all set to go. Save the config.inc.php file and restart the XAMPP server.
