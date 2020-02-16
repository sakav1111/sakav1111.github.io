---
title: How to Change Ubuntu Hostname
date: 2019-02-16 0:03:00 Z
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

# Change Ubuntu Hostname (computer name)

Type the following command to edit /etc/hostname using nano or vi text editor:
```
sudo nano /etc/hostname
```
Delete the old name and setup new name.

Next Edit the /etc/hosts file:
```
sudo nano /etc/hosts
```

Replace any occurrence of the existing computer name with your new one.
Reboot the system to changes take effect:
```
sudo reboot
```

Display the current Ubuntu hostname
```
$ hostname
```


## change the Ubuntu server hostname without a system restart
Type the following commands:
```
$ sudo hostname new-server-name-here
```

Next edit the /etc/hostname file and update hostname:
```
$ sudo nano /etc/hostname
```


Finally, edit the /etc/hosts file and update the lines that reads your old-host-name:
```
$ sudo nano /etc/hosts
```