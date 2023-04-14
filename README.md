[![nels-dev](https://circleci.com/gh/nels-dev/csis3275-proj.svg?style=shield)](https://app.circleci.com/pipelines/github/nels-dev/csis3275-proj)

# Seasell - C2C E-commerce platform

This repository contains source code of CSIS3275 (W23) Group Project and is not intended for real commercial use. 

## Backend

The backend service is a SpringBoot application.

### Local Database

There are 2 ways to setup a local database.

1. H2 in-memory Database. You don't need to do anything, just start the application and access localhost:8080/h2-console with the credentials in application.yml

2. MySQL Database. As a fast setup, install Docker and run the following command to spin up a database instance.

```
docker run --name mysql-db -v <PATH TO DATA DIRECTORY>/mysql_data:/var/lib/mysql  -p 3306:3306 -e MYSQL_PASSWORD=mysqlpw -e MYSQL_USER=mysql -e MYSQL_DATABASE=seasell -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d mysql:latest
```
Change `<PATH TO DATA DIRECTORY>` so that mysql data files are stored in the host (your machine). Note that the initialization may take a while before it is ready to connect.

### Run the application locally

Use run configuration provided by IDE or use command
```
mvn spring-boot:run
```

If you use mysql database, append the profile
```
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```


### Database Updates

To facilitate rapid development, a ddl update strategy of "Create" is employed and hence an application restart will erase all existing data.
This is of course not intended for use in production or production-like testing environment. A more sophisticated DB migration strategy is out of scope of this project.



### Deployment

The backend service is intended to be deployed to the cloud platform. The configuration under `.circleci` folder enables integration 
with the CircleCI build pipeline which tests and eventually deploys the package to AWS Elastic Container Services (ECS).


## Frontend
To run the frondend in Window OS:
```
npm install
```
Then
```
npm run lint
```
