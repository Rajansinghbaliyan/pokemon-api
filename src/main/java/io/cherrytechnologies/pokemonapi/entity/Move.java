package io.cherrytechnologies.pokemonapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@Entity
public class Move {
    @Id
    public long id;
    @ManyToOne
    @JoinColumn(name = "move_id")
    public Move2 move;

    public Move2 getMove() {
        return move;
    }

    public void setMove(Move2 move) {
        this.move = move;
    }
}
