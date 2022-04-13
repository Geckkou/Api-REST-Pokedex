FROM openjdk:11
ADD target/pokedex-0.0.1-SNAPSHOT.jar pokedex-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pokedex-0.0.1-SNAPSHOT.jar"]