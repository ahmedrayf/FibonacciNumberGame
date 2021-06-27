package com.Fibonacci.controller;

import com.Fibonacci.entity.Game;
import com.Fibonacci.exceptions.BadRequestException;
import com.Fibonacci.model.*;
import com.Fibonacci.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    private GameService gameService;
    public Controller(GameService gameService) {this.gameService = gameService;}

    @PostMapping("/new-game")
    @ResponseBody
    public Game  createNewGame( @RequestBody PlayersNames playersNames) throws BadRequestException {
        Game createNewGame = gameService.createNewGame(playersNames);
        return createNewGame;
    }

    @PostMapping("/game/{game-code}/{player-code}/play")
    public Score playMove(@PathVariable("game-code") String gameCode,
                          @PathVariable("player-code") String playerCode,
                          @RequestBody PlayAMove playerMoves )
    {
        return  gameService.playMove(gameCode,playerCode,playerMoves);
    }

    @GetMapping("/game/{game-code}/score")
    public PlayersScores getPlayerScore(@PathVariable("game-code") String gameCode){
        return gameService.getPlayersScores(gameCode);
    }

    @GetMapping("/{game-code}/turn")
    public Turn getOnTurnPlayer(@PathVariable("game-code") String gameCode){
        return gameService.playerTurn(gameCode);
    }

    @DeleteMapping("/{gameCode}/end")
    public void endGame(@PathVariable("gameCode") String gameCode){
        gameService.endGame(gameCode);
    }



}

