---
date: "2021-08-11 03:00:00 Z"
title: Spring Boot - Deploying Spring Boot Applications to Heroku
categories:
- SpringBoot
tags:
- SpringBoot
layout: article
cover: /assets/logo/springboot.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---





# SpringBoot - Deploying Spring Boot Applications to Heroku

I have Simple Employee Application written in SpringBoot.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# application.properties

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


Heroku does not have really MySQL.here we are using PostgreSQL. Update maven
dependency.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/ab43af339ffca24cb0b484ad77e807f7.png)

<br>


You can find here :
[**Github**](https://github.com/smlcodes/Books-Sync-Gitlab/tree/main/Codes/SpringBoot-Thymeleaf)

<br>


<br>


## Create Heroku Account

Create Account: <https://www.heroku.com/>

![](media/ef42ff213ba700680354933717d61f15.png)

<br>


Signup Here : <https://signup.heroku.com/>

Login to App : <https://dashboard.heroku.com/apps>

<br>


## SpringBoot + Postegess from Web

Create App

![](media/3dc916411e75dd0a0a87613111f2591d.png)

On Deploy tab

-   Deployment method : Github

-   App connected to GitHub : Choose Employee App
    ![](media/d9bde3472e3b46c76a00f5ce178da672.png)

-   Manual deploy : Click on Deployee Branch, It will deploy the code

    ![](media/49728b61abf2974742216a445563f78d.png)

<br>


Add Heroku PostgresSql add-on on Resourcs tab.
![](media/4f4e4fb4e8ee6d3fe5086dfa468da415.png)

<br>


If any erros, check log by

If you have already created your Heroku app, you can easily add a remote to your
local repository with the heroku git:remote command. All you need is your Heroku
app’s name:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ heroku git:remote -a <name>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


<br>


## Using Heroku CLI

The heroku create CLI command creates a new empty application on Heroku, along
with an associated empty Git repository. If you run this command from your app’s
root directory, the empty Heroku Git repository is automatically set as a remote
for your local repository.

![](media/1a39a912710621ecc26fd6b281288969.png)

<br>


use the git remote command to confirm that a remote named heroku has been set
for your app:

```bash
C:\\Git\\Heroku-SpringBoot-EmployeeServices\>git remote
heroku
origin
```

<br>



To deploy your app to Heroku, you typically use the git push command to push the
code from your local repository’s [master or main
branch](https://devcenter.heroku.com/articles/git-branches) to your heroku
remote, like so:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ git push heroku main
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


Application deployed.

<br>


By reading postgress dependency in Maven, it will automatically configure
PostgressSQL database. ![](media/bbe09096a427bff30ef59a9665406423.png)

## Ref.

<https://www.youtube.com/watch?v=k8z4UzV55ew>

<https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku>

<https://roytuts.com/how-to-deploy-spring-boot-data-jpa-application-to-heroku-cloud/>

<https://www.youtube.com/watch?v=E384MLaF4uU>
