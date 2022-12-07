---
title: SpringBoot -  could not initialize proxy – no Session | LazyInitializationException in SpringBoot JPA
date: 2022-12-07 00:00:00 Z
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


We will see about Failed to lazily initialize a collection of role could not initialize proxy -- no Session hibernate exception.

The aim of lazy loading is to save resources by not loading related objects into memory when we load the main object. Instead, we postpone the initialization of lazy entities until the moment they're needed. Hibernate uses proxies and collection wrappers to implement lazy loading.

When retrieving lazily-loaded data, there are two steps in the process. First, there's populating the main object, and second, retrieving the data within its proxies. Loading data always requires an open *Session* in Hibernate.

The problem arises when the second step happens after the transaction has closed, which leads to a *LazyInitializationException*.

Understanding the reason for this exception.
--------------------------------------------

Generally, This exception comes when two entities are in association mapping with fetch= FetchType.LAZY (for eg. OneToMany relationship) and we try to get child entity from the parent entity after session gets closed.

For example, consider we have two entities in OneToMany relation as below.

```java
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;

	@Column(name = "book_name")
	private String bookName;

	@OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name = "book_id",referencedColumnName="bookId")
	private List<Story> storyList = new ArrayList<>();

}
```


Story.java
```java
@Entity
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int storyId;

	@Column(name = "story_name")
	private String storyName;

}
```


Below code will throw failed to lazily initialize a collection of role could not initialize proxy -- no Session hibernate exception.
```java
@Service("bookServiceImpl")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public Book findByBookId(int bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		Session sessin = (Session)entityManager.unwrap(Session.class);
		sessin.close();
        book.getStoryList();
		return book.get();
	}

}
```


Note -- In the above code snippet, I have intentionally closed the session to replicate the issue.
```
{\
"timestamp": "2020-04-29T10:24:58.705+0000",\
"status": 500,\
"error": "Internal Server Error",\
"message": "Could not write JSON: failed to lazily initialize a collection of role: com.hibernatejpa.entity.Book.storyList, could not initialize proxy -- no Session; nested exception is com.fasterxml.jackson.databind.JsonMappingException: failed to lazily initialize a collection of role: com.hibernatejpa.entity.Book.storyList, could not initialize proxy -- no Session (through reference chain: com.hibernatejpa.entity.Book[\"storyList\"])",\
"path": "/book/1"\
}
```


Code snippet which is responsible for this exception.
```java
		if ( session == null ) {
			if ( allowLoadOutsideTransaction ) {
				tempSession = openTemporarySessionForLoading();
			}
			else {
				throwLazyInitializationException( "could not initialize proxy - no Session" );
			}
		}
```


Observe the above code, if the session is null then throwLazyInitializationException() method will get called. Let's have a quick look into throwLazyInitializationException().
```java
	private void throwLazyInitializationException(String message) {
		throw new LazyInitializationException(
				"failed to lazily initialize a collection" +
						(role == null ? "" : " of role: " + role) +
						", " + message
		);
	}
```


This is from where we are getting this exception.

Fixing by using @Transactional annotation.
------------------------------------------

The easy and quick fix would be just used @Transactinal annotation with your method.
```java
	@Transactional
	public Book findByBookId(int bookId) {
		Optional<Book> bookResponse = bookRepository.findById(bookId);
		Book book = bookResponse.get();
		Session sessin = (Session)entityManager.unwrap(Session.class);
		sessin.close();
		book.getStoryList();
		return book;
	}
```

We are using @Transactinal with findByBookId() method and yes now even we are doing session.close() we should don't have LazyInitializationException.

Fixing by calling child entity in a separate method before the session is closed.
---------------------------------------------------------------------------------

If you don't want to use @Transactinal annotation, we can define a separate method where we can call child entity. B
```java
	public Book findByBookId(int bookId) {
		Optional<Book> bookResponse = bookRepository.findById(bookId);
		Book book = bookResponse.get();
		Session sessin = (Session)entityManager.unwrap(Session.class);
		getStory(book);
		sessin.close();
		return book;
	}

	public void getStory(Book book){
		List<Story> storyList = book.getStoryList();
		storyList.size();

	}
```


