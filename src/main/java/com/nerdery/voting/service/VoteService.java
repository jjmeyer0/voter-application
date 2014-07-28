package com.nerdery.voting.service;

import com.nerdery.voting.model.Game;

public interface VoteService {
    /**
     * TODO
     * @param game
     * @return
     */
    Long castVote(Game game);
}
