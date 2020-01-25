Ubuntu - Install Maven on Ubuntu

## Installing Apache Maven on Ubuntu with Apt

```bash
#Update package index:
sudo apt update

#install Maven 
sudo apt install maven

#Verify the installation
mvn -version
```



## Download Apache Maven Manually
Go to Download Page : [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

Copy Binary tar.gz archive link:
```bash
http://apachemirror.wuchna.com/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
```


```bash
# Download Apache Maven in the /tmp directory using wget
wget https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -P /tmp

# Once the download is completed, extract the archive in the /opt directory:
sudo tar xf /tmp/apache-maven-*.tar.gz -C /opt

#To have more control over Maven versions, we will create a symbolic link to the Maven installation directory:
#Later if you want to upgrade your Maven installation - 
#simply unpack the newer version and change the symlink to point to the latest version
sudo ln -s /opt/apache-maven-3.6.0 /opt/maven
```

In case of `ln: failed to create symbolic link ‘/opt/maven’: File exists` Error

```bash
# Check existing link
ls -l /opt/maven

#Output
vagrant@vagrant-ubuntu-trusty-64:/opt$ ls -l /opt/maven
lrwxrwxrwx 1 root root 23 Jan 25 16:58 /opt/maven -> /opt/apache-maven-3.6.0

#Remove Symbolic Links with rm
#Syntax: rm <symlink_name>
sudo rm /opt/maven

# Check again
ls -l /opt/maven
```



### Setup environment variables
open your text editor and create a new file named `mavene.sh` inside of the `/etc/profile.d/`

```bash
# create & open maven.sh
sudo vi /etc/profile.d/maven.sh


#Paste the following configuration in: /etc/profile.d/maven.sh
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}


# Make the script executable with chmod:
sudo chmod +x /etc/profile.d/maven.sh


# Finally load the environment variables
source /etc/profile.d/maven.sh


#Check Maven version Now
mvn --version
```


If you got error like - `JAVA_HOME should point to a JDK not a JRE`
make sure JDK path is like `/usr/lib/jvm/java-*-openjdk-*/bin/javac`

```
#Get All JDK's in your machine
sudo update-alternatives --config java


# --------------    OUTPUT  --------------------------
  Selection    Path                                            Priority   Status
------------------------------------------------------------
  0            /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/java   1071      auto mode
  1            /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/java   1071      manual mode
* 2            /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java   1069      manual mode


# Copy Path uptp `java-*-openjdk-*` : 
/usr/lib/jvm/java-8-openjdk-amd64/

# export=it
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
```
