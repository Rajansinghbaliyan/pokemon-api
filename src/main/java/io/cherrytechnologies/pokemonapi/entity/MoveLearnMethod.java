package io.cherrytechnologies.pokemonapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MoveLearnMethod {
    @Id
    public long id;
    public String name;
    public String url;
}
