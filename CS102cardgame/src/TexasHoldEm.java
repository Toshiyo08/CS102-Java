import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TexasHoldEm {
    private GameTextDisplay gameTextDisplay;

    public TexasHoldEm(GameTextDisplay gameTextDisplay) {
        this.gameTextDisplay = gameTextDisplay;
    }

    public void startingScreen() {
        gameTextDisplay.startingScreen();
    }

    public static void main(String[] args) {
        ArrayList<Player> playersList = new ArrayList<Player>();

        TexasHoldEm texasHoldEm = new TexasHoldEm(new GameTextDisplay());
        texasHoldEm.startingScreen();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        // Initialise a player that user controls and add into list of players
        // correct to initialise here
        Player userPlayer = new Player(name, "Player");
        playersList.add(userPlayer);
        Player userPlayer1 = new PlayerBot("Bot1", "Bot", 10);
        playersList.add(userPlayer1);
        Player userPlayer2 = new PlayerBot("Bot2", "Bot", 70);
        playersList.add(userPlayer2);

        int originalBalance = userPlayer.getBalance();

        // keep track of player's turn
        Player[] turnOrder = { userPlayer, userPlayer1, userPlayer2 };

        // Initialise number of bots and add them into list of players
        // for (int i = 0; i < 3; i++) {
        // playersList.add(new PlayerBot("Bot", "Bot"));
        // }

        boolean gameContinue = true;

        // startingScreen();
        int gameCounter = 1;

        // user becomes the big blind first
        userPlayer.setBigBlind(true);

        while (gameContinue) {
            int numTimesBet = 0;
            boolean afterRound1 = false;
            System.out.println("Game " + gameCounter++ + "!");

            Table table1 = new Table();
            Deck deck1 = new Deck();
            InputHandler inputHandler = new ConsoleInputHandler();
            Game game = new Game(inputHandler);

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
            userPlayer1.draw(new Card("Clubs", 14));
            userPlayer1.draw(new Card("Hearts", 14));

            // 1st Betting Round
            game.bettingRound(playersList, table1, afterRound1, turnOrder, ++numTimesBet);
           
            afterRound1 = true;

            // Deal Flop (3 cards)
            for (int i = 0; i < 3; i++) {
                deck1.burnCard();
                table1.drawComm(deck1.dealCard());
            }
            Card.printCard(table1.getCommCards());

            // userPlayer.setChecked(false);
            // userPlayer1.setChecked(false);
            // userPlayer2.setChecked(false);
            // userPlayer.setBet(0);
            // table1.setCurrentBet(0);

            // 2nd Betting Round
            game.bettingRound(playersList, table1, afterRound1, turnOrder, ++numTimesBet);
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
            game.bettingRound(playersList, table1, afterRound1, turnOrder, ++numTimesBet);
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
            game.bettingRound(playersList, table1, afterRound1, turnOrder, ++numTimesBet);
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

            Winner.getWinner(playersList, table1);

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
            System.out.println("You lost everything!");
        } else if (userPlayer.getBalance() == 300) {
            System.out.println("Damn, you didn't win anything? Congrats on wasting your time!");
        } else /* if (originalBalance < userPlayer.getBalance()) */ {
            System.out.println("You won $" + (userPlayer.getBalance() - originalBalance));
        }

    }

    

}
