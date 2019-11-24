---
title: Git - How to remove github credentials from git
date: 2017-11-11 00:00:00 Z
categories:
- Git
tags:
- Git
layout: article
cover: /assets/logo/devops.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

# Github -How to remove github credentials from git

Now, We will see **How to remove github credentials from git**



If we are committing our changes & push into github, we may face this type of
issue like,
```dos
$ git push
remote: Permission to SomeRepo.git denied to Username.
fatal: unable to access 'https://github.com/smlcodes/SomeRepo.git/': 
The requested URL returned error: 403
```

This is because of some other credentials already stored. git is trying access
the repository with those credencials.

<br>

so we are getting above error. To resolve this follow below steps.
-   Go to **Control Panel -\> Credential Manager -\> Generic
    Credentials.** or Go to Credential Manager


-   Go to Windows Credentials

-   Remove the Github entries under Generic Credentials  
    ![http://localhost:6666/sml/wp-content/uploads/2017/09/git-error-2.png](media/9bdc47609106222fbe8cfd6bdd33a729.png)


-   Try connecting again. This time, it should prompt you for the correct
    username and password.

    ![http://localhost:6666/sml/wp-content/uploads/2017/09/git-error-3.png](media/c0bf7b9ef95f6b99acd788aaf1edf439.png)

