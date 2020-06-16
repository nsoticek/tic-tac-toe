package com.noahsoticek.demo.models;

public class Game {

    private final int gameId;
    private final Integer playerXid;
    private Integer playerOid;
    private boolean finished;
    private Integer winner;
    private Integer currentPlayer;
    private char[][] gamefield = new char[3][3];

    public Game(int gameId, Integer playerXid, Integer getPlayerOid, boolean finished, Integer winner, Integer currentPlayer) {
        this.gameId = gameId;
        this.playerXid = playerXid;
        this.playerOid = getPlayerOid;
        this.finished = finished;
        this.winner = winner;
        this.currentPlayer = currentPlayer;
    }

    public int getGameId() {
        return gameId;
    }

    public Integer getPlayerXid() {
        return playerXid;
    }

    public Integer getPlayerOid() {
        return playerOid;
    }

    public void setPlayerOid(Integer playerOid) {
        this.playerOid = playerOid;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Integer getWinner() {
        return winner;
    }

    public Integer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Integer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public char[][] getGamefield() {
        return gamefield;
    }

    public String addMove(char playerSymbol, int column, int row) {
        if (gamefield[column][row] == '\u0000') {
            gamefield[column][row] = playerSymbol;
            return "OK";
        } else {
            return "Field is already chosen";
        }
    }
}
