---
title: AngularJs - Interview Questions
date: 2017-11-11 00:00:00 Z
categories:
- Interview
tags:
- Interview
layout: article
cover: /assets/logo/angular.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

AngularJs
=========

Note : we must write module, if we used ng-app
```html
{% raw %}
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<div ng-app="myApp" ng-controller="myCtrl">

First Name: <input type="text" ng-model="firstName"><br> //binds input with model
Last Name: <input type="text" ng-model="lastName"><br> ////binds input with model
Full Name: \"{{firstName + " " + lastName}}\" //Prints the model values
//same as {{expression}}
<p ng-bind="firstName"></p>
<p ng-bind=" lastName "></p>

</div>

<script>
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope) {
    $scope.firstName= "John";
    $scope.lastName= "Doe";
    $scope.fullName = function() {
      return $scope.firstName + " " + $scope.lastName;
  };
});
</script>
{% endraw %}
```


## Internal Working

-   The **AngularJS JavaScript** file is loaded, and the Angular global object
    $scope  is created. The JavaScript file that registers the controller
    functions is executed.

-   AngularJS scans the HTML to look for **AngularJS apps and views** and finds
    **a controller function corresponding to the view.**

-   AngularJS **executes the controller functions and updates the views** with
    data from the model populated by the controller.

-   AngularJS listens for browser events, such as button clicked, mouse moved,
    input field being changed, and so on. If any of these events happen, then
    AngularJS will update the view accordingly

More

displays only the names containing the letter "i".
```html
{% raw %}
<li ng-repeat="x in names | filter : 'i'">
    {{ x }}
  </li>

<li ng-repeat="x in names | orderBy:'country'">
    {{ x.name + ', ' + x.country }}
  </li>
{% endraw %}  
```


## AngularJS Services

-   In AngularJS, a service is a **function, or object**, that is available for
    your AngularJS application.

-   AngularJS has about 30 built-in services. One of them is the $location
    service.

1.$location

returns information about the location of the current web page:
```html
var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $location) {
    $scope.myUrl = $location.absUrl();
});
{{myUrl}} //prints https://www.w3schools.com/angular/tryit.asp?filename=try_ng_services
```



2.$http 

**$http** is an AngularJS service for reading data from remote servers. The
AngularJS $http service makes a request to the server, and returns a response.
```html
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
  $http.get("welcome.htm").then(function (response) {
    $scope.myWelcome = response.data;
  });
});
```


More on Http

The example above uses the .get method of the $http service.

The .get method is a shortcut method of the $http service. There are several
shortcut methods:

-   .get()

-   .post()

-   .put()

-   .delete()

-   .head()

-   .jsonp()

-   .patch()

```html
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
  $http({
    method : "GET",
      url : "welcome.htm"
  }).then(function mySuccess(response) {
    $scope.myWelcome = response.data;
  }, function myError(response) {
    $scope.myWelcome = response.statusText;
  });
});
```


## AngularJS Events

You can add AngularJS event listeners to your HTML elements by using one or more
of these directives:

-   ng-blur

-   ng-change

-   ng-click

-   ng-copy

-   ng-cut

-   ng-dblclick

-   ng-focus

-   ng-keydown

-   ng-keypress

-   ng-keyup

-   ng-mousedown

-   ng-mouseenter

```html
<div ng-app="myApp" ng-controller="myCtrl">
        <button ng-click="myFunction()">Click me!</button>
        <p>{{ count }}</p>
</div>

<script>
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope) {
  $scope.count = 0;
  $scope.myFunction = function() {
    $scope.count++;
  }
});
</script>
```

## AngularJS Routes

If you want to navigate to different pages in your application, but you also
want the application to be a SPA (Single Page Application), with no page
reloading, you can use the ngRoute module.

```html
var app = angular.module('userregistrationsystem', [ 'ngRoute', 'ngResource' ]);

app.config(function($routeProvider) {
	$routeProvider.when('/list-all-users', {
 templateUrl : '/template/listuser.html',
 controller : 'listUserController'
	}).when('/register-new-user',{
 templateUrl : '/template/userregistration.html',
 controller : 'registerUserController'
	}).when('/update-user/:id',{
 templateUrl : '/template/userupdation.html' ,
 controller : 'usersDetailsController'
	}).otherwise({
 redirectTo : '/home',
 templateUrl : '/template/home.html',
	});
});
```

## Name the key features of AngularJS?

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

## Can AngularJS have multiple ng-app directives in a single page?

**Yes, But** Only one AngularJS application can be auto-bootstrapped per HTML
document. The first ngApp found in the document will be used to define the root
element to auto-bootstrap as an application.

If another ng-app directive has been placed then it will not be processed by
AngularJS and we will need to manually bootstrap the second app, instead of
using second ng-app directive.

## Explain the architecture of AngularJS?

AngularJS is architecture on 3 components. They are

-   The Template (View)

-   The Scope (Model)

-   The Controller (Controller)

## Explain Directives in AngularJs?

AngularJS directives are extended HTML attributes with the prefix ng-  
The 3 main directives of angular js are

-   **ng-app:-**directive is used to flag the HTML element that Angular should
    consider to be the root element of our application. Angular uses spinal-case
    for its custom attributes and camelCase for the corresponding directives
    which implement them.

-   **ng-model:-** directive allows us to bind values of HTML controls (input,
    select, textarea) to application data. When using ngModel, not only change
    in the scope reflected in the view, but changes in the view are reflected
    back into the scope.

-   **ng-bind:-**directive binds application modal data to the HTML view.

-   **ng-**controller

-   **ng-**view

## Explain AngularJS digest cycle?

AngularJS digest cycle is the process behind Angular JS data binding.  
**In each digest cycle, Angular compares the old and the new version of the
scope model values**. The digest cycle is triggered automatically. We can also
use $apply() if we want to trigger the digest cycle manually.

## What is data binding in AngularJS and What is the difference between one-way and two-way binding?

Data binding is the automatic attunement of data between the view and model
components. AngularJS uses two-way data binding. In one-way binding, the scope
variable in the html is set to the first value that its model is assigned to.

In two-way binding, the scope variable changes its value every time its model
binds to a different value. 

## Explain what a digest cycle is in AngularJS?

During every digest cycle, all new scope model values are compared against the
previous values. This is called dirty checking. If change is detected, watches
set on the new model are fired and another digest cycle executes. This goes on
until all models are stable. 

The digest cycle is triggered automatically but it can be called manually using
**“.$apply()".**

## What is Single Page Application (SPA)? 

SPA is the concept whereby pages are loaded from the server not by doing post
backs, rather by creating a single shell page or master page and loading the web
pages into the master page.

## How can SPA be implemented in AngularJS?

SPA can be implemented with Angular by using Angular routes
