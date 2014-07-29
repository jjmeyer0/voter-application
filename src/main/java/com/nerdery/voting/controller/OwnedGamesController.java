package com.nerdery.voting.controller;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Transactional
@Controller
public class OwnedGamesController {

    @Autowired
    private GameService gameService;

    static Logger logger = Logger.getLogger(OwnedGamesController.class.getName());

    @RequestMapping("/owned-games")
    public ModelAndView ownedGames() {
        List<Game> ownedGames = gameService.getOwnedGames();
        logger.log(Level.INFO, "We own this many games:" + ownedGames.size());

        return new ModelAndView("owned-games", "games", ownedGames);
    }

    @RequestMapping("/mark-games")
    public ModelAndView makeGames() {
        List<Game> wantedGames = gameService.getWantedGamesSortedByVoteCountLoadEagerly();
        ModelAndView modelAndView = new ModelAndView("mark-games", "wantedGames", wantedGames);
        modelAndView.addObject("markedGame", new Game());
        return modelAndView;
    }

    @RequestMapping(value = "/marked-game", method = RequestMethod.POST)
    public ModelAndView addGame(@ModelAttribute("markedGame") Game game) {
        ModelAndView modelAndView = new ModelAndView();

        if (game == null || game.getTitle() == null || "".equals(game.getTitle())) {
            modelAndView.setViewName("error-page");
            modelAndView.addObject("error", "A title must be selected.");
            return modelAndView;
        }

        game = gameService.getGameByTitle(game.getTitle());
        game.setIsOwned(true);
        gameService.save(game);

        modelAndView.setViewName("mark-game-success");
        modelAndView.addObject("game", game);

        return modelAndView;
    }
}
