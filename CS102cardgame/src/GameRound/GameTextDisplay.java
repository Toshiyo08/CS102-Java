package GameRound;

import java.util.ArrayList;

import Base.*;
import Players.*;

public class GameTextDisplay {
    public void startingScreen() {

        System.out.println("                                                                       Welcome to");
        System.out.println(
                "                                                        .------..------..------..------..------."          );
        System.out.println(
                "                                                        |T.--. ||E.--. ||X.--. ||A.--. ||S.--. |"          );
        System.out.println(
                "                                                        | :/\\: || (\\/) || :/\\: || (\\/) || :/\\: |"          );
        System.out.println(
                "                                                        | (__) || :\\/: || (__) || :\\/: || :\\/: |"          );
        System.out.println(
                "                                                        | '--'T|| '--'E|| '--'X|| '--'A|| '--'S|"          );
        System.out.println(
                "                                                        `------'`------'`------'`------'`------'"          );
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
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(". ");
        }

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.print("Stacking Chips ");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(". ");
        }

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.print("Loading ");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(Character.toChars(0x2593));
        }
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(" 100%");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
    }

    public static void showPlayerAttributes(Player o, Table table1, boolean afterRound1) {
        String playerName = o.getName();
        
        if (o.getType().equals("Player")){
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
        if (!afterRound1 && o.getIsBigBlind() && !o.getIsBlindPaid()) { // Not chen round, big blind, and not blinded yet
            o.raiseBet(10);
            table1.raiseCurrentBetAmt(10);
            table1.raisePot(10);
            System.out.print(o.getBalance());

        } else if (!afterRound1 && o.getIsSmallBlind() && !o.getIsBlindPaid()) {
            o.raiseBet(5);
            table1.raisePot(5);
            System.out.print(o.getBalance());

        } else {
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

}
