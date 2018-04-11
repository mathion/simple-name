public abstract class Player {

  private int playerNum;

  public Player(int playerNum) {
    this.playerNum = playerNum;
  }

  Player() {}

  int getPlayerNum() {
    return playerNum;
  }

  void setPlayerNum(int playerNum) {
    this.playerNum = playerNum;
  }

  abstract void makeMove(Game game);
}
