FROM openjdk:17

WORKDIR /app

COPY ./target/jenkinsCiCdDemo.jar /app/

EXPOSE 8282

CMD ["java", "-jar", "jenkinsCiCdDemo.jar"]