---
title: DevOps - Vagrant Permission denied 
date: 2017-12-01 00:00:00 Z
categories:
- DevOps
tags:
- DevOps
layout: article
cover: /assets/logo/devops.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---



DevOps - Vagrant Permission denied
======================
```bash
vagrant ssh
vagrant@127.0.0.1: Permission denied (publickey,gssapi-keyex,gssapi-with-mic).
```
<br/>

### To resove issue   
simply run following command in your cmd:

```bash
set VAGRANT_PREFER_SYSTEM_BIN=0
vagrant ssh
```
<br/>

### VAGRANT_PREFER_SYSTEM_BIN
If this is set, Vagrant will prefer using utility executables (like ssh and rsync) from the local system instead of those vendored within the Vagrant installation.

Vagrant will default to using a system provided ssh on Windows. This environment variable can also be used to disable that behavior to force Vagrant to use the embedded ssh executable by setting it to 0.