package com.nerdery.voting.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This entity defines what a game is. A game has a title, a one to many relationship to votes, and
 * defines whether that title is owned yet or not.
 */
@Entity
public class Game extends TimeStampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Boolean isOwned;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL) // TODO: look into this
    private Set<Vote> votes;

    protected Game() {
    }

    /**
     * Create a new game object with the title supplied. By default this this title is not owned.
     *
     * @param title The title of the XBox game.
     */
    public Game(String title) {
        this.title = title;
        this.isOwned = Boolean.FALSE;
    }

    /**
     * This constructor will take a title, whether said title is owned, and will construct a new
     * {@link Game} object.
     *
     * @param title The title of the game object being created.
     * @param isOwned Whether the title of the game object is owned or not.
     */
    public Game(String title, Boolean isOwned) {
        this.title = title;
        this.isOwned = isOwned;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isOwned() {
        return isOwned;
    }

    public void setIsOwned(Boolean owned) {
        this.isOwned = owned;
    }

    public Set<Vote> getVotes() {
        if (votes == null) {
            votes = new HashSet<Vote>();
        }

        return votes;
    }
}
