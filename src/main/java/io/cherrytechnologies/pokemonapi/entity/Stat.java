package io.cherrytechnologies.pokemonapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Stat {
    @Id
    public long id;
    public int base_stat;
    public int effort;
    @ManyToOne
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
