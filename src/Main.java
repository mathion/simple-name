//import javax.swing.*;
//import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // write your code here

        botMaster botChampion = new botMaster();
        botMaster player2 = new botMaster();
        RandomPlayer randomScrub = new RandomPlayer();
        botMaster randomSlayer = new botMaster();
        HumanPlayer me = new HumanPlayer();

        botChampion.setPlayerNum(1);
        player2.setPlayerNum(-1);
        me.setPlayerNum(-1);
        randomScrub.setPlayerNum(1);
        randomSlayer.setPlayerNum(-1);


        Game game1 = new Game(botChampion, player2);


        for (int i = 0; i < 100000; i++) {
            game1.runGame();
            game1.reset();
        }

        int botVsBot = game1.getWinCounter();

        game1.setPlayer1(randomScrub);
        game1.setPlayer2(randomSlayer);
        game1.setWinCounter(0);

        for (int i = 0; i < 100000; i++) {
            game1.runGame();
            game1.reset();
        }

        int randVsBot = game1.getWinCounter();
        game1.setPlayer1(botChampion);
        game1.setWinCounter(0);

        for (int i = 0; i < 100000; i++) {
            game1.runGame();
            game1.reset();
        }

        int botChampVsRandChamp = game1.getWinCounter();
        game1.setPlayer2(me);
        game1.setWinCounter(0);

        System.out.println("Bot vs Bot " + botVsBot);
        System.out.println("Rand vs Bot " + randVsBot);
        System.out.println("Bot Champ vs Rand Champ " + botChampVsRandChamp);


        while (true) {
            game1.runGame();
            game1.reset();
        }
    }

//    private static JFrame initializeWindow(String title, int size, int xLocation, int yLocation) {
//        JFrame window = new JFrame(title);
//        window.setSize(size, size);
//        window.setLocation(xLocation, yLocation);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setVisible(true);
//        window.getContentPane().setBackground(Color.GRAY);
//        return window;
//
//    }

}
