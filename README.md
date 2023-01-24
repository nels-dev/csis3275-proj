[![nels-dev](https://circleci.com/gh/nels-dev/csis3275-proj.svg?style=shield)](https://app.circleci.com/pipelines/github/nels-dev/csis3275-proj)

# Seasell - C2C E-commerce platform

This repository contains source code of CSIS3275 (W23) Group Project and is not intended for real commercial use. 

## Backend

The backend service is a SpringBoot application. 



### Local Database

While this service is deployed on the cloud, for development purpose it is necessary to setup a database locally.
The following command runs a docker instance with mysql installed.

```
docker run --name mysql-db -v <PATH TO DATA DIRECTORY>/mysql_data:/var/lib/mysql  -p 3306:3306 -e MYSQL_PASSWORD=mysqlpw -e MYSQL_USER=mysql -e MYSQL_DATABASE=seasell -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d mysql:latest
```
Change `<PATH TO DATA DIRECTORY>` so that mysql data files are stored in the host (your machine). Note that the initialization may take a while before it is ready to connect.

Alternatively you may download and install MySQL separately.



### Run the application locally

Use run configuration provided by IDE or use command
```
mvn spring-boot:run
```



### Database Updates

To facilitate rapid development, a ddl update strategy of "Create" is employed and hence an application restart will erase all existing data.
This is of course not intended for use in production or production-like testing environment. A more sophisticated DB migration strategy is out of scope of this project.



### Deployment

The backend service is intended to be deployed to the cloud platform. The configuration under `.circleci` folder enables integration 
with the CircleCI build pipeline which tests and eventually deploys the package to AWS Elastic Container Services (ECS).
