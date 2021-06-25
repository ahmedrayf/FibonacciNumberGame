package com.Fibonacci.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Player {
    public Player(){}

    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long playerId;

    @JsonProperty("name")
    private String playerName;

    @Getter(AccessLevel.NONE)
    private int playerScore;

    @Column(nullable = false, updatable = false)
    @JsonProperty("code")
    private String playerCode;

    @Getter(AccessLevel.NONE)
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="game_id")
    private Game game;

    public Player( String playerName,  String playerCode) {
        this.playerName = playerName;
        this.playerCode = playerCode;

    }
}
