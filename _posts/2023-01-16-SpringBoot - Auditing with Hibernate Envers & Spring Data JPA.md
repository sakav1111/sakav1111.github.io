---
title: SpringBoot - Auditing with Hibernate Envers & Spring Data JPA
date: 2023-01-16 00:00:00 Z
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

# SpringBoot - Spring Boot auditing using Spring Data Envers

Spring Data Envers is an extension of the Spring Data JPA project that allows you to easily integrate Hibernate Envers into your Spring Data JPA-based applications. Hibernate Envers is a library for auditing changes to your JPA entities, providing versioning and historical data support. This means you can keep track of changes made to your data and query historical data as well.

1.  update pom.xml
```
    <dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-envers</artifactId>
		</dependency>
                <dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-envers</artifactId>
		</dependency>
```


2. To Enable Envers in out application, please include below annotations at the top of Application.java or Configuration class

```java
@EnableJpaAuditing
@EnableEnversRepositories
@EnableJpaRepositories(basePackages = {"com.employee"}, repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class Application {
 // Code
}
```


3. Annotate entity classes with
**@Audited** @**EntityListeners**(*AuditingEntityListener*.class)

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employee")
@Accessors(chain = true)
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Employee extends BaseEntity{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column
    private String name;

    @Column
    private Double salary;

    @Column
    private String city;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Account account;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    @NotAudited
    private List<Document> documentList;

}
```

Here

**@Audited** --- When you annotate a JPA entity class with @Audited, Hibernate Envers will automatically track changes to instances of that entity, allowing you to maintain a history of modifications over time.

**@EntityListeners** --- These listeners can be useful for tasks like auditing, validation, or custom behavior that needs to be triggered in response to entity state changes.

**@NotAudited** : If you don't want the relationship to be audited mark it as org.hibernate.envers.NotAudited



4. Extend your Repository with RevisionRepository  

```java 
public interface EmployeeRepository extends RevisionRepository<Employee, Long, Long>, JpaRepository<Employee, Long>, EmployeeRepositoryCustom,
      QuerydslPredicateExecutor<Employee>,
      QuerydslBinderCustomizer<QEmployee> {

  @Override
   default void customize(QuerydslBindings bindings, QEmployee root) {
      bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
   }
}
```

**RevisionRepository** has the following methods to get Version history

Here are the common methods you might find in a RevisionRepository for querying and retrieving historical data using Hibernate Envers, presented in a table format:

`- findRevision(Number revision):` Retrieves a specific revision.

`- findRevisions(Class<T> entityClass, ID id):` Retrieves all revisions for a specific entity with the given ID.

`- findRevisions(Class<T> entityClass):`Retrieves all revisions for a specific entity class.

`- findRevisionsBetween(Class<T> entityClass, fromDate, toDate) :`Retrieves all revisions for a specific entity class that occurred between two given timestamps.

`- findRevisionsByEntityIdAndRevisionNumber(Class<T> entityClass, ID id, Number revision) :` Retrieves a specific revision of an entity with the given ID.

That's . once you run the application you can see both entity tables & audit tables get created in the DB.



### Getting History API Example. 


```java

//Controller
@ApiOperation("Get Employee Version History By Id")
@GetMapping("/{id}/history")
public List<EmployeeDto> getEmployeeHistoryById(@PathVariable("id") Long id, Pageable pageable) {
    return employeeService.getEmployeeHistoryById(id, pageable);
}

//service
@Override
public List<EmployeeDto> getEmployeeHistoryById(Long id, Pageable pageRequest) {
    if (!employeeRepository.findById(id).isPresent()) {
        throw new EntityNotFoundException(Employee.class, id);
    }
    List<EmployeeDto> employeeHistoryDtoList = null;
    try {
        Pageable pageable = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(), RevisionSort.desc());
        Page<Revision<Long, Employee>> employeeRevisions = employeeRepository.findRevisions(id, pageable);

      employeeHistoryDtoList = employeeRevisions.stream().map((p) ->
                employeeMapper.toDto(p.getEntity())
        ).collect(Collectors.toList());
    } catch (DataAccessException ex) {
       ex.printStackTrace();
    }
    return employeeHistoryDtoList;
}

