import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class mainGame {
    public static void main(String[] args) {
        ArrayList<Player> playersList = new ArrayList<Player>();

        int numberOfPlayers = 0;

        // Scanner playerNumbers = new Scanner(System.in);
        // Keeps prompting for number of players to start game
        numberOfPlayers = Game.getNumBot();
        // for (int i = 0; i < numberOfPlayers; i++) {
        //     for (Player o : playersList) {
        //         if (playersList.contains(o)) {

        //         } else {

        //         }
        //     }
            
        // }

        // Initialise a player that user controls and add into list of players
        Player userPlayer = new Player("Tom", "Player");
        playersList.add(userPlayer);
        Player userPlayer1 = new PlayerBot("Nic", "Bot", 10);
        playersList.add(userPlayer1);
        Player userPlayer2 = new PlayerBot("Ken", "Bot", 70);
        playersList.add(userPlayer2);

        int originalBalance = userPlayer.getBalance();
        Player[] turnOrder = { userPlayer, userPlayer1, userPlayer2 };

        // Initialise number of bots and add them into list of players
        // for (int i = 0; i < 3; i++) {
        // playersList.add(new PlayerBot("Bot", "Bot"));
        // }

        boolean gameContinue = true;


        // Game.startingScreen();
        int gameCounter = 1;
        int bigBlind = 10;
        int smallBlind = 5;
        userPlayer.setBigBlind(true);

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

            // deal cards
            // for (Player e : playersList) {
            // e.draw(deck1.dealCard());
            // e.draw(deck1.dealCard());
            // }
            userPlayer.draw(new Card("Clubs", 9)); // Tom
            userPlayer.draw(new Card("Hearts", 5));
            userPlayer2.draw(new Card("Clubs", 14));
            userPlayer2.draw(new Card("Hearts", 14));
            userPlayer1.draw(new Card("Diamonds", 14));
            userPlayer1.draw(new Card("Spades", 14));

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
            // for (int i = 0; i < 3; i++) {
            //     deck1.burnCard();
            //     table1.drawComm(deck1.dealCard());
            // }
            table1.drawComm(new Card("Diamonds", 9));
            table1.drawComm(new Card("Diamonds", 8));
            table1.drawComm(new Card("Spades", 14));
            Card.printCard(table1.getCommCards());

            // userPlayer.setChecked(false);
            // userPlayer1.setChecked(false);
            // userPlayer2.setChecked(false);
            // userPlayer.setBet(0);
            // table1.setCurrentBet(0);

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

            // userPlayer.setChecked(false);
            // userPlayer1.setChecked(false);
            // userPlayer2.setChecked(false);
            // userPlayer.setBet(0);
            // table1.setCurrentBet(0);

            // 3rd Betting Round------------------------------
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

            // userPlayer.setChecked(false);
            // userPlayer1.setChecked(false);
            // userPlayer2.setChecked(false);
            // userPlayer.setBet(0);
            // table1.setCurrentBet(0);

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
