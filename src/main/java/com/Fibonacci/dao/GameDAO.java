package com.Fibonacci.dao;

import com.Fibonacci.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameDAO extends JpaRepository<Game,Long> {
    String gameId = "select game_id from game where game_code = :gameCode";
    String isGameCodeExist="SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM Game g WHERE g.gameCode = ?1";

    @Query(isGameCodeExist)
    Boolean selectExistsGameCode(String gameCode);

    @Query(value = gameId , nativeQuery = true)
    int getGameIdByCode(@Param("gameCode") String gameCode);

    void deleteByGameCode(String gameCode);

}
