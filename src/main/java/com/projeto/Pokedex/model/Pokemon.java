package com.projeto.Pokedex.model;

import com.projeto.Pokedex.ValidationGroups;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @NotNull(groups = ValidationGroups.PokemonId.class)
    private Long id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pokemon pokemon = (Pokemon) o;
        return id != null && Objects.equals(id, pokemon.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
