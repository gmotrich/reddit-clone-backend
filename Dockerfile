# Use an official Maven image as the base image
FROM maven:latest as build
# Set the working directory in the container
WORKDIR /app
# Copy the Maven project files (pom.xml) into the container
COPY pom.xml .
# Download the project dependencies (only if pom.xml changed)
RUN mvn dependency:go-offline -B

# Copy the source code into the container
COPY src/ ./src/
# Build the Maven project
RUN mvn package -DskipTests

# Use a smaller and more lightweight base image to run the application
FROM openjdk:17
# Set the working directory in the container
WORKDIR /app
COPY .env /app/.env
# Copy the built artifact from the previous stage into the container
COPY --from=build /app/target/taskmanager-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]