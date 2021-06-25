package com.Fibonacci.service;

import com.Fibonacci.dao.GameDAO;
import com.Fibonacci.dao.PlayerDAO;
import com.Fibonacci.entity.Game;
import com.Fibonacci.entity.Player;
import com.Fibonacci.exceptions.BadRequestException;
import com.Fibonacci.generator.FibonacciGenerator;
import com.Fibonacci.model.PlayAMove;
import com.Fibonacci.model.PlayersNames;
import com.Fibonacci.model.PlayersScores;
import com.Fibonacci.model.Score;
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
    private FibonacciGenerator fGenerator = new FibonacciGenerator();

    @Override
    public Game createNewGame(PlayersNames playersNames){
        LinkedHashSet<String> names = playersNames.getPlayers();
        if (names.size()>10){throw new BadRequestException("Max Players in one game 10, You inserted:" + names.size()); }
        //Insert Random Game Code
        game = new Game();
        game.setGameCode(UUID.randomUUID().toString().substring(0,5));

        //Loop Over Posted List of Players Names and set their names with Random Code
        //Then add Players to The game With Generated Random Code Before
        for (String temp : names){
            player = new Player();
            player.setPlayerName(temp);
            player.setPlayerCode(UUID.randomUUID().toString().substring(0,5));
            game.add(player);

        }
        game = gameRepo.save(game);
        return game;
    }

    @Override
    public  Score playMove(String gameCode, String playerCode, PlayAMove playerMoves) {
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

    @Override
    public PlayersScores getPlayersScores(String gameCode){
        if (!gameRepo.selectExistsGameCode(gameCode)){throw new BadRequestException("Game Code: " + gameCode + " isn't Exist"); }

        playersScores = new PlayersScores();
        setScores(gameCode);
        return playersScores;
    }

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
