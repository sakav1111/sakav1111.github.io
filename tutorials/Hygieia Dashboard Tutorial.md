
# **1. Introduction to Hygieia**

Hygieia℠ is a single, configurable, easy to use dashboard to visualize near
real-time status of the entire delivery pipeline.



A typical project deals with Agile Project Management tool, Source Control,
Continuous Integration (CI) tool, Testing tools, Static Code Analysis and
Security Scanning tools, Deployment and Monitoring tools to name a few.



Large enterprises and complex systems sometimes use multiple CI, Testing and
Scanning tools. Each of these has nice dashboards to present key information
stored in it. But what is lacking is a single, comprehensive end-to-end view of
the state of a delivery pipeline in near real time.



Hygieia is the solution & provides **One Dashboard for the Entire CI/CD
Pipeline**



**Features**

-   **Team Dashboard**: Monitor things like workitems, code repo, builds,
    quality items (unit tests, security, coverage, etc.), and deployments in one
    dashboard.

-   **Real-time Status:** Monitor quality, productivity, and work in process on
    a real-time basis within the dashboard.

-   **Configurable View:** Set up the dashboard to display the widgets that
    matter most to your project, which helps amplify and shorten the feedback
    loop.

-   **Open Source:**Hygieia is open source and free for everyone to use. We also
    encourage external users to commit to the project.



## **Hyigiea Layers**

There are 3 main Layers of Hygieia Dashboard

1.  UI Layer

2.  API Layer

3.  Collectors Layer



### **1.UI Layer**

This layer is view level layer & displays the Daaashboard content to user.there
are two types of Dashboards



**a.Team Level Dashboard**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-3-1.jpeg](media/983db06bec93b4947e19e994f54f3983.jpg)



**b.Product Level Dashboard**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-12-1.png](media/7da22f7738f5212dc2a5e0b5659c7bbc.png)





### **2.API Layer**

API is work as an **Interface between Collector and UI.**It contains the
Controller classes, which helps to UI which Collector logic has to be execute
whenever request comes from UI.

API module contains following major packages

-   **Request:** this package classes are used to store Request details

-   **Rest** : this package classes contains Controller classes

-   **Service** : this package classes are used for send response to UI





### **3.Collector Layer**

Ecah and every DevOps Tool having their own collector. For example Jenkins tool
having its own collector call it as **Jenkins Build Collector**.The main job of
the collector is, it will collects the Data from DevOps tool and save that data
into the MongoDB

We have following Collectors in the latest version of Hygieia(2.0.4)

