package com.projeto.Pokedex.controller;
import com.projeto.Pokedex.mapper.PokemonMapper;
import com.projeto.Pokedex.model.Pokemon;
import com.projeto.Pokedex.model.dto.input.PokemonInput;
import com.projeto.Pokedex.model.dto.out.PokemonDto;
import com.projeto.Pokedex.repository.PokedexRepository;
import com.projeto.Pokedex.service.PokedexService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/pokedex")
@Api(value = "Pokedex API")
@CrossOrigin(origins = "*")

public class PokedexController {

    private PokedexRepository pokedexRepository;
    private PokedexService pokedexService;
    private PokemonMapper pokemonMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PokemonDto>> findall(Pageable pageable) {
        Pageable sort = PageRequest.of(0,10, Sort.by("id"));
        return ResponseEntity.ok(pokemonMapper.toCollectionModel(pokedexRepository.findAll(sort)));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PokemonDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pokedexService.buscarPokemon(id));
    }

    @GetMapping(value = "/nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PokemonDto>> findByName(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(pokedexService.buscarPokemonNome(nome, pageable));
    }

    @GetMapping(value = "/numero", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PokemonDto> findBYNumero(@RequestParam int numero ) {
        return ResponseEntity.ok(pokedexService.buscarPokemonNumero(numero));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PokemonDto> save(@Valid @RequestBody PokemonInput pokemon) {
        return ResponseEntity.ok(pokedexService.salvar(pokemon));
    }

    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PokemonDto> update(@PathVariable Long id,
                                          @Valid @RequestBody PokemonInput pokemon) {
        return ResponseEntity.ok(pokedexService.atualizar(id, pokemon));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PokemonDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(pokedexService.excluir(id));
    }
}
