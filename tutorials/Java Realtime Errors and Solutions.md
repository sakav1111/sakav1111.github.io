---
title: Java Realtime Errors & Solutions
permalink: "/tutorials/java-errors"
key: java-errors
categories:
- Java
tags:
- Java
---


# Java






# SpringBoot


### SpringBoot : Error while saving User failed to lazily initialize a collection of role:address, could not initialize proxy - no Session

Error

I'm using an `@Async` annotation on a service layer method.

Everything works fine when I EAGERLY load @OneToMany collection fields, but when I try to access LAZY loaded element I found that Hibernate `SessionImplementor` object `session` is null. That obviously give me an exception:

```
org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role:
....

```

Here is my collection field:

```
@OneToMany(mappedBy="abc", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
@OrderBy(value="xsd asc")
@JsonIgnore
private Set<Item> items = new HashSet<Item>();

```

How can I bind hibernate session in order to LAZELY load my object inside `@Async` context?

Solution

had the same problem, spent few days trying to find a solution, finally got a solution. I would like to share the details I found for those who might have the same issue.

1st - Your `@Async`-annotated method should be declared in a separate bean rather than the `@Controller`- or `@RestController`-annotated bean.

2nd - You certainly need to declare the method `@Transactional` which is called from within `@Async` declared method. However, the very first method called from `@Async` method has to be defined `@Transactional`. I had the `@Transactional` method in second or third level in the method execution stack therefore the problem was not solved and I spent two days trying to figure out what was going on.

In normal circumstances (without `@Async`) a transaction gets propagated through the call hierarchy from one Spring component to the other.

When a `@Transactional` Spring `@Component` calls a method annotated with `@Async` this does not happen. The call to the asynchronous method is being scheduled and executed at a later time by a task executor and is thus handled as a 'fresh' call, i.e. without a transactional context. If the `@Async` method (or the component in which it is declared) is not `@Transactional` by itself Spring will not manage any needed transactions.

https://stackoverflow.com/questions/25083295/spring-async-null-hibernate-session-on-lazy-collection

