package com.Fibonacci.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlayAMove {
    @JsonProperty("Numbers")
    private List numbers;
}
