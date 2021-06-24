package com.Fibonacci.controller;

import com.Fibonacci.GameScore;
import com.Fibonacci.entity.Game;
import com.Fibonacci.entity.Player;
import com.Fibonacci.exceptions.BadRequestException;
import com.Fibonacci.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.Token;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = Controller.class)
class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Controller controller;
    @MockBean
    private GameService gameService;

    @Test
    void createNewGame() throws Exception {
//        Game game = new Game();
//
//        List<String> names = new ArrayList<>();
//        names.add("ahmed");

//        String json="{\n" +
//                "    \"gameCode\": \"gameCode\",\n" +
//                "    \"players\": [\n" +
//                "        {\n" +
//                "            \"playerName\": \"ahmed\",\n" +
//                "            \"playerCode\": \"playerCode\"\n" +
//                "        }\n" +
////                "    ]\n" +
////                "}";
//        when(gameService.createNewGame(names)).thenReturn(game);
//        mockMvc.perform(MockMvcRequestBuilders.post("/new-game")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(game)))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.gameCode").isNotEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.players").isArray())
//                ;



    }
    @Test
    void endGame() {
//        when(gameService.endGame("gameCode"));
//        mockMvc.perform(MockMvcRequestBuilders.post("/new-game")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(game)))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.gameCode").isNotEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.players").isArray())
//                ;
    }

    @Test
    void getPlayerScore() {
//        List<String> actual = Arrays.asList("ahmed","7");
//        List<String> expected = Arrays.asList("ahmed","7");
//        Mockito.when(gameService.getPlayersScores("gameCode")).thenReturn(actual);
//        Controller controller = new Controller(gameService);
//        assertEquals(expected,controller.getPlayerScore("gameCode"));

    }

    @Test
    void playMove() throws Exception {
        List<Integer> moves = new ArrayList<>();
        moves.add(0);
        moves.add(1);
        moves.add(1);
//        String expected = "2";
//        Mockito.when(gameService.getPlayersScores("gameCode")).thenReturn(actual);
//        Controller controller = new Controller(gameService);
//        assertEquals(expected,controller.getPlayerScore("gameCode"));
//        String exp = "\"Score: 2\"";
//
//        when(gameService.playMove("gameCode", "playerCode" ,moves)).thenReturn(exp);
//        mockMvc.perform(MockMvcRequestBuilders.post("/game/gameCode/playerCode/play")
//                .contentType(MediaType.APPLICATION_JSON).content(exp))
//                .andExpect(status().isOk());
    }
}