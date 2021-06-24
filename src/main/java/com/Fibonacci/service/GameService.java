package com.Fibonacci.service;

import com.Fibonacci.entity.Game;
import com.Fibonacci.model.PlayAMove;
import com.Fibonacci.model.PlayersNames;
import com.Fibonacci.model.PlayersScores;
import com.Fibonacci.model.Score;

import java.util.List;

public interface GameService {
    Game createNewGame(PlayersNames playersNames);
    PlayersScores getPlayersScores(String gameCode);
    Score playMove(String gameCode, String playerCode, PlayAMove playerMoves);
    void endGame(String gameCode);
}
