# spring-security-cas #

This repository includes 3 projects:
* CAS SERVER: overlay based cas web application. You have to deploy this application to tomcat server.
* CAS CLIENT: is a spring boot cas client application having java based configuration. I lanches on embeded Tomcat server.
* CAS CLIENT XML:  is a spring boot cas client application having xml based configuration. There is no need to deploy. I lanches on embeded Jetty server.

## MICRO SERVICE A (CAS CLIENT A PROJECT) ##
* restricted page: http://localhost:7171/a/service/greeting
* public page : http://localhost:7171/a/service/greeting2


## MICRO SERVICE B (CAS CLIENT B PROJECT) ##
* restricted page: http://localhost:7172/b/report/hi
* public page: http://localhost:7172/b/report/hi2


### HOW TO RUN AND TEST SINGLE SIGN ON ?###
First, deploy CAS SERVER on a Tomcat server. Then, Launch 'CAS CLIENT A' and 'CAS CLIENT B' services.
Then access restricted page of micro service a (http://localhost:7171/a/service/greeting), it will redirect to 'cas login page' (http://localhost:8080/cas/login?service=http%3A%2F%2Flocalhost%3A7171%2Fa%2Flogin%2Fcas).
Type 'ozay' for both username and password fields. Cas will authenticate and redirect to the previous page you request.
Lastly, access restricted page of micro service b (http://localhost:7172/b/report/hi) and notice that there is no need to typing your credentials again. The protected page of 'micro service b' is served directly. 

