package com.nerdery.voting.repository;

import com.nerdery.voting.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    /**
     * This method gets the {@link Game} object from the data store that has a title that
     * matches the supplied titled.
     *
     * @param title The title to search for.
     * @return A {@link Game} object that has a title that matches the given title. If one is not found
     * null is returned.
     */
    Game findByTitle(String title);

    /**
     * This method will get the {@link Game} from the data store that is associated with the supplied ID.
     *
     * @param id The id to search for.
     * @return The {@link Game} object with {@code id} that matches the supplied id. If that id does not exist then null
     * is returned.
     */
    Game findById(Long id);

    /**
     * This method gets all games that have {@link com.nerdery.voting.model.Vote}s associated with it. It will return
     * all associated votes ordered by most number of votes then by the title.
     *
     * @return List of all {@link Game}s that have votes ordered by most votes then title. If not games exist
     * then null.
     */
    @Query(value = "from Game g where g.isOwned = false order by size(g.votes) desc, g.title")
    List<Game> findAllWantedGames();

    /**
     * This query will find all games that match {@code isOwned}.
     * @param isOwned The property to determine if a game is owned or not.
     * @return If {@code isOwned} is true then all games that are owned. If {@code isOwned} is false then return
     * all games that are not owned.
     */
    @Query(value = "from Game g where g.isOwned = :isOwned order by g.title")
    List<Game> findAllGamesByIsOwned(@Param("isOwned") Boolean isOwned);
}
