package com.Fibonacci.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turn {

    private String next;

    public void nextTurn(List<PlayerTurnDto> playerTurnDtos){
        for(PlayerTurnDto playerTurn : playerTurnDtos){
            int  turns = playerTurn.getplayerTurns();
            if ((turns % 2 ==0)){
                setNext(playerTurn.getplayerNames());
                break;
            }
            else{
                setNext(playerTurnDtos.get(0).getplayerNames());
            }
        }
    }
}
