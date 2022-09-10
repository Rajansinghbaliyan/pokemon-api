package io.cherrytechnologies.pokemonapi.utils.dataclasses;

import io.cherrytechnologies.pokemonapi.io.entity.Pokemon;

public class PokemonMaxDescription {
    private String attribute;
    private int value;
    private Pokemon pokemon;

    public String getAttribute() {
        return attribute;
    }

    public PokemonMaxDescription setAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    public int getValue() {
        return value;
    }

    public PokemonMaxDescription setValue(int value) {
        this.value = value;
        return this;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public PokemonMaxDescription setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        return this;
    }

    public static PokemonMaxDescription pokemonDescriptionBuilder(){
        return new PokemonMaxDescription();
    }
}
