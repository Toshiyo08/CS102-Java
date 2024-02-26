import java.util.Scanner;

public class BettingRound {
    // private 

    public Boolean isCheckedCalled (Player plyr, Table table1) {
        if (plyr.getBet() == table1.getCurrentBetAmt() && plyr.getCheckStatus() == true) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isPlayerTurnComplete (Player plyr, int currentHighestBet, Table table1) {
        int numChecks = 0;
        Scanner plyrturn = new Scanner(System.in);
        Scanner plyrbet = new Scanner(System.in);
        int plyrBetInput = 0;
        String plyrturninput = null;
        Boolean isActionDone = false;
        while (!isActionDone) {
            System.out.println("What will you do?");

            if (currentHighestBet > plyr.getBet()) {
                System.out.println("Raise, Call or Fold");
                plyrturninput = plyrturn.nextLine(); 
                // DOES NOT WORK FOR BOTS, bots cannot input
                // Change plyrturninput to variable that takes in player/bot action

                if (plyrturninput.equals("Call")) {
                    plyr.setCall(currentHighestBet);
                    plyr.setCheckTrue();
                    isActionDone = true;
                } else if (plyrturninput.equals("Raise")) {
                    plyrBetInput = plyrbet.nextInt();
                    plyr.setRaise(plyrBetInput);
                    table1.raiseCurrentBetAmt(plyrBetInput);
                    plyrbet.close();
                    isActionDone = true;
                } else if (plyrturninput.equals("Fold")) {
                    plyr.fold();
                    isActionDone = true;
                }
                plyrturn.close();
                
            } else {
                System.out.println("Raise, Call, Check or Fold");
                plyrturninput = plyrturn.nextLine();

                if (plyrturninput.equals("Check") && plyr.getBet() == table1.getCurrentBetAmt()) {
                    plyr.setCheckTrue();
                    isActionDone = true;
                } else if (plyrturninput.equals("Call") && currentHighestBet > plyr.getBet()) {
                    plyr.setCall(currentHighestBet);
                    plyr.setCheckTrue();
                    isActionDone = true;
                } else if (plyrturninput.equals("Raise")) {
                    plyrBetInput = plyrbet.nextInt();
                    plyr.setRaise(plyrBetInput);
                    table1.raiseCurrentBetAmt(plyrBetInput);
                    plyrbet.close();
                    isActionDone = true;
                } else if (plyrturninput.equals("Fold")) {
                    plyr.fold();
                    isActionDone = true;
                }
                plyrturn.close();
            }
            

            
        }
        
        return true;
    }
}


// Boolean answered. Only if answered can it move on.
// Answered only takes in 4 strings: Fold, Raise, Check, Call

// While asking for betting amt or any int, 
// if letters are entered instead of numbers, try catch ICE 7 Q3: InputMismatchException