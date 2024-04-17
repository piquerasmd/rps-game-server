# rps-game-server
A simple, engaging web application for the classic rock-paper-scissors game


## mysql 

[Docker image for Microsoft SQL Server 2022](https://learn.microsoft.com/es-es/sql/linux/quickstart-install-connect-docker?view=sql-server-ver16&tabs=cli&pivots=cs1-cmd)


### Create a Docker Image for SQL Server 2022

To pull the Docker image for SQL Server 2022, execute the following command:
```bash
docker pull mcr.microsoft.com/mssql/server:2022-latest
```

To create a Docker image for SQL Server 2022, execute the following command:
```bash 
docker run -e "ACCEPT_EULA=Y" \
-e "MSSQL_SA_PASSWORD=yourStrong<<#><Password" \
-e "MSSQL_PID=Evaluation" \
-p 1433:1433 \
--name sqlserver1 \
--hostname sqlserver1 \
-d mcr.microsoft.com/mssql/server:2022-latest
```

