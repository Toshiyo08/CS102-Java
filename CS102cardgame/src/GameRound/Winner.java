package GameRound;

import java.util.ArrayList;
import java.util.HashMap;

import Base.*;
import Eval.*;
import Players.*;


// Winner class is used to determine the winner of the game
public class Winner {
    public static void getWinner(ArrayList<Player> playersList, Table table1) { //called only once its time to open hand
        HashMap<Player, Integer> winner = new HashMap<>();

        //adds all players who have NOT folded along with their hand scoreinto a hashmap
        for (Player o : playersList) {
            if (o.getIsPlaying()) {
                winner.put(o, HandEval.getHandValue(o.getHand(), table1.getCommCards()));
                System.out.println();
            }

        }
        int highest = 0;
        //finds the highest handscore using handEval
        for (Player p : winner.keySet()) {
            if (winner.get(p) > highest) {
                highest = winner.get(p);
            }
        }

        //Arraylist used in case of a tie
        ArrayList<Player> winnerList = new ArrayList<Player>();
        for (Player player : winner.keySet()) {
            if (winner.get(player) == highest) {
                winnerList.add(player);
            }
        }
        if (winnerList.size() > 1) { //if tied, pot is split
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
            // Player has won the money from all the other bots versus Player has lost all their money
            if (loser == playersList.size() - 1 || (p.getType().equals("Player") && p.getBalance() == 0)) {
                return true;
            }
        }

        return false;
    }
}
