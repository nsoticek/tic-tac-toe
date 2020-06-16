package com.noahsoticek.demo.models;

public class User {

    private final int PlayerId;
    private final String username;

    public User(int playerId, String username) {
        PlayerId = playerId;
        this.username = username;
    }

    public int getPlayerId() {
        return PlayerId;
    }

    public String getUsername() {
        return username;
    }
}
