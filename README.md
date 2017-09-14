# spring-security-cas #
========================
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


