FROM openjdk:11

ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/springboot_restapi
ENV AWS_ACCESS_KEY_ID=AKIAZLECZNGC32SFVC4E
ENV AWS_SECRET_ACCESS_KEY=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

COPY ./build/libs/springboot_restapi-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
