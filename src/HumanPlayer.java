import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner scanner = new Scanner(System.in);

    public void makeMove(Game game) {
        game.printBoardState();
        boolean failedToClaim;
        do {
            System.out.println("Player " + getPlayerNum() + " make a move.");
            int choice = Integer.parseInt(scanner.nextLine());

            try {
                game.claim(choice, getPlayerNum());
                failedToClaim = false;
            } catch (IllegalArgumentException e) {
                failedToClaim = true;
            }
        } while (failedToClaim);

    }

    public void punish() {
    }

    public void reward() {
    }

    public void drawReward() {
    }
}