In the above code, we are calling getStory() method just before closing the session.

Fixing by defining enable_lazy_load_no_trans=true in application.properties file.
---------------------------------------------------------------------------------

In application.properties file we can define enable_lazy_load_no_trans = true.

We need to define spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true in applcation.properties file. By defining this attribute we are telling to hibernate initialize lazy state even for outside transactions. It will create a new temporary session and we will able to do book.getStoryList() without any LazyInitializationException.

The recommended approach is to design our application to ensure that data retrieval happens in a single transaction. But, this can sometimes be difficult when using a lazy entity in another part of the code that is unable to determine what has or hasn't been loaded.

Hibernate has a workaround, an *enable_lazy_load_no_trans* property. Turning this on means that each fetch of a lazy entity will open a temporary session and run inside a separate transaction.

just recall the code snippet from where we were getting this exception.
```java
		if ( session == null ) {
			if ( allowLoadOutsideTransaction ) {
				tempSession = openTemporarySessionForLoading();
			}
			else {
				throwLazyInitializationException( "could not initialize proxy - no Session" );
			}
		}
```


If we use enable_lazy_load_no_trans=true the value of allowLoadOutsideTransaction would be true and openTemporarySessionForLoading() will create temperary session.

Fixing by fetch= FetchType.EAGER.
---------------------------------

Fetch child entity eagerly then it will available even after the session will get close.
```java
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;

	@Column(name = "book_name")
	private String bookName;

	@OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name = "book_id",referencedColumnName="bookId")
	private List<Story> storyList = new ArrayList<>();	

}

	public Book findByBookId(int bookId) {
		Optional<Book> bookResponse = bookRepository.findById(bookId);
		Book book = bookResponse.get();
		Session sessin = (Session)entityManager.unwrap(Session.class);
		sessin.close();
		return book;
	}
```


An example using Hibernate/JPA and Spring Boot from scratch.
------------------------------------------------------------

Let's see an example of Failed to lazily initialize a collection of role could not initialize proxy -- no Session in Hibernate.

Create a maven project with the name lazilyinitializeexception and modify the pom.xml with the below code.
```
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>lazilyinitializeexception</groupId>
	<artifactId>lazilyinitializeexception</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>lazilyinitializeexception</name>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.2.RELEASE</version>
	</parent>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>

		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc6</artifactId>
			<version>11.2.0.3</version>
		</dependency>

	</dependencies>

	<build>
		<finalName>${project.artifactId}</finalName>
		<plugins>

			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>
				<configuration>
					<fork>true</fork>
					<executable>C:\Program Files\Java\jdk1.8.0_131\bin\javac.exe</executable>
				</configuration>
			</plugin>

		</plugins>
	</build>
</project>
```
**Note -- In pom.xml we have defined javac.exe path in configuration tag. You need to change accordingly i.e where you have installed JDK.**

