# Spring Boot Eureka-Enabled Spring Cloud Gateway

This project is a Spring Boot application acting as a Spring Cloud Gateway, configured to use Eureka for service discovery.  It provides a single entry point for your microservices, routing requests to the appropriate services based on their names registered in Eureka.

## Prerequisites

*   Java Development Kit (JDK) 8 or higher
*   Maven (version 3.6.0 or higher)
*   A running Eureka Discovery Service (see instructions elsewhere for setting one up).
*   An IDE (e.g., IntelliJ IDEA, Eclipse, VS Code) is recommended.

## Getting Started

1.  **Clone the repository:**

    ```bash
    git clone <your-repository-url>
    cd <your-project-directory>
    ```

2.  **Build the project using Maven:**

    ```bash
    mvn clean install
    ```

3.  **Run the application:**

    ```bash
    java -jar target/<your-application-name>.jar
    ```

    Alternatively, you can run the application directly from your IDE.

## Configuration

The application's configuration is managed through the `application.properties` or `application.yml` file in `src/main/resources`. This is where you define routing rules and connect to the Eureka server.
