package io.cherrytechnologies.pokemonapi.services;

import io.cherrytechnologies.pokemonapi.io.entity.Pokemon;
import io.cherrytechnologies.pokemonapi.io.repository.PokemonRepository;
import io.cherrytechnologies.pokemonapi.ui.controllers.models.response.MessageResponse;
import io.cherrytechnologies.pokemonapi.ui.controllers.models.response.PokemonDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonAnalyticsService {
    @Autowired
    private PokemonRepository repository;
    private final String className = String.valueOf(PokemonAnalyticsService.class);

    public MessageResponse<PokemonDescription> getHeaviestPokemon() {

        Pokemon fetchedPokemon = repository
                .findAll()
                .parallelStream()
                .reduce((first, second) ->
                        first.weight > second.weight ? first : second
                )
                .orElseThrow(() -> new RuntimeException("Server Error"));


        return new MessageResponse<PokemonDescription>()
                .setMessage("The heaviest pokemon is:")
                .setData(
                        new PokemonDescription()
                                .setPokemon(fetchedPokemon)
                                .setDescription(
                                        String.format(
                                                "Name: %s and Weight: %d",
                                                fetchedPokemon.name,
                                                fetchedPokemon.weight
                                        )
                                )

                );

    }
}

