package GameRound;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Base.*;
import Players.*;
import Input.*;

//Single Responsibility Principle -> only changing print statements here
public class GameTextDisplay {
    public void startingScreen() {

        System.out.println("                                                                       Welcome to");
        System.out.println(
                "                                                        .------..------..------..------..------.");
        System.out.println(
                "                                                        |T.--. ||E.--. ||X.--. ||A.--. ||S.--. |");
        System.out.println(
                "                                                        | :/\\: || (\\/) || :/\\: || (\\/) || :/\\: |");
        System.out.println(
                "                                                        | (__) || :\\/: || (__) || :\\/: || :\\/: |");
        System.out.println(
                "                                                        | '--'T|| '--'E|| '--'X|| '--'A|| '--'S|");
        System.out.println(
                "                                                        `------'`------'`------'`------'`------'");
        System.out.println(
                "                                                .------..------..------..------..------..------..------.");
        System.out.println(
                "                                                |H.--. ||O.--. ||L.--. ||D.--. ||'.--. ||E.--. ||M.--. |");
        System.out.println(
                "                                                | :/\\: || :/\\: || :/\\: || :/\\: || :/\\: || (\\/) || (\\/) |");
        System.out.println(
                "                                                | (__) || :\\/: || (__) || (__) || :\\/: || :\\/: || :\\/: |");
        System.out.println(
                "                                                | '--'H|| '--'O|| '--'L|| '--'D|| '--''|| '--'E|| '--'M|");
        System.out.println(
                "                                                `------'`------'`------'`------'`------'`------'`------'");
        System.out.print("Shuffling Cards ");

        for (int i = 0; i < 3; i++) {
            Game.wait(300);
            System.out.print(". ");
        }

        Game.wait(500);
        System.out.println();
        System.out.print("Stacking Chips ");
        for (int i = 0; i < 3; i++) {
            Game.wait(300);
            System.out.print(". ");
        }

        Game.wait(500);
        System.out.println();
        System.out.print("Loading ");
        for (int i = 0; i < 10; i++) {
            Game.wait(200);
            System.out.print(Character.toChars(0x2593));
        }
        Game.wait(800);
        System.out.print(" 100%");
        Game.wait(1500);
        System.out.println();
        System.out.println();
    }

