# Stage 1: Build
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/cda-v1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
