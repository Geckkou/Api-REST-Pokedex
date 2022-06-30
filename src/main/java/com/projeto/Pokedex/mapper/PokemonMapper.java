package com.projeto.Pokedex.mapper;

import com.projeto.Pokedex.model.Pokemon;
import com.projeto.Pokedex.model.dto.input.PokemonInput;
import com.projeto.Pokedex.model.dto.out.PokemonDto;
import com.projeto.Pokedex.repository.PokedexRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PokemonMapper {
    
    private ModelMapper modelMapper;
    private PokedexRepository pokedexRepository;

    public PokemonDto toModel(Pokemon pokemon) {
        return modelMapper.map(pokemon, PokemonDto.class);
    }

    public Page<PokemonDto> toCollectionModel(Page<Pokemon> pokemon) {
        List<PokemonDto> poke = pokemon
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        return new PageImpl<>(poke);
    }

    public Pokemon toEntity(PokemonInput pokemonInput) {
        return modelMapper.map(pokemonInput, Pokemon.class);
    }
}
