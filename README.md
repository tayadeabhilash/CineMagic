# MovieTheaterClub

## About the Project

The application is an end to end solution to manage a theater movie booking system.

The functional details and business logic for the application can be found inside the project report.


## Technology Stack

- Frontend: React.js
- Backend: Springboot
- Database: MySQL
- Deployment: AWS

## Requirements

For building and running the application you need:

- JDK 17
- Maven 3
- Node 10+

## Running the application

### Backend - Spring Boot

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.scrumandcoke.movietheaterclub.BackendApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

You can activate local profile using:
```shell
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### Frontend - React

Install all the dependencies using
```shell
npm install
```

Start the react application
```shell
npm start
```
