import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Bot {

  private Game hostGame;
  private List<Integer> availableSpaces;
  private Bot[] choices;
  private int[] weights;
  private Random randomGen = new Random();
  private int lastChoice;

  public Bot(Game game) {
    hostGame = game.copy();
    availableSpaces = game.availableSpaces();
    weights = new int[availableSpaces.size()];
    Arrays.fill(weights, 10);
    choices = new Bot[availableSpaces.size()];
  }

  public void setChoices(Bot[] choices) {
    this.choices = choices;
  }

  public int makeChoice() {
    int weightTotal = IntStream.of(weights).sum();
    int randomNum = randomGen.nextInt(weightTotal);
    int goal = 0;
    for (int i = 0; i < availableSpaces.size(); i++) {
      goal += weights[i];
      if (randomNum <= goal) {
        lastChoice = i;
        return availableSpaces.get(i);
      }
    }
    throw new RuntimeException();
  }

  public void punish() {
    if (weights[lastChoice] > 1) {
      weights[lastChoice] -= 1;
    }
  }

  public void reward() {
    weights[lastChoice] += 3;
  }

  public void drawReward() {
    weights[lastChoice] += 1;
  }
}
