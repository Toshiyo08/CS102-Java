package Input;

import java.util.InputMismatchException;
import java.util.Scanner;

import Players.*;

public class ConsoleInputHandler implements InputHandler {
    private Scanner sc;

    public ConsoleInputHandler() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public String getInput() {
        while (true) {
            try {
                sc = new Scanner(System.in);
                System.out.print("Action> ");
                String inputCommand = sc.nextLine();
                inputCommand = inputCommand.trim().toLowerCase();
                if (inputCommand.equals("check") || inputCommand.equals("fold") || inputCommand.equals("call")
                        || inputCommand.equals("bet") || inputCommand.equals("raise")) {
                    return inputCommand;
                } else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println("try again");
            }
        }
    }

    @Override
    public int getBetInput(Player p, String action) {
        int inputBet;
        while (true) {
            try {
                sc = new Scanner(System.in);
                if (action.equals("bet")) {
                        System.out.print("Bet to?> ");
                } else {
                    System.out.print("Raise to?> ");
                }
                if (sc.hasNextInt()){
                    inputBet = sc.nextInt();
                    if (inputBet - p.getBet() > p.getBalance()) {
                        throw new InputMismatchException("Insufficient Balance!");
                    }
                    return inputBet;
                } else {
                    throw new InputMismatchException("Invalid input!");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getNumBot() {
        int numberOfPlayers = 0;
        while (true) {
            try {
                System.out.print("Please enter the number of desired bots> ");
                Scanner playerNumbers = new Scanner(System.in);
                if (playerNumbers.hasNextInt()) {
                    numberOfPlayers = playerNumbers.nextInt();
                    if (numberOfPlayers <= 0) {
                        throw new InputMismatchException("There must be at least 1 bot!");
                    }
                    if (numberOfPlayers >= 10) {
                        throw new InputMismatchException("Too many! You can have at most 9 bots");
                    }
                    return numberOfPlayers;
                } else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    

    public boolean getGameContinue() {
        while (true) {
            Scanner scRoundEndallin = new Scanner(System.in);
            try {
                System.out.print("Start new round?(Y / N)> ");
                String newRound = scRoundEndallin.nextLine();
                if (newRound.equals("Y") || newRound.equals("y")) {
                    return true;
                } else if (newRound.equals("N") || newRound.equals("n")) {
                    return false;
                } else {
                    throw new InputMismatchException("Invalid input!");
                }
            } catch (InputMismatchException e) {

                System.out.println(e.getMessage());
            }
        }
    }

    public static String getPlayerNameInput() {
        Scanner sc = new Scanner(System.in);
        String name = "";
        System.out.println("Please enter a name between 3 and 6 characters"); 
        while (true) {
            try {
                System.out.print("Name> ");
                Scanner scName = new Scanner(System.in);
                name = scName.nextLine();
                name = name.trim();
                if (name == "") {
                    throw new InputMismatchException("No name was entered!");
                } 
                if (name.length() >= 7) {
                    throw new InputMismatchException("Please enter a name of not more than 6 characters!");
                }
                if (name.length() <= 2) {
                    throw new InputMismatchException("Please enter a name of at least 3 characters!");
                }
                return name;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

}
