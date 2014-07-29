package com.nerdery.voting.service;

import com.nerdery.init.PersistenceConfiguration;
import com.nerdery.voting.model.Game;
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
}
