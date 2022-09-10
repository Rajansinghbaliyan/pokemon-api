package io.cherrytechnologies.pokemonapi.io.entity;

import javax.persistence.*;

@Entity
public class Stat {
    @Id
    @GeneratedValue
    public long id;
    public int base_stat;
    public int effort;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stat_id")
    public Stat2 stat;

    public Stat2 getStat() {
        return stat;
    }

    public void setStat(Stat2 stat) {
        this.stat = stat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }
}
