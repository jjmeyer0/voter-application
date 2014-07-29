package com.nerdery.voting.service;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.model.Vote;
import com.nerdery.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote castVote(Game game) {
        return voteRepository.save(new Vote(game));
    }
}
