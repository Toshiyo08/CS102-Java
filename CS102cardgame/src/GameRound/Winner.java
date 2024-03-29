package GameRound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Base.*;
import Eval.*;
import Players.*;

public class Winner {
    public static void getWinner(ArrayList<Player> playersList, Table table1) {
        Map<Player, Integer> winner = new HashMap<>();

        for (Player o : playersList) {
            if (o.getIsPlaying()) {
                System.out.println("Player " + o.getName() + " hand:");
                GameTextDisplay.printCard(o.getHand());
                winner.put(o, HandEval.getHandValue(o.getHand(), table1.getCommCards()));
                System.out.println();
            }

        }
        int highest = 0;

        for (Player p : winner.keySet()) {
            if (winner.get(p) > highest) {
                highest = winner.get(p);
            }
        }

        ArrayList<Player> winnerList = new ArrayList<Player>();
        System.out.println(highest);
        for (Player player : winner.keySet()) {
            if (winner.get(player) == highest) {
                winnerList.add(player);
            }
        }
        if (winnerList.size() > 1) {
            for (Player o : winnerList) {
                o.setEndBalance(table1.getPot() / winnerList.size());
            }
        } else {
            winnerList.get(0).setEndBalance(table1.getPot());
        }
        for (Player o : winnerList) {
            System.out.println("Winner is " + o.getName());
        }
    }

    public static boolean isWinLose(ArrayList<Player> playersList) {
        int loser = 0;
        for (Player p : playersList) { // Player win
            if (!p.getType().equals("Player")) {
                if (p.getBalance() == 0) {
                    loser++;
                }
            }
            // Everyone lose to you versus You lost to everyone
            if (loser == playersList.size() - 1 || (p.getType().equals("Player") && p.getBalance() == 0)) {
                return true;
            }
        }

        return false;
    }
}
