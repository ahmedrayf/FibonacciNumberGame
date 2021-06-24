package com.Fibonacci.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Score {
    @JsonProperty("player-score")
    private int playerScore;
}
