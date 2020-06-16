package com.noahsoticek.demo.repository;

import com.noahsoticek.demo.models.Game;

import java.util.ArrayList;

public class GameRepo {

    private ArrayList<Game> games = new java.util.ArrayList<>();
    private static GameRepo instance;

    public static GameRepo getInstance() {
        if (instance == null) {
            instance = new GameRepo();
            instance.init();
        }
        return instance;
    }

    public boolean addGame(Game game) {
        games.add(game);
        return true;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<Game> getGames(Integer playerId) {
        // Return all games from current User
        ArrayList<Game> userGames = new ArrayList<>();

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getPlayerOid() != null) {
                if (games.get(i).getPlayerOid().equals(playerId) || games.get(i).getPlayerXid().equals(playerId)) {
                    userGames.add(games.get(i));
                }
            } else {
                if (games.get(i).getPlayerXid().equals(playerId)) {
                    userGames.add(games.get(i));
                }
            }
        }
        return userGames;
    }

    public Game getCurrentGame(int playerID) {
        // Get game based on ID and return it
        Game game = null;

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getPlayerOid() == null) {
                if (games.get(i).getPlayerXid() == playerID) {
                    if (!games.get(i).isFinished()) {
                        game = games.get(i);
                    }
                }
            } else {
                if (games.get(i).getPlayerXid() == playerID || games.get(i).getPlayerOid() == playerID) {
                    if (!games.get(i).isFinished()) {
                        game = games.get(i);

                    }
                }
            }
        }
        return game;
    }

    public void init() {
        games.add(new Game(1, 1, 2, true, 1, null));
        games.add(new Game(2, 2, 1, true, 1, null));
    }

    public void update(Game game) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameId() == game.getGameId()) {
                games.remove(i);
            }
        }
        games.add(game);
    }

}
