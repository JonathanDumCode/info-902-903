FROM maven:3.8.4-openjdk-17-slim AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package -DskipTests=true

FROM eclipse-temurin:17-jdk-focal
WORKDIR /opt
ENV PORT 8081
EXPOSE 8081
COPY --from=maven /usr/src/app/target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar