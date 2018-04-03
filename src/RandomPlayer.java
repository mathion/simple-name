import java.util.Random;

public class RandomPlayer extends Player {
    Random randomNumGen = new Random();

    public void makeMove(Game game) {
        int choice = game.availableSpaces().get(randomNumGen.nextInt(game.availableSpaces().size()));
        game.claim(choice, getPlayerNum());
    }

    public void punish() {
    }

    public void reward() {
    }

    public void drawReward() {
    }
}
