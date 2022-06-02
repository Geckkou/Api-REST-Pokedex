FROM openjdk:11

WORKDIR /app

ADD target/Pokedex-0.0.1-SNAPSHOT.jar Pokedex-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","Pokedex-0.0.1-SNAPSHOT.jar"]