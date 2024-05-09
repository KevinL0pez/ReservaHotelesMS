FROM maven:3.8.1-openjdk-17-slim as build
WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /usr/app
COPY --from=build /usr/src/app/target/ReservaHotelesMs-1.0.0.jar .
EXPOSE 8081
CMD ["java", "-jar", "ReservaHotelesMs-1.0.0.jar"]
