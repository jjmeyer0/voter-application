package com.nerdery.voting.controller;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class AddGameController {
    @Autowired
    private GameService gameService;

    static Logger logger = Logger.getLogger(AddGameController.class.getName());

    @RequestMapping("/add-game")
    public ModelAndView addGamePage() {
        return new ModelAndView("add-game", "game", new Game("asdf"));
    }

    @RequestMapping(value = "/save-game", method = RequestMethod.GET)
    public ModelAndView addGame(@ModelAttribute Game game) {
        game = gameService.createGame(game.getTitle(), Boolean.FALSE);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-game-success");
        modelAndView.addObject("game", game);

        return modelAndView;
    }
}