    // Prints the Player's/Bot's Balance, Bet, Blind and Table's Bet, Pot
    public static void showPlayerAttributes(Player o, Table table1, boolean afterRound1) {
        String playerName = o.getName();

        // Only if player type is a Player, print the player's hand
        if (o.getType().equals("Player")) {
            System.out.println(playerName + " hand: ");
            GameTextDisplay.printCard(o.getHand());
        }

        System.out.println("┌────────────────┐──────┐");
        switch (playerName.length()) {
            case 6:
                System.out.print("| " + playerName + " balance | ");
                break;
            case 5:
                System.out.print("| " + playerName + " balance  | ");
                break;
            case 4:
                System.out.print("| " + playerName + " balance   | ");
                break;
            default:
                System.out.print("| " + playerName + " balance    | ");
                break;
        }
        if (!afterRound1 && o.getIsBigBlind() && !o.getIsBlindPaid()) { // Pre-Flop, big blind, and not blinded
            // Change blind amount accordingly to whether bot can pay it
            if (o.getBalance() < 10) {
                int remainingBalance = o.getBalance();
                o.raiseBet(remainingBalance);
                table1.raiseCurrentBetAmt(remainingBalance);
                table1.raisePot(remainingBalance);
            } else {
                o.raiseBet(10);
                table1.raiseCurrentBetAmt(10);
                table1.raisePot(10);
            }
            
            System.out.print(o.getBalance());

        } else if (!afterRound1 && o.getIsSmallBlind() && !o.getIsBlindPaid()) { // Pre-Flop, big blind, and not blinded
            // Change blind amount accordingly to whether bot can pay it
            if (o.getBalance() < 5){
                int remainingBalance = o.getBalance();
                if (remainingBalance > table1.getCurrentBetAmt()) {
                    table1.raiseCurrentBetAmt(remainingBalance - table1.getCurrentBetAmt());
                }
                o.raiseBet(remainingBalance);
                table1.raisePot(remainingBalance);
            } else {
                if (5 > table1.getCurrentBetAmt()) {
                    table1.raiseCurrentBetAmt(5-table1.getCurrentBetAmt());
                }
                o.raiseBet(5);
                table1.raisePot(5);
            }
            
            System.out.print(o.getBalance());

        } else { // Flop/Turn/River balance
            System.out.print(o.getBalance());
        }
        if (o.getBalance() >= 1000) {
            System.out.println(" |");
        } else if (o.getBalance() >= 100) {
            System.out.println("  |");
        } else if (o.getBalance() >= 10) {
            System.out.println("   |");
        } else {
            System.out.println("    |");
        }
        System.out.print("| Your bet       | " + o.getBet());
        if (o.getBet() >= 1000) {
            System.out.println(" |");
        } else if (o.getBet() >= 100) {
            System.out.println("  |");
        } else if (o.getBet() >= 10) {
            System.out.println("   |");
        } else {
            System.out.println("    |");
        }
        if (!afterRound1 && o.getIsBigBlind() && !o.getIsBlindPaid()) {
            System.out.println("| Big Blind      | 10   |");
        } else if (!afterRound1 && o.getIsSmallBlind() && !o.getIsBlindPaid()) {
            System.out.println("| Small Blind    | 5    |");
        }
        System.out.print("| Table's pot    | " + table1.getPot());
        if (table1.getPot() >= 1000) {
            System.out.println(" |");
        } else if (table1.getPot() >= 100) {
            System.out.println("  |");
        } else if (table1.getPot() >= 10) {
            System.out.println("   |");
        } else {
            System.out.println("    |");
        }
        System.out.print("| Table's Bet    | " + table1.getCurrentBetAmt());
        if (table1.getCurrentBetAmt() >= 1000) {
            System.out.println(" |");
        } else if (table1.getCurrentBetAmt() >= 100) {
            System.out.println("  |");
        } else if (table1.getCurrentBetAmt() >= 10) {
            System.out.println("   |");
        } else {
            System.out.println("    |");
        }
        System.out.println("└────────────────┘──────┘");
    }

