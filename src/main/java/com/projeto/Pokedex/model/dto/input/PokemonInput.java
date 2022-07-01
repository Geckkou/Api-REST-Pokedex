package com.projeto.Pokedex.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PokemonInput {

    @NotBlank
    private String Nome;

    @NotNull
    private int Numero;

    @NotBlank
    private String Tipo_1;

    private String Tipo_2;

    @NotNull
    private double Peso;

    @NotNull
    private double Altura;

    private String Descricao;
}
