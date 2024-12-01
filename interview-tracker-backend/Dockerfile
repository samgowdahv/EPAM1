FROM maven:3.8.3-openjdk-17 AS BUILD
COPY pom.xml /workdir/
COPY src /workdir/src
WORKDIR /workdir/
RUN mvn -f /workdir/pom.xml clean install -DskipTests=true -X

FROM openjdk:17-jdk-alpine
EXPOSE 8083
RUN mkdir /app
COPY --from=BUILD /workdir/target/*.jar /app/client-interview-tracker.jar
ENTRYPOINT ["java","-jar","/app/client-interview-tracker.jar"]