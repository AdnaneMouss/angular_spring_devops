# Use an official JDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/inventoryapp-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot app listens on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
