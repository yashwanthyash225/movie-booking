# End to End Movie Booking App

This is an End to End Movie Booking App consisting of both backend and frontend implementations.

## Backend

The backend of this application is implemented using Spring Boot framework with MySQL database. Key technologies and tools utilized include:

- **Spring Boot:** Used as the main framework for building the backend application.
- **MySQL:** Utilized as the relational database management system for storing application data.
- **Spring Data JPA:** Implemented to provide easy and efficient data access using JPA.
- **Java 8 Streams:** Leveraged for processing collections of objects in a functional style.
- **Spring Transactions:** Used for managing transactions across multiple database operations.
- **Project Strucuture:** Followed best practices of project structures having packages Model, Controller, Service, ServiceImpl, Repository, Dto, Mapper etc.

### Database Tables

The database consists of the following tables:

1. **Movie:** Stores information about movies available for booking.
2. **Theatre:** Contains details of different theatres where movies are screened.
3. **Screen:** Stores information about the screens within each theatre.
4. **Shows:** Contains details about show timings for each movie in each screen.
5. **User:** Stores user information for booking tickets.
6. **Ticket:** Contains details of tickets booked by users.
7. **Seat:** Stores information about seats in each screen.

## Frontend

The frontend of this application is implemented using React Native, providing a seamless and intuitive user interface for movie booking. Key features and tools used in the frontend development include:

- **React Native:** Utilized for building the cross-platform mobile application.
- **Navigation Between Screens:** Implemented to facilitate smooth navigation within the application.
- **FlatList:** Utilized for efficiently displaying lists of data.
- **Displaying Image:** Implemented functionality to display movie posters and other images.
- **Axios for REST APIs:** Utilized Axios for making HTTP requests to the backend RESTful APIs.

### Screens

The frontend consists of the following screens:

1. **HomeScreen:** The main screen of the application displaying featured movies and options for navigation.
2. **MoviesScreen:** Displays a list of available movies for booking.
3. **TheatreScreen:** Shows the list of theatres and available shows.

## Screenshots

Screenshots of the application can be found in the `screenshots` folder for reference.
