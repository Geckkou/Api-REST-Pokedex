FROM openjdk:11
WORKDIR /app
ADD ./out/artifacts/Pokedex_jar/Pokedex.jar Pokedex.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Pokedex.jar"]