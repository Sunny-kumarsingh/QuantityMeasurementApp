# ==========================================
# BUILD STAGE
# ==========================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build

# Copy the entire multi-module project (including parent pom.xml)
COPY . .

# Build all modules, bypassing tests to speed up the build image creation
RUN mvn clean package -DskipTests

# ==========================================
# RUN STAGE
# ==========================================
FROM eclipse-temurin:17-jre-alpine

# Use an ARG to determine which microservice to copy from the build stage
ARG MODULE
ENV MODULE_NAME=${MODULE}

WORKDIR /app

# Copy the specific module's jar file. 
# Wildcard is used to ignore the specific version number in the jar file name.
COPY --from=build /build/${MODULE}/target/*.jar app.jar

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
