package io.cherrytechnologies.pokemonapi.io.entity;

import javax.persistence.*;

@Entity
public class Move {
    @Id
    @GeneratedValue
    public long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "move_id")
    public Move2 move;

    public Move2 getMove() {
        return move;
    }

    public void setMove(Move2 move) {
        this.move = move;
    }
}
