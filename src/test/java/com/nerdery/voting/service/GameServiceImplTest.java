package com.nerdery.voting.service;

import com.nerdery.init.PersistenceConfiguration;
import com.nerdery.voting.model.Game;
import com.nerdery.voting.model.Vote;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { PersistenceConfiguration.class })
@Transactional
public class GameServiceImplTest {
    @Autowired
    GameService gameService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void makeSureCreateGameWithNoTitleCorrectlyCreatesGame() throws Exception {
        Game g = gameService.createGame("ooo");
        Assert.assertEquals("ooo", g.getTitle());
        Assert.assertNotNull(g.getCreated());
        Assert.assertFalse(g.isOwned());
    }

    @Test
    public void makeSureNullTitleThrowsError() throws Exception {
        expectedException.expectMessage("not-null property references a null or transient value : com.nerdery.voting");
        gameService.createGame(null);
    }

    @Test
    public void makeSureObjectIsUpdated() throws Exception {
        Game g = gameService.createGame("title");
        g.setIsOwned(true);
        gameService.save(g);

        Assert.assertTrue(gameService.getGameByTitle(g.getTitle()).isOwned());
    }

    @Test
    public void makeSureAllWantedGamesAreReturned() throws Exception {
        gameService.save("1", false);
        gameService.save("2", false);
        gameService.save("3", false);

        Assert.assertEquals(3, gameService.getWantedGamesSortedByVoteCountLoadEagerly().size());
    }

    @Test
    public void makeSureAllWantedOwnedAreReturned() throws Exception {
        gameService.save("1", true);
        gameService.save("2", true);
        gameService.save("3", false);

        Assert.assertEquals(2, gameService.getOwnedGames().size());
    }

    @Test
    public void makeSureDoesGameExistByTitleReturnsTrue() throws Exception {
        gameService.save("6", true);
        Assert.assertTrue(gameService.doesGameExistByTitle("6"));
        Assert.assertFalse(gameService.doesGameExistByTitle("2345"));
    }

    @Test
    public void makeSureNullAndEmptyStringWorkProperlyForDoesGameExistByTitle() throws Exception {
        Assert.assertFalse(gameService.doesGameExistByTitle(""));
        Assert.assertFalse(gameService.doesGameExistByTitle("  "));
        Assert.assertFalse(gameService.doesGameExistByTitle(null));
    }

    @Test
    public void castVoteWorksProperly() throws Exception {
        Vote v = gameService.castVote(new Game("title"));
        Assert.assertEquals("title", v.getGame().getTitle());
    }
}
