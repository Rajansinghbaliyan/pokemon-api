package io.cherrytechnologies.pokemonapi.entity.repository;

import io.cherrytechnologies.pokemonapi.entity.Pokemon;
import io.cherrytechnologies.pokemonapi.services.PokemonConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> getById(long id);
    Optional<Pokemon> getByName(String name);
}
