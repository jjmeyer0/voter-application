package com.nerdery.voting.service;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.model.Vote;
import com.nerdery.voting.repository.GameRepository;
import com.nerdery.voting.repository.VoteRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Game createGame(String title) {
        return save(title, Boolean.FALSE);
    }

    @Override
    public Game save(String title, Boolean isOwned) {
        return gameRepository.save(new Game(title, isOwned));
    }

    @Override
    public List<Game> getWantedGamesSortedByVoteCountLoadEagerly() {
        List<Game> allGamesWithVotes = gameRepository.findAllWantedGames();
        Hibernate.initialize(allGamesWithVotes);
        return allGamesWithVotes;
    }

    @Override
    public List<Game> getOwnedGames() {
        return gameRepository.findAllGamesByIsOwned(Boolean.TRUE);
    }

    @Override
    public Game getGameByTitle(String title) {
        return gameRepository.findByTitle(title);
    }

    @Override
    public boolean doesGameExistByTitle(String title) {
        return getGameByTitle(title) != null;
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Vote castVote(Game game) {
        return voteRepository.save(new Vote(game));
    }
}
