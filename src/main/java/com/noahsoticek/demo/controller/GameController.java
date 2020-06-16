package com.noahsoticek.demo.controller;

import com.noahsoticek.demo.models.Field;
import com.noahsoticek.demo.models.Game;
import com.noahsoticek.demo.models.User;
import com.noahsoticek.demo.repository.GameRepo;

import java.util.ArrayList;

public class GameController {

    GameRepo gameRepo = GameRepo.getInstance();

    public static void gameStart(User user) {
        // Assign user to a game
        // Check if there is an open game; if not, create new one; if yes, add current player to the game
        GameRepo gameRepo = GameRepo.getInstance();
        ArrayList<Game> games = gameRepo.getGames();
        Game game = null;

        if(games.get(games.size()-1).getPlayerOid() == null) {
            // Check if there is an open game; if yes, add current player to the game
            games.get(games.size()-1).setPlayerOid(user.getPlayerId());
            game = games.get(games.size()-1);
        } else {
            // If there is no open game, create new one
            game = new Game(games.size()+1, user.getPlayerId(), null, false, null, null);
        }
        gameRepo.update(game);
    }

    public ArrayList<Game> getGames(Integer playerId) {
        // Get all games of current User from DB and return them
        return GameRepo.getInstance().getGames(playerId);
    }

    public Game getGame(int gameId) {
        // Get game based on ID from DB and return it
        return GameRepo.getInstance().getCurrentGame(gameId);
    }

    public String gameExecutor(int playerId, Field field) {
        Game game = gameRepo.getCurrentGame(playerId);

        if(game == null) {
            // Check if game == null; if yes, create new one and save it to the DB
            Game newGame = new Game(gameRepo.getGames().size(), playerId, null, false, null,null);
            gameRepo.addGame(newGame);
        }

        if(game.getCurrentPlayer() == null) {
            // Current player var is null by default, that is why I set the
            // first player on current player var in the first round
            game.setCurrentPlayer(playerId);
        }

        if(game.getCurrentPlayer() == playerId) {
            // Check which player's turn it is and set X or O an the right field
            if (game.getCurrentPlayer().equals(game.getPlayerXid())) {
                game.addMove('X', field.getColumn(), field.getRow());
                game.setCurrentPlayer(game.getPlayerOid());
            } else {
                game.addMove('O', field.getColumn(), field.getRow());
                game.setCurrentPlayer(game.getPlayerXid());
            }
            if (checkIfWinner(game.getGamefield(), game).contains("Player hat gewonnen")) {
                return checkIfWinner(game.getGamefield(), game);
            } else {
                return "Player " + String.valueOf(game.getCurrentPlayer()) + " ist dran";
            }
        } else {
            return "Du bist jetzt nicht dran! Player " + game.getCurrentPlayer() + " ist dran...";
        }
    }

    private String checkIfWinner(char[][] field, Game game) {
        String msg = "";
        int xVertical = 0;
        int xHorizontal = 0;
        int oVertical = 0;
        int oHorizontal = 0;
        int oDiagonal = 0;
        int xDiagonal = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'O') {
                    oHorizontal++;
                }
                if (field[j][i] == 'O') {
                    oVertical++;
                }
                if (field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O') {
                    oDiagonal++;
                }
                if (field[0][2] == 'O' && field[1][1] == 'O' && field[2][0] == 'O') {
                    oDiagonal++;
                }
                if (field[i][j] == 'X') {
                    xHorizontal++;
                }
                if (field[j][i] == 'X') {
                    xVertical++;
                }
                if (field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X') {
                    xDiagonal++;
                }
                if (field[0][2] == 'X' && field[1][1] == 'X' && field[2][0] == 'X') {
                    xDiagonal++;
                }
            }

            if(oHorizontal == 3 || oVertical == 3 || oDiagonal == 3) {
                msg = "o Player hat gewonnen";
                game.setFinished(true);
                game.setWinner(game.getPlayerOid());
                gameRepo.update(game);
            }

            if(xHorizontal == 3 || xVertical == 3 || xDiagonal == 3) {
                msg = "x Player hat gewonnen";
                game.setFinished(true);
                game.setWinner(game.getPlayerXid());
                gameRepo.update(game);
            }

            oHorizontal = 0;
            oVertical = 0;
            xHorizontal = 0;
            xVertical = 0;
            oDiagonal = 0;
            xDiagonal = 0;
        }
        return msg;
    }

    public StringBuilder createTable(int playerID) {
        // Create gamefield for html view
        int height = 3;
        int rows = 3;
        GameRepo gameRepo = GameRepo.getInstance();
        StringBuilder gameField = new StringBuilder();

        char[][] field = gameRepo.getCurrentGame(playerID).getGamefield();

        for (int i = 0; i < height; i++) {
            for (int c = 0; c < rows; c++) {
                if(field[c][i] == '\u0000') {
                    gameField.append("|--").append(field[c][i]);
                } else {
                    gameField.append("|").append(field[c][i]);
                }
            }
            gameField.append("|<br>");
        }
        return gameField;
    }
}
