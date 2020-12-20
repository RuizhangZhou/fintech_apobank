Starting Business-Logic Microservice:

Go to business-logic directory.
Compile the microservice trough:
    $ mvn clean install spring-boot:repackage

Start microservice trough:
    $ java -jar target/business-logic-0.0.1-SNAPSHOT.jar

Sending requests to Business-Logic:
Base url: 
        http://localhost:8081/business-logic/v1/ 

The base url shouldn't show anything for now.

Example with Customer request url: 
    http://localhost:8081/business-logic/v1/customer?id=100002

Swagger: 
    http://localhost:8081/business-logic/v1/swagger-ui/index.html#/