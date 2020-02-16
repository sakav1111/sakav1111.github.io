---
title: Vagrant - default- Warning Authentication failure-Retrying
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
# Vagrant - default- Warning Authentication failure-Retrying


I tried to destroy and re-create my box with the same result : it eventually
timedout, but the provisions were not executed.

After searching the internet and a lot of experimentation, I managed to make it
work by commenting out the following line in my Vagrantfile

```yaml
# config.ssh.private_key_path = "~/.ssh/id_rsa"
```

