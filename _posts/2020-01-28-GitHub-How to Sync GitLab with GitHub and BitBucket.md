---
title: GitHub- How to Sync GitLab with GitHub and BitBucket
date: 2019-01-28 0:01:00 Z
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

Sync GitLab to GitHub
=====================

To set up a mirror from GitLab to GitHub, you need to follow these steps:

1.Create a [GitHub personal access
token](https://help.github.com/en/articles/creating-a-personal-access-token-for-the-command-line) with
the `public_repo box` checked.

**Creating GitHub token**  
Profile > Settings > Developer settings > Personal access tokens > Generate
New > Select the scopes, or permissions(public_repo  cheked) >Generate

copy the token to your clipboard : HXCGGJsdfCGGsAASSssassSDDSDZsss

2.Login to Gitlab > Select Repository > Settings > Repository > Select :
Mirroring repositories > Provide Below details

3.Fill in the Git repository URL field using this format:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
https://<your_github_username>@github.com/<your_github_group or username>/<your_github_project>.git

#Example
https://smlcodes@github.com/smlcodes/springmvc.git
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Cross Check By hitting above URL in browser

4.Fill in Password field with your GitHub personal access token.

![](media/2c438d80d8b73c69ebc4395385e2916b.png)

5.Click the Mirror repository button.

The mirrored repository will be listed. For
example, https://*****:*****\@github.com\<your_github_group>\<your_github_project>.git.

The repository will push soon. To force a push, click the appropriate button

![](media/0e48e0589e1b039aaa1de383296e05c8.png)

Sync GitLab to BitBucket
========================

1.Open BitBucket > Profile > Settings > App Passwords > Create App Password
> Select Permissions > Create

2.Copy Password

3.Login to Gitlab > Select Repository > Settings > Repository > Select :
Mirroring repositories > Provide Below details

-Git repository URL :
<https://satyacodes@bitbucket.org/satyacodes/satyacodes.bitbucket.io.git>

-Mirror direction : pull/push

-Authentication method : above copied password from bitbucket

![](media/c60f54743be5b45017df5733353bdd2a.png)

4.Click on Create Mirror Repository.

![](media/1452087ebe7362e26eac1919a3ff6765.png)

5.It will add to the List. Please click update button to Test.

Ref.
----

<https://docs.gitlab.com/ee/user/project/repository/repository_mirroring.html>

<https://docs.gitlab.com/ee/user/project/repository/repository_mirroring.html>
