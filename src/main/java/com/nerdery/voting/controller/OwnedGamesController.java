package com.nerdery.voting.controller;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class OwnedGamesController {

    @Autowired
    private GameService gameService;

    static Logger logger = Logger.getLogger(OwnedGamesController.class.getName());

    @Transactional
    @RequestMapping("/owned-games")
    public ModelAndView ownedGames() {
        List<Game> ownedGames = gameService.getOwnedGames();
        logger.log(Level.INFO, "We own this many games:" + ownedGames.size());

        return new ModelAndView("owned-games", "games", ownedGames);
    }
}
