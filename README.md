# Your Car Your Way - Backend

## Description
The project is the backend for a proof of concept, for the OpenClassrooms project "Définissez une solution fonctionnelle et concevez l’architecture d’une application."

## Prerequisites
Before you begin, ensure you have met the following requirements:
- You have installed the latest version of Java and Maven.

## Test Users

For your convenience, two test users will be initialized once you complete the backend installation steps. 
You can then use these credentials to access the frontend of the application.
To try out the websocket chat feature using these two users, login as each test user on a different browser.

  User with "Employee" role:
  - email : `ht@oc.fr`
  - username : `heather`
  - password : `code1234`
  
  User with "Client" role:
  - email : `emma@oc.fr`
  - username : `emma`
  - password : `emma1234`

## Backend installation and launch

### 1. Clone the repository

  ```bash
  git clone https://github.com/taylorfullstack/poc_backend.git
  ```

### 2. Update the application.properties file

  You need to update the application properties file with the correct client url and jwt secret variables.

  You may use a .env file (recommended), or replace the variables in the resources/application.properties file directly.
  
  The following variables need to updated with your own values:
  
  ```properties
  jwt.secret=${JWT_SECRET} #a secure, random string (UUID recommended)
  client.url=${CLIENT_URL} #example: http://localhost:4200
  ```

  *Note* - You can optionally change the server.url via the application properties file. The default url is `http://localhost:8080`
  
  If you change the server url, be sure to change it [here in the frontend environments directory](https://github.com/taylorfullstack/poc_frontend/tree/main/src/environments) as well.

### 3. Build the project and install its dependencies

  ```bash
  mvn clean install
  ```

### 4. Launch the backend of the application

  ```bash
  mvn spring-boot:run
  ```

Once the server is running locally, you can access the API endpoints at `http://localhost:8080/api/`.


## Frontend installation and launch

### 1. Follow the instructions [here in the ReadMe for the frontend](https://github.com/taylorfullstack/poc_frontend/blob/main/README.md)