If you see any error for oracle dependency then follow [these](https://netsurfingzone.com/adding-ojdbc-maven-dependency-in-pom-xml) steps.

Book.java
```java
package com.hibernatejpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;

	@Column(name = "book_name")
	private String bookName;

	@OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name = "book_id",referencedColumnName="bookId")
	private List<Story> storyList = new ArrayList<>();

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public List<Story> getStoryList() {
		return storyList;
	}

	public void setStoryList(List<Story> storyList) {
		this.storyList = storyList;
	}

}
```


Story.java
```java
package com.hibernatejpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int storyId;

	@Column(name = "story_name")
	private String storyName;

	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	public String getStoryName() {
		return storyName;
	}

	public void setStoryName(String storyName) {
		this.storyName = storyName;
	}

}
```


Define the repository interface extending CrudRepository.

BookRepository.java
```java
package com.hibernatejpa.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hibernatejpa.entity.Book;
@Repository
public interface BookRepository extends CrudRepository<Book,Serializable> {
	public Book findByBookId(int bookId);
}
```


Define service interface i.e BookService.java
```java
package com.hibernatejpa.service;
import org.springframework.stereotype.Component;
import com.hibernatejpa.entity.Book;

@Component
public interface BookService {
	public Book saveBook(Book book);
}
```


Define service implementation class.

BookServiceImpl.java
```java
package com.hibernatejpa.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernatejpa.entity.Book;
import com.hibernatejpa.entity.Story;
import com.hibernatejpa.repository.BookRepository;
import com.hibernatejpa.service.BookService;

@Service("bookServiceImpl")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Book saveBook(Book book) {

		book = bookRepository.save(book);
		return book;

	}

	// problematic code
	public Book findByBookId(int bookId) {
		Optional<Book> bookResponse = bookRepository.findById(bookId);
		Book book = bookResponse.get();
		Session sessin = (Session)entityManager.unwrap(Session.class);
		sessin.close();
		book.getStoryList();
		return book;
	}

	// Using @Transactional
	@Transactional
	public Book findByBookIdUsingTransaction(int bookId) {
		Optional<Book> bookResponse = bookRepository.findById(bookId);
		Book book = bookResponse.get();
		Session sessin = (Session)entityManager.unwrap(Session.class);
		sessin.close();
		book.getStoryList();
		return book;
	}

	//Using getChild()
	public Book findByBookIdUsingGetChild(int bookId) {
		Optional<Book> bookResponse = bookRepository.findById(bookId);
		Book book = bookResponse.get();
		Session sessin = (Session)entityManager.unwrap(Session.class);
		getStory(book);
		sessin.close();
		return book;
	}

	public void getStory(Book book){
		List<Story> storyList = book.getStoryList();
		storyList.size();

	}
}
```


Note -- See here more about @Component, @Controller, @Service and @Repository annotations [here](https://netsurfingzone.com/spring/component-controller-service-and-repository-annotations-example-using-spring-boot/).

Define the controller class or endpoint.

BookController.java
```java
package com.hibernatejpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hibernatejpa.entity.Book;
import com.hibernatejpa.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/savebook", method = RequestMethod.POST)
	@ResponseBody
	public Book saveBook(@RequestBody Book book) {
		Book bookResponse = bookService.saveBook(book);
		return bookResponse;
	}

	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
	@ResponseBody
	public Book getBookDetails(@PathVariable int bookId) {
		Book bookResponse = bookService.findByBookId(bookId);

		return bookResponse;
	}

}
```


Note -- See more details about @Controller and RestController [here](https://netsurfingzone.com/spring/restcontroller-and-controller-annotation-example-in-spring-boot/).

Define the JpaConfig.java
```java
package com.hibernatejpa.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.hibernatejpa.repository")
public class JpaConfig {

}
```


Step 12 -- Define the SpringMain.java
```java
package com.hibernatejpa.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.hibernatejpa.*")
@EntityScan("com.hibernatejpa.*")
public class SpringMain {
	public static void main(String[] args) {

        SpringApplication.run(SpringMain.class, args);
    }

}
```


And finally, we have an application.properties file where we have database details.

application.properties
```
# Connection url for the database
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=SYSTEM
spring.datasource.password=oracle3
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
# Show or not log for each sql query
spring.jpa.show-sql = true

spring.jpa.hibernate.ddl-auto =create
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.Oracle10gDialect

server.port = 9091

#spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
```



We are almost done. Just build the project once running the main method. Open git bash or cmd and Run mvn clean install.

Let's deploy the application running SpringMain class as a java application and do testing. First, we will save one book with three stories.

Sample request JSON data-
```
{
	"bookName": "Premchand's best stories",
	"storyList": [{
			"storyName": "Stories of two oxes"
		},
		{
			"storyName": "idgah"
		},
		{
			"storyName": "Poosh Ki Rat"
		}
	]
}
```

[![Failed to lazily initialize a collection of role could not initialize proxy - no Session](https://netsurfingzone.com/wp-content/uploads/2020/04/lazyexcp1.png)](https://netsurfingzone.com/hibernate/failed-to-lazily-initialize-a-collection-of-role-could-not-initialize-proxy-no-session/attachment/lazyexcp1/)

In case the session is closed.

[![](https://netsurfingzone.com/wp-content/uploads/2020/04/lazyinitializexecepetion.png)](https://netsurfingzone.com/hibernate/failed-to-lazily-initialize-a-collection-of-role-could-not-initialize-proxy-no-session/attachment/lazyinitializexecepetion/)

Using @Transaction or other solutions.

[![](https://netsurfingzone.com/wp-content/uploads/2020/04/lazyexcep3-1.png)](https://netsurfingzone.com/hibernate/failed-to-lazily-initialize-a-collection-of-role-could-not-initialize-proxy-no-session/attachment/lazyexcep3-2/)



# Conclusion

Just in case you started with spring boot and have a DataBase structure with many `@onetomany` relationships.

1. You might face `*MultipleBagFetchException:cannot simultaneously fetch multiple bags*` 🛑 if you set more than one of these relationships as `*(fetch = FetchType.EAGER)*`.


2. So only option you are left with is to set these `@onetomany` relationships as `(fetch = FetchType.LAZY)` .


3. But as soon as you do that and if you try to get the child entity from the parent entity you might face `*LazyInitializationException: could not initialize proxy-no Session*`🛑 error.


4. Then after searching on internet you might find ok. The way to fetch these lazy loaded entities is to use `@transactional` annotation around the function of the controller or service in which lazy loading is performed. And Voila! everything works as usual.


6. Then Comes a day that you need to run a async thread which could be for many reasons for e.g You are having a MultiTenant Application in which you are trying to access different tenants in different threads. And load the data from one tenant to another.


7. Now in this case adding `@transactional` annotation to the calling method which is running in request thread or adding `@transactional` annotation to the called method in the async thread, would not work as expected. Which means it could still give `*LazyInitializationException: could not initialize proxy-no Session*`🛑 for the lazy loading which is getting performed in the async thread.
- Two possible mistakes you might be doing is:-
	-   The method visibility is not public.
	-   The invocation is coming from inside of the bean (Meaning the caller and the called functions are in same file).


8. In that case you can define your called method (which is called inside an async thread) in a separate service and get it`@autowired` inside the calling service in which the calling function(Running in a request thread) would be calling it.
So that the called method(Running in async thread) is included as part of bean which is managed by spring itself by `@autowired` annotation.



9. And most probably in that case you would need to use `@transactional(propagation=REQUIRES_NEW)` .So that the async method run in the separate transaction of itself.


10. Voila! Now You are able to load your lazy loaded entities in any thread you want. Enjoy!!. ✅




The database query generation for all Scenario.
-----------------------------------------------

The query generated for the first three solutions.
```
Hibernate: select book0_.book_id as book_id1_0_0_, book0_.book_name as book_name2_0_0_ from book book0_ where book0_.book_id=?

Hibernate: select storylist0_.book_id as book_id3_1_0_, storylist0_.story_id as story_id1_1_0_, storylist0_.story_id as story_id1_1_1_, storylist0_.story_name as story_name2_1_1_ from story storylist0_ where storylist0_.book_id=?

The query generated for the fourth solution(fetch= FetchType.EAGER).

Hibernate: select book0_.book_id as book_id1_0_0_, book0_.book_name as book_name2_0_0_, storylist1_.book_id as book_id3_1_1_, storylist1_.story_id as story_id1_1_1_, storylist1_.story_id as story_id1_1_2_, storylist1_.story_name as story_name2_1_2_ from book book0_ left outer join story storylist1_ on book0_.book_id=storylist1_.book_id where book0_.book_id=?
```

That's all about Failed to lazily initialize a collection of role could not initialize proxy -- no Session

Visit [docs](https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/performance.html#performance-fetching-lazy) related to LazyInitializationException.

[Ref. Link](https://www.baeldung.com/hibernate-lazy-loading-workaround)
