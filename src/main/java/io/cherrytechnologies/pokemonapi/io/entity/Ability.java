package io.cherrytechnologies.pokemonapi.io.entity;

import javax.persistence.*;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
@Entity
public class Ability {
    @Id
    @GeneratedValue
    public long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ability_id")
    public Ability2 ability;
    public boolean is_hidden;
    public int slot;

    public Ability2 getAbility() {
        return ability;
    }

    public void setAbility(Ability2 ability) {
        this.ability = ability;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
