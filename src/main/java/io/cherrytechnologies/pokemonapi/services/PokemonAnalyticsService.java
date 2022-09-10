package io.cherrytechnologies.pokemonapi.services;

import io.cherrytechnologies.pokemonapi.io.entity.Pokemon;
import io.cherrytechnologies.pokemonapi.io.repository.PokemonRepository;
import io.cherrytechnologies.pokemonapi.ui.controllers.models.response.MessageResponse;
import io.cherrytechnologies.pokemonapi.ui.controllers.models.response.PokemonDescription;
import io.cherrytechnologies.pokemonapi.utils.dataclasses.PokemonCategoriesBy;
import io.cherrytechnologies.pokemonapi.utils.dataclasses.PokemonMaxDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PokemonAnalyticsService {
    @Autowired
    private PokemonRepository repository;
    private final String className = String.valueOf(PokemonAnalyticsService.class);

    public MessageResponse<PokemonDescription> getHeaviestPokemon() {

//        Pokemon fetchedPokemon = repository
//                .findAll()
//                .parallelStream()
//                .reduce((first, second) ->
//                        first.weight > second.weight ? first : second
//                )
//                .orElseThrow(() -> new RuntimeException("Server Error"));

        // More efficient way could also use maxBy
        Pokemon fetchedPokemon = repository
                .findAll()
                .parallelStream()
                .max(Comparator.comparing(Pokemon::getWeight))
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

    public MessageResponse<Map<String, PokemonMaxDescription>> getMaxPokemonsForEachAttribute() {
        String baseExperience = "base_experience";
        String weight = "weight";
        String height = "height";
        Map<String, PokemonMaxDescription> resultMap = new HashMap<>();
        resultMap.put(baseExperience, PokemonMaxDescription
                .pokemonDescriptionBuilder()
                .setValue(0)
                .setAttribute("Base Experience")
                .setPokemon(null)
        );
        resultMap.put(weight, PokemonMaxDescription
                .pokemonDescriptionBuilder()
                .setValue(0)
                .setAttribute("weight")
                .setPokemon(null)
        );
        resultMap.put(height, PokemonMaxDescription
                .pokemonDescriptionBuilder()
                .setValue(0)
                .setAttribute("height")
                .setPokemon(null)
        );
        repository
                .findAll()
                .parallelStream()
                .forEach(pokemon -> {
                    if (pokemon.base_experience > resultMap.get(baseExperience).getValue())
                        resultMap.get(baseExperience).setValue(pokemon.getBase_experience())
                                .setPokemon(pokemon);
                    if (pokemon.weight > resultMap.get(weight).getValue())
                        resultMap.get(weight).setValue(pokemon.getWeight())
                                .setPokemon(pokemon);
                    if (pokemon.height > resultMap.get(height).getValue())
                        resultMap.get(height).setValue(pokemon.getHeight())
                                .setPokemon(pokemon);
                });
        return new MessageResponse<Map<String, PokemonMaxDescription>>()
                .setMessage("Maxed Pokemon for each attribute")
                .setData(resultMap);
    }

    public MessageResponse<Map<String,
            Collection<PokemonCategoriesBy>>> getPokemonByCategories() {
        return new MessageResponse<Map<String,
                Collection<PokemonCategoriesBy>>>()
                .setMessage("Pokemon grouped in categories")
                .setData(
                        repository
                                .findAll()
                                .parallelStream()
                                .flatMap(pokemon -> pokemon.types
                                        .parallelStream()
                                        .map(type ->
                                                PokemonCategoriesBy
                                                        .factory()
                                                        .setCategoryType(type.type.name)
                                                        .setId(pokemon.id)
                                                        .setName(pokemon.name)
                                                        .setBaseExperience(pokemon.base_experience))
                                )
                                .collect(
                                        Collectors
                                                .groupingBy(PokemonCategoriesBy::getCategoryType,
                                                        Collectors.toCollection(TreeSet::new)
                                                )
                                )
                );
    }
}

