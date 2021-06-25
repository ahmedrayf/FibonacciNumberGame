package com.Fibonacci.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class PlayersScores {
      private Map<String,Integer> scores =new LinkedHashMap<>();

      public void addNames(String name , int score){
            scores.put(name , score);
      }

}
