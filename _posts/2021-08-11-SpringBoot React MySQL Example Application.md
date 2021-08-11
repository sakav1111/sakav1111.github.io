---
date: "2021-08-11 01:00:00 Z"
title: Spring Boot - Spring Boot + React + MySQL CRUD example
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





# Spring Boot - Spring Boot + React + MySQL CRUD example

We will build two projects:

1.  **sprint boot-backend (server)**   To develop REST API

2.  **react-frontend (client)**   Consume REST API

![](media/18d9e4326f69449d87b95826e11713d0.png)

<br>


## SpringBoot   Employee Backend Service

![](media/989923c7a07b93e08a5cae2a121a3e50.png)


<br>


### Configuring MySQL Database

application.properties

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
spring.datasource.url=jdbc:mysql://localhost:3306/webapp?useSSL=false
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto = update
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  `employee` (
	`id` (11)   ,
	`name` (50)    '0'  'latin1_swedish_ci',
	`address` (50)    '0'  'latin1_swedish_ci',
	`salary`    '0',
	  (`id`)  
);
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


#### Model class   Employee.java

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ java
 springboot.emp.model;

 javax.persistence.Column;
 javax.persistence.Entity;
 javax.persistence.GeneratedValue;
 javax.persistence.GenerationType;
 javax.persistence.Id;
 javax.persistence.Table;

@Entity
@Table
  Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.)
	 Long id;
	
	@Column
	 String name;
	
	@Column
	 String address;
	
	@Column
	 Double salary; 
	
	
	 Employee(Long id, String name, String address, Double salary) {
		();
		.id = id;
		.name = name;
		.address = address;
		.salary = salary;
	} 
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


#### Repository   EmployeeRepository.java

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ java
 springboot.emp.repository;

 org.springframework.data.jpa.repository.JpaRepository;
 org.springframework.stereotype.Repository;

 springboot.emp.model.Employee;

@Repository
  EmployeeRepository  JpaRepository<Employee, Long>{

}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


