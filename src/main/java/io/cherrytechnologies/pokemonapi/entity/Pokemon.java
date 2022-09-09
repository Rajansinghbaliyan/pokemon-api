package io.cherrytechnologies.pokemonapi.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pokemon{
    @Id
    public int id;
    @OneToMany
    public Set<Ability> abilities;
    public int base_experience;
    @OneToMany
    public Set<Form> forms;
    public int height;
    public boolean is_default;
    public String location_area_encounters;
    @OneToMany
    public Set<Move> moves;
    public String name;
    public int order;
    @ManyToOne
    @JoinColumn(name = "species_id")
    public Species species;
    @OneToMany
    public Set<Stat> stats;
    @OneToMany
    public Set<Type> types;
    public int weight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public Set<Form> getForms() {
        return forms;
    }

    public void setForms(Set<Form> forms) {
        this.forms = forms;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    public Set<Move> getMoves() {
        return moves;
    }

    public void setMoves(Set<Move> moves) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Set<Stat> getStats() {
        return stats;
    }

    public void setStats(Set<Stat> stats) {
        this.stats = stats;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}


