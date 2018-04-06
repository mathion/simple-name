import java.util.ArrayList;
import java.util.List;

public class Game {

  /**
   * This array stores the game state The index of the array corresponds to the position on the
   * board like so:
   * 0|1|2
   * -----
   * 3|4|5
   * -----
   * 6|7|8
   * The value of each element stores the contents of the space 0 = unclaimed 1  = player 1 -1 =
   * player 2
   */

  private int[] boardState = new int[9];
  private int turnCounter = 0;
  private Player player1;
  private Player player2;

  private int winCounter = 0;


  public Game(Player player1, Player player2) {
    setPlayer1(player1);
    setPlayer2(player2);
  }

  private Game(int[] boardState, int turnCounter) {
    this.boardState = boardState.clone();
    this.turnCounter = turnCounter;
  }

  public Game copy() {
    return new Game(boardState, turnCounter);
  }


  public Player getPlayer1() {
    return player1;
  }

  public void setPlayer1(Player player1) {
    this.player1 = player1;
    player1.setPlayerNum(1);
  }

  public Player getPlayer2() {
    return player2;
  }

  public void setPlayer2(Player player2) {
    this.player2 = player2;
    player2.setPlayerNum(-1);
  }

  public int getTurnCounter() {
    return turnCounter;
  }

  public int getWinCounter() {
    return winCounter;
  }

  public void setWinCounter(int winCounter) {
    this.winCounter = winCounter;
  }

  public int[] getBoardState() {
    return boardState;
  }

  public void setBoardState(int[] boardState) {
    boolean dontSetFlag = false;
    for (int space : boardState) {
      if (space / 2 != 0) {
        System.out.println("Invalid game state");
        dontSetFlag = true;
        break;
      }

    }
    if (!dontSetFlag) {
      this.boardState = boardState;
    }
  }

  private int checkBoardState() {
    /** Checks the game state for if a player has three pieces in a row.
     * Returns 1 if player 1 has won, -1 if player two has, 0 for undecided and 2 for a draw
     */

    int temp = 0;

    //Checks vertical lines

    for (int i = 0; i < 3; i++) {
      temp = (boardState[i] + boardState[i + 3] + boardState[i + 6]) / 3;
      if (temp != 0) {
        return temp;
      }

      //Checks horizontal lines
      temp = (boardState[3 * i] + boardState[(3 * i) + 1] + boardState[(3 * i) + 2]) / 3;

      if (temp != 0) {
        return temp;
      }
    }

      //Checks diagonal lines
    temp = (boardState[0] + boardState[4] + boardState[8]) / 3;

    if (temp != 0) {
      return temp;
    }

    temp = (boardState[2] + boardState[4] + boardState[6]) / 3;

    if (temp != 0) {
      return temp;
    }

    //Checks for empty squares
    for (int space : boardState) {
      if (space == 0) {
        return 0;
      }
    }

    //If no player has won and their are no empty squares the game is a draw
    return 2;
  }

  public void claim(int location, int player) throws IllegalArgumentException {

    /**
     Marks a space as taken by a player.
     player can either be 1 or -1
     Prints an error message if inputs are invalid
     */

    if (location > 8 || location < 0) {
      System.out.println("Invalid Location");
      throw new IllegalArgumentException();
    }

    if (player != 1 && player != -1) {
      System.out.println("Player num out of bounds");
      throw new RuntimeException();
    }

    if (this.boardState[location] != 0) {
      System.out.println("Space Already taken");
      throw new IllegalArgumentException();
    }

    this.boardState[location] = player;
    turnCounter++;
  }

  public List<Integer> availableSpaces() {
    //returns an arrayList of available spaces

    List<Integer> available = new ArrayList<Integer>();

    for (int i = 0; i < boardState.length; i++) {
      if (boardState[i] == 0) {
        available.add(i);
      }
    }
    return available;
  }

  public void printBoardState() {
    /**
     * Prints a representation of the game board to the console.
     */
    System.out.println("Board State");
    System.out.println(boardState[0] + "|" + boardState[1] + "|" + boardState[2]);
    System.out.println("-----");
    System.out.println(boardState[3] + "|" + boardState[4] + "|" + boardState[5]);
    System.out.println("-----");
    System.out.println(boardState[6] + "|" + boardState[7] + "|" + boardState[8]);
  }

  public void runGame() {
    /**
     * This method will run the main game loop
     * and will send punishment and reward commands to each player at the end of the game.
     */

    //this is the game loop
    while (this.checkBoardState() == 0) {
      player1.makeMove(this);
      if (this.checkBoardState() != 0) {
        break;
      }
      player2.makeMove(this);
    }

    //these blocks will print the outcome of the game and reward or punish players
    int endBoardState = this.checkBoardState();

    if (endBoardState == 2) {
      System.out.println("Draw after " + turnCounter + " Turns");
      player1.drawReward();
      player2.drawReward();

    } else {
      System.out.println("Player " + (endBoardState == 1 ? "1" : "2") + " Wins!");
      this.winCounter += endBoardState;
      System.out.println("Balance of power is " + winCounter);
    }
    if (endBoardState == 1) {
      player1.reward();
      player2.punish();
    } else if (endBoardState == -1) {
      player2.reward();
      player1.punish();
    }
  }

  public void reset() {
    boardState = new int[9];
    turnCounter = 0;
  }

}
