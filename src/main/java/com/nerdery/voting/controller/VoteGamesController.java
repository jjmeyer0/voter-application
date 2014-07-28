package com.nerdery.voting.controller;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VoteGamesController {
    @Autowired
    private GameService gameService;

    @Transactional
    @RequestMapping(value = "/vote-games")
    public ModelAndView viewVoteableGames() {
        // TODO I would usually page this. If there were a large amount you would have to.

        List<Game> games = gameService.getNonOwnedGamesSortedByVoteCount();
        return new ModelAndView("vote-games", "games", games);
    }
}
