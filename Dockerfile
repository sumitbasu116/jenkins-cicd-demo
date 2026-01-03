FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY ./target/jenkinsCiCdDemo.jar /app/

EXPOSE 8282

CMD ["java", "-jar", "jenkinsCiCdDemo.jar"]