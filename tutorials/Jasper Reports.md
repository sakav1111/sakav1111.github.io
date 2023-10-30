---
title: Jasper Reports Tutorial
permalink: "/tutorials/jasper-tutorial"
key: jasperreports-tutorial
categories:
- jasperreports
tags:
- jasperreports
---


# Jasper Reports

JasperReports is an open-source reporting tool for Java that is used for reporting of applications. This reporting tool helps in writing rich content onto the screen, to the printer, or into a variety of files like HTML, PDF, XLS, RTF, CSV, XML, ODT and TXT. JasperReports can also be used for Java-enabled applications like Java EE or web applications to generate dynamic content.

- #### **Install JasperReports**

install Jasper Reports Community edition **from Here.**

[https://community.jaspersoft.com/community-download](https://community.jaspersoft.com/community-download)

- #### **Lifecycle of JasperReports**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.001.png)

A **.jrxml** report template is consist of the following parts shown in the below figure.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.002.png)

## 1\. Create JasperReport Project in Eclipse

**Jaspersoft Studio Plugin:**

To program **Jasperreport** report you need to install **JasperSoft Studio** on Eclipse.

- #### **SQL Query**

```sql
Select Acc.Open_Branch_Id
     ,Bra.Name           Branch_Name
     ,Acc.Cust_Id
     ,Cus.Address        Cust_Address
     ,Acc.Account_Id
     ,Acc.Product_Cd
     ,Acc.Avail_Balance
From   Account  Acc
     ,Customer Cus
     ,Branch   Bra
Where  Acc.Cust_Id = Cus.Cust_Id
And    Acc.Open_Branch_Id = Bra.Branch_Id

```

- #### **Create JasperReport Project**

Open Perspective: **Report Design**.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.003.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.004.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.005.png)

Next, create a project **:**

- **File/New/Other..**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.006.png)

Enter the name of the Project:

- **HelloJasperReport**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.007.png)

Project has been created:

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.008.png)

- #### **Create Data Adaptor**

Here I connect with **Oracle** database. You can do the same with **MySQL** or **SQLServer**. There is some differences in the using library and ***URL Connection String***.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.009.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.010.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.011.png)

Error emerges when you do not declare the classpath of Database Library Driver.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.012.png)

Just Add the **jar** file location, the JDBC Driver library for this database

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.013.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.014.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.015.png)

OK, ***Data Adaptor*** was created.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.016.png)



## 1. Design report

Create a new report

**File/New/Other...**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.017.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.018.png)

Enter the name of the report: **FirstJasperReport**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.019.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.020.png)

?

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.021.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.022.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.023.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.024.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.025.png)

The report is generated.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.026.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.027.png)

**Outline View** is very important. We often have to use it while designing a report.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.028.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.029.png)

A blank report page is created. In which, parts of content of ***Page Header***, ***Column Header***, ***Column Footer***, ***Page Footer*** are repeatedly displayed on each page of the report. You can delete unnecessary parts of content by right-click on it and click **Delete**.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.030.png)

Here I deleted all of parts, except for **Column Header** & **Detail** in order to make it easy to design. We can recreate them if necessary.

- **Column Header**: contains titles of data columns. It is duplicated in all pages by default.
- **Detail**: contains records. It repeat numbers of records.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.031.png)



## 2. Deep design report

Drag and drop **Rectangle** object from ***Pallete*** to **Column Header** of the report. Then, fully spread out the width of **Rectangle**.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.032.png)

Next, adjust the height of **Column Header** so that it is equal to the height of newly-made ***Rectangle***. The purpose is to erase unnecessary space in the report.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.033.png)

Results:

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.034.png)

Change the background color of the rectangle:

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.035.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.036.png)

Next, we create 2 groups in turn:

- **Group Branch**
- **Group Cust**

Their meaning is described in the following illustrative image:

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.037.png)

On the **Outline**, right click to **FirstJasperReport**, select Create Group:

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.038.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.039.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.040.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.041.png)

Next, we drag a **Rectangle** from **Pallete** to ***"Branch Group Header 1"*** area.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.042.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.043.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.044.png)

Change the background color for the rectangle you just created.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.045.png)

Next, we will drag drop respectively the **Field Open\_Branch\_Id** and **Branch\_Name** into **Rectangle** on the block **"Branch Group Header 1".**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.046.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.047.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.048.png)

Similarly, we drag and drop ***Branch\_Name*** next to ***Open\_Branch\_Id***.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.049.png)

**Next, Create Customer group:**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.050.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.051.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.052.png)

After **Customer Group** is created, drag and drop Rectangle object into ***"Customer Group Header 1"***. Adjust and change background of Rectangle as the following illustrative image:

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.053.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.054.png)

Drag & drop Field: **Cust\_ID** into Rectangle just created.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.055.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.056.png)

Similarly, drag & drop **Cust\_Address** field.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.057.png)

Next, drag and drop fields ***Account\_Id***, ***Product\_Cd***, ***Avail\_Balance*** into ***"Detail 1"*** area in turn:

*Note: If you drag and drop FIELDS into **Detail** area, column titles will be automatically created in **Column Header** area.*

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.058.png)

Adjust the height of **"Detail 1"** area to a minimum:

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.059.png)

