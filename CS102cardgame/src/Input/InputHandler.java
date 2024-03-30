package Input;

import Players.Player;

//Liskov Substitution Principle
//This interface is implemented by the ConsoleInputHandler class

public interface InputHandler {
    String getInput();
    int getBetInput(Player p, String action);
    String getBigBlindInput(String action, Player o);
    boolean getGameContinue();
    
}
