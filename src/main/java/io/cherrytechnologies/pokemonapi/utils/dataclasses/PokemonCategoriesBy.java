package io.cherrytechnologies.pokemonapi.utils.dataclasses;

public class PokemonCategoriesBy {
    private Long id;
    private String name;
    private int baseExperience;

    private PokemonCategoriesBy(){}

    public static PokemonCategoriesBy factory(){
        return new PokemonCategoriesBy();
    }

    public Long getId() {
        return id;
    }

    public PokemonCategoriesBy setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PokemonCategoriesBy setName(String name) {
        this.name = name;
        return this;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public PokemonCategoriesBy setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
        return this;
    }
}
