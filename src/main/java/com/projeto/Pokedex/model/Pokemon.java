package com.projeto.Pokedex.model;

import com.projeto.Pokedex.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "pokemon")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @NotNull(groups = ValidationGroups.PokemonId.class)
    private int id;

    @NotNull
    @Column(name = "numero")
    private int numero;

    @NotBlank
    @Column(name = "tipo_1")
    private String tipo_1;

    @Column(name = "tipo_2")
    private String tipo_2;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "peso")
    @Digits(integer = 3, fraction =2)
    private double peso;

    @NotNull
    @Column(name = "altura")
    @Digits(integer = 3, fraction = 2)
    private double altura;

    @Column(name = "descricao")
    private String descricao;


}
