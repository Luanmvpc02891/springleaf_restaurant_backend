#Dockerfile for deploying the backend

#Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn -f pom.xml clean package -DskipTests

#Stage 2: Create the production image
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/springleaf_restaurant_backend-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
