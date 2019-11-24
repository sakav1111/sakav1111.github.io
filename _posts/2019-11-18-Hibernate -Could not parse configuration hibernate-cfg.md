---
title: Hibernate -Could not parse configuration hibernate-cfg-xml 
date: 2019-11-11 00:00:00 Z
categories:
- Hibernate
tags:
- Hibernate
layout: article
cover: /assets/logo/hibernate.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

Hibernate - Could not parse configuration: hibernate.cfg.xml

**org.hibernate.HibernateException**: Could not parse configuration:
hibernate.cfg.xml

It was failing because there was no internet connection / you are behind proxy.
To solve this issues

-   Extract hibernate3.jar file find hibernate-mapping-3.0.dtd,
    hibernate-configuration-3.0.dtd files

-   Paste the above two files root folder of your project

 

In **hibernate.cfg.xml** change lines to
```xml
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration SYSTEM "hibernate-configuration-3.0.dtd">
```


 

In **\<Class\>.hbm.xml** change lines to
```xml
<?xml version="1.0"?>

<!DOCTYPE hibernate-mapping SYSTEM "hibernate-mapping-3.0.dtd">
```

