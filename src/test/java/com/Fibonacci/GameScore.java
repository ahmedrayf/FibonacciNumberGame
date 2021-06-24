package com.Fibonacci;

import java.util.List;

public class GameScore {
    private List<String> playerName;
    private List<String> PlayerScore;

    public GameScore() {
    }

    public GameScore(List<String> playerName, List<String> playerScore) {
        this.playerName = playerName;
        PlayerScore = playerScore;
    }

    public void setPlayerName(List<String> playerName) {
        this.playerName = playerName;
    }

    public void setPlayerScore(List<String> playerScore) {
        PlayerScore = playerScore;
    }

    public List<String> getPlayerName() {
        return playerName;
    }

    public List<String> getPlayerScore() {
        return PlayerScore;
    }
}
