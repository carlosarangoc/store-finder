# Jumbo Store Finder

This application finds the nearest Jumbo stores to a given location (longitude, latitude).

Test it:
```
curl https://store-finder-api-xsilgukcba-uc.a.run.app/stores?lat=50.827242&lon=5.726086&size=3
```

A front end application ([soure](https://github.com/carlosarangoc/store-finder-ui)) is available too at https://store-finder-ui.vercel.app/

## API

The API is a spring boot application using Mongo DB as a repository.

For testing purposes, the class `com.carlosarango.jumbo.storefinder.dataloader.JsonDataLoader` loads the provided stores into the database during application startup.

### Finding the nearest stores

* Run an HTTP GET request:

`curl http://localhost:8080/stores?lon=1&lat=1&size=5`

The request has 3 parameters:

* **lon**: (REQUIRED) The longitude of the current location.
* **lat**: (REQUIRED) The latitude of the current location.
* **size**: (OPTIONAL) The amount of stores to find. Defaults to 5.

### How to run it

* Clone this repository: `git clone git@github.com:carlosarangoc/store-finder.git`
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

### Building, running and pushing a docker image

* Build a new image:

`docker build -t carlosarangoc/store-finder:latest . `

* Run the image:

`docker run -p 8080:8080 -e "SPRING_PROFILE=prod" -e "MONGO_USER=user" -e "MONGO_PASSWORD=secret" carlosarangoc/store-finder`

* Push the image: 

`docker push carlosarangoc/store-finder:latest`

### Resources

This application was built using:

* Java 11
* Spring boot
* Maven
* Mongo DB
* Spring data mongo
* Spring Web MVC
