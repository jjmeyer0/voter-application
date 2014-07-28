package com.nerdery.voting.service;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game createGame(String title) {
        return createGame(title, Boolean.FALSE);
    }

    @Override
    public Game createGame(String title, Boolean isOwned) {
        return gameRepository.save(new Game(title, isOwned));
    }

    @Override
    public List<Game> getNonOwnedGamesSortedByVoteCount() {
        return gameRepository.findAllGamesWithVotes();
    }

    @Override
    public List<Game> getOwnedGames() {
        return gameRepository.findAllGamesByIsOwned(Boolean.TRUE);
    }
}
