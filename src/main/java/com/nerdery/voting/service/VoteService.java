package com.nerdery.voting.service;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.model.Vote;

public interface VoteService {
    /**
     * TODO
     * @param game
     * @return
     */
    Vote castVote(Game game);
}
