---
title: Vagrant - access guest from host via hostname
date: 2019-02-16 0:03:00 Z
categories:
- HowTo
tags:
- HowTo
layout: article
cover: /assets/logo/vagrant.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

# Vagrant - access guest from host via hostname

Vagrant private networks allow you to access your guest machine by some address
that is not publicly accessible from the global internet.

Multiple machines within the same private network can communicate with each
other on private networks.

Private Network
---------------

 [private
networks](https://www.vagrantup.com/docs/networking/private_network.html) should
never allow the general public access to your machine, public networks can.

DHCP
----

The easiest way to use a private network is to allow the IP to be assigned via
DHCP.

```yaml
Vagrant.configure("2") do |config|
    config.vm.network "private_network", type: "dhcp"
end
```


This will automatically assign an IP address from the reserved address space.
The IP address can be determined by using vagrant ssh to SSH into the machine
and using the appropriate command line tool to find the IP, such as ifconfig.

Static IP
---------

You can also specify a static IP address for the machine. This lets you access
the Vagrant managed machine using a static, known IP. The Vagrantfile for a
static IP looks like this:

```yaml
Vagrant.configure("2") do |config|
    config.vm.network "private_network", ip: "192.168.50.4"
end
```


It is up to the users to make sure that the static IP does not collide with any
other machines on the same network.

IPv6
----

You can specify a static IP via IPv6. DHCP for IPv6 is not supported. To use
IPv6, just specify an IPv6 address as the IP:

```yaml
Vagrant.configure("2") do |config|
    config.vm.network "private_network", ip: "fde4:8dba:82e1::c4"
end
```


This will assign that IP to the machine. The entire /64 subnet will be reserved.
Please make sure to use the reserved local addresses approved for IPv6.

Disable Auto-Configuration
--------------------------

If you want to manually configure the network interface yourself, you can
disable Vagrant's auto-configure feature by specifying auto_config:
```yaml
Vagrant.configure("2") do |config|
    config.vm.network "private_network", ip: "192.168.50.4",
    auto_config: false
end
```



Forwarded Ports
===============

f the guest machine is running a web server listening on port 80, you can make a
forwarded port mapping to port 8080 (or anything) on your host machine. You can
then open your browser to localhost:8080 and browse the website, while all
actual network data is being sent to the guest

The forwarded port configuration expects two parameters, the port on the guest
and the port on the host. Example:
```yaml
Vagrant.configure("2") do |config|
	config.vm.network "forwarded_port", guest: 80, host: 8080
end
```



I created a private network with a static IP address. Then in the Windows host,
I configured hostname with that IP address. This way, I am able to access the
webserver from local/host OS.

Now, in VagrantFile, as my host computer's port 88 is forwarded to guest's
80(config.vm.network :forwarded_port, guest: 80, host: 88), I can access
webserver from local LAN computers with: [http://host-computer-name:88/](http://host-computer-name:88/).

Port Collisions and Correction
------------------------------

It is common when running multiple Vagrant machines to unknowingly create
forwarded port definitions that collide with each other (two separate Vagrant
projects forwarded to port 8080, for example). Vagrant includes built-in
mechanism to detect this and correct it, automatically.

Port collision detection is always done. Vagrant will not allow you to define a
forwarded port where the port on the host appears to be accepting traffic or
connections.

Port collision auto-correction must be manually enabled for each forwarded port,
since it is often surprising when it occurs and can lead the Vagrant user to
think that the port was not properly forwarded. Enabling auto correct is easy:

```yaml
Vagrant.configure("2") do |config|
    config.vm.network "forwarded_port", guest: 80, host: 8080,
    auto_correct: true
end
```

