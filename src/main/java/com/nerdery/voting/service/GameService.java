package com.nerdery.voting.service;

import com.nerdery.voting.model.Game;

import java.util.List;

public interface GameService {
    /**
     * TODO
     * @param title
     * @return
     */
    Game createGame(String title);

    /**
     * TODO
     * @param title
     * @param isOwned
     * @return
     */
    Game createGame(String title, Boolean isOwned);

    /**
     * TODO
     * @return
     */
    List<Game> getNonOwnedGamesSortedByVoteCountLoadEagerly();

    /**
     * TODO
     * @return
     */
    List<Game> getOwnedGames();

    /**
     * TODO
     * @return
     */
    List<Game> getAllGames();
}
