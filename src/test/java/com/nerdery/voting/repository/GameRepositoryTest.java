package com.nerdery.voting.repository;

import com.nerdery.init.PersistenceConfiguration;
import com.nerdery.voting.model.Game;
import com.nerdery.voting.model.Vote;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { PersistenceConfiguration.class })
public class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    VoteRepository voteRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void makeSureGameProperlySavesWithTwoParamConstructor() throws Exception {
        Game g = gameRepository.save(new Game("nice", Boolean.TRUE));

        Assert.assertTrue(g.isOwned());
        Assert.assertEquals("nice", g.getTitle());
        Assert.assertEquals(0, g.getVotes().size());
        Assert.assertNotNull(g.getCreated());
    }

    @Test
    public void makeSureGameProperlySavesWithOneParamConstructor() throws Exception {
        Game g = gameRepository.save(new Game("whoa"));

        Assert.assertEquals(g.getTitle(), "whoa");
        Assert.assertFalse(g.isOwned());
        Assert.assertNotNull(g.getCreated());
        Assert.assertEquals(0, g.getVotes().size());
    }

    @Transactional
    @Test
    public void makeSureFindByTitleWorks() throws Exception {
        gameRepository.save(new Game("test", Boolean.FALSE));
        Game expected = gameRepository.save(new Game("test1", Boolean.FALSE));

        Game g = gameRepository.findByTitle("test1");

        Assert.assertEquals(expected, g);
    }

    @Transactional
    @Test
    public void makeSureFindByIdWorksProperly() throws Exception {
        Game g = new Game("test2", Boolean.FALSE);

        Long id = gameRepository.save(g).getId();

        Assert.assertEquals(g, gameRepository.findById(id));
    }

    @Test
    public void makeSureGameIsNullWhenTitleDoesNotExist() throws Exception {
        Game game = gameRepository.findByTitle("t");
        Assert.assertNull(game);
    }

    @Test
    public void makeSureNullIsReturnedWhenSearchingForNull() throws Exception {
        Game g = gameRepository.findByTitle(null);
        Assert.assertNull(g);
    }

    @Test
    public void makeSureExpectedNotNullExceptionOccursWithIsOwned() throws Exception {
        expectedException.expectMessage("not-null property references a null or transient value : " +
                "com.nerdery.voting.model.Game.isOwned; nested");

        gameRepository.save(new Game("haha", null));
    }

    @Test
    public void makeSureExpectedNotNullExceptionOccursWithTitle() throws Exception {
        expectedException.expectMessage("not-null property references a null or transient value : " +
                "com.nerdery.voting.model.Game.title; nested exception is org.hibernate.PropertyValueException:");

        gameRepository.save(new Game(null, Boolean.FALSE));
    }

    @Transactional
    @Test
    public void makeSureProperGamesAndOrderAreReturned() throws Exception {
        Game game = new Game("1");
        Game game2 = new Game("2");
        Game game3 = new Game("3");
        Game game4 = new Game("4");

        voteRepository.save(new Vote(game));
        voteRepository.save(new Vote(game));
        voteRepository.save(new Vote(game4));
        voteRepository.save(new Vote(game4));
        voteRepository.save(new Vote(game3));
        voteRepository.save(new Vote(game3));
        voteRepository.save(new Vote(game3));
        voteRepository.save(new Vote(game2));

        List<Game> g = gameRepository.findAllWantedGames();
        Assert.assertTrue(g.size() == 4);

        List<Game> expected = Arrays.asList(game3, game, game4, game2);
        Assert.assertEquals(expected, g);
    }

    @Transactional
    @Test
    public void makeSureFindByIsOwnedWorks() throws Exception {
        Game game = new Game("q", Boolean.TRUE);
        Game game2 = new Game("w", Boolean.TRUE);
        Game game3 = new Game("e", Boolean.TRUE);
        Game game4 = new Game("r");
        gameRepository.save(Arrays.asList(game, game2, game3, game4));

        Assert.assertEquals(3, gameRepository.findAllGamesByIsOwned(Boolean.TRUE).size());
    }

    @Test
    public void findByTitleWithNonexistentTitlesMustReturnNull() throws Exception {
        Assert.assertNull(gameRepository.findByTitle(null));
        Assert.assertNull(gameRepository.findByTitle(""));
        Assert.assertNull(gameRepository.findByTitle("   \n"));
        Assert.assertNull(gameRepository.findByTitle("iopsjdfioejwoijf"));
    }
}
