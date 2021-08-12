---
title: IBM MySQL Datasource configuration & Example
date: 2021-07-20 00:00:00 Z
categories:
- Java
tags:
- Java
layout: article
cover: /assets/logo/java.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

# IBM MySQL Datasource configuration & Example

Creating a data source involves the following processes:

1.  [Creating a JAAS J2C
    Authentication](https://docs.kony.com/6_5/konylibrary/sync/kmf_sync_installation_windowsmanual_websphere/Content/Creating_Datasource_for_SyncConsole_Schema__MySQL_.htm#Creating__JAAS_J2C_Authentication)
 
2.  [Creating a JDBC
    Provider](https://docs.kony.com/6_5/konylibrary/sync/kmf_sync_installation_windowsmanual_websphere/Content/Creating_Datasource_for_SyncConsole_Schema__MySQL_.htm#Creating___JDBC_Provider)

 
3.  [Creating a Data
    Source](https://docs.kony.com/6_5/konylibrary/sync/kmf_sync_installation_windowsmanual_websphere/Content/Creating_Datasource_for_SyncConsole_Schema__MySQL_.htm#Creating_Data_source)
 
4.  [Setting Custom Properties of Data
    Source](https://docs.kony.com/6_5/konylibrary/sync/kmf_sync_installation_windowsmanual_websphere/Content/Creating_Datasource_for_SyncConsole_Schema__MySQL_.htm#Setting_Custom_Properties_of_Data_Source)

## Creating a JAAS J2C Authentication

**To create a JAAS J2C authentication, follow these steps:**

1.  Go to **WebSphere Administration** Console > **Security** > **Global
    Security**.

 
2.  Expand **Java Authentication** and **Authorization Service** >
    click **J2C authentication data **\> click **New**.




3.  Provide your MySQL database user details shown below:
 ![](media/ibmimage1.png)

You must enter your MySQL database alias, user name and password
in **Alias**, **User ID** and **Password**.

4.  Click **OK** : A screen appears to save the master configuration.

5.  Click **Save**.

## Creating a JDBC Provider

**To create a JDBC provider, follow these steps:**

1.  Go to **Resources** > **JDBC**, and click the **JDBC Provider**.


2.  Select your server scope. The sope name may appear
    as **Node=XXXXNode01**,** Server=server1**.

3.  Click **New**.

4.  Create a JDBC provider with name *MySQL JDBC Provider*.

5.  Set *User defined* as **Database type**.

6.  Download MySQL connector j jar & provide class path :
    `C:\\IBM\\jars\\mysql-connector-java-5.1.47-bin.jar`




6.  Set `com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource` as Implementation
    class name**.

![](media/ibmimage2.png) 

7.  Click **Next**.




8.  Set Database class path to the location of MySQL JDBC connector jar
    file.




9.  Click **Next** and review your inputs.




10. Click **Finish**.




## Creating a Data Source

**To create a Data source, follow these steps:**

1.  Go to **Resources** > **JDBC**, and click **Data sources**.




2.  Select you server Scope. The scope name may appear
    as **Node=XXXXNode01**,** Server=server1**.




3.  Click **New**.




4.  Provide *ConsoleDBDS* as **Datasource Name**.




5.  Provide *jdbc/ConsoleDB* as **JNDI Name** (you must configure the
    same JNDI name in the syncconsole.properties file).




6.  Click **Next**.




7.  Select an existing JDBC provider, for example, *MySQL JDBC
    Provider* (created in [Creating a JDBC
    Provider](https://docs.kony.com/6_5/konylibrary/sync/kmf_sync_installation_windowsmanual_websphere/Content/Creating_Datasource_for_SyncConsole_Schema__MySQL_.htm#Creating___JDBC_Provider)).




8.  Click **Next**.




9.  Choose the data store helper class name. Retain the default values.




10. Click **Next**.




11. Setup security aliases shown below, choose already created JAAS --
    J2C Authentication Data here:

**Component-managed authentication alias**: *XXXNode01/mysqlroot*\
**Mapping configuration alias**: *DefaultPrincipalMapping*\
**Container-managed authentication alias**: *XXXNode01/mysqlroot.*

12. Click **Next**, review changes, and then click **Finish**.

## Setting Custom Properties of Data Source

For user defined data sources, you must provide database details (Server
Name, Port, Database Name, User and Password) under custom properties of
a data source.

**To set the custom properties of a data source, follow these steps:**

1.  Click **MySQL Data source** to open it.




2.  Click **Custom Properties** under **Additional Properties** section.




3.  Click **New** and set the following properties. If Customer property
    already exists with the below names, edit them and provide values.
 ```sql
serverName = localhost (the host name or IP address of the MySQL server)
port = 3306 (MySQL database port)
databaseName = syncconsole (the name of the syncconsole database)
user = root (the user name of the MySQL server)
password = rootpassword (the password associated with the user name)
```
 **Note** : You can use the URL property to specify complete JDBC URL of
syncconsole database shown below instead of above all properties:

URL = jdbc:mysql://localhost:3306/syncconsole?user=root&password=rootpassword

4.  Restart the WebSphere application server for the changes to take
    effect.

## JDBC Example

```java
public class DBConnection {
	
	
	public static Connection getConnection() {
		Connection con =null;
		try {
		
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
		 con = ds.getConnection("root", "root");
		 
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}

}
```

UI page - db.jsp
```java
<h2>Connection Check</h2>
	<%
		Connection con = DBConnection.getConnection();
		if (con != null) {
			out.write("<h1>Connected....</h1>");

			try {

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from user");
				while (rs.next())
					out.write("<br/>" + rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			out.write("<h1>Failed....</h1>");
		}
	%>
```


![](media/ibmimage3.png)