//Repository
public interface EmployeeRepository extends RevisionRepository<Employee, Long, Long>, JpaRepository<Employee, Long>, EmployeeRepositoryCustom,
      QuerydslPredicateExecutor<Employee>,
      QuerydslBinderCustomizer<QEmployee> {

    default void customize(QuerydslBindings bindings, QEmployee root) {
      bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
   }

}
```


Errors
*org.springframework.dao.InvalidDataAccessApiUsageException: Service is not yet initialized; nested exception is java.lang.IllegalStateException: Service is not yet initialized*

-   Make sure audit tables are created in DB or not
-   Make sure integration.envers.enabled: true should be TRUEx





# Hibernate Envers


In the context of ORM, database auditing means tracking and logging events related to persistent entities, or simply entity versioning. Inspired by SQL triggers, the events are insert, update, and delete operations on entities. The benefits of database auditing are analogous to those provided by source version control.

Here are the sample related entities, *Bar* and *Foo,* that we'll use in this example:

Hibernate Envers
----------------------------------------------------------------------------

With Hibernate, we can make use of *Interceptors* and *EventListeners,* as well as database triggers, to accomplish auditing. But the ORM framework offers Envers, a module implementing auditing and versioning of persistent classes.

### 1.Get Started With Envers

To set up Envers, we need to add the *hibernate-envers* JAR into our classpath:

```
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-envers</artifactId>
    <version>${hibernate.version}</version>
</dependency>
```

Then we add the *@Audited* annotation, either on an *@Entity* (to audit the whole entity) or on specific *@Column*s (if we need to audit specific properties only):

```
@Entity
@Audited
public class Bar { ... }
```

Note that *Bar* has a one-to-many relationship with *Foo*. In this case, we either need to audit *Foo* as well by adding *@Audited* on *Foo,* or set *@NotAudited* on the relationship's property in *Bar*:

```
@OneToMany(mappedBy = "bar")
@NotAudited
private Set<Foo> fooSet;
```

### 2. Creating Audit Log Tables

There are several ways to create audit tables:

-   set *hibernate.hbm2ddl.auto* to *create*,* create-drop,* or *update*, so Envers can create them automatically
-   use o*rg.hibernate.tool.EnversSchemaGenerator* to export the complete database schema programmatically
-   set up an Ant task to generate appropriate DDL statements
-   use a Maven plugin for generating a database schema from our mappings (such as Juplo) to export Envers schema (works with Hibernate 4 and higher)

We'll go the first route, as it's the most straightforward, but be aware that using *hibernate.hbm2ddl.auto* isn't safe in production.

In our case, the *bar_AUD* and *foo_AUD* (if we've set *Foo* as *@Audited* as well) tables should be generated automatically. The audit tables copy all audited fields from the entity's table with two fields, *REVTYPE *(values are: "0" for adding, "1" for updating, and "2" for removing an entity) and *REV*.

Besides these, an extra table named *REVINFO* will be generated by default. It includes two important fields, *REV* and *REVTSTMP,* and records the timestamp of every revision. As we can guess, *bar_AUD.REV* and *foo_AUD.REV* are actually foreign keys to *REVINFO.REV.*

### 3. Configuring Envers

We can configure Envers properties just like any other Hibernate property.

For example, let's change the audit table suffix (which defaults to "*_AUD*") to "*_AUDIT_LOG.*" Here's how we set the value of the corresponding property *org.hibernate.envers.audit_table_suffix*:

```
Properties hibernateProperties = new Properties();
hibernateProperties.setProperty(
  "org.hibernate.envers.audit_table_suffix", "_AUDIT_LOG");
sessionFactory.setHibernateProperties(hibernateProperties);
```

A full listing of available properties can be found [in the Envers documentation](http://docs.jboss.org/envers/docs/#configuration).

### 4. Accessing Entity History

We can query for historic data in a way similar to querying data via the Hibernate Criteria API.  We can access the audit history of an entity using the *AuditReader* interface, which we can obtain with an open *EntityManager* or *Session* via the *AuditReaderFactory*:

```
AuditReader reader = AuditReaderFactory.get(session);
```

Envers provides *AuditQueryCreator* (returned by *AuditReader.createQuery()*) in order to create audit-specific queries. The following line will return all *Bar* instances modified at revision #2 (where *bar_AUDIT_LOG.REV = 2*):

```
AuditQuery query = reader.createQuery()
  .forEntitiesAtRevision(Bar.class, 2)
```

Here's how we can query for *Bar*'s revisions. It'll result in getting a list of all audited *Bar* instances in all their states:

```
AuditQuery query = reader.createQuery()
  .forRevisionsOfEntity(Bar.class, true, true);
```

If the second parameter is false, the result is joined with the *REVINFO* table. Otherwise, only entity instances are returned. The last parameter specifies whether to return deleted *Bar* instances.

Then we can specify constraints using the *AuditEntity* factory class:

```
query.addOrder(AuditEntity.revisionNumber().desc());
```




