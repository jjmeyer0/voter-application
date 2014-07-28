package com.nerdery.voting.repository;

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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { PersistenceConfiguration.class })
@Transactional
public class VoteRepositoryTest {
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    GameRepository gameRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void makeSureFindByGameReturnsEmptyVotesWhenNotVote() throws Exception {
        Game g = gameRepository.save(new Game("test", Boolean.FALSE));
        List<Vote> votes = voteRepository.findByGame(g);

        Assert.assertEquals(0, votes.size());
    }

    @Test
    public void makeSureVoteProperlySaves() throws Exception {
        Game g = gameRepository.save(new Game("test1", Boolean.FALSE));
        Vote v = voteRepository.save(new Vote(g));

        Assert.assertNotNull(v.getId());
        Assert.assertEquals(v.getGame(), g);
        Assert.assertNotNull(v.getCreated());
    }

    @Test
    public void makeSureFindByGameReturnsSetOfVotesAssociatedWithGame() throws Exception {
        Game g = gameRepository.save(new Game("test2", Boolean.FALSE));
        Vote v = voteRepository.save(new Vote(g));
        Vote v2 = voteRepository.save(new Vote(g));

        List<Vote> votes = voteRepository.findByGame(g);

        Assert.assertEquals(2, votes.size());

        for (Vote vote : votes) {
            Assert.assertTrue(vote.equals(v) || vote.equals(v2));
        }
    }

    // TODO
}
