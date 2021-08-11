---
title: Spring Boot - Interview Questions 
date: 2017-11-11 00:00:00 Z
categories:
- Interview
tags:
- Interview
layout: article
cover: /assets/logo/springboot.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

Spring Boot - Interview Questions 
===============

## What is the latest version of spring boot and its System requirement?

As per July, 2018 Spring boot latest version is **2.1.**

Spring boot needs **Java 8+ version** and Spring 5 framework as minimum version.

## How does Spring enable create production ready applications in quick time?

Spring Boot aims to enable production ready applications in quick time. Spring
Boot provides a few non functional features out of the box like caching,
logging, monitoring and embedded servers.

-   spring-boot-starter-actuator - To use advanced features like monitoring &
    tracing to your application out of the box

-   spring-boot-starter-undertow, spring-boot-starter-jetty,
    spring-boot-starter-tomcat - To pick your specific choice of Embedded
    Servlet Container

-   spring-boot-starter-logging - For Logging using logback

-   spring-boot-starter-cache - Enabling Spring Framework’s caching support

## What is the minimum baseline Java Version for Spring Boot 2 and Spring 5?

Spring Boot 2.0 requires Java 8 or later. Java 6 and 7 are no longer supported.

## What do Dev Tools in Spring boot mean?

By using devtools, You don’t have to redeploy your application each time you
made the changes.

The developer can reload the progressions without restart of the server.

It maintains a strategic distance from the agony of redeploying application each
time when you roll out any improvement. This module will can’t be utilized in a
production environment.

## Spring DevTools & uses

Applications that use spring-boot-devtools will **automatically restart whenever
files on the classpath change**. This can be a useful feature when working in an
IDE as it gives a very fast feedback loop for code changes.

## Would we be able to Use Spring Boot with Applications Which Are Not Using Spring?

No, it isn’t conceivable starting at now. Spring boot is restricted to Spring
applications only.

## How to connect to an external database like MSSQL or oracle with Spring boot?

**Step 1** -The first step to connect the database like Oracle or MySql is
adding the dependency for your database connector to pom.xml.

**Step 2** -The next step is the elimination of H2 Dependency from pom.xml

**Step 3** -Step 3 includes the schema and table to establish your database.

**Step 4** -The next step is configuring of the database by using Configure
*application.properties* to connect to your database.

**Step 5**-And the last step is to restart your device and your connection is
ready to use.

## How to disable a specific auto-configuration?

If we want to disable a specific auto-configuration, we can indicate it using
the **exclude attribute** of the *@EnableAutoConfiguration* annotation. For
instance, this code snippet neutralizes DataSourceAutoConfiguration:

```java
// other annotations
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class MyConfiguration { }
```


If we enabled auto-configuration with the *@SpringBootApplication* annotation —
which has @EnableAutoConfiguration as a meta-annotation — we could disable
auto-configuration with an attribute of the same name:
```java
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MyConfiguration { }
```


We can also disable an auto-configuration with the
*spring.autoconfigure.exclude* environment property in the
*application.properties*
```java
spring.autoconfigure.exclude =
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```


## How to deploy Spring Boot web applications to WebSphere as a WAR?

spring-boot-maven-plugin handles the Packing things.
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```


If we mentioned in our POM.xml packaging as jar it will generate Jar by
including Tomcat server as well.

To Build WAR with out Tomact we need to below 2 Changes

```xml
// 1.In POM.xml – package type as ‘war’
<parent>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-parent</artifactId>
 <version>2.1.3.RELEASE</version>
 <relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>
	<name>demo</name>
	<description>Demo project for Spring Boot</description>

// 2.Remove tomcat starter Dependency 
       <dependency>
 	<groupId>org.springframework.boot</groupId>
 	<artifactId>spring-boot-starter-tomcat</artifactId>
 	<scope>provided</scope>
 </dependency>
