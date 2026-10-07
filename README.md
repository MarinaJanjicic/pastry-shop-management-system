# Pastry Shop Management System

A Java desktop client-server application for managing pastry shop operations, including administrators, pastries, pastry types, ingredients, and receipts.

## Features

- Administrator login and management
- Add, search, update, and delete administrators
- Manage pastries and pastry types
- Manage pastry ingredients
- Create and manage receipts and receipt items
- Search and update pastry information
- View pastry statistics by type
- Client-server communication using request and response objects
- Database transaction handling

## Technologies

- Java
- Java Swing
- MySQL
- JDBC
- NetBeans
- Client-Server Architecture

## Project Structure

The application is organized into three main modules:

- **Klijent** – desktop client application and Swing user interface
- **Server** – server-side logic, database access, and system operations
- **Zajednicki** – shared domain models and transfer objects used for client-server communication

## Architecture

The application follows a client-server architecture.

The client communicates with the server through serialized request and response objects. The server processes client requests through dedicated system operation classes and communicates with a MySQL database using JDBC.

Database operations use transaction management to ensure data consistency.

## Database Configuration

Database connection settings are located in:

```text
Server/config.properties
```

Example configuration:

```properties
url=jdbc:mysql://localhost:3306/poslasticarnica
username=root
password=
```

Update these values according to your local MySQL configuration before running the application.

## Running the Application

1. Create a MySQL database named `poslasticarnica`.
2. Configure the database connection in `Server/config.properties`.
3. Open the projects in NetBeans.
4. Start the **Server** application.
5. Start the **Klijent** application.
6. Log in through the client application.

## Key Concepts Demonstrated

- Object-oriented programming
- Client-server communication
- JDBC database connectivity
- CRUD operations
- Transaction management
- Java Swing GUI development
- Separation of client, server, and shared domain logic

## Author

**Marina Janjičić**
