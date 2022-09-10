package io.cherrytechnologies.pokemonapi.utils.dataclasses;

import io.cherrytechnologies.pokemonapi.utils.RestUtils;

import java.util.Comparator;


public class PokemonCategoriesBy implements Comparator<PokemonCategoriesBy>, Comparable<PokemonCategoriesBy> {
    private String id;
    private String name;
    private String categoryType;


    private int baseExperience;

    private PokemonCategoriesBy() {
    }

    public String getCategoryType() {
        return categoryType;
    }

    public PokemonCategoriesBy setCategoryType(String categoryType) {
        this.categoryType = categoryType;
        return this;
    }

    public static PokemonCategoriesBy factory() {
        return new PokemonCategoriesBy();
    }

    public String getId() {
        return id;
    }

    public PokemonCategoriesBy setId(Long id) {
        this.id = RestUtils.getBasePokemonUrl(id);
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

    @Override
    public int compare(PokemonCategoriesBy o1, PokemonCategoriesBy o2) {
        return Integer.compare(o1.baseExperience, o2.baseExperience);
    }

    @Override
    public int compareTo(PokemonCategoriesBy o) {
        return Integer.compare(this.baseExperience,o.baseExperience);
    }
}
