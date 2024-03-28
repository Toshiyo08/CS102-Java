import java.util.*;

public class mainGame {
    public static void main(String[] args) {
        // Game.startingScreen(); // AESTHETICS
        ArrayList<Player> playersList = new ArrayList<Player>();
        // String userName = "";
        // System.out.println("Please enter a name between 3 and 6 characters"); 
        // while (true) {
        //     try {
        //         System.out.print("Name> ");
        //         Scanner sc = new Scanner(System.in);
        //         userName = sc.nextLine();
        //         userName = userName.trim();
        //         if (userName == "") {
        //             throw new InputMismatchException("No name was entered!");
        //         } 
        //         if (userName.length() >= 7) {
        //             throw new InputMismatchException("Please enter a name of not more than 6 characters!");
        //         }
        //         if (userName.length() <= 2) {
        //             throw new InputMismatchException("Please enter a name of at least 3 characters!");
        //         }
        //         break;
        //     } catch (InputMismatchException e) {
        //         System.out.println(e.getMessage());
        //     }
        // }
        // Initialise a player that user controls and add into list of players
        // Player userPlayer = new Player(userName, "Player");
        Player userPlayer = new Player("Tom", "Player");
        playersList.add(userPlayer);
        Player userPlayer1 = new PlayerBot("Nic", "Bot", 10);
        playersList.add(userPlayer1);
        Player userPlayer2 = new PlayerBot("Ken", "Bot", 70);
        playersList.add(userPlayer2);

        

        // Keeps prompting for number of players to start game
        int numberOfPlayers = 0;
        // numberOfPlayers = Game.getNumBot();
        // Map<String, PlayerBot> botMap = new HashMap<String, PlayerBot>();
        // botMap.put("Arthur", new PlayerBot("Arthur", "Bot", 75));
        // botMap.put("Paul", new PlayerBot("Paul", "Bot", 65));
        // botMap.put("Ken", new PlayerBot("Ken", "Bot", 15));
        // botMap.put("Nic ", new PlayerBot("Nic", "Bot", 10));
        // botMap.put("Juan", new PlayerBot("Juan", "Bot", 20));
        // botMap.put("Anna", new PlayerBot("Abigail", "Bot", 50));
        // botMap.put("José", new PlayerBot("José", "Bot", 35));
        // botMap.put("Sally", new PlayerBot("Sally", "Bot", 45));
        // botMap.put("Helen", new PlayerBot("Helen", "Bot", 75));
        // for (String key : botMap.keySet()) {
        //     if (!(playersList.contains(botMap.get(key)))) {
        //         playersList.add(botMap.get(key));
        //     }
        //     if (playersList.size() == numberOfPlayers + 1) {
        //         break;
        //     }
        // }

        // DON'T USE BELOW DON'T DELETE
        // for (int i = 0; i < numberOfPlayers; i++) {
        // Convert HashMap entries to a List
        // List<Map.Entry<String, PlayerBot>> entryList = new ArrayList<>(botMap.entrySet());

        // // Shuffle the List
        // Collections.shuffle(entryList);

        // // Create a new HashMap to store shuffled entries
        // HashMap<String, PlayerBot> shuffledMap = new HashMap<>();

        // // Populate the shuffled HashMap with shuffled entries
        // for (Map.Entry<String, PlayerBot> entry : entryList) {
        //     shuffledMap.put(entry.getKey(), entry.getValue());
        // }
        //     // Get the entry at the first index (index 0)
        //     Map.Entry<String, PlayerBot> firstEntry = shuffledMap.entrySet().iterator().next();

        //     if (!(playersList.contains(firstEntry.getValue()))) {
        //         playersList.add(firstEntry.getValue());
        //     }
        //     System.out.println(firstEntry.getValue().getName());
        // }
        // DON'T USE ABOVE DON'T DELETE

        
        // Player[] turnOrder = { userPlayer, userPlayer1, userPlayer2 };
        Player[] turnOrder = new Player[playersList.size()];
        for (int i = 0; i < playersList.size(); i++) {
            turnOrder[i] = playersList.get(i);
        }
        turnOrder[0].setBigBlind(true);
        turnOrder[1].setSmallgBlind(true);

        boolean gameContinue = true;
        int originalBalance = userPlayer.getBalance();
        int gameCounter = 1;
        int bigBlind = 10;
        int smallBlind = 5;


        while (gameContinue) {
            int numTimesBet = 0;
            boolean afterRound1 = false;
            System.out.println("Game " + gameCounter++ + "!");

            Table table1 = new Table();
            Deck deck1 = new Deck();

            Game game = new Game();

            // Shuffle Deck
            Collections.shuffle(deck1.getCards());

            // Cut Deck
            // Scanner getCutNum = new Scanner (System.in);
            // deck1.cutDeck();

            //deal cards
            for (Player e : playersList) {
            e.draw(deck1.dealCard());
            e.draw(deck1.dealCard());
            }

            // userPlayer.draw(new Card("Clubs", 3)); // Tom
            // userPlayer.draw(new Card("Hearts", 11));
            // userPlayer2.draw(new Card("Clubs", 12));
            // userPlayer2.draw(new Card("Hearts", 14));
            // userPlayer1.draw(new Card("Diamonds", 2));
            // userPlayer1.draw(new Card("Spades", 3));

            // 1st Betting Round
            Game.bettingRound(playersList, table1, afterRound1, turnOrder, ++numTimesBet);
            table1.setCurrentBet(0);
            for (Player t : playersList) {
                t.setBet(0);
            }
            if (Game.timeToOpenHand(playersList, table1, deck1, numTimesBet)) {
                Game.resetRound(playersList, table1); // Resets player and table attributes

                if (Game.isWinLose(playersList)) { // If you win all or lost all, game ends.
                    gameContinue = false;
                    break;
                }

                Scanner scRoundEndallin = new Scanner(System.in);
                System.out.println("Start new round?(Y / N)> ");
                String newRound = scRoundEndallin.nextLine();
                if (newRound.equals("N") || newRound.equals("n")) {
                    gameContinue = false;
                    scRoundEndallin.close();
                } else if (newRound.equals("Y") || newRound.equals("y")) {
                    // Reset everything
                    Game.moveBlind(turnOrder);
                    continue;
                }

                continue;
            }
            afterRound1 = true;

           // Deal Flop (3 cards)
            for (int i = 0; i < 3; i++) {
                deck1.burnCard();
                table1.drawComm(deck1.dealCard());
            }
            // table1.drawComm(new Card("Diamonds", 9));
            // table1.drawComm(new Card("Spades", 8));
            // table1.drawComm(new Card("Clubs", 9));
            // table1.drawComm(new Card("Hearts", 8));
            // table1.drawComm(new Card("Spades", 14));
            Card.printCard(table1.getCommCards());

            // 2nd Betting Round
            Game.bettingRound(playersList, table1, afterRound1, turnOrder, ++numTimesBet);
            table1.setCurrentBet(0);
            for (Player t : playersList) {
                t.setBet(0);
            }
            if (Game.timeToOpenHand(playersList, table1, deck1, numTimesBet)) {
                Game.resetRound(playersList, table1);

                if (Game.isWinLose(playersList)) {
                    gameContinue = false;
                    break;
                }

                Scanner scRoundEndallin = new Scanner(System.in);
                System.out.println("Start new round?(Y / N)> ");
                String newRound = scRoundEndallin.nextLine();
                if (newRound.equals("N") || newRound.equals("n")) {
                    gameContinue = false;
                    scRoundEndallin.close();
                } else if (newRound.equals("Y") || newRound.equals("y")) {
                    Game.moveBlind(turnOrder);
                    continue;
                }

                continue;
            }

            // Deal Turn
            deck1.burnCard();
            table1.drawComm(deck1.dealCard());
            Card.printCard(table1.getCommCards());

            // 3rd Betting Round
            Game.bettingRound(playersList, table1, afterRound1, turnOrder, ++numTimesBet);
            table1.setCurrentBet(0);
            for (Player t : playersList) {
                t.setBet(0);
            }
            if (Game.timeToOpenHand(playersList, table1, deck1, numTimesBet)) {
                Game.resetRound(playersList, table1);

                if (Game.isWinLose(playersList)) {
                    gameContinue = false;
                    break;
                }
                Scanner scRoundEndallin = new Scanner(System.in);
                System.out.println("Start new round?(Y / N)> ");
                String newRound = scRoundEndallin.nextLine();
                if (newRound.equals("N") || newRound.equals("n")) {
                    gameContinue = false;
                    scRoundEndallin.close();
                } else if (newRound.equals("Y") || newRound.equals("y")) {
                    Game.moveBlind(turnOrder);
                    continue;
                }

                continue;
            }

            // Deal River
            deck1.burnCard();
            table1.drawComm(deck1.dealCard());
            Card.printCard(table1.getCommCards());

            // Last Betting Round
            Game.bettingRound(playersList, table1, afterRound1, turnOrder, ++numTimesBet);
            table1.setCurrentBet(0);
            for (Player t : playersList) {
                t.setBet(0);
            }
            if (Game.timeToOpenHand(playersList, table1, deck1, numTimesBet)) {
                Game.resetRound(playersList, table1);

                if (Game.isWinLose(playersList)) {
                    gameContinue = false;
                    break;
                }
                Scanner scRoundEndallin = new Scanner(System.in);
                System.out.println("Start new round?(Y / N)> ");
                String newRound = scRoundEndallin.nextLine();
                if (newRound.equals("N") || newRound.equals("n")) {
                    gameContinue = false;
                    scRoundEndallin.close();
                } else if (newRound.equals("Y") || newRound.equals("y")) {
                    Game.moveBlind(turnOrder);
                    continue;
                }

                continue;
            }

            // for second game
            System.out.println(table1.getPot());

            Game.getWinner(playersList, table1);

            System.out.println("Round over");

            if (Game.isWinLose(playersList)) {
                gameContinue = false;
                break;
            }
            Scanner scRoundEndallin = new Scanner(System.in);
            System.out.println("Start new round?(Y / N)> ");
            String newRound = scRoundEndallin.nextLine();
            if (newRound.equals("N") || newRound.equals("n")) {
                gameContinue = false;
                scRoundEndallin.close();
            } else if (newRound.equals("Y") || newRound.equals("y")) {
                Game.resetRound(playersList, table1);
                Game.moveBlind(turnOrder); // moves big blind small blind, moves order of players
                continue;
            }

            continue;
        }
        System.out.println("Thanks for playing Texas Hold'em!");
        System.out.println("See you next time!");
        if (originalBalance > userPlayer.getBalance() && userPlayer.getBalance() != 0) {
            System.out.println("You lost $" + (originalBalance - userPlayer.getBalance()));
            System.out.println("Better luck next time!");
        } else if (userPlayer.getBalance() == originalBalance * playersList.size()) {
            System.out.println("You won everything!");
        } else if (userPlayer.getBalance() == 0) {
            System.out.println("HAHAHA HOW TF YOU LOSE EVERYTHING TO BOTS");
        } else if (userPlayer.getBalance() == 100) {
            System.out.println("Damn, you didn't win anything? Congrats on wasting your time!");
        } else /* if (originalBalance < userPlayer.getBalance()) */ {
            System.out.println("You won $" + (userPlayer.getBalance() - originalBalance));
        }
    }
}
