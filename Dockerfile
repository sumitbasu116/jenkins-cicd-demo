FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY ./target/jenkinsCiCdDemo.jar /app/

EXPOSE 8282

CMD ["java", "-jar", "jenkinsCiCdDemo.jar"]