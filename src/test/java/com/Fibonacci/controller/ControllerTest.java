package com.Fibonacci.controller;

import com.Fibonacci.entity.Game;
import com.Fibonacci.entity.Player;
import com.Fibonacci.model.PlayAMove;
import com.Fibonacci.model.PlayersNames;
import com.Fibonacci.model.PlayersScores;
import com.Fibonacci.model.Score;
import com.Fibonacci.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = Controller.class)
class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GameService gameService;
    private String baseUrl = "/api";


    @Test
    void createNewGame() throws Exception {
        LinkedHashSet<String> player = new LinkedHashSet<>(Arrays.asList("ahmed","mohamed"));
        PlayersNames playersNames = new PlayersNames(player);

        Player player1 =new Player("ahmed","player1Code");
        Player player2 =new Player("mohamed","player2Code");
        List<Player> players = new ArrayList<>(Arrays.asList(player1,player2));

        Game game = new Game("gameCode", Arrays.asList(player1,player2));

        Mockito.when(gameService.createNewGame(playersNames)).thenReturn(game);
        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/new-game").
                contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(playersNames)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.player-codes").isArray())
                .andDo(print());

    }

    @Test
    void endGame() throws Exception {
        doNothing().when(gameService).endGame("gameCode");
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl+"/gameCode/end"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getPlayerScore() throws Exception {
        String gameCode="gameCode";
        Map<String,Integer> score = new LinkedHashMap<>();
        score.put("ahmed",0);
        score.put("mohamed",0);

        PlayersScores playersScores = new PlayersScores(score);

        when(gameService.getPlayersScores(gameCode)).thenReturn(playersScores);
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/game/gameCode/score"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scores").isMap())
                .andDo(print());
    }



    @Test
    void playMove() throws Exception {
        List<Integer> moves = new ArrayList<>(Arrays.asList(0,1,1));
        PlayAMove playAMove = new PlayAMove(new ArrayList(Arrays.asList(0,1,0)));
        Score score = new Score(2);

        when(gameService.playMove("gameCode", "code1" ,playAMove)).thenReturn(score);
        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/game/gameCode/code1/play")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(playAMove)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.player-score").isNumber())
                .andDo(print());
    }
}

