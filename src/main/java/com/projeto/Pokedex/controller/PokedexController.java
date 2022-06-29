package com.projeto.Pokedex.controller;
import com.projeto.Pokedex.model.Pokemon;
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Pokemon>> findall(Pageable pageable) {
        Pageable sort = PageRequest.of(0,10, Sort.by("id"));
        return ResponseEntity.ok(pokedexRepository.findAll(sort));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pokedexService.buscarPokemon(id));
    }

    @GetMapping(value = "/nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Pokemon>> findByName(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(pokedexService.buscarPokemonNome(nome, pageable));
    }

    @GetMapping(value = "/numero", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> findBYNumero(@RequestParam int numero ) {
        return ResponseEntity.ok(pokedexService.buscarPokemonNumero(numero));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> save(@Valid @RequestBody Pokemon pokemon) {
        return ResponseEntity.ok(pokedexService.salvar(pokemon));
    }

    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> update(@PathVariable Long id,
                                          @Valid @RequestBody Pokemon pokemon) {
        return ResponseEntity.ok(pokedexService.atualizar(id, pokemon));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> delete(@PathVariable Long id) {
        return ResponseEntity.ok(pokedexService.excluir(id));
    }
}
