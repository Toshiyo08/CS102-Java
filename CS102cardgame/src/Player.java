package CS102cardgame.src;

import java.util.List;

public class Player {
    private String type;
    private String name;
    private boolean playing;
    private boolean bigBlind;
    private boolean smallBlind;
    private int betAmt;
    private int playerChips;
    private List<Card> hand; // 10jqka|23456789 10|11|12|13|14
    
    public Player(String name, String type) {
        this.name = name;
        this.type = type;
        this.playing = true;
        this.hand = null;
        this.playerChips = 100;
        this.betAmt = 0;
        this.bigBlind = false;
        this.smallBlind = false;
    }

    public int getBet(){
        return this.betAmt;
    }

    public int getChips(){
        return this.playerChips;
    }
    
    // Misc Actions: Draw, Buy in
    public void draw(Card newCard) {
        this.hand.add(newCard);
    }

    // Game Actions: Fold, Call, Raise, Check
    public void fold() {
        this.hand = null;
        playerChips = 0;
        this.playing = false;
    }

    public void setCall(int calledAmt) {
        this.betAmt = calledAmt;
        this.playerChips -= calledAmt;
    }

    public void setRaise(int raisedAmt) {
        this.betAmt = raisedAmt;
        this.playerChips -= raisedAmt;
    }

    public Boolean setCheck(String answer) {
        if (answer.equal("Check")){
            return true;
        } else {
            return false;
        }
    }
}
