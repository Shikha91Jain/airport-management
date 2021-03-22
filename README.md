
# Airport Management Application

## Overview
Airport Management is an application to manage the airport related functionalities and entities such as airport, runways and countries in which airport is present.

Currently the application provides below functionalities:

* Search Countries
    * Retrieve all countries
    * Search Country based on countryCode or/and country's name
* Search Airport
    * Search Airport based on countryCode or/and country's name
    * Search airport without any filters
* Retrieve Airport details for particular airport
* Retrieve list of runways for given airport
* Retrieve count of airports per country


The application is a spring boot application built on Java 8 with using the starters such Spring Web Starter, Spring Data JPA, Spring boot actuator, logback access log starter.

* Spring Web Starter --> To expose REST web services
* Spring Data JPA with Hibernate + Hikari connection pooling --> To provide the data access API that Hibernate will use to interact with the database
* Spring Actuator --> To retrieve operational information about the application. Currently application exposes info, health and prometheus endpoints to retrieve the information. This will be used to monitor the application and check metrics of application.
* Logback Access Log Starter --> To log the access logs for incoming request in the logs


Application is connecting to PostgreSQL database to retrieve the required details for country/airport/runway. PFB the Entity Relationship(ER) diagram of the tables created in PostgeSQL database that will be used by the application:


