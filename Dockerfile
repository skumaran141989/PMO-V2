FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG target/*.jar
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]