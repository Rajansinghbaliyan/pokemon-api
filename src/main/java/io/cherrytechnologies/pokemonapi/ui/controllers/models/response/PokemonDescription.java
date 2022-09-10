package io.cherrytechnologies.pokemonapi.ui.controllers.models.response;

import io.cherrytechnologies.pokemonapi.io.entity.Pokemon;

public class PokemonDescription {
    private String description;
    private Pokemon pokemon;

    public String getDescription() {
        return description;
    }

    public PokemonDescription setDescription(String description) {
        this.description = description;
        return this;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public PokemonDescription setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        return this;
    }
}
