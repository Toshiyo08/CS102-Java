package CS102cardgame.src;

import java.util.*;

public class Table {
    private List<Card> commCards;
    private int pot;

    public Table() {
        this.commCards = null;
        this.pot = 0;
    }

    public void drawComm(Card newCard) {
        this.commCards.add(newCard);
    }

    public int getPot(ArrayList<Player> Tableplayer) {
        pot = 0;
        for (Player plyr: Tableplayer) {
            pot += plyr.getBet();
        }
        return pot;
    }
    
}
