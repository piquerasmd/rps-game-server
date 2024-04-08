# rps-game-server
A simple, engaging web application for the classic rock-paper-scissors game


## mysql 

[Docker image for Microsoft SQL Server 2022](https://learn.microsoft.com/es-es/sql/linux/quickstart-install-connect-docker?view=sql-server-ver16&tabs=cli&pivots=cs1-cmd)


### Crear la imagen de docker de SQL Server 2022

Traer la imagen de docker de SQL Server 2022, ejecutar el siguiente comando:
```bash
docker pull mcr.microsoft.com/mssql/server:2022-latest
```

Para crear la imagen de docker de SQL Server 2022, ejecutar el siguiente comando:
```bash 
docker run -e "ACCEPT_EULA=Y" \
-e "MSSQL_SA_PASSWORD=yourStrong<<#><Password" \
-e "MSSQL_PID=Evaluation" \
-p 1433:1433 \
--name sqlserver1 \
--hostname sqlserver1 \
-d mcr.microsoft.com/mssql/server:2022-latest
```

