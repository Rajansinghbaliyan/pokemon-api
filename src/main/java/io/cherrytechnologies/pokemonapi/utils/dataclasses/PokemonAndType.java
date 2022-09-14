package io.cherrytechnologies.pokemonapi.utils.dataclasses;

import io.cherrytechnologies.pokemonapi.utils.RestUtils;

public class PokemonAndType {
    private String type;
    private String pokemonName;
    private String pokemonUrl;

    private PokemonAndType(){

    }

    public static PokemonAndType factory(){
        return new PokemonAndType();
    }

    public String getType() {
        return type;
    }

    public PokemonAndType setType(String type) {
        this.type = type;
        return this;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public PokemonAndType setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
        return this;
    }

    public String getPokemonUrl() {
        return pokemonUrl;
    }

    public PokemonAndType setPokemonUrl(long id) {
        this.pokemonUrl =  RestUtils.getBasePokemonUrl(id);
        return this;
    }
}
