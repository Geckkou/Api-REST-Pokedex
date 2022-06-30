package com.projeto.Pokedex.model.dto.out;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PokemonDto {

    private Long id;

    @NotBlank
    private String Nome;

    @NotBlank
    private String Tipo_1;

    private String Tipo_2;

    @NotNull
    private int Numero;

    @NotNull
    @Digits(integer = 3, fraction =2)
    private double Peso;

    @NotNull
    @Digits(integer = 3, fraction = 2)
    private double Altura;

    private String Descricao;
}
