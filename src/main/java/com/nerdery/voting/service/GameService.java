package com.nerdery.voting.service;

import com.nerdery.voting.model.Game;

import java.util.List;

public interface GameService {
    /**
     * This will create a {@link Game} and will return the {@link Game} object that
     * was persisted.
     *
     * @param title The title to give the new {@link Game}.
     * @return The {@link Game} object that was created.
     */
    Game createGame(String title);

    /**
     *
     * @param title
     * @param isOwned
     * @return
     */
    Game createGame(String title, Boolean isOwned);

    /**
     * TODO
     * @return
     */
    List<Game> getOwnedGames();

    /**
     * TODO
     * @param title
     * @return
     */
    Game getGameByTitle(String title);

    /**
     * TODO
     * @param title
     * @return
     */
    boolean doesGameExistByTitle(String title);

    /**
     * TODO
     *
     * @param game
     * @return
     */
    Game save(Game game);

    /**
     * This will get all of the {@link Game}s and their associated collections.
     * @return The list of {@link Game}s that are wanted.
     */
    List<Game> getWantedGamesSortedByVoteCountLoadEagerly();
}
