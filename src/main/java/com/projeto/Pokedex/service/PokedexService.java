package com.projeto.Pokedex.service;

import com.projeto.Pokedex.exceptions.PokedexException;
import com.projeto.Pokedex.model.Pokemon;
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

    public Pokemon buscarPokemon(Long id) {
        return pokedexRepository.findById(id)
                .orElseThrow(() -> new PokedexException(MessageUtils.POKEMON_NOT_EXIST));
    }

    public Page<Pokemon> buscarPokemonNome(String nome, Pageable pageable) {
        Page<Pokemon> pokemon = pokedexRepository.findByNomeIgnoreCaseContaining(nome, pageable)
               .orElseThrow(() -> new PokedexException(MessageUtils.POKEMON_NOT_EXIST));

        if(pokemon.isEmpty()) throw new PokedexException(MessageUtils.POKEMON_NOT_EXIST);

        return pokemon;
    }

    public Pokemon buscarPokemonNumero(int numero) {
        return pokedexRepository.findByNumero(numero)
                .orElseThrow(() -> new PokedexException(MessageUtils.POKEMON_NOT_EXIST));
    }

    @Transactional
    public Pokemon salvar(Pokemon pokemon) {
        boolean nomeEmUso = pokedexRepository.findByNomeIgnoreCase(pokemon.getNome())
                .stream()
                .anyMatch(pokemonExistente -> !pokemonExistente.equals(pokemon));

        boolean numeroEmUso = pokedexRepository.findByNumero(pokemon.getNumero())
                .stream()
                .anyMatch(pokemonExistente -> !pokemonExistente.equals(pokemon));

        if(nomeEmUso | numeroEmUso) throw new PokedexException(MessageUtils.POKEMON_ALREADY_EXIST);

        return pokedexRepository.save(pokemon);
    }

    @Transactional
    public Pokemon excluir(Long id) {
        Pokemon pokemon = this.buscarPokemon(id);

        pokedexRepository.deleteById(pokemon.getId());
        return pokemon;
    }

    @Transactional
    public Pokemon atualizar(Long id, Pokemon pokemon) {
        Pokemon poke = this.buscarPokemon(id);

        pokemon.setId(poke.getId());
        pokemon = pokedexRepository.save(pokemon);

        return pokemon;
    }
}
