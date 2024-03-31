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


}
