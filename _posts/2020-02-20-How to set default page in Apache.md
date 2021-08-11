---
title: How To - How to set / change default page in Apache
date: 2017-11-26 00:00:00 Z
categories:
- HowTo
tags:
- HowTo
layout: article
cover: /assets/logo/howto.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---


## How to set / change default page in Apache

**Apache file path/conf/httpd.conf**

Make the following changes in httpd.conf file. Change index.html or index.php to
whatever default page you want.

```html
#
# DirectoryIndex: sets the file that Apache will serve if a directory
# is requested.
#
<IfModule dir_module>
DirectoryIndex index.html index.php
</IfModule>
```

Restart Apache \~ Done.

On Ubuntu the main Apache configuration file is actually apache2.conf . At
directory:` /etc/apache2/`
