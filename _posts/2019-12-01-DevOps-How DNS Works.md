---
title: DevOps - How DNS Works 
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



DevOps - How DNS Works
======================

![DNS query diagram](media/585dc6c77eb36d9ff7264a2e827d1428.png)

1.  A user types **‘example.com’** into a web browser and the query travels into
    the Internet and is received by a DNS recursive resolver.

2.  The resolver then queries a DNS root nameserver (.).

3.  The root server then responds to the resolver with the address of a
    **Top-Level Domain (TLD) DNS server (such as .com or .net),** which stores
    the information for its domains. When searching for example.com, our request
    is pointed toward the .com TLD.

4.  The resolver then makes a request to the .com TLD.

5.  The TLD server then responds with the IP address of the domain’s nameserver,
    example.com.

6.  Lastly, the recursive resolver sends a query to the domain’s nameserver.

7.  The IP address for example.com is then returned to the resolver from the
    nameserver.

8.  The DNS resolver then responds to the web browser with the IP address of the
    domain requested initially.

Once the 8 steps of the DNS lookup have returned the IP address for example.com,
the browser is able to make the request for the web page:

1.  The browser makes
    a [HTTP](https://www.cloudflare.com/learning/ddos/glossary/hypertext-transfer-protocol-http/) request
    to the IP address.

2.  The server at that IP returns the webpage to be rendered in the browser
    (step 10).

What is a DNS resolver ?
------------------------

![https://www.freecodecamp.org/news/content/images/2019/06/dns_resolve.png](media/43f0dcc7d935edd3278f7cee7e1e0fef.png)

![https://d1.awsstatic.com/Route53/how-route-53-routes-traffic.8d313c7da075c3c7303aaef32e89b5d0b7885e7c.png](media/e2f6fe34c52e613cd80585054ae48a2f.png)

Read More…

[How to make a machine accessible from the LAN using its
hostname](https://unix.stackexchange.com/questions/16890/how-to-make-a-machine-accessible-from-the-lan-using-its-hostname)

<https://unix.stackexchange.com/questions/16890/how-to-make-a-machine-accessible-from-the-lan-using-its-hostname>
