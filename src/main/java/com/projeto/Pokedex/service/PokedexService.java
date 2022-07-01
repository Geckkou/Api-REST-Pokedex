package com.projeto.Pokedex.service;

import com.projeto.Pokedex.exceptions.NotFoundException;
import com.projeto.Pokedex.exceptions.PokedexException;
import com.projeto.Pokedex.mapper.PokemonMapper;
import com.projeto.Pokedex.model.Pokemon;
import com.projeto.Pokedex.model.dto.input.PokemonInput;
import com.projeto.Pokedex.model.dto.out.PokemonDto;
import com.projeto.Pokedex.repository.PokedexRepository;
import com.projeto.Pokedex.util.MessageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PokedexService {

    private PokedexRepository pokedexRepository;
    private PokemonMapper pokemonMapper;

    public PokemonDto buscarPokemon(Long id) {
        return pokedexRepository.findById(id)
                .map(pokemonMapper::toModel)
                .orElseThrow(NotFoundException::new);
    }

    public PokemonDto buscarPokemonNumero(int numero) {
        return pokedexRepository.findByNumero(numero)
                .map(pokemonMapper::toModel)
                .orElseThrow(NotFoundException::new);
    }

    public Page<PokemonDto> buscarPokemonNome(String nome, Pageable pageable) {
        Page<PokemonDto> pokemon = pokedexRepository.findByNomeIgnoreCaseContaining(nome, pageable)
                .map(pokemonMapper::toCollectionModel)
               .orElseThrow(NotFoundException::new);

        if(pokemon.isEmpty()) throw new PokedexException(MessageUtils.POKEMON_NOT_EXIST);

        return pokemon;
    }

    @Transactional
    public PokemonDto salvar(PokemonInput pokemon) {

        Pokemon poke = pokemonMapper.toEntity(pokemon);

        boolean nomeEmUso = pokedexRepository.findByNomeIgnoreCase(poke.getNome())
                .stream()
                .anyMatch(pokemonExistente -> !pokemonExistente.equals(pokemon));

        boolean numeroEmUso = pokedexRepository.findByNumero(poke.getNumero())
                .stream()
                .anyMatch(pokemonExistente -> !pokemonExistente.equals(pokemon));

        if(nomeEmUso || numeroEmUso) throw new PokedexException(MessageUtils.POKEMON_ALREADY_EXIST);

        Pokemon pokemonCriado = pokedexRepository.save(poke);
        return pokemonMapper.toModel(pokemonCriado);
    }

    public Pokemon buscar(Long id) {
        return pokedexRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public PokemonDto excluir(Long id) {
        PokemonDto pokemon = this.buscarPokemon(id);

        pokedexRepository.deleteById(pokemon.getId());
        return pokemon;
    }

    @Transactional
    public PokemonDto atualizar(Long id, PokemonInput pokemon) {
        Pokemon poke = this.buscar(id);

        Pokemon pokemonToUpdate = pokemonMapper.toEntity(pokemon);

        pokemonToUpdate.setId(poke.getId());
        Pokemon pokemonUpdate = pokedexRepository.save(pokemonToUpdate);
        return pokemonMapper.toModel(pokemonUpdate);
    }
}
