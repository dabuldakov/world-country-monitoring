# Build stage
FROM gradle:8.8-jdk21 AS build

WORKDIR /app
COPY . .

RUN chmod +x gradlew && ./gradlew build --no-daemon -x test

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=build /app/infrastructure/build/libs/infrastructure.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
