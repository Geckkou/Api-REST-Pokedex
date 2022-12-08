FROM openjdk:11

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app/Pokedex-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","Pokedex-0.0.1-SNAPSHOT.jar"]