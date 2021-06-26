package com.Fibonacci.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.LinkedHashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayersNames {
    private LinkedHashSet<String> players;
}
