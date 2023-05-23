# Spring boot with docker example

### Prerequisites

* JDK 11
* Maven 3.0+
* Latest Docker installation

### How to Run

#### Standalone
Open a command prompt and in the same directory as the pom run:
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev  (runs dev profile with debug log levels and access to h2 console)
OR
mvn clean package 
java -jar -Dspring.profiles.active=dev -Dserver.port=8080 target/football-team-rest-service-1.0.jar
```

#### Docker
```
mvn clean package
docker build -t footie-app:1.0 .
docker-compose up -d
```

NOTE: When running docker locally port 8080 is used by default as specified in docker-compose.yaml.  In the jenkins pipeline the port is set in the jenkinsFile - environment variable HOST_PORT

### URLS

* Get all teams - http://localhost:8080/
* Get specific team - http://localhost:8080/{team} e.g. spurs
* Get teams in order of stadium capacity: http://localhost:8080/capacity?sort=asc and http://localhost:8080/capacity?sort=desc
* Create a team - Open git bash command prompt and run following curl
```
curl -X POST -H "Content-Type: application/json" -d '{
  "name": "Liverpool",
  "city": "Merseyside",
  "owner": "Americans",
  "stadiumCapacity": 47000,
  "competition": "fa cup",
  "numberOfPlayers": 17,
  "dateOfCreation": "17/07/2012"
}' 'http://localhost:8080/create'
```

Swagger/OpenApi 3.0 accessible at http://localhost:8080/swagger-ui.html

### BDDS

mvn clean verify -Pintegration-tests

### H2
Console url: http://localhost:8080/h2-console/  
Database url: jdbc:h2:mem:testdb  
username: sa  
password is empty  
