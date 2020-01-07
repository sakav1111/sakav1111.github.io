---
title: AWS- Launch a Linux Virtual Machine with Amazon EC2
permalink: /aws/aws-launch-a-linux-virtual-machine-with-amazon-ec2
key: aws-launch-a-linux-virtual-machine-with-amazon-ec2
categories:
- aws
tags:
- aws
---


### Launch a Linux Virtual Machine with Amazon EC2

AWS Services > Amazon EC2 to open the service console.

Select Launch Instance to create and configure your virtual machine

![](media/00c869a481f20d1c94aab59f2ff83d34.png)

choose an **Amazon Machine Image (AMI)** > **Amazon Linux AMI** and click
Select

![](media/ba4e18cd25f01a4f3e55a9afca234709.png)

Choose an Instance type : various combinations of CPU, memory, storage, and
networking capacity.default option of **t2.micro**

![](media/528740166bbb2d6af2dc2f1a1cd002dc.png)

Configure Instance

![](media/f8ae54016c109bc87fa7095f0d3ee07b.png)

Add Storage

![](media/e03c5d17a5117bac5df1b9708b0e2eaa.png)

Add Tags

![](media/b637d3a4876da435b101d8b7a1e8b4ea.png)

Configure Security Groups & SSH connection details - Click Review and Launch

![](media/7b098de81717e111d9ad693809f3e66f.png)

On the next screen you will be asked to choose an existing key pair or create a
new key pair. A key pair is used to securely access your Linux instance using
SSH

\- Select Create a new key pair and give it the name LinuxVM. Next click the
Download Key Pair button.

![](media/092ccc8ac3dd957844eb475f2d47020a.png)

saving your key pair in your user directory in a sub-directory called .ssh (ex.
C:\\user\\{yourusername}\\.ssh\\Linux-VM.pem).

Click View Instances- Copy the Public IP address of your AWS instance, so you
can use it when we connect to the instance using SSH

![](media/62c9dc2b70b5dace994a1a1629b6fed2.png)

#### Login to VM

**Login Using Git**

-   Download & Install Git

-   Open Git Bash > Run

>   ssh -i {full path of your .pem file} ec2-user@{instance IP address}

>   ssh -i 'C:\\Users\\Kavetis\\.ssh\\Linux-VM.pem' ec2-user@3.135.224.183

![](media/c41e6d93914a2f49926a45a2aaf8156b.png)

**Login Using Putty**

1.Open Downloaded .pem file

-   If we are using Linux system, we can directly use .pem file for connection.

-   If we are using Windows System, we don’t have SSH directly.

we use PuttY for SSH connections. but Putty doesn't support .pem file it only
supports .ppk file. for that we need to convert .pem file to .ppk file

2.Covert .pem to .ppk using Putty

-   Launch PuttyGen

-   Load .pem file

-   Save Private Key: Linux-VM.ppk

3.Open Putty

-   Enter Ip : 18.216.255.66

-   Connection Type : SSH

-   Left Menu > SSH >+AUTH : Browse saved Linux-VM.ppk file

Remember : Default user for EC2 Instance is : "ec2-user"
