---
title: AWS- Launch a Windows Virtual Machine
permalink: /aws/aws-launch-a-windows-virtual-machine
key: aws-launch-a-windows-virtual-machine
categories:
- aws
tags:
- aws
---

### Launch a Windows Virtual Machine

Amazon EC2 \> Click Launch Instance

![](media/00c869a481f20d1c94aab59f2ff83d34.png)

Amazon Machine Image (AMI) \> Microsoft Windows Server 2012 R2

![](media/3599bd8f9126a828e40d7d11e5940215.png)

instance type - select the default option of **t2.micro**

![](media/c8759158d7b3663e20a6b39f54537278.png)

Then click Review and Launch

![](media/6aaaf92918a0355a9f22726304de655d.png)

Create a Key Pair and Launch Your Instance – We already created Key Pair, we use
the same

![](media/8e97a729a17db4bf2e2e50f9567749f6.png)

Select the Windows Server instance you just created and click Connect.

![](media/f5f1f4e541dc31810ebd1eba75de6423.png)

In order to connect to your Windows Instance via RDP, you will need a user name
and password:

The User name defaults to Administrator & To receive your password, click Get
Password

![](media/6716874fcdd4bdb9cd5acedbb1162e1f.png)

to retrieve the password, you will need to locate the Key Pair you created in
before & Click Decrypt Password. Copy/save that Password

![](media/3034de90ea5af591d88606d2030fb141.png)

![](media/605bc039eb076f72522ec61e2fb123ad.png)

Click Download Remote Desktop File and open the file to connect via RDP

![](media/63889d03b61082fc2b54e6eaff665fc5.png)

![](media/1fa0bf8d2305d7739f382902c972231e.png)
