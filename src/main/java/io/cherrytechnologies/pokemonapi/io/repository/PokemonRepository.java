package io.cherrytechnologies.pokemonapi.io.repository;

import io.cherrytechnologies.pokemonapi.io.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> getById(long id);
    Optional<Pokemon> getByName(String name);

    List<Optional<Pokemon>> findAllByIdBetween(long start,long end);
}
