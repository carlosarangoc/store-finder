FROM maven:3.6-jdk-11 as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

FROM openjdk:11.0.6-jdk-slim

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/store-finder-*.jar /store-finder.jar

# Run the web service on container startup.
CMD ["bash", "-c", "java -DXmx512m \
-Dspring.profiles.active=${SPRING_PROFILE} \
-DMONGO_USER=${MONGO_USER} \
-DMONGO_PASSWORD=${MONGO_PASSWORD} \
-jar /store-finder.jar"]