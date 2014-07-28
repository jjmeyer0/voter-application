package com.nerdery.init;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.model.Vote;
import com.nerdery.voting.repository.GameRepository;
import com.nerdery.voting.repository.VoteRepository;
import org.junit.Assert;
import org.junit.Test;
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
public class PersistenceConfigurationTest {
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    GameRepository gameRepository;

    @Test
    public void doesContextConfigurationLoad() throws Exception {
    }

    @Test
    public void makeSureCascadeSetOnGame() throws Exception {
        Game game = new Game("asdf", Boolean.FALSE);
        game.setIsOwned(Boolean.FALSE);
        game.getVotes().add(new Vote(game));

        gameRepository.save(game);

        Assert.assertTrue(gameRepository.count() == 1);
        Assert.assertTrue(voteRepository.count() == 1);

        voteRepository.save(new Vote(game));

        Game g = gameRepository.findById(game.getId());
        Assert.assertTrue(gameRepository.count() == 1);
        Assert.assertTrue(voteRepository.count() == 2);
    }
}
