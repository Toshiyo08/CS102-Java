public class GameTextDisplay {
    public void startingScreen() {

        System.out.println("                                                                       Welcome to");
        System.out.println(
                "                                                        .------..------..------..------..------.          ");
        System.out.println(
                "                                                        |T.--. ||E.--. ||X.--. ||A.--. ||S.--. |          ");
        System.out.println(
                "                                                        | :/\\: || (\\/) || :/\\: || (\\/) || :/\\: |          ");
        System.out.println(
                "                                                        | (__) || :\\/: || (__) || :\\/: || :\\/: |          ");
        System.out.println(
                "                                                        | '--'T|| '--'E|| '--'X|| '--'A|| '--'S|          ");
        System.out.println(
                "                                                        `------'`------'`------'`------'`------'          ");
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

    public static void showPlayerAttributes(Player o, Table table1, Boolean afterRound1) {
        String playerName = o.getName();
        System.out.println(playerName + " hand: ");
        Card.printCard(o.getHand());
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

}
