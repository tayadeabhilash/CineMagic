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
## Feature List

Common Features for All Users
Home/Landing Page: Homepage designed for a quick preview.
Features include:
- View Currently Playing Movies: Displaying currently showing movies with poster,          name and genre.
- View Upcoming Movies: List of soon-to-be-released movies.
- View All Theaters: List of theaters that include details about the theater.
- View All Locations: Feature to view all theater locations. 
- View All Showtimes: Users can see showtimes for different movies across various theaters.
- View Membership Type: View type of membership whether it is Regular or Premium.
- Registration/Signup: Sign-in, sign-up and registration page.
- Ticket Booking: A booking system where users can,
	1. Select a movie.
	2. Choose a showtime.
	3. Pick seats.
	4. Complete the transaction. The system displays total cost, including any service fees (with waivers for members).
Features for Members (Regular and Premium)

- View Member's Page: Dashboard displaying recent ticket purchases, reward points.
- Create Multiple Seat Booking: Booking functionality allowing members to book up to 8 seats in a single transaction.
- Add Rewards Points Accumulation: Members earn points for every purchase.
- Create service fee waiver for premium members: Booking fee automatically waived at checkout.
Features for Theater Employees (Admins)

Movie/Showtime Management: System accessible only to theater employees. This system allows for the addition, updating, and removal of movie listings and showtimes.
Includes,
- Add movies: Employees can add new movies to the system with details like movie title, synopsis, duration, genre and release date.
- Schedule showtimes: Schedule showtimes for each movie, assigning them to specific screens at different theaters. 
-Add movies to theaters: Assigning movies to specific theaters within a location and seating capacity.
- Updating movies: Updating existing movies and showtimes.
- Removing movies: Delete existing movies and showtimes.
-View movies: View list of movies.
-Configure seating capacity: Theater employees can set the seating capacity for each screen within a theater.
- View analytics dashboard: Theater occupancy rates over (30/60/90 days) summarized by location and movies.
-Configure discounts: Discount management system that allows theater employees to set and manage discounted prices for shows.
	1.Discount based on time: Setting discounted prices for shows before 6 pm.
	2.Discount based on day: Reduced ticket prices on Tuesdays.

Role-Based Authentication Control
RBAC based on three types of users: Regular, Premium and Guest.

Deployment
Deploying API and Database to AWS EC2 Cluster  + RDS.

## XP Values

Two of the core XP values we selected are - "Communication" and "Feedback."

1. Communication:

Effective communication was at the heart of our project's success. We recognized that clear and constant communication was essential to keep everyone on the same page and address any issues promptly. Here's how we embodied this value:

a. Regular Team Meetings: We held regular team meetings, both scheduled and ad-hoc, to discuss project progress, roadblocks, and upcoming tasks. These meetings allowed us to share updates, brainstorm ideas, and align our efforts.

b. Open Channels: We maintained open channels of communication through messaging platforms and email. Team members were encouraged to ask questions, seek clarification, and share insights at any time, fostering a collaborative atmosphere.

c. Documentation: We placed a strong emphasis on documenting project details, including API specifications, database schema, and user stories. This documentation served as a reference point for all team members, reducing ambiguity.

2. Feedback:

The value of feedback cannot be overstated in our project. It played a pivotal role in shaping our product, refining our processes, and ensuring that we delivered a high-quality solution. Here's how feedback was integral to our project,

a. Iterative Development: We adopted an iterative development approach, releasing incremental versions of our application. This allowed us to gather feedback from team members early in the process.

b. Peer Code Reviews: Before merging any code changes, we conducted thorough peer code reviews. This practice not only ensured code quality but also provided an opportunity for team members to learn from each other.

c. Retrospectives: After each development sprint, we held retrospectives to reflect on what went well and what could be improved. This feedback loop allowed us to make continuous process enhancements.

In summary, by adhering to the XP Core Values of "Communication" and "Feedback," our team cultivated a culture of transparency, collaboration, and adaptability throughout the project. These values not only helped us deliver a successful application but also fostered a sense of ownership and shared responsibility among team members, ensuring that everyone's contributions were valued and considered.
