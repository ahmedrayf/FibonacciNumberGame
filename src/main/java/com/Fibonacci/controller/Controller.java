package com.Fibonacci.controller;

import com.Fibonacci.entity.Game;
import com.Fibonacci.exceptions.BadRequestException;
import com.Fibonacci.model.PlayAMove;
import com.Fibonacci.model.PlayersNames;
import com.Fibonacci.model.PlayersScores;
import com.Fibonacci.model.Score;
import com.Fibonacci.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private GameService gameService;
    public Controller(GameService gameService) {this.gameService = gameService;}


    @PostMapping("/new-game")
    public ResponseEntity<Game>  createNewGame(@RequestBody PlayersNames playersNames) throws BadRequestException {
        Game createNewGame = gameService.createNewGame(playersNames);
        return new ResponseEntity<>(createNewGame, HttpStatus.CREATED);
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

    @DeleteMapping("/{gameCode}/end")
    public void endGame(@PathVariable("gameCode") String gameCode){
        gameService.endGame(gameCode);
    }

}
