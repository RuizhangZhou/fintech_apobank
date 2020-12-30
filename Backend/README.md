# How to run Backend (and access Swagger UI)
## "data" project (developed by Alex)
### 1.  MySQL-Database [_mysql_] and Data-Service [_data-service_]
The database for customer data is managed in Alex' separate "data" project.
In the root folder of the **master branch** you can find bash scripts to build the maven project and run the docker containers:

`$ ./rebuild.sh` will rebuild the project and update the docker containers.\
(If you have done this before and nothing was changed in the project files, there's no need to run this.)

`$ ./start_database.sh` will start up the container running a mysql database.\
_(press ENTER to return to your console)_

`$ ./start_data-service` will start up the _data-service_ container.\
**The database container has to run before the data-service!**

Swagger UI for _data-service_ is accessible at http://localhost:8080/api/v1/swagger-ui/

`$ ./stop.sh` will terminate those 2 containers.
## "web-application" project
### 2. Full Backend [_business-logic_], [_mongodb_], [productive-data-service], [_batch-process_]
All of our microservices have Dockerfiles so they can be run in docker containers:

`$ ./rebuild.sh` will rebuild all maven projects and update the docker containers.

`$ ./start.sh` will run all docker containers at once.\
(This may be visually confusing because all containers print their outputs into the same terminal, but it's the easiest way to conveniently run every thing at once :) )

`$ ./stop.sh` will terminate those 4 containers.
#### 2.1. Business Logic [_business-logic_]
Swagger UI for _business-logic_ is accessible at http://localhost:8081/business-logic/v1/swagger-ui/
#### 2.2. mongoDB [_mongodb_] und Productive-Data-Service [_productive-data-service_]
Swagger UI for _productive-data-service_ is accessible at http://localhost:8082/productive-data-service/v1/swagger-ui/
#### 2.3. Batch Process [_batch-process_]
Swagger UI for _batch-process_ is accessible at http://localhost:8083/batch-process/v1/swagger-ui/
