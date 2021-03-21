
# Airport Management Application

## Overview
Airport Management is an application

* Search Countries
    * Retrieve all countries
    * Search Country based on countryCode or/and country's name
* Search Airport
    * Search Airport based on countryCode or/and airport's name
    * Search airport without any filters
* Retrieve Airport details for particular airport
* Retrieve list of runways for given airport
* Retrieve count of airports per country

## Code Build Details


## Application Setup

### Local setup
If the application needs to be setup on local machine/environment, then please follow below setup:

**Pre-requisites:**
* Java 8 or higher present in the system
* PostgreSql should be installed in system

**Steps:**
1. Setup the PostgreSql to be used by application by executing below scripts
    * database-setup.sql
    * airport-management-ddl.sql
    * country-insert.sql
    * airport-insert.sql
    * runway-insert.sql
2. Update the application-{profile}.properties with the connection details for postgresql. Available profiles: dev, uat, prod

3. Build the application using below command (if application artifact is not available)
```
mvn clean install
```
4. Run the application using below command:
With the profile that application needs to be deployed
```
java -Dspring.profiles.active=${profile} -jar airport-management.0.0.1-SNAPSHOT.jar 
```
If to be used with default profile, then command will be as below:
```
java -jar airport-management.0.0.1-SNAPSHOT.jar 
```


### Testing using docker image


## How to test

