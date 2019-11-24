---
title: XAMPP - The uploaded file exceeds the upload_max_filesize
date: 2019-011-11 00:00:00 Z
categories:
- XAMPP
tags:
- XAMPP
layout: article
cover: /assets/logo/java.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---
# The uploaded file exceeds the upload_max_filesize directive in php.ini



**What**

The uploaded file exceeds the **upload_max_filesize** directive in php.ini.

![image4](media/b454ae71307d8597f6752174fbf52c9b.png)



**Why?**

If you are uploading WordPress theme in locally hosted WordPress site you may
face this problem



**How?**

-   Go to XAMPP PHP location for
    ```xml
    C:\\xampp\\php
    ```

-   Open php.ini file in any text editor

-   Find “upload_max_filesize” in that file

-   Change upload file size to 100MB or based on your requirement.
    ```xml
    upload_max_filesize=100M
    ```


-   Save the File. Restart Apache server