Next, drag and drop the remaining column titles:

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.060.png)

Minimize the height of 2 areas:

- Customer Group Footer 1
- Branch Group Footer 1

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.061.png)

Next drag & drop Field: **AVAIL\_BALANCE** into **"Customer Group Footer 1**" & **"Branch Group Footer 1"**.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.062.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.063.png)

Simultaneously, we change background of two objects we have just dragged and dropped.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.064.png)


-  **Run report (First time)**

We test the report by clicking **Preview** TAB:

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.065.png)




## 3. Beauty Report

Selecting the fields and setting properties as shown below.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.066.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.067.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.068.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.069.png)

-  **Run Report (Second time)**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.070.png)



## 4. **Design Header section of the report**

- Create **images** folder in project, copy file ***bank.png*** to this folder, as illustrated below:

- **bank.png**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.071.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.072.png)

Drag and drop **Image** object from the ***Palette*** to the page header.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.073.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.074.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.075.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.076.png)

Next drag & drop ***"Static Text"*** object to Page Header.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.077.png)

Similarly, drag & drop second ***"Static Text"*** object to Page Header.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.078.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.079.png)


**- Run Report (Third time)**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.080.png)




# JasperReport using Java

Download JasperReport library for Java

[http://community.jaspersoft.com/download](http://community.jaspersoft.com/download)

You need a **JasperReport** file. We will run that report on **Java**. You can download an example of a report file at: [https://community.jaspersoft.com/sites/default/files/files/StyledTextReport.zip](https://community.jaspersoft.com/sites/default/files/files/StyledTextReport.zip)

Unzip the downloaded file, we will have ***jrxml*** file.

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.081.png)

Create a Java project and declare library.Create Java Project:**JavaCallJasperReportTutorial**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.082.png)

Create a **libs** folder and copy the most vital libraries into this folder, including:

- commons-beanutils-\*.jar
- commons-collections-\*.jar
- commons-digester-\*.jar
- commons-logging-\*.jar
- groovy-all-\*.jar
- itextpdf-\*.jar
- itext-pdfa-\*.jar
- jasperreports-\*.jar

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.083.png)

Next, copy JDBC Drivers into ***libs*** folder. Here I copy all of three **JDBC Driver** into Database types including **Oracle**, **MySQL**, **SQLServer**. 

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.084.png)

To declare the library, right-click Project and select **Properties**

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.085.png)

Select all the **jar** files in the **libs** folder:

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.086.png)

![](media/jasper/Aspose.Words.f0008d41-f20c-4dd9-a122-aba43a82c387.087.png)

- ## **Java - JasperReport without DB**

Here is a simple example, **Java** will call to report **JasperReport** and generate **PDF** file. This simple example does not have the participation of the **Database**.

```java
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class PdfFromXmlFile {

	public static void main(String[] args) throws JRException, IOException {

		// Compile jrxml file.
		JasperReport jasperReport = JasperCompileManager
				.compileReport("C:/jasperreport/StyledTextReport/StyledTextReport.jrxml");

		// Parameters for report
		Map<String, Object> parameters = new HashMap<String, Object>();

		// DataSource
		// This is simple example, no database.
		// then using empty datasource.
		JRDataSource dataSource = new JREmptyDataSource();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		// Make sure the output directory exists.
		File outDir = new File("C:/jasperoutput");
		outDir.mkdirs();

		// Export to PDF.
		JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/jasperoutput/StyledTextReport.pdf");

		System.out.println("Done!");
	}
}
```



- ## **Java - JasperReport with MySQL DB**

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "learningsql";
		String userName = "root";
		String password = "12345";
		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {

		// Declare the class Driver for MySQL DB
		// This is necessary with Java 5 (or older)
		// Java6 (or newer) automatically find the appropriate driver.
		// If you use Java> 5, then this line is not needed.
		Class.forName("com.mysql.jdbc.Driver");

		// Cấu trúc URL Connection dành cho Oracle
		// Ví dụ: jdbc:mysql://localhost:3306/simplehr
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
}

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import org.o7planning.tutorial.javajasperreport.conn.ConnectionUtils;

public class JavaCallJasperReport {

	public static void main(String[] args) throws JRException, ClassNotFoundException, SQLException {

		String reportSrcFile = "F:/ECLIPSE_TUTORIAL/JASPERREPORT/HelloJasperReport/FirstJasperReport.jrxml";

		// First, compile jrxml file.
		JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

		Connection conn = ConnectionUtils.getConnection();

		// Parameters for report
		Map<String, Object> parameters = new HashMap<String, Object>();

		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

		// Make sure the output directory exists.
		File outDir = new File("C:/jasperoutput");
		outDir.mkdirs();

		// PDF Exportor.
		JRPdfExporter exporter = new JRPdfExporter();

		ExporterInput exporterInput = new SimpleExporterInput(print);
		// ExporterInput
		exporter.setExporterInput(exporterInput);

		// ExporterOutput
		OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
				"C:/jasperoutput/FirstJasperReport.pdf");
		// Output
		exporter.setExporterOutput(exporterOutput);

		//
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		exporter.setConfiguration(configuration);
		exporter.exportReport();

		System.out.print("Done!");
	}
}


```