package Eval;

import Base.Table;
import Players.Player;

public interface BotLogic {
    int getBotAction(Player o, String previousAction, boolean afterBet1, Table table1);
    // int getBotRaiseAmt(int tightness, int handValue, Player o);
    void botThinking(String name);
}
