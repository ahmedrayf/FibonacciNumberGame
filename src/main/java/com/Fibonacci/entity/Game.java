package com.Fibonacci.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "game")
public class Game {
    public Game() {}

    @JsonProperty
    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id",nullable = false, updatable = false)
    private long gameId;

    @Column(nullable = false, updatable = false)
    @JsonProperty("game-code")
    private String gameCode;

    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL)
    @JsonProperty("player-codes")
    private List<Player> players;

    public void add(Player thePlayer) {
        if (players == null) {
            players = new ArrayList<>();
        }
        players.add(thePlayer);
        thePlayer.setGame(this);
    }

    public Game(String gameCode, List<Player> players) {
        this.gameCode = gameCode;
        this.players = players;
    }
}
