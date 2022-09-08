package io.cherrytechnologies.pokemonapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MoveLearnMethod {
    @Id
    @GeneratedValue
    public long id;
    public String name;
    public String url;
}
