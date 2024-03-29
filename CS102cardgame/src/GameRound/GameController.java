package GameRound;
import java.util.*;

import Eval.*;
import Players.*;
import Base.*;

public class GameController {

    public static boolean newGame(ArrayList<Player> playersList, boolean gameContinue, Table table1, Player[] turnOrder) {
        if (Winner.isWinLose(playersList)) {
            gameContinue = false;
        }
        Scanner scRoundEndallin = new Scanner(System.in);
        System.out.println("Start new round?(Y / N)> ");
        String newRound = scRoundEndallin.nextLine();
        if (newRound.equals("N") || newRound.equals("n")) {
            gameContinue = false;
            scRoundEndallin.close();
        } else if (newRound.equals("Y") || newRound.equals("y")) {
            Game.resetRound(playersList, table1);
            PlayerOrder.moveBlind(turnOrder); // moves big blind small blind, moves order of players
        }

        return gameContinue;
    }
   
    public static boolean roundEnd(ArrayList<Player> playersList,Table table1, Deck deck1,int numTimesBet, boolean gameContinue, Player[] turnOrder){
            if (Game.timeToOpenHand(playersList, table1, deck1, numTimesBet)) {
                Game.resetRound(playersList, table1); // Resets player and table attributes

            if (Winner.isWinLose(playersList)) { // If you win all or lost all, game ends.
                gameContinue = false;
                return gameContinue;
            }

            Scanner scRoundEndallin = new Scanner(System.in);
            System.out.println("Start new round?(Y / N)> ");
            String newRound = scRoundEndallin.nextLine();
            if (newRound.equals("N") || newRound.equals("n")) {
                gameContinue = false;
                scRoundEndallin.close();
            } else if (newRound.equals("Y") || newRound.equals("y")) {
                // Reset everything
                PlayerOrder.moveBlind(turnOrder);
            }

            
        }
        return gameContinue;
    }
}
       


