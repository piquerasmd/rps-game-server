# Rock-Paper-Scissors Game Server

This is a server-side application for the classic Rock-Paper-Scissors game. It's built with Kotlin (version 1.9.23), Java (version 21), Spring Boot (version 3.2.4), and Maven.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Installing

1. Clone the repository

```bash
git clone https://github.com/piquerasmd/rps-game-server.git
```

2. Navigate into the project directory

```bash
cd rps-game-server
```

3. Build the project with Maven

```bash
mvn clean install
```

4. Run the application

```bash
mvn spring-boot:run
```

## Running the tests

To run the unit tests, use the following command:

```bash
mvn test
```

## Database

This project uses Microsoft SQL Server 2022. You can pull the Docker image and create a Docker container using the following commands:

```bash
docker pull mcr.microsoft.com/mssql/server:2022-latest
```

```bash
docker run -e "ACCEPT_EULA=Y" \
-e "MSSQL_SA_PASSWORD=yourStrong<<#><Password" \
-e "MSSQL_PID=Evaluation" \
-p 1433:1433 \
--name sqlserver1 \
--hostname sqlserver1 \
-d mcr.microsoft.com/mssql/server:2022-latest
```

## Built With

- [Kotlin](https://kotlinlang.org/)
- [Java](https://www.java.com/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)