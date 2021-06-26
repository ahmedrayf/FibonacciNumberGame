package com.Fibonacci.service;

import com.Fibonacci.dao.GameDAO;
import com.Fibonacci.dao.PlayerDAO;
import com.Fibonacci.entity.Game;
import com.Fibonacci.entity.Player;
import com.Fibonacci.exceptions.BadRequestException;
import com.Fibonacci.generator.FibonacciGenerator;
import com.Fibonacci.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class GameServiceImpl implements GameService{
    @Autowired
    private PlayerDAO playerRepo;
    @Autowired
    private GameDAO gameRepo;
    private Player player;
    private Game game;
    private PlayersScores playersScores;
    private Turn turn;
    private FibonacciGenerator fGenerator = new FibonacciGenerator();

    /**
     *
     * @param playersNames
     * @return
     */
    @Override
    public Game createNewGame(PlayersNames playersNames){
        LinkedHashSet<String> names = playersNames.getPlayers();
        if (names.size()>10){throw new BadRequestException("Max Players in one game 10, You inserted:" + names.size()); }

        game = new Game();
        game.setGameCode(UUID.randomUUID().toString().substring(0,5));

        for (String temp : names){
            player = new Player();
            player.setPlayerName(temp);
            player.setPlayerCode(UUID.randomUUID().toString().substring(0,5));
            game.add(player);
        }
        game = gameRepo.save(game);
        return game;
    }

    /**
     *
     * @param gameCode
     * @param playerCode
     * @param playerMoves
     * @return
     */
    @Override
    public  Score playMove(String gameCode, String playerCode, PlayAMove playerMoves) {
        int turns = playerRepo.getPlayerTurnByPlayerCode(playerCode);
        if (turns == 20){throw new BadRequestException("You Played all your moves");}
        List<Integer> moves = playerMoves.getNumbers();
        Score  score = new Score();

        if (moves.size()>3){throw new BadRequestException("Max Numbers in a move 3");}
        if (!gameRepo.selectExistsGameCode(gameCode)){throw new BadRequestException("Game Code: " + gameCode + " isn't Exist");}
        else if (!playerRepo.selectExistsPlayerCode(playerCode)){throw new BadRequestException("Player Code: " + playerCode + " isn't Exist");}

        int result = 0;
        for (int temp : moves){
            if(fGenerator.isNumberExist(temp)){result += 1;}
        }

        score.setPlayerScore(result);
        int gameId = gameRepo.getGameIdByCode(gameCode);
        playerRepo.updatePlayerScore(result , playerCode , gameId);
        return score;
    }

    /**
     *
     * @param gameCode
     * @return
     */
    @Override
    public PlayersScores getPlayersScores(String gameCode){
        if (!gameRepo.selectExistsGameCode(gameCode)){throw new BadRequestException("Game Code: " + gameCode + " isn't Exist"); }

        playersScores = new PlayersScores();
        setScores(gameCode);
        return playersScores;
    }

    /**
     *
     * @param gameCode
     * @return
     */
    @Override
    public Turn playerTurn(String gameCode){
        if (!gameRepo.selectExistsGameCode(gameCode)){throw new BadRequestException("Game Code: " + gameCode + " isn't Exist");}
        turn = new Turn();
        List<PlayerTurnDto> playerTurnDtos = playerRepo.getPlayerTurns(gameCode);
        turn.nextTurn(playerTurnDtos);
        return turn;
    }

    /**
     *
     * @param gameCode
     */
    @Override
    public void endGame(String gameCode) {
        if (!gameRepo.selectExistsGameCode(gameCode)){throw new BadRequestException("Game Code: " + gameCode + " isn't Exist"); }
        gameRepo.deleteByGameCode(gameCode);
    }

    void setScores(String gameCode){
        List<String> playersNames = playerRepo.getPlayersNames(gameCode);
        List<Integer> scores = playerRepo.getPlayersScores(gameCode);
        Iterator<String> it1 = playersNames.iterator();
        Iterator<Integer> it2 = scores.iterator();
        while (it1.hasNext() && it2.hasNext()) {playersScores.addNames(it1.next(),it2.next());}
    }

}
