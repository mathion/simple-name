package com.company;

public abstract class Player {
    private int playerNum;

    public Player(int playerNum) {
        this.playerNum = playerNum;
    }

    public Player() {
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public abstract void makeMove(Game game);

    public abstract void punish();

    public abstract void reward();

    public abstract void drawReward();

}
