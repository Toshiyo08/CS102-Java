package Input;

import java.util.InputMismatchException;
import java.util.Scanner;

import Players.Player;

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
                String inputCommand = sc.nextLine();
                inputCommand = inputCommand.trim().toLowerCase();
                // inputCommand = inputCommand.replaceAll("\\s", "");
                if (inputCommand.equals("check") || inputCommand.equals("fold") || inputCommand.equals("call")  
                || inputCommand.equals("bet")|| inputCommand.equals("raise")){
                    return inputCommand;
                } 
                else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println("try again");
            }
        }
    }

    @Override
    public int getBetInput(Player p) {
         int inputBet;
        while (true) {
            try {
                sc = new Scanner(System.in);
                if (sc.hasNextInt()) {
                    inputBet = sc.nextInt();
                    if (inputBet - p.getBet() > p.getBalance()) {
                        throw new InputMismatchException("Insufficient Balance, Enter new bet> ");
                    }
                    return inputBet;
                } else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.print(e.getMessage());
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


}