#### Exception   EmployeeException

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ java
 springboot.emp.exception;

  EmployeeException  RuntimeException{
	 EmployeeException(String message) {
		(message);
	}
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


#### Exception   EmployeeController

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ java
 springboot.emp.controller;

 java.util.List;
@RestController
@RequestMapping("/api/v1/")
  EmployeeController {
	
	@Autowired
	 EmployeeRepository repository;
	
	@GetMapping("/all")
	 List<Employee> getAllEmployees(){
		 repository.findAll();
	}
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


#### Test Rest API

<http://localhost:8080/api/v1/all>

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
[
  {
    "name": "DILEEP",
    "address": "XXXXXXXXX",
    "salary": 0
  },
  {
    "name": "DILEEP",
    "address": "BANGLORE",
    "salary": 0
  }
]
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


<br>


## React   Employee Front End

#### 1.Create EmployeeReactApp   Empty Repo

Open Visual Studio Code \> Terminal

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  employee-react-app
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


Start react app

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
cd employee-react-app
npm start
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


You can now view employee-react-app in the browser.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  Local:            http://localhost:3000
  On Your Network:  http://192.168.0.105:3000
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/83661e3b906ecf4997260f0540165725.png)

<br>


<br>


**Main files**

-   public/**index.html**   Single page appl €™n , contains `<div
    id="root"></div>`

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ html
<!DOCTYPE  html>
<html  lang="en">
    <head>
        <meta  charset="utf-8"  />
        <link  rel="icon"  href="%PUBLIC_URL%/favicon.ico"  />
        <meta  name="viewport"  content="width=device-width,  initial-scale=1"  />
        <meta  name="theme-color"  content="#000000"  />
        <meta
            name="description"
            content="Web  site  created  using  create-react-app"
        />
        <link  rel="apple-touch-icon"  href="%PUBLIC_URL%/logo192.png"  />          
        <link  rel="manifest"  href="%PUBLIC_URL%/manifest.json"  />
        <title>React  App</title>
    </head>
    <body>
        <noscript>You  need  to  enable  JavaScript  to  run  this  app.</noscript>
        <div  id="root"></div>

    </body>
</html>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


-   src/**App.js** - Contains Component code to generate UI.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ javascript
import  logo  from  './logo.svg';
import  './App.css';

function  App()  {
    return  (
        <div  className="App">
            <header  className="App-header">

            <h1>Hello,  World!!!</h1>

            </header>
        </div>
    );
}

export  default  App;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


-   src/**index.js** - get code from component & replace div content to render
    UI.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ javascript
import  React  from  'react';
import  ReactDOM  from  'react-dom';
import  './index.css';
import  App  from  './App';
import  reportWebVitals  from  './reportWebVitals';

ReactDOM.render(
    <React.StrictMode>
        <App  />
    </React.StrictMode>,
    document.getElementById('root')
);

//  If  you  want  to  start  measuring  performance  in  your  app,  pass  a  function
//  to  log  results  (for  example:  reportWebVitals(console.log))
//  or  send  to  an  analytics  endpoint.  Learn  more:  https://bit.ly/CRA-vitals
reportWebVitals();
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


#### 3. Get All Employees   React Code 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
employee-react-app\src\component\

import  React,  {  Component  }  from  'react';
import  EmployeeService  from  '../services/EmployeeService';

class  ListAllEmployeesComponent  extends  Component  {

        constructor(props)  {
                super(props)

                this.state  =  {
                                employees:  []
                }
              
        }

        componentDidMount(){
                EmployeeService.getEmployees().then((res)  =>  {
                        this.setState({  employees:  res.data});
                });
        }

        render()  {
                return  (
                        <div>
                                <table  className="table">
                                        <thead>
                                                <tr>
                                                        <th  scope="col">ID</th>
                                                        <th  scope="col">Name</th>
                                                        <th  scope="col">Address</th>
                                                        <th  scope="col">Salary</th>
                                                        <th  scope="col">Action</th>
                                                </tr>
                                        </thead>
                                        <tbody>
                                                                {
                                                                        this.state.employees.map(
                                                                                employee  =>  
                                                                                <tr  key  =  {employee.id}>
                                                                                        
                                                                                          <td>  {employee.name}  </td>      
                                                                                          <td>  {employee.address}</td>
                                                                                          <td>  {employee.salary}</td>
                                                                                          <td>
                                                                                                  Action
                                                                                          </td>
                                                                                </tr>
                                                                        )
                                                                }
                                                        </tbody>
                                </table>
                        </div>
                );
        }
}
export  default  ListAllEmployeesComponent;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
employee-react-app\src\services\

import  axios  from  'axios';
const  EMPLOYEE_API_BASE_URL  =  "http://localhost:8080/api/v1/all";
class  EmployeeService  {
        getEmployees()  {
                return  axios.get(EMPLOYEE_API_BASE_URL);
        }
}
export  default  new  EmployeeService()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


Now if we open Root URL, it will display the Table Data.

[http://localhost:3000![](media/6d9544303633dfef93ecc79b83b29587.png)](http://localhost:3000#)

<br>


<br>


#### 3. Header & Footer

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
employee-react-app\src\component\

import  React,  {  Component  }  from  'react';

class  HeaderComponent  extends  Component  {
        render()  {
                return  (
                        <div>
                                <nav  class="navbar  navbar-dark  bg-dark"  >

                                <div  className="container">  
                                        <h3  style={{'color':'white'}}>EmployeeServices „¢  </h3>
                                </div>
                                          
                                </nav>
  
                        </div>
                );
        }
}
export  default  HeaderComponent;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
employee-react-app\src\component\

import  React,  {  Component  }  from  'react';

class  FooterComponent  extends  Component  {
        render()  {
                return  (
                        <div>
                                <footer  className="footer">
                                        <div  className="container">
                                                <span  className="text-muted">
                      ©  SatyaCodes  2020  ,  Satya  Kaveti's  Writing.
                  </span>
                                        </div>
                                </footer>
                        </div>
                );
        }
}
export  default  FooterComponent;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
employee-react-app\src\

import  './App.css';
import  HeaderComponent  from  './component/HeaderComponent';
import  FooterComponent  from  './component/FooterComponent';
import  ListAllEmployeesComponent  from  './component/ListAllEmployeesComponent';

function  App()  {
    return  (

        <div>
            </>
        <div  className="container">  
                </>
        </div>
        </>
        </div>
    );
}
export  default  App;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

![](media/6480c8c35cd0a8c897e916aab08b54a3.png)

<br>


<br>


## Routing

Install `react-router-dom`

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
npm install react-router-dom --save
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ javascript
import  './App.css';
import  HeaderComponent  from  './component/HeaderComponent';
import  HomeComponent  from  './component/HomeComponent';
import  FooterComponent  from  './component/FooterComponent';
import  ListAllEmployeesComponent  from  './component/ListAllEmployeesComponent';
import  {  BrowserRouter,  Route,  Switch  }  from  'react-router-dom';
import  Employee  from  './component/Employee';
function  App()  {
    return  (

        <div>
            <HeaderComponent  />

            <BrowserRouter>
                <div  className="container">
                    <Switch>
                        	<Route  exact  path="/"  component={HomeComponent}></Route>
                        	<Route  exact  path="/one"  component={Employee}></Route>
                      	<Route  exact  path="/all"  component={ListAllEmployeesComponent}></Route>
                        	<Route  component={Error}  />
                    </Switch>
                </div>
            </BrowserRouter>

            <FooterComponent  />
        </div>
    );
}
export  default  App;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<br>


**Complete Code. Github**

[**https://github.com/smlcodes/Books-Sync-Gitlab/tree/main/Codes/employee-react-app**](https://github.com/smlcodes/Books-Sync-Gitlab/tree/main/Codes/employee-react-app)

<br>




## Ref.

<https://github.com/RameshMF/ReactJS-Spring-Boot-CRUD-Full-Stack-App>

<https://www.javaguides.net/2020/07/spring-boot-react-js-crud-example-tutorial.html>

<https://www.youtube.com/watch?v=n43h1eJ2EUE&list=PLGRDMO4rOGcNLnW1L2vgsExTBg-VPoZHr>
