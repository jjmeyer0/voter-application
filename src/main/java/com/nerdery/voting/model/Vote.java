package com.nerdery.voting.model;

import javax.persistence.*;

/**
 * This class defines what it means to be a vote. A vote has a many to one relation ship
 * with a {@link com.nerdery.voting.model.Game}
 */
@Entity
public class Vote extends TimeStampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Game game;

    protected Vote() {
    }

    /**
     * This creates a vote for the given {@link com.nerdery.voting.model.Game} object.
     * @param game The game to cast a vote for.
     */
    public Vote(Game game) {
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
