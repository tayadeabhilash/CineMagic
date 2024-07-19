# CineMagic
A movie ticket booking application.

## Technology Stack

- Frontend: React.js
- Backend: Springboot
- Database: MySQL
- Deployment: AWS

## Documentation
1. [Component Diagrams](https://github.com/gopinathsjsu/teamproject-scrumandcoke/blob/main/docs/component-diagram.png)
2. [Deployment diagram](https://github.com/gopinathsjsu/teamproject-scrumandcoke/blob/main/docs/deployment-diagram.png)
3. [ER Diagram](https://github.com/gopinathsjsu/teamproject-scrumandcoke/blob/main/docs/er-diagram.jpeg)
4. [Feature List](https://github.com/gopinathsjsu/teamproject-scrumandcoke/blob/main/docs/feature-list.md)
5. [Identity and Access-Management Design](https://github.com/gopinathsjsu/teamproject-scrumandcoke/blob/main/docs/iam-documentation.md)
6. [Use-Case Diagram](https://github.com/gopinathsjsu/teamproject-scrumandcoke/blob/main/docs/iam-documentation.md)
7. [XP Values Applied](https://github.com/gopinathsjsu/teamproject-scrumandcoke/blob/main/docs/iam-documentation.md)

## Requirements

For building and running the application you need:

- JDK 17
- Maven 3
- Node 10+

## Runbook

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
