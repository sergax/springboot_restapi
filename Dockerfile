#FROM openjdk:11
#
##aws S3
#ENV SPRING_DATASOURCE_URL=jdbc:mysql://db/database
#ENV AWS_ACCESS_KEY_ID=AKIAZLECZNGC32SFVC4E
#ENV AWS_SECRET_ACCESS_KEY=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
#
## instad employee-jdbc:app
#COPY ./build/libs/springboot_restapi-0.0.1-SNAPSHOT.jar app-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "app-0.0.1-SNAPSHOT.jar"]
#EXPOSE 8080:8080
