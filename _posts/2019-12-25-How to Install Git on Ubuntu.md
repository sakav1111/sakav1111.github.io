---
title: Ubuntu - Ubuntu
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

# Install Git on Ubuntu

#### Installing Git with Apt

```bash
#updating the package index:
sudo apt update


# install Git:
sudo apt install git


# Verify the installation
git --version
```

# Install Git on CentOS

```bash
# install Git:
sudo yum install git

# Verify the installation
git --version
```


# Configuring Git

The following commands will set your git commit username and email address:
```bash
git config --global user.name "Satya Codes"
git config --global user.email "ubuntu_slave@satyacodes.com"
```


#The configuration settings are stored in the ~/.gitconfig file:
