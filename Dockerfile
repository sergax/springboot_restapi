FROM openjdk:11
COPY ./build/libs/springboot_restapi-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
