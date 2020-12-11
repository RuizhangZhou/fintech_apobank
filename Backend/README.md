Starting Business-Logic Microservice:

Go to business-logic directory.
Compile the microservice trough:
    $ mvn clean install spring-boot:repackage

Start microservice trough:
    $ java -jar target/business-logic-0.0.1-SNAPSHOT.jar

Sending requests to Business-Logic:
Base url: 
        http://localhost:8081/business-logic/v1/ 

The base url shouldn't show anything fo now.

Customer request url: 
    http://localhost:8081/business-logic/v1/customer?id=100002

The number in the end is the customer_number from database.

For now a false customer_number doesn't return anuthing, in the future it should return a "Error 404".  