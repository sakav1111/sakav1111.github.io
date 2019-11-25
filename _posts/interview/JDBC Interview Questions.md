
## Write SQL query to find second highest salary in employee table?
```sql
SELECT MAX(Salary) FROM Employee WHERE Salary NOT IN (SELECT MAX(Salary) FROM Employee)
```


This query first finds maximum salary and then exclude that from the list and
again finds maximum salary. Obviously second time, it would be second highest
salary.

## Difference between WHERE vs HAVING clause in SQL - GROUP BY Comparison with Example

*main difference between WHERE and HAVING clause in SQL* is that, condition
specified in WHERE clause is used while fetching data (rows) from table, on the
other hand HAVING clause is later used to filter summarized data or grouped
data.

**SELECT** \* **FROM** Employee;

| **EMP_ID** | **EMP_NAME** | **EMP_AGE** | **EMP_SALARY** | **DEPT_ID** |
|------------|--------------|-------------|----------------|-------------|
| 1          | Virat        | 23          | 10000          | 1           |
| 2          | Rohit        | 24          | 7000           | 2           |
| 3          | Suresh       | 25          | 8000           | 3           |
| 4          | Shikhar      | 27          | 6000           | 1           |
| **5**      | **Vijay**    | **28**      | **5000**       | **2**       |

**SELECT** \* **FROM** Department;

| **DEPT_ID** | **DEPT_NAME** |
|-------------|---------------|
| 1           | Accounting    |
| 2           | Marketing     |
| 3           | Sales         |

```sql
SELECT d.DEPT_NAME, count(e.EMP_NAME) as NUM_EMPLOYEE, avg(e.EMP_SALARY) as AVG_SALARY 
FROM Employee e,Department d 
WHERE e.DEPT_ID=d.DEPT_ID 
AND EMP_SALARY > 5000 
GROUP BY d.DEPT_NAME;
```


| **DEPT_NAME** | **NUM_EMPLOYEE** | **AVG_SALARY** |
|---------------|------------------|----------------|
| Accounting    | 1                | 8000           |
| Marketing     | 1                | 7000           |
| Sales         | 2                | 8000           |

From the number of employee (NUM_EMPLOYEE) column you can see that only Vijay
who work for Marketing department is not included in result set because his
earning 5000. This example shows that, condition in WHERE clause is used to
filter rows before you aggregate them.

```sql
SELECT d.DEPT_NAME, count(e.EMP_NAME) as NUM_EMPLOYEE, avg(e.EMP_SALARY) asAVG_SALARY 
FROM Employee e,Department d 
WHERE e.DEPT_ID=d.DEPT_ID 
AND EMP_SALARY > 5000 
GROUP BY d.DEPT_NAME
HAVING AVG_SALARY > 7000;
```


| **DEPT_NAME** | **NUM_EMPLOYEE** | **AVG_SALARY** |
|---------------|------------------|----------------|
| Accounting    | 1                | 8000           |
| Sales         | 2                | 8000           |

then HAVING clause comes in picture for final filtering, which is clear from
following query, now Marketing department is excluded because it doesn't pass
condition in HAVING clause i.e AVG_SALARY \> 7000

## What is JNDI?

JNDI is the Java Naming and Directory Interface. It's used to separate the
concerns of the application *developer* and the application *deployer*.

When you're writing an application which relies on a database, you shouldn't
need to worry about the user name or password for connecting to that database.

For Doing that,

1.  **JNDI Url is configured in Server side, we need to just place that Url in
    Context.xml with some Resource name=””**

2.  **Configure Resource name in web.xml**

Add a file `META-INF/context.xml` into the root of your web application folder,
which defines database connection details
```xml
<Context>
  <Resource name="jdbc/satyadb" auth="Container" type="javax.sql.DataSource"
               maxActive="50" maxIdle="30" maxWait="10000"
               username="mysqluser" password="mysqlpassword" 
               driverClassName="com.mysql.jdbc.Driver"
               url="jdbc:mysql://localhost:3306/mkyongdb"/>
</Context>
```

In `web.xml`, defines your MySQL data source again :
```xml
<resource-ref>
 <description>MySQL Datasource example</description>
 <res-ref-name>jdbc/satyadb</res-ref-name>
 <res-type>javax.sql.DataSource</res-type>
 <res-auth>Container</res-auth>
</resource-ref
```


get the datasource via context lookup service

```java
public Connection getConnection() {
   try {
        InitialContext context = new InitialContext();
        DataSource ds = (DataSource) context.lookup("jdbc:mysql://localhost:3306/satyadb");       	 
        Connection  conn = ds.getConnection();

        } catch (SQLException ex) {            
        }
        return conn;
    }
```


## What are the steps to connect to the database in java?

```java
public class JDBC {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection
                       ("jdbc:mysql://localhost:3306/mydb", "root", "root");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM customer");

		while (rs.next())
		System.out.println(rs.getInt(1) + ": " + rs.getString(2));		
	}
}
```

## What are the JDBC statements?

There are 3 types of JDBC Statements, as given below:

-   **Statement**:  It will execute SQL query (static SQL query) against the
    database.

-   **Prepared Statement:**  Used when we want to execute SQL statement
    repeatedly.  Input data is dynamic and  taken input at the run time.

-   **Callable Statement:** Used when we want to execute stored procedures.
```java
public CallableStatement prepareCall("{ call procedurename(?,?...?)}"); 
CallableStatement cs=con.prepareCall("{call myprocedure(?,?)}");
```


## Explain the difference between RowSet vs. ResultSet in JDBC?

RowSet extends the ResultSet interface, so it holds all methods from ResultSet.
**RowSet is serialized.**

## What is the difference between execute(), executeQuery, executeUpdate in JDBC?

-   `boolean execute()`: it can be used for any kind of SQL Query.

-   `ResultSet executeQuery()` : it can be used for select query.

-   `int executeUpdate()`: it can be used to change/update table.


## **What is JDBC database Connection Pool? How to setup in Java?**  
JDBC connection pool maintains pool of JDBC connection which is used by
application to query database. Since JDBC connection are expensive it takes time
to create them which can slow response time of server if created during request
time. Creating them on application start-up and reusing them result in better
performance.

## **What is use of setAutoCommit(false) in JDBC ?**  
By **default** setAutoCommit() is **TRUE** . making setAutoCommit(false) saves a
lot of performance as it doesn't commit transaction automatically after each
query and we do batch update. It allows you to handle it
using **commit() and rollback().** 

## Batch Processing?

Instead of executing a single query, we can execute a group of queries. The
java.sql.Statement and java.sql.PreparedStatement interfaces provide methods for
batch processing

-   **void addBatch(String query) –** It adds query into batch.

-   **int[] executeBatch() –** It executes the batch of queries.

```java
Statement stmt=con.createStatement();
stmt.addBatch("insert into user420 values(190,'abhi',40000)"); 
stmt.addBatch("insert into user420 values(191,'umesh',50000)");
stmt.executeBatch();//executing the batch
```


## **Difference between java.util.Date and java.sql.Date in Java? (answer)**  
**java.util.Date** contains both date and time while **java.sql.Date** contains
only date part

Read
more: <http://www.java67.com/2018/03/top-50-core-java-interview-questions.html#ixzz5fuYL91FG>
