# How to run the Backend (and access Swagger UI)
## "data" and "ml-model" projects (developed by Alex)
### 1.  MySQL-Database [_mysql_] and Data-Service [_data-service_]
The database for customer data is managed in Alex' separate "data" project.
In the root folder of the **master branch** you can find bash scripts to build the maven project and run the docker containers:

`$ ./rebuild.sh` will rebuild the _data-service_ and update the docker containers.\
(If you have done this before and nothing was changed in the project files, there's no need to run this.)

`$ ./start_database.sh` will start up the container running a mysql database.\
_(press ENTER to return to your console)_

`$ ./start_data-service` will start up the _data-service_ container.\
**The database container has to run before the data-service!**

Swagger UI for _data-service_ is accessible at http://localhost:8080/api/v1/swagger-ui/

`$ ./stop.sh` will terminate both docker containers.
### 2.  Machine Learning Model [_ml-model_]
The machine learning model is managed in the other separate project "ml-model". (Please clone this from our repository if you haven't already.)
In its root folder there is a similar set of bash scripts to build and run the docker container:

`$ ./rebuild.sh` will build or update the _ml-model_ container.\
(Again if the project didn't change since last time you executed this, there's no need to do this another time.)

`$ ./start.sh` will start up the _ml-model_ container.

The description for the POST request is accessible at http://localhost:5000/

`$ ./stop.sh` will terminate the docker container.
## "web-application" project
### 3. Full Backend [_business-logic_], [_mongodb_], [_productive-data-service_], [_batch-process_]
Because we work with requests to `localhost` between the microservices, we have to run the _business-logic_ and _batch-process_ in the local java runtime.\
The _mongodb_ and the correpsonding _productive-data-service_ run in docker containers in the same fashion as Alex' _mysql_ database and _data-service_.

The `Backend` folder contains scripts to run the different microservices:
#### 3.1. Business Logic [_business-logic_]
`$ ./run_business-logic.sh` will build and run the _business-logic_ application.

Swagger UI for _business-logic_ is accessible at http://localhost:8081/business-logic/v1/swagger-ui/

To stop the application, press `Ctrl+C`.
#### 3.2. mongoDB [_mongodb_] und Productive-Data-Service [_productive-data-service_]
`$ ./rebuild_productive-data.sh` will rebuild the _productive-data-service_ and update the docker containers.

`$ ./start_productive-data.sh` will start up both docker containers.

Swagger UI for _productive-data-service_ is accessible at http://localhost:8082/productive-data-service/v1/swagger-ui/

`$ ./stop_productive-data.sh` will terminate both docker containers.
#### 3.3. Batch Process [_batch-process_]
`$ ./run_batch-process.sh` will build and run the _batch-process_ application.

Swagger UI for _batch-process_ is accessible at http://localhost:8083/batch-process/v1/swagger-ui/

To stop the application, press `Ctrl+C`.