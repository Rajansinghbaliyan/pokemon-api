package io.cherrytechnologies.pokemonapi.entity;

import javax.persistence.*;

@Entity
public class Type {
    @Id
    @GeneratedValue
    public long id;
    public int slot;
    @ManyToOne
    @JoinColumn(name = "type_id")
    public Type2 type;

    public Type2 getType() {
        return type;
    }

    public void setType(Type2 type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
