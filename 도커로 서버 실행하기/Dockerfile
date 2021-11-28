FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar

COPY application.yml application.yml
COPY application-dev.yml application-dev.yml
COPY ${JAR_FILE} app.jar
ENV	PROFILE default
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]

# docker build -t example/spring-docker .
# docker run -d -p 8080:8282 --rm --name example-dev -e PROFILE=DEV example/spring-docker
# docker ps -a
# docker [start, restart, stop] example-dev
# docker rm example-dev