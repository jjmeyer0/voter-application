package com.nerdery.voting.service;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.model.Vote;
import com.nerdery.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository voteRepository;

    /**
     * TODO
     * @param game
     * @return
     */
    @Override
    public Long castVote(Game game) {
        return voteRepository.save(new Vote(game)).getId();
    }
}