![alt text][https://github.com/Shikha91Jain/airport-management/tree/master/src/main/resources/images/Assessment_ER_Diagram.PNG "ER Diagram"]


## Usecase Walkthrough

### Runways for each airport given a country code or country name. 
To retrieve the list of runways for each airport based on country code or country name, below APIs have been introduced:

1. Search Country (GET /airport-management/v1/countries)
This API is introduced to retrieve the list of countries present. The data returned in the API will be used as an input to search the airports.
2. Search Airport (GET /airport-management/v1/airports)
API provides functionality to search the list of airports based on country code or country's name. API supports pagination and provides the option list of airports in paginated format. API is also capable to perform sorting of the search based on the given parameter. Sorted results can be returned in either ascending or descending order as requested by the consumer.

**Note**: Currently the search airport API is returning the runway details at each airport level in the response. But if runway details needs to be retrieve separately, then after searching the airports as above, GET /airport-management/v1/airports/{airportId}/runways API can be used to retrieve runway information for specific airport.

### Top 10 countries with highest number of airports.
To retrieve the aggregated data of airport count per countries, API GET /airport-management/v1/countries/aggregation is introduced.
API will retrieve aggregated data for countries based on the provided groupBy and aggregation filters provided in request.
Currently the API supports to provide the airport count per country.
API also provides the option to sort the aggregated data based on the aggregation being performed. The number of result that needs to be retrieved can also be provided in request to retrieve limited result.

## Code Build

To build the code, please run below command:
```
mvn clean install
```
This will prepare the JAR artifact to be deployed.
If the code analysis also needs to be done with the build, then please run below command
```
mvn clean install sonar:sonar
```


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

### Setup using docker image

**Pre-requisites:**
* Docker is installed
* PostgreSql is installed
* Maven is installed (optional, if code build needs to be done)

#### PostgreSql Setup
Below are the steps to setup postgres:
1. Pull the docker image of postgres from docker hub using below command
```
docker pull postgres
```
2. Once image is pulled, run the container with below command:
```
docker run --name assessment-postgres -p 5432:5432 -e POSTGRES_PASSWORD=<password> -d postgres
```
3. Verify in docker if the container is running using below command
```
docker ps
```
4. Once the postgres container is running, then connect to the database  and Setup the PostgreSql DB to be used by application by executing below scripts
    * database-setup.sql
    * airport-management-ddl.sql
    * country-insert.sql
    * airport-insert.sql
    * runway-insert.sql

#### Application deployment to docker container
Below are the steps to deploy the application in docker:

1. Update the application-{profile}.properties with the connection details for postgresql. Available profiles: dev, uat, prod. Once updated, build the application.
2. Build docker image with the present Dockerfile of the project and tag it. Below is the command to prepare
```
docker build -t <hub-user>/<repo-name>[:<tag>] .
ex: docker build -t assessment/airport-management .
```
3. Once the docker image is tagged in local repository, image can be pushed to docker hub using below command:
```
docker push <hub-user>/<repo-name>:<tag>
ex: docker push assessment/airport-management:latest
```
4. Run the docker image in container using below command:
```
docker run -p 8081:8081 --name airport-management -e SPRING_PROFILES_ACTIVE={profile} <hub-user>/<repo-name>
ex: docker run -p 8081:8081 --name airport-management -e SPRING_PROFILES_ACTIVE=dev assessment/airport-management
```
5. Verify in docker if the container is running using below command
```
docker ps
```
If the container is running, then application is ready to be tested.

## How to Test
Once the application is deployed, API can be tested by triggering below sample requests:

### Search Country
**Sample Requests**
Search by Country Code and Country's name (partial search):
```
GET http://<ip:port>/airport-management/v1/countries?name=in&countryCode=i
User-Agent: Apache-HttpClient/4.5.5 (Java/12.0.1)
```
Search by Country Code
```
GET http://<ip:port>/airport-management/v1/countries?countryCode=in
```
Search by Country's name
```
GET http://<ip:port>/airport-management/v1/countries?name=India
```

**Sample Response**
Success Scenario
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8

[
	{
		"countryId": 302693,
		"countryCode": "HU",
		"name": "Hungary",
		"continent": "EU",
		"wikipediaLink": "https://en.wikipedia.org/wiki/Hungary",
		"keywords": "RepÃƒÂ¼lÃ…â€˜terek MagyarorszÃƒÂ¡g"
	},
	{
		"countryId": 302785,
		"countryCode": "UM",
		"name": "United States Minor Outlying Islands",
		"continent": "OC",
		"wikipediaLink": "https://en.wikipedia.org/wiki/United_States_Minor_Outlying_Islands"
	},
	{
		"countryId": 302755,
		"countryCode": "US",
		"name": "United States",
		"continent": "NA",
		"wikipediaLink": "https://en.wikipedia.org/wiki/United_States",
		"keywords": "America"
	}
]
```
Countries not found for given input
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8

[]
```

### Retrieve Airport
**Sample Request**
Retrieve airport based on airportId
```
GET http://<ip:port>/airport-management/v1/airports/2153
```

**Sample Response**
Success Scenario
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8

{
	"airportId": 2153,
	"identifier": "EBBE",
	"name": "Beauvechain Air Base",
	"latitude": "50.7585983276367",
	"longitude": "4.76833009719848",
	"elevation": "370",
	"countryRef": {
		"countryId": 302676,
		"countryCode": "BE"
	},
	"isoRegion": "BE-WBR",
	"municipality": "Beauvechain",
	"scheduledService": "no",
	"gpsCode": "EBBE",
	"wikipediaLink": "https://en.wikipedia.org/wiki/Beauvechain_Air_Base",
	"runways": [
		{
			"runwayId": 233627,
			"length": "10085",
			"width": "148",
			"surface": "PEM",
			"lighted": true,
			"closed": false,
			"lengthLatitude": "50.7472",
			"lengthLongitude": "4.75317",
			"lengthElevation": "358",
			"lengthHeading": "39.4",
			"lengthDisplacementThreshold": "2095",
			"heightLatitude": "50.7685",
			"heightLongitude": "4.78083",
			"heightElevation": "323",
			"heightHeading": "219.4",
			"heightDisplacementThreshold": "2106"
		},
		{
			"runwayId": 233626,
			"length": "8038",
			"width": "74",
			"surface": "PEM",
			"lighted": true,
			"closed": false,
			"lengthLatitude": "50.7469",
			"lengthLongitude": "4.75986",
			"lengthElevation": "370",
			"lengthHeading": "38.7",
			"lengthDisplacementThreshold": "197",
			"heightLatitude": "50.7638",
			"heightLongitude": "4.7811",
			"heightElevation": "327",
			"heightHeading": "218.7",
			"heightDisplacementThreshold": "200"
		}
	]
}
```
Airport not found for given Id
```
HTTP/1.1 404 
Content-Type: application/json

{
	"code": "404001",
	"reason": "airport not found for given input: 21533322"
}
```

### Retrieve Runways for Airport
**Sample Request**
Retrieve runways for given airportId
```
GET http://<ip:port>/airport-management/v1/airports/2153/runways
```

**Sample Response**
Success Scenario
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8

[
	{
		"runwayId": 233627,
		"length": "10085",
		"width": "148",
		"surface": "PEM",
		"lighted": true,
		"closed": false,
		"lengthLatitude": "50.7472",
		"lengthLongitude": "4.75317",
		"lengthElevation": "358",
		"lengthHeading": "39.4",
		"lengthDisplacementThreshold": "2095",
		"heightLatitude": "50.7685",
		"heightLongitude": "4.78083",
		"heightElevation": "323",
		"heightHeading": "219.4",
		"heightDisplacementThreshold": "2106"
	},
	{
		"runwayId": 233626,
		"length": "8038",
		"width": "74",
		"surface": "PEM",
		"lighted": true,
		"closed": false,
		"lengthLatitude": "50.7469",
		"lengthLongitude": "4.75986",
		"lengthElevation": "370",
		"lengthHeading": "38.7",
		"lengthDisplacementThreshold": "197",
		"heightLatitude": "50.7638",
		"heightLongitude": "4.7811",
		"heightElevation": "327",
		"heightHeading": "218.7",
		"heightDisplacementThreshold": "200"
	}
]
```
Airport not found for given Id
```
HTTP/1.1 404 
Content-Type: application/json

{
	"code": "404001",
	"reason": "airport not found for given input: 21533322"
}
```

### Search Airport
**Sample Requests**
Search Airport without any filters
```
GET http://<ip:port>/airport-management/v1/airports
```
Search airport by countryCode
```
GET http://<ip:port>/airport-management/v1/airports?country.countryCode=IN
```
Search airport by Country's name
```
GET http://<ip:port>/airport-management/v1/airports?name=in
```
Search airport by Country Code and Country's name
```
GET http://<ip:port>/airport-management/v1/airports?country.countryCode=in&country.name=in
```
Search airport by Country code and Country's name with pagination and sorting filters
```
GET http://<ip:port>/airport-management/v1/airports?country.countryCode=in&country.name=in&offset=0&limit=10&sortBy=country.countryCode&sortOrder=ASC
```

**Sample Response**
Success Scenario
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8

{
	"totalNumberOfRecords": 44,
	"airports": [
		{
			"airportId": 331218,
			"identifier": "IN-0107",
			"name": "Sindhudurg Airport",
			"latitude": "16.002552",
			"longitude": "73.529846",
			"elevation": "203",
			"countryRef": {
				"countryId": 302634,
				"countryCode": "IN"
			},
			"isoRegion": "IN-MM",
			"municipality": "Chipi-Parule",
			"scheduledService": "yes",
			"wikipediaLink": "https://en.wikipedia.org/wiki/Sindhudurg_Airport",
			"keywords": "VA85",
			"runways": []
		},
		{
			"airportId": 329504,
			"identifier": "VOKN",
			"name": "Kannur International Airport",
			"latitude": "11.918614",
			"longitude": "75.547211",
			"elevation": "330",
			"countryRef": {
				"countryId": 302634,
				"countryCode": "IN"
			},
			"isoRegion": "IN-KL",
			"municipality": "Kannur",
			"scheduledService": "yes",
			"gpsCode": "VOKN",
			"iataCode": "CNN",
			"wikipediaLink": "https://en.wikipedia.org/wiki/Kannur_International_Airport",
			"runways": []
		},
		{
			"airportId": 311049,
			"identifier": "IN-0092",
			"name": "Jajpur ( Sukinda ) Airstrip",
			"latitude": "21.0319444444",
			"longitude": "85.7538888889",
			"elevation": "400",
			"countryRef": {
				"countryId": 302634,
				"countryCode": "IN"
			},
			"isoRegion": "IN-OR",
			"scheduledService": "no",
			"localCode": "R",
			"runways": [
				{
					"runwayId": 311051,
					"length": "3000",
					"width": "100",
					"lighted": false,
					"closed": false,
					"lengthElevation": "400",
					"heightElevation": "400"
				}
			]
		}
	]
}
```
No airports found for given search parameters
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8

{
	"totalNumberOfRecords": 0,
	"airports": []
}
```

### Retrieve Aggregated data for Country
**Sample Request**
Retrieve count of airports per country with sorting and limited results
```
GET http://<ip:port>/airport-management/v1/countries/aggregation?groupBy=airport&aggregation=COUNT&limit=5&sortOrder=DESC
```
Retrieve count of airports per country with default sorting
```
GET http://<ip:port>/airport-management/v1/countries/aggregation?groupBy=airport&aggregation=COUNT
```

**Sample Response**
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8

[
	{
		"countryId": 302755,
		"countryCode": "US",
		"aggregatedData": [
			{
				"type": "COUNT",
				"value": "23820"
			}
		]
	},
	{
		"countryId": 302791,
		"countryCode": "BR",
		"aggregatedData": [
			{
				"type": "COUNT",
				"value": "5324"
			}
		]
	},
	{
		"countryId": 302730,
		"countryCode": "CA",
		"aggregatedData": [
			{
				"type": "COUNT",
				"value": "2803"
			}
		]
	}
]
```