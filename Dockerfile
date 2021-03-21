FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/airport-management-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} airport-management-0.0.1.jar
ENTRYPOINT ["java","-jar","/airport-management-0.0.1.jar"]