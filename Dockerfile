# Stage 1. Building project
FROM gradle:8.4-jdk21-alpine AS builder
WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradlew ./
COPY gradle/ ./gradle/

RUN ./gradlew build --no-daemon || return 0

COPY src ./src

RUN ./gradlew build --no-daemon


# Stage 2. Creating runner
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]