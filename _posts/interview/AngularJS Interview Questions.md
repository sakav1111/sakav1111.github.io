AngularJs
=========

###### Name the key features of AngularJS?

The key features of AngularJS are:

-   Scope

-   Controller

-   Model

-   View

-   Services

-   Data Binding

-   Directives

-   Filters

-   Testable

###### Can AngularJS have multiple ng-app directives in a single page?

No. Only one AngularJS application can be auto-bootstrapped per HTML document.
The first ngApp found in the document will be used to define the root element to
auto-bootstrap as an application. If another ng-app directive has been placed
then it will not be processed by AngularJS and we will need to manually
bootstrap the second app, instead of using second ng-app directive.

###### Explain the architecture of AngularJS?

AngularJS is architecture on 3 components. They are

-   The Template (View)

-   The Scope (Model)

-   The Controller (Controller)

###### Explain Directives in AngularJs?

AngularJS directives are extended HTML attributes with the prefix ng-  
The 3 main directives of angular js are

-   **ng-app:- **directive is used to flag the HTML element that Angular should
    consider to be the root element of our application. Angular uses spinal-case
    for its custom attributes and camelCase for the corresponding directives
    which implement them.

-   **ng-model:-** directive allows us to bind values of HTML controls (input,
    select, textarea) to application data. When using ngModel, not only change
    in the scope reflected in the view, but changes in the view are reflected
    back into the scope.

-   **ng-bind:- **directive binds application modal data to the HTML view.

-   **ng-**controller

-   **ng-**view

###### Explain AngularJS digest cycle?

AngularJS digest cycle is the process behind Angular JS data binding.  
In each digest cycle, Angular compares the old and the new version of the scope
model values. The digest cycle is triggered automatically. We can also use
\$apply() if we want to trigger the digest cycle manually.

###### What is data binding in AngularJS and What is the difference between one-way and two-way binding?

Data binding is the automatic attunement of data between the view and model
components. AngularJS uses two-way data binding. In one-way binding, the scope
variable in the html is set to the first value that its model is assigned to.

In two-way binding, the scope variable changes its value every time its model
binds to a different value. 

###### Explain what a digest cycle is in AngularJS?

During every digest cycle, all new scope model values are compared against the
previous values. This is called dirty checking. If change is detected, watches
set on the new model are fired and another digest cycle executes. This goes on
until all models are stable. 

The digest cycle is triggered automatically but it can be called manually using
“.\$apply()”.

###### What is Single Page Application (SPA)? 

SPA is the concept whereby pages are loaded from the server not by doing post
backs, rather by creating a single shell page or master page and loading the web
pages into the master page.

###### How can SPA be implemented in AngularJS?

SPA can be implemented with Angular by using Angular routes

JUnit
=====

###### How to create Parameterized tests?

Answer:  
There are five steps to create Parameterized tests−

-   First, test class is annotated with \@RunWith which is a
    Parameterized.class.

-   Then create a public static method which is annotated with \@Parameters. it
    returns a Collection of Objects as test data set.

-   Next, create a public constructor which takes in one row of test data.

-   Create an instance variable that is for each column of the test data row.

-   Create tests case(s) using the instance variables as a source of the test
    data.

-   The test case invokes once per each row of data.

###### What are JUnit classes? List some of them?

JUnit classes are important classes which are used in writing and testing
JUnits. Some of the important classes are:

-   Assert – A set of assert methods.

-   Test Case – It defines the fixture to run multiple tests.

-   Test Result – It collects the results of executing a test case.

-   Test Suite – It is a Composite of Tests.

MongoDB vs SQL
==============

| **SQL Terms/Concepts** | **MongoDB Terms/Concepts**                                                                                                                                      |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| database               | [database](https://docs.mongodb.com/manual/reference/glossary/#term-database)                                                                                   |
| table                  | [collection](https://docs.mongodb.com/manual/reference/glossary/#term-collection)                                                                               |
| row                    | [document](https://docs.mongodb.com/manual/reference/glossary/#term-document) or [BSON](https://docs.mongodb.com/manual/reference/glossary/#term-bson) document |
| column                 | [field](https://docs.mongodb.com/manual/reference/glossary/#term-field)                                                                                         |
| index                  | [index](https://docs.mongodb.com/manual/reference/glossary/#term-index)                                                                                         |
| table joins            | [\$lookup](https://docs.mongodb.com/manual/reference/operator/aggregation/lookup/#pipe._S_lookup), embedded documents                                           |
| primary key            | [primary key](https://docs.mongodb.com/manual/reference/glossary/#term-primary-key)                                                                             |

>   Specify any unique column or column combination as primary key.

>   In MongoDB, the primary key is automatically set to
>   the [\_id](https://docs.mongodb.com/manual/reference/glossary/#term-id)field.

<https://docs.mongodb.com/manual/reference/sql-comparison/>

**Read this, Don’t forget important**

Views & Joins
