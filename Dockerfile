FROM maven AS builder
WORKDIR /app
COPY pom.xml ./
COPY src ./src

RUN mvn clean package


FROM openjdk:21-
WORKDIR /app
COPY --from=builder /app/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]