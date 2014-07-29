package com.nerdery.voting.controller;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.service.GameService;
import com.nerdery.voting.service.VoteService;
import com.nerdery.voting.util.CookieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class VoteGamesController {
    @Autowired
    private GameService gameService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private CookieHelper cookieHelper;

    static Logger logger = Logger.getLogger(AddGameController.class.getName());

    @Transactional
    @RequestMapping(value = "/vote-games")
    public ModelAndView viewVoteableGames() {
        List<Game> games = gameService.getWantedGamesSortedByVoteCountLoadEagerly();
        return new ModelAndView("vote-games", "games", games);
    }

    @Transactional
    @RequestMapping(value = "/vote-for-game", method = RequestMethod.POST)
    public ModelAndView castVoteForGame(@Validated @ModelAttribute("wantedGame") Game game
            , BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        cookieHelper.hasVotedToday(request, result);
        cookieHelper.validateWeekday(result);

        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce(" ", String::concat);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error-page");
            modelAndView.addObject("error", errors);

            return modelAndView;
        }

        Game g = voteService.castVote(gameService.getGameByTitle(game.getTitle())).getGame();
        response.addCookie(cookieHelper.createCookieForToday("lastVote", LocalDate.now().toString()));
        return new ModelAndView("add-game-success", "game", g);
    }

    @Transactional
    @RequestMapping("/all-games")
    public ModelAndView allGames() {
        ModelAndView modelAndView = new ModelAndView("all-games");
        modelAndView.addObject("ownedGames", gameService.getOwnedGames());
        modelAndView.addObject("wantedGames", gameService.getWantedGamesSortedByVoteCountLoadEagerly());
        modelAndView.addObject("wantedGame", new Game());
        return modelAndView;
    }
}
