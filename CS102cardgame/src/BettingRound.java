import java.util.Scanner;

public class BettingRound {
    // private 

    //checks each player with other player's check amount 
    public Boolean isCheckedCalled (Player plyr, Table table1) {
        if (plyr.getBet() == table1.getCurrentBetAmt() && plyr.getCheckStatus() == true) {
            return true;
        } else {
            return false;
        }
    }

    //asks player for action and updates player's bet
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

                //IMPORTANT
                //getAction() if player return player, if bot, return check. DONT need to request bot ANYTHING


                //add exceptions for player input 
                //change player turn input to accept bot's input -> once bot receives prompt for action, it needs to create hand eval class instance and return 
                if (plyrturninput.equals("Call")) {
                    // If balance less than what they want to bet
                    if (plyr.getChips() < currentHighestBet) {
                        // All in
                        // Check for double counting
                        plyr.setCall(plyr.getChips());
                    } else {
                        plyr.setCall(currentHighestBet);
                        plyr.setCheckTrue();
                        isActionDone = true;
                    }
                    
                } else if (plyrturninput.equals("Raise")) {
                    plyrBetInput = plyrbet.nextInt();
                    plyr.setRaise(plyrBetInput);
                    table1.raiseCurrentBetAmt(plyrBetInput);
                    plyrbet.close();
                    for (Player e : table1.getActivePlayers()) {
                        e.setCheckfalse();
                    }
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