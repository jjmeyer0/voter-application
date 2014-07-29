package com.nerdery.voting.service;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.model.Vote;

public interface VoteService {
    /**
     * This method takes a {@link Game} object and will create a {@link Vote} based on
     * the given object.
     * @param game The {@link Game} object to cast the {@link Vote} on.
     * @return The {@link Vote} object created when casting a vote.
     */
    Vote castVote(Game game);
}
