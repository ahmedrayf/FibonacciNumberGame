package com.Fibonacci.model;

import com.Fibonacci.dao.PlayerDAO;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.util.*;

@Data
public  class PlayersScores {
      private Map<String,Integer> scores =new LinkedHashMap<>();

      public void addNames(String name , int score){
            scores.put(name , score);
      }

}
