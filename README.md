# MovieTheaterClub

## About the Team

### Team Members:
- Siddhant Nagpal
- Samarth Ghulyani
- Dhrithi
- Abhilash Tayade

### Areas of Contribution:
- **Samarth Ghulyani**: Focused on frontend development with ReactJS, including UI design and implementation.
- **Siddhant Nagpal**: Responsible for backend development, covering authentication, RBAC, membership features, user benefits, and deployment strategies.
- **Abhilash Tayade**: Worked on backend APIs for booking processes, membership benefits, points systems, and refunds/cancellations.
- **Dhrithi Vishwanath Gulannavar**: Handled integration of Movies and Theaters modules, managed Showtimes and Theaters tables, and implemented data validations.


## UI Wireframes

https://www.figma.com/file/DigU0IPhe6VL703f1tjSdt/UI-Wireframes?type=whiteboard&node-id=0-1&t=6j7TlY0gNn6xKUyK-0


## Project Journal

https://docs.google.com/document/d/1jyDVsNKu3KOSlWnhsmGDcxdcgfirm6aOnr48qjVGOo4/edit?usp=sharing


## Project Board
https://docs.google.com/spreadsheets/d/1T8QXka73NOndBJtUbxvMrBagtgWpx79l08CMXAXkeLQ/edit#gid=576886113

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