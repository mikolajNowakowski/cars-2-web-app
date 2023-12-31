# Three-Module Java Backend Application for Cars Management

____________________________________________________________________________________________________________________________
This Java backend application is designed to facilitate cars management, providing a modular and efficient solution for handling cars. The application is divided into three distinct modules, each serving a specific purpose.

## Modules

### 1. Persistence Module

The `persistence` module is responsible for communication with various data sources, including databases and text files in different formats. It ensures data retrieval, storage, and management, making it a crucial component for handling car-related data.

The use of the [Abstract Factory](persistence/src/main/java/com/app/data) pattern enables the use of multiple data sources, such as databases and text files in various formats like txt, xml, or json. Separating the loader from the subsequent data processing layers allows for easy modification and the addition of functionality to the application.

### 2. Service Module
The `service` module serves as the core of the application and consists of microservices designed to manage various aspects of the system, such as:

- **Car List Management**: Handling and processing cars list.
- **User Registration**: Managing user registration and activation.
- **Authorization**: Managing access to routes.
- **Email Notification**: Sending email notifications to users.
- **Tokens Service**: For generating and refreshing tokens.

These microservices are essential for the overall functionality of the application, providing features like user registration, authentication, car processing, and document generation.

### 3. API Module
The `api` module is responsible for routing and managing data flows within the application. It serves as the interface through which external systems or clients can interact with the application's services. This module handles the routing of requests and responses, ensuring seamless communication between the frontend and the service layer.


## Set-up

* Java 20
* Maven
* Docker
* Spark
* Spring
* Gson
* Simplejavamail
* Jjwt
* Junit
* Mockito
* Jdbi
* MySQL
* Hamcrest
* Log4j
* AssertJ
* Lombok

## Design Patterns
* Abstract Factory
* Singleton
* Builder
* Facade

# How to install?
If you want ot create local repository, use command below:
```bash
mvn clean install -DskipTests
```
or if you don't want to install anything use that command:
```bash
mvn clean install -DskipTests
```


# How to run?
Both the application and the associated databases are containerized using Docker.
This containerization ensures that the application and its dependencies can be easily deployed and run in various environments without compatibility issues.



To create containers for the app and both the main and test database, use the following command in your terminal:
```bash
docker-compose up -d --build
```
If you only want to create containers for the app and the main SQL server, use this command:
```bash
docker-compose up -d --build cars-web-app mysql_main
```

# Unit Tests
To run unit tests in the right way, go to the project destination and execute command below to run the test database container:
```bash
docker-compose up -d --build mysql_test
```
When mysql for tests is ready, run tests with command bellow:
```bash
mvn test
```

# Application properties file

To change the data source from the database to a .json or .txt file, you need to navigate to 
[application.properties](api/src/main/resources/application.properties). In that file, 
you also have the ability to modify the regex for product names or email validation. 
Additionally, within the same file, you can find the URL for the database and for example check all URIs for each user role.

For the application to function correctly, you need to individually fill in the login details for the email address used by the application. The data to be filled in includes:
- simplejavamail.smtp.host=smtp.gmail.com
- simplejavamail.smtp.port=465
- simplejavamail.defaults.from.name=
- simplejavamail.defaults.from.address=
- simplejavamail.smtp.username=
- simplejavamail.smtp.password=