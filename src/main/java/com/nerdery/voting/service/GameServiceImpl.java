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
    public List<Game> getWantedGamesSortedByVoteCountLoadEagerly() {
        List<Game> allGamesWithVotes = gameRepository.findAllWantedGames();
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
        return title != null && getGameByTitle(title) != null;
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }
}
