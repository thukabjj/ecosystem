# ECOSYSTEM
-------------------
Objective of this project
-------------------
-------------------
Ecosystem is a RESTFul API for the purpose of creating, listing, updating and deleting a user (CRUD).

Pre requisites
-------------------
-------------------
* [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven 3.6.1](https://maven.apache.org/download.cgi)
* [Docker](https://www.docker.com/)

**Note:** For Maven and Java it is necessary to configure the environmental variables of each of them to configure see also these articles.
* [Configure Java environment variable](https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html)
* [Configure Maven environment variable](https://docs.wso2.com/display/IS323/Installing+Apache+Maven+on+Windows)

Architecture
-------------------
-------------------

Ecosystem is a RESTFul API developed on the basis of service-oriented architecture (SOA) along with the test-oriented development (TDD) methodology. Using Java and the Spring Boot framework as a programming language, with the MySQL database and the H2 Database Engine, and for all these technologies to be executed, Docker was used to configure the environment in which it will run.
Following is the list of technologies used in project development:

* [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Docker](https://www.docker.com/)
* [Maven 3.6.1](https://maven.apache.org/download.cgi)
* [Spring Boot 2.1.4](https://spring.io/)
* [MySql 5.6](https://dev.mysql.com/downloads/mysql/5.6.html) 
* [H2 Database Engine](https://www.h2database.com/html/main.html)
* [Nginx 1.13](https://www.nginx.com/)
* [JUnit](https://junit.org/junit5/)

Let's Go !
-------------------
-------------------
First, clone the repository:
     
    $ git clone https://github.com/thukabjj/ecosystem   

If this is your first time using Github, review http://help.github.com to learn the basics.

You can also download the zip file containing the code from https://github.com/thukabjj/ecosystem

To build the application
-------------------	
-------------------
From the command line with Maven installed:

    $ cd ecosystem
    $ mvn clean install
	
**Note:** 
When running this command automatically the tests developed in the application will be run if you want to run only the tests use this command:

    $ mvn test

Run the application from Docker  
-------------------
-------------------
After building the project and you have created the ecosystem-1.0.0.jar inside the / target folder so that we can upload the containers of the spring boot and mysql just run this command

    $ cd ecosystem
    $ docker-compose up

To see the whole process that takes place at execution run with this command
    
    $ docker-compose up --build

If you are using Linux use this command

    $ sudo docker-compose up --build

For the see the containers running execute this command

    $ docker-compose container ls
    
For the see the logs of the container running

    $ docker-compose logs "CONTAINER ID"
    
**Note:** While the mysql-docker-container is not running the spring-boot-docker-container will not start the same as the nginx-docker-container

Access the application
-------------------
-------------------

After the containers go up, just access the documentation of the endpoints through this link: 

http://localhost:8080/swagger-ui.html

Or use the Nginx access redirect through this link:

http://localhost/api

Monitoring the application through the actuator
-------------------
-------------------
Spring Boot Actuator provides secured endpoints for monitoring and managing our Spring Boot application.

For the acess this application through this link:

http://localhost:8080/actuator/

Or use the Nginx access redirect through this link:

http://localhost:8080/actuator

Some important Spring Boot Actuator endpoints are provided below. Simply access the route through the browser to monitor the application that is running in the container docker.

**Note:** All routes should be accessed from this link:
http://localhost:8080/actuator/

| Endpoints | Description |
| ------ | ------ |
| /metrics | To view the application metrics such as memory used, memory free, threads, classes, system uptime etc. |
| /env | To view the list of Environment variables used in the application. |
| /beans | To view the Spring beans and its types, scopes and dependency. |
| /health | To view the application health |
| /info | To view the information about the Spring Boot application. |
| /httptrace | To view the list of Traces of your Rest endpoints.|

