package com.projeto.Pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
		System.out.println("API URL : http://localhost:8080/swagger-ui.html#/");
		System.out.println("WEB URL: https://api-rest-pokedex.herokuapp.com/swagger-ui.html#/");

	}

}
