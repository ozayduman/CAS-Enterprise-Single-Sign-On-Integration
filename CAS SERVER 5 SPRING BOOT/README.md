CAS Server Clustering Wiht Redis
================================
This is a sample project that demonstrates how to cluster cas server with redis. This could be freely used as a starting template. The CAS services management overlay is available [here](https://github.com/apereo/cas-services-management-overlay).

# Versions

```xml
<cas.version>5.1.x</cas.version>
```

# Requirements
* JDK 1.8+

# Configuration

The `etc` directory contains the configuration files and directories that need to be copied to `/etc/cas/config`.

# Build

To see what commands are available to the build script, run:

```bash
./build.sh help
```

To package the final web application, run:

```bash
./build.sh package
```

To update `SNAPSHOT` versions run:

```bash
./build.sh package -U
```

# Deployment

- Create a keystore file `thekeystore` under `/etc/cas`. Use the password `changeit` for both the keystore and the key/certificate entries.
- Ensure the keystore is loaded up with keys and certificates of the server.

On a successful deployment via the following methods, CAS will be available at:

* `http://cas.server.name:8080/cas`
* `https://cas.server.name:8443/cas`

## Executable WAR

Run the CAS web application as an executable WAR. You can run multiple instance of cas server as follows
Note that: you should put signing and encryption keys to  etc\cas\config\cas.properties file
```bash
java -jar -Dcas.standalone.config="D:\etc\cas\config" cas.war
java -jar -Dserver.port=7979 -Dcas.standalone.config="D:\etc\cas\config" cas.war
```

you can configure a loadbanced with non-sticky ip (no hash).  For nginx you can use the following configuration file.

```bash
worker_processes  1;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

    server {
        listen       80;
        server_name  localhost;

        location /{
			proxy_pass http://cas;
        }
    }
	
	upstream cas {
        server localhost:8080;
        server localhost:7979;
   }

}
```

## Spring Boot

Run the CAS web application as an executable WAR via Spring Boot. This is most useful during development and testing.

```bash
./build.sh bootrun
```

### Warning!

Be careful with this method of deployment. `bootRun` is not designed to work with already executable WAR artifacts such that CAS server web application. YMMV. Today, uses of this mode ONLY work when there is **NO OTHER** dependency added to the build script and the `cas-server-webapp` is the only present module. See [this issue](https://github.com/apereo/cas/issues/2334) and [this issue](https://github.com/spring-projects/spring-boot/issues/8320) for more info.


## Spring Boot App Server Selection
There is an app.server property in the pom.xml that can be used to select a spring boot application server.
It defaults to "-tomcat" but "-jetty" and "-undertow" are supported. 
It can also be set to an empty value (nothing) if you want to deploy CAS to an external application server of your choice and you don't want the spring boot libraries included. 

```xml
<app.server>-tomcat<app.server>
```

## Windows Build
If you are building on windows, try build.cmd instead of build.sh. Arguments are similar but for usage, run:  

```
build.cmd help
```

## External

Deploy resultant `target/cas.war`  to a servlet container of choice.

## References

java -jar -Dcas.standalone.config=D:\cas\config cas.war
java -jar -Dcas.standalone.config=D:\cas5_1\cas-overlay-template\etc\cas\config cas.war
overlay template ==> https://github.com/apereo/cas-overlay-template

https://github.com/casinthecloud/cas-overlay-demo
https://github.com/apereo/cas/tree/master/webapp
https://apereo.github.io/2017/02/02/cas51-authn-handlers/
https://wiki.jasig.org/display/CASUM/Technical+Overview
https://wiki.jasig.org/display/CAS/How+To+Write+an+AuthenticationHandler
https://groups.google.com/forum/#!topic/jasig-cas-user/ddCmLLTxeGU
https://github.com/apereo/cas/blob/v5.0.0/support/cas-server-support-authy/src/main/java/org/apereo/cas/adaptors/authy/config/AuthyConfiguration.java
https://github.com/apereo/cas/blob/4293255c6db71666a1256cf7e4c515df77e91265/core/cas-server-core/src/test/java/org/apereo/cas/config/CasMultifactorTestAuthenticationEventExecutionPlanConfiguration.java
https://apereo.github.io/cas/5.0.x/installation/Configuration-Properties.html
https://apereo.github.io/cas/5.0.x/installation/Configuring-SSO-Session-Cookie.html