package com.Fibonacci.service;

import com.Fibonacci.entity.Game;
import com.Fibonacci.model.*;


public interface GameService {
    Game createNewGame(PlayersNames playersNames);
    PlayersScores getPlayersScores(String gameCode);
    Score playMove(String gameCode, String playerCode, PlayAMove playerMoves);

    Turn playerTurn(String gameCode);

    void endGame(String gameCode);
}
