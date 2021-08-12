---
title: Curl Tutorial
permalink: "/tutorials/curl-tutorial"
key: curl-curl-tutorial
categories:
- Curl
tags:
- Curl
---

1.Introduction
---------------

Curl is a command line tool for doing all sorts of URL manipulations and
transfers, but this particular document will focus on how to use it when doing
HTTP requests for fun and profit. I'll assume that you know how to invoke 'curl
--help' or 'curl --manual' to get basic information about it.

This tool is used for sending or receiving files from a server. The different
protocols supported by curl are HTTP, HTTPS, FTP, TELNET, LDAP and FILE etc.,

**curl** offers [proxy](http://www.computerhope.com/jargon/p/proxyser.htm) support,
user [authentication](http://www.computerhope.com/jargon/a/auth.htm),
FTP [uploading](http://www.computerhope.com/jargon/u/upload.htm), HTTP
posting, [SSL](http://www.computerhope.com/jargon/s/ssl.htm)connections, [cookies](http://www.computerhope.com/jargon/c/cookie.htm),
file transfer resume, Metalink, and many other features

2.CURL Installation & Configuration
------------------------------------

Curl is available in two formats.

1.  [Source code formate(.zip)](https://curl.haxx.se/download.html)

2.  [Binary formate(.exe)](https://curl.haxx.se/dlwiz/)

You can download these from the [Official
website](https://curl.haxx.se/download.html) :
<https://curl.haxx.se/download.html>

After downloading/ Installing you can directly go to the curl folder where
curl.exe exists trough windows command prompt to run curl commands.you can find
this in src folder. For example –curl-7.x.x/src/curl.exe

![](media/b77be217f555b6b9c7baea2877366839.png)

3.CURL Commands
----------------

**Curl syntax**
```dos
curl [options] [URL...]
```


### 3.1 CURL –Options 

| **Tag**                                                                                                                         | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
|---------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **url**                                                                                                                         | One or multiple URLs that will be fetched in sequence. Multiple URLs or parts of URLs can be specified by writing part sets within braces as in: http://site.{one,two,three}.com or get sequences of alphanumeric series by using [] as in: ftp://ftp.numericals.com/file[1-100].txt ftp://ftp.numericals.com/file[001-100].txt (with leading zeros) ftp://ftp.letters.com/file[a-z].txt                                                                                                                                                                                                                                                                                                                                                     |
| **-A "agent string"--user-agent "agent string"**                                                                                | Specify the User-Agent string to send to the HTTP server. To encode blanks in the string, surround the string with single quote marks. This can also be set with -H, --header option. (HTTP)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| **-b name=data --cookie name=data**                                                                                             | Send the data to the HTTP server as a cookie. It is supposedly the data previously received from the server in a "Set-Cookie:" line. The data should be in the format "NAME1=VALUE1; NAME2=VALUE2".                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| **-c filename --cookie-jar file name**                                                                                          | Save cookies to file after a completed operation. Curl writes all cookies previously read from a specified file as well as all cookies received from remote server(s). If no cookies are known, no file will be written. To write to stdout, set the file name to a single dash, "-".                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| **--compressed**                                                                                                                | Request a compressed response using one of the algorithms curl supports (gzip), and save the uncompressed document. If this option is used and the server sends an unsupported encoding, curl will report an error.(HTTP)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **-d @file**                                                                                                                   | Send the specified data in an (HTTP) POST request, in the same way that a web browser does. This will pass the data using the content-type application/x-www-form-urlencoded. Compare to -F, --form.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| **-d "string"**                                                                                                                 |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **--data "string"**                                                                                                             |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-d, --data is the same as --data-ascii. To post data in pure binary, use --data-binary.**                                     | To URL-encode the value of a form field you may use --data-urlencode. Multiple date options will be merged together. Thus, using '-d name=daniel -d skill=lousy' would generate a post that looks like 'name=daniel&skill=lousy'. If the data starts with @, the rest should be a filename containing the data.                                                                                                                                                                                                                                                                                                                                                                                                                             |
| **-F name=@file**                                                                                                              | Emulate a filled-in form in which a user has pressed the submit button. This will POST data using the Content-Type multipart/form-data according to RFC 2388. This enables uploading of binary files etc. If the data starts with @, the rest should be a filename. To just get the content part from a file, prefix the file name with the symbol <. The difference between @ and < is that @ makes a file get attached in the post as a file upload, while the < makes a text field and gets the contents for that text field from a file.                                                                                                                                                                                           |
| **-F name=content**                                                                                                             |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **--form name=content**                                                                                                         |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-k**                                                                                                                          | This option explicitly allows curl to perform "insecure" SSL connections and transfers. All SSL connections are attempted in secure mode using the CA certificate bundle installed by default. This makes all connections considered "insecure" fail unless -k, --insecure is used.(SSL).                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **--insecure**                                                                                                                  |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **--limit-rate speed**                                                                                                          | Specify the maximum transfer rate. This feature is useful if you have a limited pipe and you'd like your transfer not to use your entire bandwidth. The given speed is measured in bytes/second, unless a suffix is appended. Appending 'k' or 'K' will count the number as kilobytes/sec, 'm' or M' megabytes, while 'g' or 'G' makes it gigabytes/sec. Eg: 200K, 3m, 1G.                                                                                                                                                                                                                                                                                                                                                                   |
| **-m seconds**                                                                                                                  |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **--max-time seconds**                                                                                                          |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-o file**                                                                                                                     | Write output to file instead of stdout. If you are using {} or [] to fetch multiple documents, you can use '\#' followed by a number in the file specifier. That variable will be replaced with the current string for the URL being fetched. Like in: curl http://{one,two}.site.com -o "file_\#1.txt" or use several variables like: curl http://{site,host}.host[1-5].com -o "\#1_\#2" You may use this option as many times as the number of URLs you have. See also --create-dirs option to create the local directories dynamically. Specify '-' to force the output to stdout.                                                                                                                                                        |
| **--output file**                                                                                                               |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-O**                                                                                                                          | Write output to a local file named like the remote file we get. (Only the file part of the remote file is used, the path is cut off.) The remote file name to use for saving is extracted from the given URL, nothing else. Consequentially, the file will be saved in the current working directory.                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| **--remote-name**                                                                                                               |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-s**                                                                                                                          | Silent or quiet mode. Don't show progress meter or error messages.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| **--silent**                                                                                                                    |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **--trace-ascii file**                                                                                                          | Enable a full trace dump of all incoming and outgoing data, including descriptive information, to the given output file. Use "-" as filename to have the output sent to stdout. This option overrides previous uses of -v, --verbose or --trace-ascii. If this option is used several times, the last one will be used.                                                                                                                                                                                                                                                                                                                                                                                                                      |
| **-T file**                                                                                                                     | Transfer the specified local file to the remote URL. PUT If there is no file part in the specified URL, Curl will append the local file name. You must use a trailing / on the last directory to really prove to Curl that there is no file name or curl will think that the last directory name is the remote file name to use. Use the file name "-" to use stdin. You can specify one -T for each URL on the command line. Each -T + URL pair specifies what to upload and to where. curl also supports "globbing" of the -T argument, meaning that you can upload multiple files to a single URL like this: curl -T "{file1,file2}" http://www.uploadtothissite.com or even curl -T "img[1-1000].png" ftp://ftp.picturemania.com/upload/ |
| **--upload-file file**                                                                                                          |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-I**                                                                                                                          | Fetch the HTTP-header only! (HTTP/FTP/FILE) HTTP-servers feature the command HEAD which this uses to get nothing but the header of a document. When used on an FTP or FILE file, curl displays the file size and last modification time only.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| **--head**                                                                                                                      |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-u user:password**                                                                                                            | The username and password to use for server authentication. Overrides -n, --netrc and --netrc-optional. If you just give the user name (without entering a colon) curl will prompt for a password. If you use an SSPI-enabled curl binary and do NTLM authentication, you can force curl to pick up the username and password from your environment by specifying a single colon with this option: "-u :". If this option is used several times, the last one will be used.                                                                                                                                                                                                                                                                  |
| **--user user:password**                                                                                                        |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-w**                                                                                                                          | Define extra info to display on stdout after a completed and successful operation. The format is a string that may contain plain text mixed with any number of variables. The format string can be specified as "string", or to read from a file specify "@filename" to read the format from stdin use "@-". Various variables may be included in the format and will be substituted by curl (file size, ip address etc see man curl for details). variables are specified as %{variable_name} Output a newline using \\n, a carriage return with \\r and a tab space with \\t.                                                                                                                                                            |
| **--write-out format**                                                                                                          |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-x host:port**                                                                                                                | Use the specified HTTP proxy. If the port number is not specified, it is assumed at port 1080.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| **-x [protocol://][user:password@]proxyhost[:port]**                                                                           |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **--proxy [protocol://][user:password@]proxyhost[:port]**                                                                      |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
|                                                                                                                                 |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-H "name: value"**                                                                                                            | Add Header when getting a web page. You may specify any number of extra headers.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| **--header "name: value"**                                                                                                      |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-H "name:"**                                                                                                                  | Remove Header, remove an internal header.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **--header "name:"**                                                                                                            |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-L**                                                                                                                          | Follow redirects if the server reports that the requested page has moved (indicated with a Location: header and a 3XX response code)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| **--location**                                                                                                                  |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **-v**                                                                                                                          | Make more verbose/talkative. Mostly useful for debugging.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **--verbose**                                                                                                                   |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |

### 3.2 CURL –Environment Variables

The environment variables can be specified in lower case or upper case. The
lower case version has precedence. **http_proxy** is an exception as it is only
available in lower case.

Using an environment variable to set the proxy has the same effect as using
the **--proxy** option.

| **http_proxy** [*protocol***://**]*<host>*[**:***port*]              | Sets the proxy server to use for HTTP.                                                                                                                                                                 |
|------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **HTTPS_PROXY** [*protocol***://**]*<host>*[**:***port*]             | Sets the proxy server to use for HTTPS.                                                                                                                                                                |
| [*url-protocol*]**_PROXY** [*protocol***://**]*<host>*[**:***port*] | Sets the proxy server to use for [*url-protocol*], where the *protocol* is a protocol that **curl** supports and as specified in a URL: **FTP**, **FTPS**, **POP3**, **IMAP**, **SMTP**, **LDAP** etc. |
| **ALL_PROXY** [*protocol***://**]*<host>*[**:***port*]               | Sets the proxy server to use if no protocol-specific proxy is set.                                                                                                                                     |
| **NO_PROXY***<comma-separated list of hosts>*                        | list of host names that shouldn't go through any proxy. If set to a asterisk '*****' only, it matches all hosts.                                                                                      |

### 3.3 Exit Codes

There are a bunch of different error codes and their corresponding error
messages that may appear during bad conditions. We have 90+ exit codes, listed
from 0,1,2,….88,89,90,91..

We can find these all these Exit code from [Official
website](https://curl.haxx.se/libcurl/c/libcurl-errors.html) :
<https://curl.haxx.se/libcurl/c/libcurl-errors.html>




# 4. CURL Examples


### 4.1 Simple Usage

 **1.Get the main page from any Website/WebServer**
```dos
curl www.google.com
```
![E:\\Users\\satyacodes\\Desktop\\temp.png](media/5ba63d5bfdd5a996cc4da6dca08885fe.png)

 

**2.Get a directory listing of an FTP site:** 
```dos
curl ftp://servername/
```


**3.Fetch two documents at once:**
```dos
curl http://localhost:8080/ http://localhost:9090/
```


![E:\\Users\\satyacodes\\Desktop\\temp.png](media/92919a4b6a6e6d5773bda364aec2e9f2.png)

### 4.2 Using Passwords

Curl supports user and password in HTTP URLs, thus you can pick a file like: 
```dos
curl http://name:passwd@machine.domain/full/path/to/file
```


or specify user and password separately like in 
```dos
curl -u name:passwd http://machine.domain/full/path/to/file
```


![E:\\Users\\satyacodes\\Desktop\\temp.png](media/05ed2d80aba2a92b7820019a09551154.png)

### 4.3 Ranges

HTTP 1.1 introduced byte-ranges. Using this, a client can request to get only
one or more subparts of a specified document. Curl supports this with the -r
flag.

**Get the first 100 bytes of a document:** 
```dos
curl -r 0-99 http://localhost:9090
```
 

**Get the last 500 bytes of a document:** 
```dos
curl -r -500 http://localhost:9090
```
![E:\\Users\\satyacodes\\Desktop\\temp.png](media/a0defd15a7237da7db397d61c0e9a127.png)

### 4.4 Uploading
```dos
// Upload all data on stdin to a specified server 
       curl -T - ftp://ftp.smlcodes.com/myfile

 //Upload data from a specified file, login with user and password
       curl -T uploadfile -u user:passwd ftp://ftp.smlcodes.com/myfile
```


### 4.5 POST (HTTP)

  It's easy to post data using curl. This is done using the **-d <data>
option**. The post data must be urlencoded.
```dos
curl post request with no data:
curl -X POST http://URL/example.php

curl post request with data:
curl -d "data=example1&data2=example2" http://URL/example.cgi

curl POST to a form:
curl -X POST -F "name=user" -F "password=test" http://URL/example.php

curl POST with a file:
curl -X POST -F "image=@/path/example.gif" http://URL/uploadform.cgi
```


5.CURL Real-Time Operations
----------------------------

#### 1. How to Download a Single File using CURL

The following command will get the content of the URL and display it in the
STDOUT
```dos
curl http://localhost:9090/
```

To store the output in a file, you an redirect it as shown below. This will also
display some additional download statistics.
```dos
curl http://localhost:9090/> stats.html
```


![E:\\Users\\satyacodes\\Desktop\\temp.png](media/ea466b2696f78a330191f504f6ee1fcf.png)

#### 2. How to save the CURL Output to a file

We can save the result of the curl command to a file by using -o/-O options.

-   \-o (lowercase o) the result will be saved in the filename provided in the
    command line

-   \-O (uppercase O) the filename in the URL will be taken and it will be used
    as the filename to store the result
```dos
curl -o outputfile.html http://localhost:9090/
```

Now the the output data will be stored in **outputfile.html**

![E:\\Users\\satyacodes\\Desktop\\temp.png](media/4b46eabbaf5944b176ed56040d36b026.png)

#### 3. How to Fetch Data from Multiple Files at a time

We can download multiple files in a single shot by specifying the URLs on the
command line.  
```dos
curl -O URL1 -O URL2

curl -O http://localhost:9090/Files/f1.txt -O http://localhost:9090/Files/f2.txt
```
![E:\\Users\\satyacodes\\Desktop\\temp.png](media/e4e9164a9d3536c76daf6fdea32d3902.png)

#### 4. How to Get More Information using Verbose and Trace Option

You can get to know what is happening using the -v option. -v option enable the
verbose mode and it will print the details
```dos
curl -v http://hostname/
```
![](media/1c83f257fb325fdd8b09c415f7c18356.png)

#### 5. How to use Proxy to Download a File

We can specify cURL to use proxy to do the specific operation using -x option.
We need to specify the host and port of the proxy.
```dos
$ curl -x proxysever.test.com:3128 http://google.co.in
```


#### 6. How to Send a Mail using SMTP Protocol

CURL can also be used to send mail using the SMTP protocol. You should specify
the from-address, to-address, and the mailserver ip-address as shown below.
```dos
$ curl --mail-from ceo@smlcodes.com --mail-rcpt smlcodes@gmail.com smtp://mailserver.com
```


#### 7. Download Files and Resume Downloads

I want to download a sample PDF file who’s URL is
<http://www.pdf995.com/samples/pdf.pdf>

**To Download PDF file with Default name(CAPITAL ‘O’ – default name)**
```dos
//To Download PDF file with Default name(CAPITAL ‘O’ – default name) 
curl -O -C - http://www.pdf995.com/samples/pdf.pdf

//To Download PDF file with Custom name(small ‘o’ – Custom name) 
curl -o -C –mypdf.pdf http://www.pdf995.com/samples/pdf.pdf

//To download multiple files at once, i.e  one after the other:
curl -O file1.txt -O file2.pdf -O file3.zip
```

#### 8. How to Check If a Website Is Down or Not

To check website down or not by using below command
```dos
curl -Is https://www.domain.com -L | grep HTTP/
```


#### 9. How to Get HTTP header information from a website

You can easily get HTTP header information from any website you want by adding
the -I option (capital ‘i’) to cURL.
```dos
curl -I http://domain.com
```
![E:\\Users\\satyacodes\\Desktop\\temp.png](media/19766cd77979632ddaa5afd7f466ecad.png)

#### 10. How to Test URL by injecting header

You can user curl  by injecting header with your data to test or troubleshoot
the specific issue. Let’s see following example to request with Content-Type
```dos
curl --header 'Content-Type: application/json' http://domain.com
```


#### 11. How to Connect HTTPS/SSL URL and ignore any SSL certificate error

If you are trying to access https URL and that is having certificate error due
to host name mismatch or others, you can ignore them by using the following
syntax.
```dos
curl --insecure https://domain.com
```


#### References
<http://www.computerhope.com/unix/curl.htm>
<https://curl.haxx.se/docs/manual.html>
