# Step 1: Build Stage
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml ./

# Download dependencies to leverage Docker cache
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY . .

# Build the application, skipping tests
RUN mvn clean install -DskipTests

# Step 2: Runtime Stage
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built application JAR from the build stage
COPY --from=build /app/target/aws-0.0.1-SNAPSHOT.jar /app/aws-0.0.1-SNAPSHOT.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "aws-0.0.1-SNAPSHOT.jar"]