-   **Build Collectors:**

    -   [Bamboo](https://github.com/capitalone/Hygieia/blob/master/collectors/build/bamboo)

    -   [Jenkins](https://github.com/capitalone/Hygieia/blob/master/collectors/build/jenkins)

    -   [Jenkins
        Cucumber](https://github.com/capitalone/Hygieia/blob/master/collectors/build/jenkins-cucumber)

    -   [Sonar](https://github.com/capitalone/Hygieia/blob/master/collectors/build/sonar)

-   **Cloud Collectors:**

    -   [AWS](https://github.com/capitalone/Hygieia/blob/master/collectors/cloud/aws)

-   **Deploy Collectors:**

    -   [uDeploy](https://github.com/capitalone/Hygieia/blob/master/collectors/deploy/udeploy)

    -   [XLDeploy](https://github.com/capitalone/Hygieia/blob/master/collectors/deploy/xldeploy)

-   **Feature Collectors:**

    -   [Jira](https://github.com/capitalone/Hygieia/blob/master/collectors/feature/jira)

    -   [VersionOne](https://github.com/capitalone/Hygieia/blob/master/collectors/feature/versionone)

-   **Misc Collectors:**

    -   [Chat
        Ops](https://github.com/capitalone/Hygieia/blob/master/collectors/misc/chat-ops)

-   **SCM Collectors:**

    -   [Bitbucket](https://github.com/capitalone/Hygieia/blob/master/collectors/scm/bitbucket)

    -   [Github](https://github.com/capitalone/Hygieia/blob/master/collectors/scm/github)

    -   [Gitlab](https://github.com/capitalone/Hygieia/blob/master/collectors/scm/gitlab)

    -   [Subversion](https://github.com/capitalone/Hygieia/blob/master/collectors/scm/subversion)





## **How Hygieia Dashboard Works**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-13.png](media/b41ad2bcca1080665b8b54c8dac913b5.png)



1.  **Install & Congigure DevOps** Tools in Hygiea Dashboard

2.  Collectors will **collect the data from the DevOps tool** based on the
    configuration

3.  Collector will **convert the collected data into MongoDB data format**

4.  **Saves the converted data** into MongoDB Collector

5.  UI calls the **RestAPI call to get the Data** from the Database

6.  RestAPI **get the data from database** and returns to UI

7.  UI will **Displays the collected data** in the Dashboard

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-14.png](media/1cde627a892b08ae7571d6b4b7a296f9.png)





# **2. Hygieia Dashboard Setup**

To install & configure Hygieia Dashboard we need to install below Software
Tools.

1.  [Java](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html)

2.  [Apache Maven](http://maven.apache.org/download.cgi)

3.  [Eclipse IDE for Java EE
    Developers](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/heliossr2)

4.  [MongoDB](https://www.mongodb.com/download-center)

5.  [NodeJs](https://nodejs.org/en/download/)

6.  [Git](https://git-scm.com/downloads)

7.  [RoboMongo](https://robomongo.org/download) **(or)** [Admin
    Mongo](https://github.com/mrvautin/adminMongo)



### **2.1 Install Java & Set JAVA_HOME**

1.  Download Java JDK from [Oracle Official
    website](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html)
    **& Install It.**



1.  Configure the**JAVA_HOME** Windows environment variables by specifying Java
    Installation Location

**Control Panel → User Accounts → User Accounts → Change my
EnvironmentVariables**

![http://localhost:6666/sml/wp-content/uploads/2017/02/hygieia-1.png](media/afd3a40757814642ab146fe78066b76b.png)



3.  Also update the**PATH** variable with bin location

    ![](media/98d6ccf11afc1d307c397f33dca6dfac.png)

    




### **2.2 Install Apache Mavem & Set MAVEN_HOME**

**1.Download Apache Maven**

Go to[Maven official website](http://maven.apache.org/download.cgi), download
the Maven zip file, Unzip It.



2.Configure the**JAVA_HOME** Windows environment variables by specifying Java
Installation Location

**Control Panel → User Accounts → User Accounts → Change my
EnvironmentVariables**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-17.png](media/e6774e47f0cf83bc65bf4a790c607e10.png)



3.Addboth**M2_HOME**and**MAVEN_HOME** as Windows environment variable &
point it to Maven folder

![http://localhost:6666/sml/wp-content/uploads/2017/02/mvn.png](media/698f5befdcd0cc671582b138095a717a.png)



4.Update**PATH**variable, append Maven bin folder –**%M2_HOME%\\bin**, so
that you can run the Maven’s command everywhere.

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-19.png](media/5b980e3853bfc4a51d0f0a4c94ec3f8f.png)



5.Verify Maven is installed properly or not by **mvn –version** command
```dos
mvn -version" >>mvn -version

Apache Maven 3.0.5 (...)
Maven home: ...
Java version: 1.8.0_60, vendor: Oracle Corporation
Java home: ...
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 7", version: "6.1", arch: "amd64", family: "dos"
```






**6.Change PROXY Settings**  
If you are behind a proxy, Maven will fail to download any dependencies.in this
type of situation we have to declare the proxy server setting in Maven
configuration filesettings.xml.

Find **{M2_HOME}/conf/settings.xml,** and put your proxies detail inside.
Un-comment the proxy options and fill in your proxy server detail.
```xml
<!-- proxies
   | This is a list of proxies which can be used on this machine to connect to the network.
   |-->
  <proxies>
      <proxy>
      <id>optional</id>
      <active>true</active>
      <protocol>http</protocol>
      <username>smlcodes</username>
      <password>password</password>
      <host>proxy.smlcodes.com</host>
      <port>8888</port>
      <nonProxyHosts>local.net|some.host.com</nonProxyHosts>
    </proxy>
  </proxies>
```

### **2.3 Configure Apache Maven in Eclipse**

**1.Download**[Eclipse IDE for Java EE
Developers](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/heliossr2)
**, Extract and Run
Eclipse**[](http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-21.png)



**2.Copy maven installation path (Ex. C:\\apache-maven-3.3.9)**

**Window → preferences → Maven → Installations == add maven path**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-21.png](media/141f62f500805236a27f18071c833eea.png)

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-22.png](media/fd26df4e09ebfb9ed9a7811aee800ad1.png)





**3.Change Settings.xml file path**

**Window → preferences → Maven → User Settings**





### **2.4 MongoDB Installation**

Know your PC Architecture, Open Command prompt and run below command

**C:>wmic os get osarchitecture**



**1.Download MongoDB**

There are three builds of MongoDB for Windows:

-   MongoDB for Windows Server 2008 R2 edition [[Download
    link](https://fastdl.mongodb.org/win32/mongodb-win32-x86_64-2008plus-2.6.1.zip)]

-   MongoDB for Windows 64-bit [[Download
    link](https://fastdl.mongodb.org/win32/mongodb-win32-x86_64-2.6.1.zip)]

-   MongoDB for Windows 32-bit [[Download
    link](https://fastdl.mongodb.org/win32/mongodb-win32-i386-2.6.1.zip)]



**2.Extract and create Folders**  
download zip files which you extract directly onto any place in your system of
your choice.I have extracted them in **“d:/mongodb“.**

Also,**create following directories**inside d:/mongodb

-   D:\mongodb\data

-   D:\mongodb\log



**3.Path and Installation**

**Go to Control Panel →User Accounts →Change my Environment Variables →Add below
Variables**

[MongoDB =
D:\\mongodb](http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-24.png)

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-24.png](media/1dd8ecf81bce2ee84f41e0f2c4ae65a8.png)

```xml
D:\mongodb\bin
```


![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-25.png](media/ebbca54f6fc3d4821034c859562557ba.png)



**4.Create a MongoDB config file**

Create new file,add below content to mongo.config in given location for
```plaintext
//Ex:d:\\mongodb\\mongo.config

##store data here
dbpath=D:\mongodb\data

##all output go here
logpath=D:\mongodb\log\mongo.log

##log read and write operations
diaglog=3
```


**5.Start/Shutdown the MongoDB Server**

**Start**: Run below command in command prompt
```plaintext
•	d:\mongodb\bin>mongod –dbpath=D:/mongodb    (or)
•	d:\mongodb\bin>mongod.exe --config="D:\mongodb\mongo.config"
```
![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-26.png](media/90942c024908bdfa68e3d876232db41d.png)



**6.To run Commands on MongoDB**  
Open new commandline run mongo command


**7.Create an User by running below commands on Mongo**
```plaintext
db.createUser(
                {
                  user: "dashboarduser",
                  pwd: "dashboarduser123",
                  roles: [
                     {role: "readWrite", db: "dashboarddb"}
                          ]
                  })
```

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-29.png](media/59b9c94fd3f6aa294c9e4eb8d701d57b.png)

**Now we are SuccessfullyConfigured MongoDB**





### **2.5 Install Git**

[Download & Install Git Client](https://git-scm.com/downloads) , add Update PATH
variable

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-30.png](media/94e39463ee3daa9463b850e000becfb4.png)





### **2.5 Install Node.Js**

1.Download & Install Node.js from [Nodejs Official
website](https://nodejs.org/en/download/)

2.Update **Path** variable with installed nodejs location

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-31.png](media/622b3d898d84d1fd161fab7b4c1df21b.png)



3.Open command Prompt run below commands one by one
```plaintext
npm install -g bower
npm install -g gulp
```



4.If you are under the Proxy, then configure proxy settings in below files

-   Run **"notepad .npmrc"** and enter your proxy details
    ```plaintext
    proxy         = http://yourproxyservername:80/
    https-proxy  = http://yourproxyservername:80/ strict-ssl = false
    ```


-   Run **"notepad .gitconfig"**, and enter your proxy details
    ```plaintext
    [http] proxy = http://yourproxyservername:80/
    ```




-   Run **"notepad .bowerrc"** and enter your proxy details
    ```plaintext
    {
    "proxy":"http://yourproxyservername:80/",
    "https-proxy":"http:// http://yourproxyservername:80"
    }
    ```


-   If you are not foung npm folder in
    **"C:\\Users\\YOUR_USER_Name\\AppData\\Roaming"**, then we have to manually
    create "npm" folder under

**for this error : 'bower' is not recognized as an internal or external
command**

Go to C:\\Users\\USER_NAME\\AppData\\Roaming\\npm, Copy this location add to
**PATH** variable



### **2.6 RoboMongo (or) Admin Mongo**

**2.6.1 RoboMongo**

Robomongo is a shell-centric cross-platform MongoDB management tool. Unlike most
other MongoDB admin UI tools, Robomongo embeds the actual mongo shell in a
tabbed interface with access to a shell command line as well as GUI interaction.

We can [Download the RoboMongo from here](https://robomongo.org/download) , you
can install it as easy as normal windows software



**2.6.2 Admin Mongo**

adminMongo is a cross platform user interface (GUI) to handle all your MongoDB
connections/databases needs. adminMongo is fully responsive and should work on a
range of devices.You can [download
adminMongo](https://github.com/mrvautin/adminMongo) from Github.

**Installation**

1.  Navigate to folder & install adminMongo: git clone
    https://github.com/mrvautin/adminMongo.git && cd adminMongo

2.  Install dependencies:npm install

3.  Start application:npm startornode app

4.  Visit[http://127.0.0.1:1234](http://127.0.0.1:1234/)in your browser





# **3. Configure Hygieia Dashboard**

**Download Hygieia Source code from**
[Github](https://github.com/capitalone/Hygieia/releases) (Hygieia 2.0.4 release
)

<https://github.com/capitalone/Hygieia/releases/>





## **3.1 Run Maven Build**

**1.Update Settings.xml** file at **C:\\apache-maven-3.3.9\\conf if you are
under proxy**
```xml
<proxies>
              <proxy>
               <id>your-proxy-id</id>
               <active>true</active>
               <protocol>http</protocol>
               <host>your.proxy.domain.name</host>
               <port>8080</port>
               <!-- For authenticated proxy, please set the following, as well -->
               <username>companyId999</username>
               <password>yourPassword</password>
               <nonProxyHosts>*.local</nonProxyHosts>
       </proxy>
</proxies>
```



**2.Extract Hygieia-xxx.zip and Open. Make sure it contains following files**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-32.png](media/4d231c0a199fbf6f94efd8b3275aaadd.png)



**3.Open command prompt and go to Hygieia folder and run one of the below mvn
cmds**
```dos
mvn clean install package

(or)

mvn clean install package -Dmaven.test.skip=true

(or –for Skipping PDM Violations)

mvn clean install package -Dmaven.test.skip=true -Dpmd.failOnViolation=false
```

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-33.png](media/5eaa80b2e6a6495228171ccc4b252316.png)





## **3.2 Install Bower & npm Packges on UI Folder**

1.Open **Hygieia\\UI** on command line

2.Issue the following commands via command line:
```dos
npm install -g bower
npm install -g gulp
```


3.Navigate to your project root via command line and use the following command:
```dos
npm install
```


Use Git Shell to install bower in the following manner; do so from your
project's root directory:
```dos
bower install
```


select option 2 when prompted for user input







4.Start the UI by running **gulp serve** on **Hygieia\\UI folder.**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-34.png](media/8793341debfdaa6b581825bfa4be03df.png)



**5.Open** <http://localhost:3000/> **from browser, it will display the
Dashboard**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-35.png](media/743cd590f71f3735f5d5667df3e86ed2.png)

**If we see above API is not connected. To connect with API we must run the api
project. To do that we need to import the code to Eclipse. Follow below steps to
do so.**







## **3.3 Import code to Eclipse**

**1.Open Eclipse IDE**

**2.File → Import → Maven→ Existing Maven Projects, browse Downloaded Hygieia
folder→ Finish**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-36.png](media/f3394acce22fd1d9f0e3b6fba19eeea8.png)



**3.we will get Project structure as below like below**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-37.png](media/6913837f4a5156ac25a9e083a3e96cee.png)



**4.Update Maven Project by Selecting all → Right click → Maven Update → Ok**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-38.png](media/4b10da28bd6054eb579d2642ea5839f0.png)

**Now We are successfully Setup all the thing which are required for Running
Hygieia Dashboard.So Let’s Move next step to Running Hygieia Dashboard**







## **3.4 Steps to Running Hygieia Dashboard**



**3.4.1 Start MongoDB**

**First, we need to start MongoDB Database and Make sure it is Running**




Check MongoDB is Started or not, by running mongo command on command-prompt

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-40.png](media/57bda68654807208f8196984ea770b21.png)

**Blinking cursor** means it is running.





**3.4.2 Start “api"**

Before we starting API we need add **dashboard.properties file**, change
**application.propeties** file as per our requirement.

**1.dashboard.properties**(Path: Hygieia\\api\\dashboard.properties)
```dockerfile
dbname=dashboarddb
#dbusername=[MogoDb Database Username, defaults to empty]
#dbpassword=[MongoDb Database Password, defaults to empty]
dbhost=localhost
dbport=27017
```



**2.application.properties**(Path:
Hygieia\\api\\src\\main\\resources\\application.properties)
```dockerfile
# Default server configuration values
dbname=dashboard
#dbusername=dashboarduser
#dbpassword=1qazxSw2
dbhost=127.0.0.1
dbport=11500
server.contextPath=/api
server.port=8080
```


**3.Configure api run configurations by going,**

**api project → Go to src → com.capitalone.dashboard.Application.java
→Rightclick→Run Configuartions → Java Application → go to arguments → program
args : add**
```dockerfile
--spring.config.location=dashboard.properties
```



**4.Apply and Run the Application.**
```dos
.   ____          _            __ _ _
 \\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.2.5.RELEASE)
2017-02-23 17:53:51,243 INFO  com.capitalone.dashboard.Application - Starting Application on Local with PID 5580 (C:\DevOps\Hygieia\api\target\classes started by smlcodes in C:\DevOps\Hygieia\api)
2017-02-23 17:53:51,468 INFO  o.s.b.c.e.AnnotationConfigEmbeddedWebApplicationContext - 
2017-02-23 17:54:02,632 INFO  o.s.b.c.e.t.TomcatEmbeddedServletContainer - Tomcat initialized with port(s): 8080 (http)
2017-02-23 17:54:03,707 INFO  o.a.catalina.core.StandardService - Starting service Tomcat
2017-02-23 17:54:21,068 INFO  o.s.b.c.e.t.TomcatEmbeddedServletContainer - Tomcat started on port(s): 8080 (http)
2017-02-23 17:54:21,074 INFO  com.capitalone.dashboard.Application - Started Application in 30.837 seconds (JVM running for 35.29)
```


If we see the message “Tomcat started on port(s): 8080 (http)" means api is
running successfully





**3.4.3 Start “UI"**

For Starting UI, Open nodejs command prompt, navigate to UI folder location &
run **gulp serve** command

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-42.png](media/3e21b2e7dd4bbd96d8ad2578b2ab1b55.png)



Hygieia people giving some sample Mock data to visualize dashboard. So for demo
purpose i'm running gulp serve with mock data. For doing this we need run below
command on UI folder

**gulp serve --local true**

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-43.png](media/cb05d649f5c6a481ebcdfcb4a8259a88.png)



**Steps to See Hygieia Dashboard with Mock Data**

1.Open <http://localhost:3000/#/>

2.**Signup** with username & password (minimum 6 characters); Ex: admin & 123456

3.**Login** with username & password

4.It will Display the dashboard homepage as below

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-44.png](media/274143224efa618fd9eeb301be1a53f2.png)



5.Select on any Dashboard, it will displays that Dashboard data

![http://localhost:6666/sml/wp-content/uploads/2017/02/Hygieia_tutorial_setup_smlcodes-45.png](media/6a302ea83b9ecaf26068bc330ff1fb97.png)



**Now we just run the Hygieia Dashboard with some Demo Data. If you want to run
the Dashboard with real data, we need configure & run our own Collectors. Next
step will explain how to configure and run any collector in hygieia dashboard**





**3.5 Steps to Configuring Any Collector**

We have many collectors in hygiea like Bamboo, Jenkins,Sonar etc.,.but don’t
worry! We can easily configure any hygieia collector because all collectors
follows same steps. We need do some minor changes in application.properties
file. That’s it

**1.Select a collector (for Ex: jenkins-build-collector)**

**2.Rightclick on Project →Create → new File → application.properties**

**3.Copy** [sample application.properties file data from
Github](https://github.com/capitalone/Hygieia/tree/master/collectors/build/jenkins)
**file to local application.properties & change the details as per our
requirement**
```dockerfile
#Database Name 
dbname=dashboarddb

#Database HostName - default is localhost
#dbhost=localhost

#Database Port - default is 27017
#dbport=9999

#Database Username - default is blank
#dbusername=db

#Database Password - default is blank
#dbpassword=dbpass

jenkins.servers[0]=http://localhost:8181/jenkins/
#Collector schedule (required)
jenkins.cron=0 0/1 * * * *

#Jenkins server (required) - Can provide multiple

#If using username/token for api authentication (required for Cloudbees Jenkins Ops Center) see sample
#jenkins.servers[1]=http://username:token@jenkins.company.com

#Another option: If using same username/password Jenkins auth - set username/apiKey to use HTTP Basic Auth (blank=no auth)
#jenkins.username=
#jenkins.apiKey=

#Determines if build console log is collected - defaults to false
jenkins.saveLog=true
```

**4.Right click on Project → Run As → Run configurations → Run
Applciation.java**

5.Open → Hygieia dashboard on webpage

6.Click on **Configure Build Widget → select Jenkins/Hudson →save**

**7.It will display Jenkins data**



### References



<https://github.com/capitalone/Hygieia>

<https://developer.capitalone.com/opensource-projects/hygieia>






