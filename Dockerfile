FROM maven:3.8.5-jdk-8-slim as build
WORKDIR /namjai-be
COPY src ./src
COPY pom.xml ./
RUN mvn clean install -DskipTests 


FROM adoptopenjdk/openjdk8:alpine-slim as deploy
ARG JAR_FILE=/namjai-be/target/namjai-0.0.1-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} namjai-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","namjai-0.0.1-SNAPSHOT.jar"]