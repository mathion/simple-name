import java.util.Scanner;

public class HumanPlayer extends Player {

  Scanner scanner = new Scanner(System.in);

  public void makeMove(Game game) {
    game.printBoardState();
    boolean failedToClaim;
    do {
      System.out.println("Player " + (getPlayerNum() == 1 ? "1" : "2") + " make a move.");
      int choice = Integer.parseInt(scanner.nextLine());

      try {
        game.claim(choice, getPlayerNum());
        failedToClaim = false;
      } catch (IllegalArgumentException e) {
        failedToClaim = true;
      }
    } while (failedToClaim);

  }
}
