import java.util.InputMismatchException;
import java.util.Scanner;

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
                if (inputCommand.equals("check") || inputCommand.equals("fold") || inputCommand.equals("call")) {
                    return inputCommand;
                } else if (inputCommand.contains("bet")) {
                    return inputCommand;
                } else if (inputCommand.contains("raise")) {
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
}