    // Prints the card for however many cards there are in the given ArrayList
    public static void printCard(ArrayList<Card> handTable) {
        for (int i = 0; i < handTable.size(); i++) {
            System.out.print(" ┌─────────┐ ");
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) {
            if (handTable.get(i).getRank() <= 9) {
                System.out.printf(" |%d        | ", handTable.get(i).getRank());
            } else if (handTable.get(i).getRank() == 10) {
                System.out.printf(" |%d       | ", handTable.get(i).getRank());
            } else if (handTable.get(i).getRank() == 11) {
                System.out.print(" |J        | ");
            } else if (handTable.get(i).getRank() == 12) {
                System.out.print(" |Q        | ");
            } else if (handTable.get(i).getRank() == 13) {
                System.out.print(" |K        | ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.print(" |A        | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) { // ------------First Row
            int s = 0;
            if (handTable.get(i).getSuit().equals("Hearts")) {
                s = 3;
            } else if (handTable.get(i).getSuit().equals("Diamonds")) {
                s = 4;
            } else if (handTable.get(i).getSuit().equals("Clubs")) {
                s = 5;
            } else if (handTable.get(i).getSuit().equals("Spades")) {
                s = 6;
            }

            if (handTable.get(i).getRank() >= 4 && handTable.get(i).getRank() <= 10) {
                System.out.print(" |  " + (char) s + "   " + (char) s + "  | ");
            } else if (handTable.get(i).getRank() == 11) {
                System.out.print(" |       _ | ");
            } else if (handTable.get(i).getRank() == 12) {
                System.out.print(" |       ^ | ");
            } else if (handTable.get(i).getRank() == 13) {
                System.out.print(" |       W | ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.print(" |         | ");
            } else {
                System.out.print(" |    " + (char) s + "    | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) { // -----------Second Row
            int s = 0;
            if (handTable.get(i).getSuit().equals("Hearts")) {
                s = 3;
            } else if (handTable.get(i).getSuit().equals("Diamonds")) {
                s = 4;
            } else if (handTable.get(i).getSuit().equals("Clubs")) {
                s = 5;
            } else if (handTable.get(i).getSuit().equals("Spades")) {
                s = 6;
            }

            if (handTable.get(i).getRank() >= 2 && handTable.get(i).getRank() <= 6) {
                System.out.print(" |         | ");
            } else if (handTable.get(i).getRank() >= 8 && handTable.get(i).getRank() <= 10) {
                System.out.print(" |  " + (char) s + "   " + (char) s + "  | ");
            } else if (handTable.get(i).getRank() == 11) {
                System.out.print(" |   ___<O | ");
            } else if (handTable.get(i).getRank() == 12) {
                System.out.print(" |   ___<O>| ");
            } else if (handTable.get(i).getRank() == 13) {
                System.out.print(" |   ___<O | ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.print(" |         | ");
            } else {
                System.out.print(" |    " + (char) s + "    | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) { // --------------Third Row
            int s = 0;
            if (handTable.get(i).getSuit().equals("Hearts")) {
                s = 3;
            } else if (handTable.get(i).getSuit().equals("Diamonds")) {
                s = 4;
            } else if (handTable.get(i).getSuit().equals("Clubs")) {
                s = 5;
            } else if (handTable.get(i).getSuit().equals("Spades")) {
                s = 6;
            }

            if (handTable.get(i).getRank() == 3 || handTable.get(i).getRank() == 5 || handTable.get(i).getRank() == 7
                    || handTable.get(i).getRank() == 9 || handTable.get(i).getRank() == 14) {
                System.out.print(" |    " + (char) s + "    | ");
            } else if (handTable.get(i).getRank() == 2 || handTable.get(i).getRank() == 4
                    || handTable.get(i).getRank() == 8) {
                System.out.print(" |         | ");
            } else if (handTable.get(i).getRank() == 6 || handTable.get(i).getRank() == 10) {
                System.out.print(" |  " + (char) s + "   " + (char) s + "  | ");
            } else if (handTable.get(i).getRank() == 11 || handTable.get(i).getRank() == 12
                    || handTable.get(i).getRank() == 13) {
                System.out.print(" |  / " + (char) s + "  | | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) { // ---------------4th Row
            int s = 0;
            if (handTable.get(i).getSuit().equals("Hearts")) {
                s = 3;
            } else if (handTable.get(i).getSuit().equals("Diamonds")) {
                s = 4;
            } else if (handTable.get(i).getSuit().equals("Clubs")) {
                s = 5;
            } else if (handTable.get(i).getSuit().equals("Spades")) {
                s = 6;
            }

            if (handTable.get(i).getRank() >= 2 && handTable.get(i).getRank() <= 6) {
                System.out.print(" |         | ");
            } else if (handTable.get(i).getRank() >= 8 && handTable.get(i).getRank() <= 10) {
                System.out.print(" |  " + (char) s + "   " + (char) s + "  | ");
            } else if (handTable.get(i).getRank() >= 11 && handTable.get(i).getRank() <= 13) {
                System.out.print(" |  |____/ | ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.print(" |         | ");
            } else {
                System.out.print(" |    " + (char) s + "    | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) {// ---------Last Row
            int s = 0;
            if (handTable.get(i).getSuit().equals("Hearts")) {
                s = 3;
            } else if (handTable.get(i).getSuit().equals("Diamonds")) {
                s = 4;
            } else if (handTable.get(i).getSuit().equals("Clubs")) {
                s = 5;
            } else if (handTable.get(i).getSuit().equals("Spades")) {
                s = 6;
            }

            if (handTable.get(i).getRank() == 2 || handTable.get(i).getRank() == 3) {
                System.out.print(" |    " + (char) s + "    | ");
            } else if (handTable.get(i).getRank() >= 4 && handTable.get(i).getRank() <= 10) {
                System.out.print(" |  " + (char) s + "   " + (char) s + "  | ");
            } else if (handTable.get(i).getRank() == 11) {
                System.out.print(" | O>      | ");
            } else if (handTable.get(i).getRank() == 12) {
                System.out.print(" |<O>      | ");
            } else if (handTable.get(i).getRank() == 13) {
                System.out.print(" | O>      | ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.print(" |         | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) {
            if (handTable.get(i).getRank() <= 9) {
                System.out.printf(" |        %d| ", handTable.get(i).getRank());
            } else if (handTable.get(i).getRank() == 10) {
                System.out.printf(" |       %d| ", handTable.get(i).getRank());
            } else if (handTable.get(i).getRank() == 11) {
                System.out.printf(" |        J| ", handTable.get(i).getRank());
            } else if (handTable.get(i).getRank() == 12) {
                System.out.printf(" | V      Q| ");
            } else if (handTable.get(i).getRank() == 13) {
                System.out.printf(" | M      K| ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.printf(" |        A| ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) {
            System.out.print(" └─────────┘ ");
        }
        System.out.println();

    }

    public static void printShowDown(ArrayList<Player> showDownPlayers) {
        ArrayList<Card> showDownCards = new ArrayList<Card>();
        for (Player h : showDownPlayers) {
            if (h.getIsPlaying()) {
                switch (h.getName().length()) {
                    case 6:
                        System.out.print(" " + h.getName() + "                   ");
                        break;
                    case 5:
                        System.out.print(" " + h.getName() + "                    ");
                        break;
                    case 4:
                        System.out.print(" " + h.getName() + "                     ");
                        break;
                    default:
                        System.out.print(" " + h.getName() + "                      ");
                        break;
                }
                showDownCards.addAll(h.getHand());
            }
        }
        System.out.println();
        GameTextDisplay.printCard(showDownCards);
    }

    // Changes the Player's action prompts accordingly if they have a big blind
    public static String getBigBlindInput(String action, Player o) {
        ConsoleInputHandler handler = new ConsoleInputHandler();
        while (true) {
            try {
                System.out.println("Check / Bet / Fold");
                action = handler.getInput();
                if (action.equals("call") || action.equals("raise")) {
                    throw new InputMismatchException("You cannot " + action + "!");
                }
                o.setIsBlindPaid(true);
                return action;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Changes the Player's action prompts accordingly if they have a small blind
    public static String getSmallBlindInput(Player o, Table table1, InputHandler inputHandler, String action) {
        while (true) {
            try {
                if (o.getBet() < table1.getCurrentBetAmt()) { // Someone raised
                    if (table1.getCurrentBetAmt() == 10) {
                        System.out.println("Call / Bet / Fold");
                        action = inputHandler.getInput();
                        if (action.equals("check") || action.equals("raise")) {
                            throw new InputMismatchException("You cannot " + action + "!");
                        }
                    } else {
                        System.out.println("Call / Raise / Fold");
                        action = inputHandler.getInput();
                        if (action.equals("check") || action.equals("bet")) {
                            throw new InputMismatchException("You cannot " + action + "!");
                        }
                    }
                } else { // No one raised yet
                    System.out.println("Check / Bet / Fold");
                    action = inputHandler.getInput();
                    if (action.equals("call") || action.equals("raise")) {
                        throw new InputMismatchException("You cannot " + action + "!");
                    }
                }
                o.setIsBlindPaid(true);
                return action;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    // Changes the Player's action prompts accordingly if they have no blind
    public static String getNonBlindPlayerInput(Player o, Table table1, InputHandler inputHandler, String action){
        // If broke
        if (o.getBalance() == 0) {
            System.out.println("You have 0 balance left, no actions available");
            action = "check";
            return action;
            // If not broke
        } else {
            while (true) {
                try {
                    // Someone raised
                    if (o.getBet() < table1.getCurrentBetAmt()) {

                        // Not enough to raise beyond, balance not enough
                        if (o.getBalance() + o.getBet() <= table1.getCurrentBetAmt()) {
                            System.out.println("Call (All in) /       / Fold");
                            action = inputHandler.getInput();

                            if (action.equals("check") || action.equals("raise")
                                    || action.equals("bet")) {
                                throw new InputMismatchException("You cannot " + action + "!");
                            }
                            return action;

                            // Enough to call OR raise beyond
                        } else {
                            System.out.println("Call / Raise / Fold");
                            action = inputHandler.getInput();

                            if (action.equals("check") || action.equals("bet")) {
                                throw new InputMismatchException("You cannot " + action + "!");
                            }
                            return action;
                        }

                        // No one raised
                    } else {
                        System.out.println("Check / Bet / Fold");
                        action = inputHandler.getInput();

                        if (action.equals("call") || action.equals("raise")) {
                            throw new InputMismatchException("You cannot " + action + "!");
                        }
                        return action;
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    public static void bettingRoundNameArt(int bettingRoundNumber) {
        switch (bettingRoundNumber) {
            case 1:
                System.out.println(".------..------..------.        .------..------..------..------.");
                System.out.println("|P.--. ||R.--. ||E.--. |        |F.--. ||L.--. ||O.--. ||P.--. |");
                System.out.println("| :/\\: || :(): || (\\/) |        | :(): || :/\\: || :/\\: || :/\\: |");
                System.out.println("| (__) || ()() || :\\/: |        | ()() || (__) || :\\/: || (__) |");
                System.out.println("| '--'P|| '--'R|| '--'E|        | '--'F|| '--'L|| '--'O|| '--'P|");
                System.out.println("`------'`------'`------'        `------'`------'`------'`------'");
                break;
            case 2:
            System.out.println(".------..------..------..------.");
            System.out.println("|F.--. ||L.--. ||O.--. ||P.--. |");
            System.out.println("| :(): || :/\\: || :/\\: || :/\\: |");
            System.out.println("| ()() || (__) || :\\/: || (__) |");
            System.out.println("| '--'F|| '--'L|| '--'O|| '--'P|");
            System.out.println("`------'`------'`------'`------'");
                break;
            case 3:
                System.out.println(".------..------..------..------.");
                System.out.println("|T.--. ||U.--. ||R.--. ||N.--. |");
                System.out.println("| :/\\: || (\\/) || :(): || :(): |");
                System.out.println("| (__) || :\\/: || ()() || ()() |");
                System.out.println("| '--'T|| '--'U|| '--'R|| '--'N|");
                System.out.println("`------'`------'`------'`------'");


                break;
            case 4:
                System.out.println(".------..------..------..------..------.");
                System.out.println("|R.--. ||I.--. ||V.--. ||E.--. ||R.--. |");
                System.out.println("| :(): || (\\/) || :(): || (\\/) || :(): |");
                System.out.println("| ()() || :\\/: || ()() || :\\/: || ()() |");
                System.out.println("| '--'R|| '--'I|| '--'V|| '--'E|| '--'R|");
                System.out.println("`------'`------'`------'`------'`------'");
                break;
            default:
                System.out.println(".------..------..------..------.        .------..------..------..------.");
                System.out.println("|S.--. ||H.--. ||O.--. ||W.--. |        |D.--. ||O.--. ||W.--. ||N.--. |");
                System.out.println("| :/\\: || :/\\: || :/\\: || :/\\: |        | :/\\: || :/\\: || :/\\: || :(): |");
                System.out.println("| :\\/: || (__) || :\\/: || :\\/: |        | (__) || :\\/: || :\\/: || ()() |");
                System.out.println("| '--'S|| '--'H|| '--'O|| '--'W|        | '--'D|| '--'O|| '--'W|| '--'N|");
                System.out.println("`------'`------'`------'`------'        `------'`------'`------'`------'");
                break;
        }
        


    }
}
