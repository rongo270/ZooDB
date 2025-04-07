# ZooDB Project

## About

This project was created as a learning exercise for working with **PostgreSQL**.  
Note: The database was built *after* the main code was already written.

## Requirements

- **PostgreSQL** installed and running
- **Eclipse IDE** for Java development
- Java project files from this repository

## Setup Instructions

1. **Configure PostgreSQL:**
   - Open PostgreSQL and connect to your database.
   - Navigate to the `tables` folder in the project.
   - Run the SQL commands from the file `tables of zoo.txt` to create the necessary tables.
   - (Optional) To reset the database, run the commands from `drop tables.txt`.

2. **Configure Java Project:**
   - In Eclipse, open the file:  
     `project_zoo -> DateBaseNew.java`
   - Locate the lines:
     ```java
     String user = "your_username";
     String password = "your_password";
     ```
   - Replace the values with your actual PostgreSQL username and password.

## Notes

- The current setup is temporary. A more detailed README with better configuration instructions will be provided soon.
