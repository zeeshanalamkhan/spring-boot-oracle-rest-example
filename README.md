# spring-boot-oracle-rest-example
This code will help you to build Spring Boot RESTful CRUD Example with ORACLE Database. As we know, making REST service in Spring Boot application is super easy and fast. Spring Boot data provides CrudRepository interface for generic CRUD operation in org.springframework.data.repository package.

To create a connection with the MySQL database, we need to configure data source properties in the application.properties file starting with spring.datasource.*. Spring boot uses spring-boot-starter-data-jpa to configure spring Java Persistence API (JPA).

What we’ll build---
In this example, we will create a Spring Boot application that connects with our external ORACLE database, consume and produce the JSON data and performs the following operations:

->Save the user submitted data into the database.
->Fetch all user submitted data from the database
->Fetch particular data from the database by a given ID.
->Update existing data, And
->delete a record from the database.

1. CrudRepository Interface
CrudRepository is an interface which provided by Spring Framework itself. CrudRepository extends Spring Data Repository which is a central repository marker interface. CrudRepository provides the generic method for create, read, update, and delete (CRUD) operation.

CrudRepository contains total 11 methods for CRUD operation.
