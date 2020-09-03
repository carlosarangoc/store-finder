# Jumbo Store Finder

This application finds the nearest Jumbo stores to a given location (longitude, latitude).

## API

The API is a spring boot application using Mongo DB as a repository.

For testing purposes, the class `com.carlosarango.jumbo.storefinder.dataloader.JsonDataLoader` loads the provided stores into the database during application startup.

### How to run it

* Clone this repository: `git clone `
* Make sure you have at least java 11: `java --version`
* (Optional) In order to run a local version of Mongo DB a `docker-compose.yaml` is provided. If you want to run Mongo DB locally then make sure you have **docker** and **docker-compose** installed.
    * `docker --version`
    * `docker-compose --version`
    * Run mongo locally `docker-compose up -d`
    * If you want to connect to a different Mongo DB then edit the connection attributes at: `src/main/resources/application.yaml`
* Start the application
    * If you have maven installed then run: `mvn spring-boot:run`
    * Or if you do not have maven then run:
        * Windows: `mvnw.cmd spring-boot:run`
        * MacOS/Linux: `./mvnw spring-boot:run`
* The application should be available at: `http://localhost:8080`

### Finding the nearest stores

* Run an HTTP GET request:

`curl http://localhost:8080/stores?lon=1&lat=1&size=5`

The request has 3 parameters:
* **lon**: (REQUIRED) The longitude of the current location.
* **lat**: (REQUIRED) The latitude of the current location.
* **size**: (OPTIONAL) The amount of stores to find. Defaults to 5.

