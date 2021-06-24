package com.Fibonacci.dao;

import com.Fibonacci.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerDAO extends JpaRepository<Player,Long> {

////    String playersScore ="SELECT p.player_name as playerName , p.player_score playerScore FROM player p , game g where p.game_id = g.game_id and g.game_code = :gameCode";
//@Query("SELECT new com.Fibonacci.aggregation.custom.PlayersScore(playerName, playerScore) "
//        + "FROM Player AS p , Game g  where p.game = g.gameId and g.gameCode = :gameCode")

    String playersNames ="SELECT  p.player_name FROM player p , game g where p.game_id = g.game_id and g.game_code = :gameCode";
    String playersScores ="SELECT  p.player_score FROM player p , game g where p.game_id = g.game_id and g.game_code = :gameCode";
    String setPlayerScore ="update player set player_score = player_score + :score where player_code = :playerCode and game_id = :gameId";
    String isPlayerCodeExist="SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Player p WHERE p.playerCode = ?1";

    @Query(value = playersNames, nativeQuery = true)
    List<String> getPlayersNames(@Param("gameCode") String gameCode);
    @Query(value = playersScores, nativeQuery = true)
    List<Integer> getPlayersScores(@Param("gameCode") String gameCode);

    @Query(isPlayerCodeExist)
    Boolean selectExistsPlayerCode(String playerCode);

    @Modifying
    @Query(value = setPlayerScore, nativeQuery = true)
    void updatePlayerScore(int score , String playerCode , int gameId);

}
