package com.projeto.Pokedex.repository;

import com.projeto.Pokedex.model.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface PokedexRepository extends JpaRepository<Pokemon, Long> {
    Optional<Page<Pokemon>> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);
    Optional<Pokemon> findByNumero(int numero);
    Optional<Pokemon> findByNomeIgnoreCase(String nome);
}
