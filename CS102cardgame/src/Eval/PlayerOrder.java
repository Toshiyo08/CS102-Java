package Eval;

import Players.*;
import java.util.ArrayList;

public class PlayerOrder {
    private Player[] players;


    public PlayerOrder() {
        this.players = players.clone(); // Create a copy if necessary to avoid external modifications.
    }

    public static Player[] setInitialBlinds(ArrayList<Player> playersList) {
          // keep track of player's turn
          Player[] turnOrder = new Player[playersList.size()];
          for (int i = 0; i < playersList.size(); i++) {
              turnOrder[i] = playersList.get(i);
          }
          turnOrder[0].setIsBigBlind(true);   
          turnOrder[1].setIsSmallBlind(true);
          return turnOrder;
    }

    public static void moveBlind(Player[] turnOrder) {
        Player temp = turnOrder[0];
        for (int i = 1; i < turnOrder.length; i++) {
            turnOrder[i - 1] = turnOrder[i];
            turnOrder[i].setIsBlindPaid(false);
            turnOrder[i].setIsBigBlind(false);
            turnOrder[i].setIsSmallBlind(false);
        }

        turnOrder[turnOrder.length - 1] = temp;
        turnOrder[0].setIsBigBlind(true);
        turnOrder[1].setIsSmallBlind(true);
    }

}
