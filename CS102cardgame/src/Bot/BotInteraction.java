package Bot;

import java.util.Random;

import GameRound.Game;

//utility class for bot
//Single Responsibility Principle

public class BotInteraction {
    public static void botThinking(String name) {
        System.out.print(name + ": ");
        Random random = new Random();
        int randomint = random.nextInt(6) + 1;
        Game.wait(100);
        System.out.print("H");
        for (int i = 0; i < randomint; i++) {
            Game.wait(100);
            System.out.print("m");
        }
        try {
            Thread.sleep(1000); // Wait for 0.1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
