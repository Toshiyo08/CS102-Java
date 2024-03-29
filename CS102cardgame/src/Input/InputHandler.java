package Input;

import Players.Player;

public interface InputHandler {
    String getInput();
    int getBetInput(Player p);
    String getBigBlindInput(String action, Player o);
}
