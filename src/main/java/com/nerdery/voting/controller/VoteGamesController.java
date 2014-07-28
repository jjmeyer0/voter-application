package com.nerdery.voting.controller;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.service.GameService;
import com.nerdery.voting.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VoteGamesController {
    @Autowired
    private GameService gameService;

    @Autowired
    private VoteService voteService;

    @Transactional
    @RequestMapping(value = "/vote-games")
    public ModelAndView viewVoteableGames() {
        // TODO I would usually page this. If there were a large amount you would have to.

        List<Game> games = gameService.getNonOwnedGamesSortedByVoteCountLoadEagerly();
        return new ModelAndView("vote-games", "games", games);
    }

    @Transactional
    @RequestMapping(value = "/vote-for-game", method = RequestMethod.GET)
    public ModelAndView castVoteForGame(@ModelAttribute Game game) {
        voteService.castVote(game);
        // TODO
        return new ModelAndView();
    }
}
