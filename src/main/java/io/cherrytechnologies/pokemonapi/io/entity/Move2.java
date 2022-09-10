package io.cherrytechnologies.pokemonapi.io.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Move2 {
    @Id
    @GeneratedValue
    public long id;
    public String name;
    public String url;
}
