## Description
**The Project**

As an e-mobility charging solutions platform, we would like to provide a REST API that is
capable of managing Charge Detail Records (CDR) in real time to a network of Charge Point
Operators (CPO).
In order to achieve that goal, a CDR contract and a set of endpoints are required as follows:
Charge Detail Record fields (no pre defined field types are imposed)
- Session identification
- Vehicle identification
- Start time
- End time
- Total cost

**Endpoints**

- Create a Charge Detail Record
  - The "End time" cannot be smaller than "Start time"
  - The "Start time" of an upcoming Charge Detail Record for a particular vehicle must always be bigger than the "End time" of any previous Charge Detail Records.
  - The "Total cost" must be greater the 0
- Get a Charge Detail Record by id
- Search all Charge Detail Records for a particular vehicle
- "Start time" and "End time" fields must be sortable

**Constraints**

- Use Java 1.8+
- Use the Spring Framework
- Use Maven or Gradle as a build tool

## Installation
- Open IDE
- Run maven command ```mvn install```
- Go to terminal
- In terminal go to the folder containing _docker-compose-postgres.yml_ file
- Run command ```bash docker compose -f docker-compose-postgres.yml up ```

**THEN**

- Go to the folder cocntaining _Dockerfile_ file
- Run command ```docker build -t charger-api .```
- Run command ```docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:30003/dcs charger-api```

**OR**
- Start the project from IDE

## Sample requests

```
POST http://localhost:8080/api/v1/cdr
Content-Type: application/json

{
  "vehicleId": "SDF54",
  "startTime" : "2023-04-05T14:38:59",
  "endTime": "2023-04-05T15:38:59",
  "cost": 0.1
}
```
```
GET http://localhost:8080/api/v1/cdr/id/1
```
```
GET http://localhost:8080/api/v1/cdr/vehicleId/KAIH5501?page=0&limit=20&sort=startTime,desc
```

## Comments
- In order to run tests, the database container should be started
- PostgreSQL was chosen as a primary database, but in case of big volume of data, the suggested solution would be to place ElasticSearch index on top of Postgres, especially for the text queries, like Vehicle Id