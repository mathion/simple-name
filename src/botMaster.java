import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class botMaster extends Player implements Learning {

  private List<Bot> bots = new ArrayList<>();
  private List<int[]> gameStates = new ArrayList<>();
  private List<Bot> chosenBots = new ArrayList<>();


  public void makeMove(Game game) {
    int[] currentBoard = game.getBoardState().clone();
    boolean alreadyCreated = false;
    int choice = -1;
    if (gameStates.size() != 0) {
      for (int[] gameState : gameStates) {
        if (Arrays.equals(currentBoard, gameState)) {
          alreadyCreated = true;
          break;
        }
      }
    }
    if (!alreadyCreated) {
      gameStates.add(currentBoard.clone());
      bots.add(new Bot(game));
    }
    for (int i = 0; i < gameStates.size(); i++) {

      if (Arrays.equals(currentBoard, gameStates.get(i))) {
        choice = bots.get(i).makeChoice();
        chosenBots.add(bots.get(i));
        break;
      }
    }
    game.claim(choice, getPlayerNum());
  }

  public void punish() {
    for (Bot failedBot : chosenBots) {
      failedBot.punish();
    }
    chosenBots.removeAll(chosenBots);
  }

  public void reward() {
    for (Bot failedBot : chosenBots) {
      failedBot.reward();
    }
    chosenBots.removeAll(chosenBots);
  }

  public void drawReward() {
    for (Bot failedBot : chosenBots) {
      failedBot.drawReward();
    }
    chosenBots.removeAll(chosenBots);
  }

}
