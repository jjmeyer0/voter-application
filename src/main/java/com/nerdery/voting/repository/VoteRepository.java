package com.nerdery.voting.repository;

import com.nerdery.voting.model.Game;
import com.nerdery.voting.model.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoteRepository extends CrudRepository<Vote, Long> {
    /**
     * This will return all {@link com.nerdery.voting.model.Vote}s that are associated with {@code game}.
     *
     * @param game The {@link Game} to find all {@link Vote}s for.
     * @return If {@code game} has no votes empty list otherwise all the {@link Vote}s associated with {@code game}.
     */
    List<Vote> findByGame(Game game);
}
