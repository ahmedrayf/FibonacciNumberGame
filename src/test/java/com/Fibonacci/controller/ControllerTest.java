package com.Fibonacci.controller;

import com.Fibonacci.entity.Game;
import com.Fibonacci.entity.Player;
import com.Fibonacci.exceptions.BadRequestException;
import com.Fibonacci.model.*;
import com.Fibonacci.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = Controller.class)
class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GameService gameService;
    private String baseUrl = "/api";

    /**
     *
     * @throws Exception
     */
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

    /**
     *
     * @throws Exception
     */
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

    /**
     *
     * @throws Exception
     */
    @Test
    void getPlayerScore() throws Exception {
        Map<String,Integer> score = new LinkedHashMap<>();
        score.put("ahmed",0);
        score.put("mohamed",0);

        PlayersScores playersScores = new PlayersScores(score);

        when(gameService.getPlayersScores("gameCode")).thenReturn(playersScores);
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/game/gameCode/score"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scores").isMap())
                .andDo(print());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    void getOnTurnPlayer() throws Exception{
        Turn turn =new Turn("code1");

        when(gameService.playerTurn("gameCode")).thenReturn(turn);
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/gameCode/turn"))
                .andExpect(status().isOk());

    }

    @Test
    void endGame() throws Exception {
        doNothing().when(gameService).endGame("gameCode");
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl+"/gameCode/end"))
                .andExpect(status().isOk()).andDo(print());
    }

    /**
     *
     * @throws Exception
     */
    //Test Exceptions
    @Test
    void toDoWhenPlayersMoreThan10() throws Exception{
        LinkedHashSet<String> player =
                new LinkedHashSet<>(Arrays.asList(
                        "ahmed","mohamed","wael","mostafa","rashed","hassan","yasser","amr","omar","ramy","yassin"));
        PlayersNames playersNames = new PlayersNames(player);

        Mockito.when(gameService.createNewGame(playersNames)).thenThrow(BadRequestException.class);
        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/new-game").
                contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(playersNames)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    void toDoWhenGameCodeIsNotExist() throws Exception{

        doThrow(BadRequestException.class).when(gameService).endGame("24fsa63");
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl+"/24fsa63/end"))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    void toDoWhenPlayerCodeIsNotExist() throws Exception{
        List<Integer> moves = new ArrayList<>(Arrays.asList(0,1,1));
        PlayAMove playAMove = new PlayAMove(new ArrayList(Arrays.asList(0,1,0)));

        when(gameService.playMove("gameCode","54sfas",playAMove))
                .thenThrow(BadRequestException.class);

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/game/gameCode/54sfas/play").
                contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(playAMove)))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    void toDoWhenMovesMoreThan3() throws Exception{
        List<Integer> moves = new ArrayList<>(Arrays.asList(0,1,1));
        PlayAMove playAMove = new PlayAMove(new ArrayList(Arrays.asList(0,1,0,5,8)));

        when(gameService.playMove("gameCode","54sfas",playAMove))
                .thenThrow(BadRequestException.class);

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/game/gameCode/54sfas/play").
                contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(playAMove)))
                .andExpect(status().isBadRequest()).andDo(print());
    }


}

