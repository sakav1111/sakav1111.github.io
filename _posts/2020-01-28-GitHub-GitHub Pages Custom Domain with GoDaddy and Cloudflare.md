---
title: GitHub- GitHab Pages Custom Domain with GoDaddy and Cloudflare
date: 2019-01-28 0:03:00 Z
categories:
- GitHub
tags:
- GitHub
layout: article
cover: /assets/logo/devops.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

GitHub - Custom Domain Configuration
====================================

GitHub Pages Configuration
--------------------------

**Create Your Website**  
Once you’ve [signed in](https://github.com/login), you’ll create a new
repository to get started.

**Provide Repository Name**,  
it must be a proper GitHub domain:
```bash
Syntax
<username>.github.io

Example
satyacodes.github.io

#must be Public Repository
```


**To Change Username**  
-   In the upper-right corner > **click your profile photo** > then click
    **Settings**.

-   In the left sidebar, click **Account settings**.

-   In the "**Change username**" section, click Change username.

![](media/dabfe20a3c018da113ecec06f7e995dd.png)



**Configure Theme**  
Repository > Settings > GitHub Pages Section > Choose a theme

![](media/a1ab0895b915d4b82959901ef48365fb.png)

Commit Changes.

GitHub Custom Domain Configuration
----------------------------------

**Add custom domain**  
`Go to Repository > Setting > GitHub pages Section > Custom Domain : Enter
Domain (Tick : Enforce HTTPS)`

![](media/843c7630a1a078ed172d186a1447af6c.png)

GitHub Cloudflare Configuration
-------------------------------

GitHub Cloudflare GoDaddy

### GitHub to Cloudflare

Login to Cloudflare > Add Site : satya.com > Select Plan : It will scan the
DNS records.

It will Navigate to DNS Entries Page

![](media/82657f282bfc1b7ebc700330054a9f14.png)

**DNS – A Records Entry**

In this step, we inform Cloudflare to point "our domain to the Github Pages
server" using two A Record DNS entry:

**A** name Records : GitHub DNS Servers
```bash
192.30.252.153

192.30.252.154
```


![](media/5051ab21dbf993ff506d772011e8b175.png)

**CNAME record DNS entry:**  
which will point your subdomain(www) to your apex domain(\@).

![](media/32ff23f2ab9e841c152dc5f7e3db3d83.png)

Finally, DNS records for Your domain looks like

![](media/1d4a8e83677a3cd2ba176efebee91775.png)

<br>

### Cloudflare to GoDaddy

**Get Cloudflare Name Servers**

To use Cloudflare, ensure your authoritative DNS servers, or nameservers have
been changed. These are your assigned Cloudflare nameservers.

```bash
#Type Value
NS arya.ns.cloudflare.com
NS elmo.ns.cloudflare.com
```


**Update in GoDaddy Name Servers**   
`Login to GoDaddy > Product > Domain Tab : DNS`

![](media/fb050061aaa74fb27d5fa9e4136a1666.png)

Update Cloudflare nameservers

![](media/16939154538fc339c01cb986285e6332.png)

That’s it. Access Website Now

Ref.
----

<https://guides.github.com/features/pages/>

<https://www.freecodecamp.org/news/an-illustrated-guide-for-setting-up-your-website-using-github-cloudflare-5a7a11ca9465/>