```


## What is Spring Boot DevTools used for?

Spring Boot Developer Tools, or DevTools, is a set of tools making the
development process easier. 

The *spring-boot-devtools* module is automatically disabled if the application
runs in production

By default, DevTools applies properties suitable to a development environment.
These properties disable template caching, enable debug logging for the web
group, and so on. As a result, we have this sensible development-time
configuration without setting any properties.

Applications using DevTools restart whenever a file on the classpath changes.
This is a very helpful feature in development, as it gives quick feedback for
modifications.

By default, static resources, including view templates, don’t set off a restart.
Instead, a resource change triggers a browser refresh. Notice this can only
happen if the LiveReload extension is installed in the browser to interact with
the embedded LiveReload server that DevTools contains.

## What is LiveReload?

The spring-boot-devtools module includes an **embedded LiveReload server that
can be used to trigger a browser refresh when a resource is changed**.
LiveReload browser extensions are freely available for Chrome, Firefox and
Safari from livereload.com.

## How to exclude auto restart for static files?

By default changing resources
in **/META-INF/maven**, **/META-INF/resources**,**/resources,
/static**, **/public or /templates** will not trigger a restart.

But If you want to customize these exclusions you can use
the **spring.devtools.restart.exclude** property.

If you want to keep those defaults and add additional exclusions, use
the **spring.devtools.restart.additional-exclude** property instead.

## What is Hot swapping in spring boot?

**Reloading the changes without restarting the server is called hot swapping**,
Modern IDEs (Eclipse, IDEA, etc.) all support hot swapping of bytecode, so if
you make a change that doesn’t affect the class or method signatures it should
reload cleanly with no side effects.

## How to write integration tests?

When you create Project using Spring.io, by default test class for Application
class also will created. It is annotated with **@SpringBootTest**
```java
annotated with @SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
```

##  What is Spring Boot Actuator?

Spring Boot provides actuator to monitor and manage our application. **Actuator
is a tool which has HTTP endpoints**. when application is pushed to production,
you can choose to manage and monitor your application using HTTP endpoints.

**The actuator provides features like auditing, health, metrics,
environment information, thread dump etc.** using HTTP or JMX endpoints. Here
are some of the most common built-in actuator endpoints:

-   *beans* – **Displays a complete list of all the Spring beans in your
    application.**

-   auditevents – Exposes audit events information for the current application.

-   *caches* – Exposes available caches.

-   *configprops* – Displays a collated list of all @ConfigurationProperties.

-   *health* – **Shows application health information**.

-   *info* – Displays arbitrary application info.

-   *metrics* – Shows ‘metrics’ information for the current application.

-   *mappings* – **Displays a collated list of all @RequestMapping paths.**

-   *sessions* – Allows retrieval and deletion of user sessions from a Spring
    Session-backed session store.

-   threaddump – Performs a thread dump.

## How do you Change tomcat HTTP port & Context URL?

You can change the Tomcat HTTP port by changing default HTTP property in the
**application.properties** file.
```java
Server.port = 8080
Server.context.path = /
```


## Can you control logging with Spring Boot? How?

Yes, we can control logging with Spring Boot by specifying log levels on
application.properties file.

Spring Boot loads this file when it exists in
the [classpath](http://www.java67.com/2012/08/what-is-path-and-classpath-in-java-difference.html) and
it can be used to configure both Spring Boot and application code.  
  
Spring Boot uses Commons Logging for all internal logging and you can change log
levels by adding following lines in the **application.properties** file:  
```java
logging.level.org.springframework=DEBUG
logging.level.com.demo=INFO
```


## What is YAML ?

YAML is a human-readable data serialization language. It is commonly used for
configuration files.

Compared to properties file, YAML file is much more structured and less
confusing in case we want to add complex properties in the configuration file.
As can be seen YAML has hierarchical configuration data

## How to set the active profile in Spring Boot?

There are two ways to set the active profile in Spring Boot.

Pass in the active profile as an argument while launching the application.

```java
java -jar -Dspring.profiles.active=production application-1.0.0-RELEASE.jar //pass as command line argument
```


Use the application.properties file to set the active profile.
```java
spring.profiles.active=production
```

