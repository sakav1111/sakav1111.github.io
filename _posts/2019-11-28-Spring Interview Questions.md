---
title: Spring - Interview Questions 
date: 2017-11-11 00:00:00 Z
categories:
- Interview
tags:
- Interview
layout: article
cover: /assets/logo/spring.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---




## List out the new features available in Spring 4.0 and Spring 5.0?

-   Spring 2.5 **Annotations/Autowire**

-   Spring 3.0 **Java Configuration**

-   Spring 4.0 is the first to support **Java 8 features**.

-   Spring 5.0 has the support for **Reactive Programming**

## What is the default scope of bean in Spring framework? ([answer](http://javarevisited.blogspot.sg/2012/05/what-is-bean-scope-in-spring-mvc.html))

The default scope of a **Spring bean** is the **Singleton** scope and in the
**WebApplication** default scope of a spring bean is **request** scope.

Singleton bean means the same instance of a bean is shared with all other beans,
while request scope means a bean is alive only for a request.

## Does Spring Bean provide thread safety?

The default scope of Spring bean is *singleton*, so there will be *only one
instance per context*. If two threads calls **factory.getBean(“s"),** it returns
same object. if any threads changes bean property by setName() , then other
thread may get inconsistence results.

That means that all the having a class level variable that any thread can update
will lead to inconsistent data. **Hence in default mode spring beans are not
thread-safe**.

However, we can change spring bean scope to request, prototype or session to
achieve thread-safety at the cost of performance. It’s a design decision and
based on the project requirements.

### <br>What is Inversion of Control concept, how does Spring support IOC? ([answer](http://javarevisited.blogspot.sg/2012/12/inversion-of-control-dependency-injection-design-pattern-spring-example-tutorial.html))

**Removing bean creation things from developer End.** The simple meaning of
inversion of the control means that now the framework, Spring is responsible for
creating objects, wiring dependencies and managing their life-cycle instead of a
developer, which was the case before. That's where control is inverted from
developer to framework.

## **What is the difference between @Autowired and @Inject annotation in Spring?**

The **@Inject** annotation also serves the same purpose as **@Autowired,** but
the main difference between them is that 

-   **@Inject** is a **standard annotation(JRS -330)** for dependency injection

-   **@Autowired** is **spring specific.**

## How to create ApplicationContext in a Java Program?

-   **AnnotationConfigApplicationContext**: If we are using Spring in standalone
    java applications and *using annotations for Configuration*, then we can use
    this to initialize the container and get the bean objects.

-   **ClassPathXmlApplicationContext**: If we use *SpringConfig.xml* file in
    standalone application, then we can use this class to load the file and get
    the container object.

-   **FileSystemXmlApplicationContext**: This is similar to
    ClassPathXmlApplicationContext except that the *xml configuration file can
    be loaded from anywhere in the file system.*

## Name some of the design patterns used in Spring Framework?

Spring Framework is using a lot of design patterns, some of the common ones are:

1.  Singleton Pattern: Creating beans with default scope.

2.  [Factory
    Pattern](https://www.journaldev.com/1392/factory-design-pattern-in-java):
    Bean Factory classes

3.  [Prototype
    Pattern](https://www.journaldev.com/1440/prototype-design-pattern-in-java):
    Bean scopes

4.  [Adapter
    Pattern](https://www.journaldev.com/1487/adapter-design-pattern-java):
    Spring Web and Spring MVC

5.  [Proxy Pattern](https://www.journaldev.com/1572/proxy-design-pattern):
    Spring Aspect Oriented Programming support

6.  [Template Method
    Pattern](https://www.journaldev.com/1763/template-method-design-pattern-in-java):
    JdbcTemplate, HibernateTemplate etc

7.  Front Controller: Spring MVC DispatcherServlet

8.  Data Access Object: Spring DAO support

9.  Dependency Injection

10. Aspect Oriented Programming

## How to inject a java.util.Properties into a Spring Bean?

By writing in Spring bean xml file with *<property>* tag

```java
<bean id="configBean" class="com.boraji.tutorial.spring.xml.DatabaseConfig">
 <property name="properties">
 	<props>
  <prop key="driverClassName">com.mysql.jdbc.Driver</prop>
  <prop key="url">jdbc:mysql://localhost:3306/mydb</prop>
  <prop key="username">root</prop>
  <prop key="password">abcdxyz</prop>
 	</props>
 </property>
	</bean>
util:properties in Spring reads configuration file from a location
<beans>
	<util:properties id="props">
 <prop key="driverClassName">com.mysql.jdbc.Driver</prop>
 <prop key="url">jdbc:mysql://localhost:3306/mydb</prop>
 <prop key="username">root</prop>
 <prop key="password">abcdxyz</prop>
	</util:properties>

	<bean id="configBean" class="com.boraji.tutorial.spring.xml.DatabaseConfig">
 <property name="properties" ref="props" />
	</bean>
</beans>

public class DBConnection {

	@Value("${DB_DRIVER_CLASS}")
	private String driverClass;
	@Value("${DB_URL}")
	private String dbURL;
	@Value("${DB_USERNAME}")
	private String userName;
	@Value("${DB_PASSWORD}")
	private char[] password;

	public DBConnection() {
	}

	public void printDBConfigs() {
 System.out.println("Driver Class = " + driverClass);
 System.out.println("DB URL = " + dbURL);
 System.out.println("User Name = " + userName);

 // Never do below in production environment :D
 System.out.println("Password = " + String.valueOf(password));
	}
}
```

## How do you turn on annotation based autowiring?

-   Include <context:annotation-config > in bean configuration file.

-   Use **AnnotationConfigApplicationContext** to get Context Object.

## Differentiate between BeanFactory and ApplicationContext.

| **BeanFactory**                                           | **ApplicationContext**                             |
|-----------------------------------------------------------|----------------------------------------------------|
| It uses **Lazy initialization**                           | It uses **Eager/ Aggressive initialization**       |
| It explicitly provides a resource object using the syntax | It creates and manages resource objects on its own |
| It **doesn’t supports internationalization**              | It supports internationalization                   |
| It doesn’t supports annotation based dependency           | It supports annotation based dependency            |

## Can we have multiple Spring configuration files in one project?

You can load multiple Java-based configuration files:

```java
@Configuration
@Import({MainConfig.class, SchedulerConfig.class})
public class AppConfig {

//Or load one XML file that will contain all other configs:
ApplicationContext context = new ClassPathXmlApplicationContext("spring-all.xml");

//And inside this XML file you’ll have:
<import resource="main.xml"/>
<import resource="scheduler.xml"/>
```

<br>

# Spring MVC


## What’s the difference between @Component, @Controller, @Repository & @Service annotations in Spring?

| **ANNOTATION**   | **USE** | **DESCRIPTION**                                                                                                                                                       |
|------------------|---------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **@Component**  | Type    | Generic stereotype annotation for any Spring-managed component.                                                                                                       |
| **@Controller** | Type    | Stereotypes a component as a Spring MVC controller.                                                                                                                   |
| **@Repository** | Type    | Stereotypes a component as a repository. Also indicates that SQLExceptions thrown from the component's methods should be translated into Spring DataAccessExceptions. |
| **@Service**    | Type    | Stereotypes a component as a service.                                                                                                                                 |

## What is ViewResolver in Spring?

*ViewResolver* implementations are used to resolve the view pages by name.
Usually we configure it in the spring bean configuration file. For example:
```xml
<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<beans:property name="prefix" value="/WEB-INF/views/" />
	<beans:property name="suffix" value=".jsp" />
</beans:bean>
```


## What is View Resolver pattern? how it works in Spring MVC

View Resolver pattern is a J2EE pattern which allows a web application to
dynamically choose it's view technology e.g. HTML, JSP, Tapestry, JSF, XSLT or
any other view technology.  
  
In this pattern, View resolver holds mapping of different views, controller
return name of the view, which is then passed to View Resolver for selecting an
appropriate view.

## **What is the difference between @Controller and @RestController?**

@RestController is better when you are developing RESTful web services using
Spring MVC framework. It's a combination of **@Controller + @ResponseBody**
annotation which allows the controller to directly write the response and
bypassing the view resolution process, which is not required for RESTful web
service.   
  
It also instructs DispatcherServlet to use different HttpMessageConverters to
represent the response in the format client is expecting e.g.
HttpMessageJackson2Convert to represent response in JSON format and JAXB based
message converts to generate XML response

## **What does @RequestMapping annotation do? (answer)**

The @RequestMapping annotation is used to map web requests to Spring Controller
methods. You can map request based upon HTTP methods  e.g. GET and POST and
various other parameters.

For examples, if you are developing RESTful Web Service using Spring then you
can use produces and consumes property along with media type annotation to
indicate that this method is only used to produce or consumers JSON as shown
below:
```java
@RequestMapping (method = RequestMethod.POST, consumes="application/json")
public Book save(@RequestBody Book aBook) {
   return bookRepository.save(aBook);
}
```


## **When do you need @ResponseBody annotation in Spring MVC?**

The @ResponseBody annotation can be put on a method to indicates that the
return type should be written directly to the HTTP response body (and not placed
in a Model, or interpreted as a view name).
```java
@RequestMapping(path = "/hello", method = RequestMethod.PUT)
@ResponseBody
public String helloWorld() {
   return "Hello World";
}
```


Alternatively, you can also use @RestController annotation instead
of @Controller annotation. This will remove the need for
using @ResponseBody because as discussed in the previous answer, it comes
automatically with @RestController annotation.

## **What does @PathVariable do in Spring MVC? Why it's useful in REST with Spring?**

For example, in the URL http://myapp.com/books/101 if you want to extract 101
the id, then you can use @PathVariable annotation of Spring MVC

## **Where do you need @EnableWebMVC? (answer)**

The @EnableWebMvc annotation is required to **enable Spring MVC when Java
configuration is used to configure Spring MVC instead of XML**. It is equivalent
to <mvc: annotation-driven>  in XML configuration.

## How to Call Stored procedure in Spring Framework?

To call a Stored procedure in Spring framework you need to create Class which
will should extends **StoredProcedure** class. You just need to call the execute
method from the DAO layer.
```java
public class EmployeeInfo extends StoredProcedure 
{
   private static final String EMP_ID = "EMP_ID"; 
   private static final String EMP_NAME = "EMP_NAME";
   private static final String JOIN_DATE = "JOIN_DATE";
   public SnapshotSearchStoredProcedure(DataSource dataSource, String procedureName) 
   {
       super(dataSource, procedureName); 
       declareParameter(new SqlParameter(EMP_ID, Types.NUMERIC));
       declareParameter(new SqlOutParameter(EMP_NAME, Types.VARCHAR));
       declareParameter(new SqlOutParameter(JOIN_DATE, Types.VARCHAR));
       compile ();
   }
   public Map execute(Integer empId) 
   {
       Map<String, Object> inputs = new HashMap<String, Object>();
       inputs.put(P_CLD_IDR, empId);
       Map<String, Object> result = execute (inputs);
       return result;
   }
}
```

## How to get ServletContext and ServletConfig object in a Spring Bean?

There are two ways to get Container specific objects in the spring bean.

**Using @Autowired** annotation with bean variable of
type ServletContext and ServletConfig. They will work only in servlet container
specific environment only though.They alreay comes with Server jar
```java
@Autowired
ServletContext servletContext;
```


## How to upload file in Spring MVC Application?

Spring provides built-in support for uploading files
through **MultipartResolver** interface implementations. 




## How to use Tomcat JNDI DataSource in Spring Web Application?

For using servlet container configured JNDI DataSource, we need to configure it
in the spring bean configuration file and then inject it to spring beans as
dependencies. Then we can use it with **JdbcTemplate** to perform database
operations
```java
<beans:bean id="dbDataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
    <beans:property name="jndiName" value="java:comp/env/jdbc/MyLocalDB"/>
</beans:bean>
```


## Spring Security 


If we think about the meaning of authentication, it seems that it is all about a
client identifying itself to the server. After client identification is done,
the server can remember the client each time the request comes from the client.
There are two common approaches to authentication mechanisms: one of them is
called "Session Cookie Based" and the other one is "Token Based".

## Session Cookie based

The most common approach we probably all know is to use a server generated
secret token (Session key) in the form of a JSESSIONID cookie. Initial setup for
this is near nothing these days perhaps making you forget you have a choice to
make here in the first place. Even without further using this “Session key" to
store any other state “in the session", the key itself is in fact *state* as
well.  I.e. without a shared and persistent storage of these keys, no successful
authentication will survive a server reboot or requests being load balanced to
another server.

## OAuth2 / API keys

Whenever talking about REST APIs and Security; OAuth2 and other types of API
keys are mentioned. Basically they involve sending custom tokens/keys within the
HTTP Authorization header. When used properly both relieve clients from dealing
with Cookies using the header instead. This solves CSRF vulnerabilities and
other Cookie related issues. One thing they do not solve however is the need for
the server to check the presented authentication keys, pretty much demanding
some persistent and maintainable shared storage for linking the keys to
users/authorizations.

```java
private HttpHeaders createHeaders(final String userId, final String password) {
 String auth = userId + ":" + password;
 byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
 String authHeader = "Basic " + new String(encodedAuth);

 HttpHeaders headers = new HttpHeaders();
 headers.set("Authorization", authHeader);
 return headers;
	}


private ResponseEntity<String> makeRestCall(String url, String userId,
 	String password) {
 // Basic Auth only.
 if (!"".equals(userId) && !"".equals(password)) {
 	return restOperations.exchange(url, HttpMethod.GET,
  	new HttpEntity<>(createHeaders(userId, password)),
  	String.class);

 } else {
 	return restOperations.exchange(url, HttpMethod.GET, null,
  	String.class);
 }

	}
```
