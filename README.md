# Flight Booking System

This project is a microservices-based booking system for flights, built using Spring Boot, Spring Cloud, Docker, and various other technologies. 
The system includes services for user management, flight management, booking, and payment, along with integrated monitoring and tracing.

## About
A comprehensive system to manage flight bookings with a microservices architecture.

## Prerequisites

- JDK 22 
- Maven 3.6.3 or higher
- Docker and Docker Compose

## Services

The project is divided into the following microservices:
- User Service
- Flight Service
- Booking Service
- Payment Service
- Notification Service

## Setup and Running

Follow these steps to set up and run the project:

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/complex-booking-system.git
    cd complex-booking-system
    ```

2. **Configure your database**
   
   configure the credentials in the environment in `application-cloud.yml` if you need use the app in docker
   and if u need use it in local configure it in 'application.yml' :**

4. **Build each service individually:**

    For each service, navigate to the service directory and run:

    ```bash
    mvn clean install
    ```

    Example for User Service:

    ```bash
    cd User
    mvn clean install
    cd ..
    ```

    Repeat the above steps for all services (user, flight, booking, payment, notification).

5. **Set up Docker and run Docker Compose:**

    ```bash
    docker-compose up -d
    ```
    ```

6. **Access the services:**
    - **Prometheus:** `http://localhost:9090`
    - **Admin-Server:** `http://localhost:5555`
    - **Eureka-Server:** `http://localhost:8761`
    - **User Service:** `http://localhost:8885`
    - **Flight Service:** `http://localhost:8886`
    - **Booking Service:** `http://localhost:8887`
    - **Payment Service:** `http://localhost:8888`
    - **Notification Service:** `http://localhost:8889`



For any issues or contributions, please refer to the [issues section](https://github.com/yourusername/complex-booking-system/issues).

Enjoy working with the complex booking system!
