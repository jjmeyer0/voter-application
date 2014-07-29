package com.nerdery.voting.controller;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.service.GameService;
import com.nerdery.voting.service.VoteService;
import com.nerdery.voting.util.CookieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.logging.Logger;

@Controller
public class AddGameController {
    @Autowired
    private VoteService voteService;

    @Autowired
    private GameService gameService;

    @Autowired
    private CookieHelper cookieHelper;

    static Logger logger = Logger.getLogger(AddGameController.class.getName());

    @RequestMapping("/add-game")
    public ModelAndView addGamePage() {
        return new ModelAndView("add-game", "game", new Game("", Boolean.FALSE));
    }

    @RequestMapping(value = "/save-game", method = RequestMethod.POST)
    public ModelAndView addGame(@Validated @ModelAttribute Game game, BindingResult results
            , HttpServletResponse response, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        // These should be validators
        cookieHelper.validateWeekday(results);
        //cookieHelper.hasVotedToday(request, results); TODO add this back

        if (gameService.doesGameExistByTitle(game.getTitle())) {
            results.addError(new ObjectError("gameExists", "The title already exists."));
        }

        if (results.hasErrors()) {
            String errors = "";
            for (ObjectError oe : results.getAllErrors()) {
                errors += " " + oe.getDefaultMessage();
            }

            modelAndView.setViewName("error-page");
            modelAndView.addObject("error", errors);
        } else {
            game.setIsOwned(false);
            voteService.castVote(game);

            response.addCookie(cookieHelper.createCookieForToday("lastVote", LocalDate.now().toString()));
            modelAndView.setViewName("add-game-success");
            modelAndView.addObject("game", game);
        }

        return modelAndView;
    }
}
