package com.nerdery.voting.service;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.model.Vote;

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
     * This will take the needed information for a {@link Game} and will create a {@link Game}.
     *
     * @param title the title of the game
     * @param isOwned whether the game is owned or not.
     * @return The newly created {@link Game} object.
     */
    Game save(String title, Boolean isOwned);

    /**
     * This will return a list of all the games that are owned.
     * @return A list of games the are owned.
     */
    List<Game> getOwnedGames();

    /**
     * This returns the {@link com.nerdery.voting.model.Game} associated with {@code title}.
     * @param title The title to find.
     * @return The {@link Game} object with the given title.
     */
    Game getGameByTitle(String title);

    /**
     * This will determine if a given {@code title} exists.
     * @param title The game title to find.
     * @return If the title exists then true otherwise false.
     */
    boolean doesGameExistByTitle(String title);

    /**
     * This method will update or create a new game.
     *
     * @param game The {@link Game} to update or save.
     * @return the newly update {@link Game}.
     */
    Game save(Game game);

    /**
     * This will get all of the {@link Game}s and their associated collections.
     * @return The list of {@link Game}s that are wanted.
     */
    List<Game> getWantedGamesSortedByVoteCountLoadEagerly();

    /**
     * This method takes a {@link Game} object and will create a {@link com.nerdery.voting.model.Vote} based on
     * the given object.
     * @param game The {@link Game} object to cast the {@link com.nerdery.voting.model.Vote} on.
     * @return The {@link com.nerdery.voting.model.Vote} object created when casting a vote.
     */
    Vote castVote(Game game);
}
