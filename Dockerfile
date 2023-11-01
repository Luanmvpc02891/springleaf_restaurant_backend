#Deploy backend-end
#stage1
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY ./springleaf_restaurant_backend ./springleaf_restaurant_backend
RUN mvn -f springleaf_restaurant_backend/pom.xml clean package -DskipTests

#stage2
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app/springleaf_restaurant_backend/target/springleaf_restaurant_backend-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
