# OMS

Requirements
-
1) 1 running relational DB(Oracle/Postgresql/MSSQL/MySQL/HSQLDB)
2) JDK 17 or above.
3) Maven
4) GIT bash

Setting up OMS API Server
-
1) Clone backend server code from github repo : git@github.com:96-gabbar/OMS.git using git cmd.
2) Import in IntelliJ or Eclipse.
3) Run OrderManagementSystemStarter.
4) Check if application.properties file contains correct details of the DB you are going to use.
5) You should not see any issues/exceptions/errors in console if the server starts up.

Setup Local PostgreSQL DB
-
1) Install postgresql db locally referring postgres documention : https://www.postgresql.org/docs/16/tutorial-start.html
2) Once postgresql db is installed, create a new database named.(Note down username/password which will be needed in API server setup)
3) Install pgAdmin and configure your database server in pgAdmin.(Default hostname:port is localhost:5432)
