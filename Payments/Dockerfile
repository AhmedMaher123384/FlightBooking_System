FROM openjdk:22
LABEL authors="ahmedmaher"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=cloud","/app.jar"]