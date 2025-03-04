FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY target/txtarjetas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8095

ENTRYPOINT ["java", "-jar", "app.jar"]