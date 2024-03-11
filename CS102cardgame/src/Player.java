// package CS102cardgame.src;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String type; // Player or Bot
    private String name;
    private boolean isPlaying;
    private boolean checked;
    private boolean bigBlind;
    private boolean smallBlind;
    private int betAmt;
    private int balance;
    private ArrayList<Card> hand; // 10jqka|23456789 10|11|12|13|14
    
    //what is type?
    public Player(String name, String type) {
        this.name = name;
        this.type = type;
        this.isPlaying = true;
        this.checked = false;
        // this.status = true/false -> playing/folded
        // this.check = true/false -> check/called already
        this.hand = new ArrayList<Card>();
        this.balance = 100;
        this.betAmt = 0;
        this.bigBlind = false;
        this.smallBlind = false;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Boolean getChecked () {
        return checked;
    }

    public void setChecked(Boolean isCheck) {
        this.checked = isCheck;
    }

    public Boolean getIsPlaying() {
        return this.isPlaying;
    }

    public void setIsPlaying(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public int getBet(){
        return this.betAmt;
    }

    public void setBet(int newBet){
        this.betAmt = newBet;
    }

    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int raised) {
        this.balance -= raised;
    }

    public ArrayList<Card> getHand(){
        return (ArrayList<Card>) this.hand;
    }
    
    
    
    // Misc Actions: Draw, Buy in
    public void draw(Card newCard) {
        this.hand.add(newCard);
    }

    // Game Actions: Fold, Call, Raise, Check
    public void fold() {
        this.hand = null;
        //pplayerchips will still remain
        //playerChips = 0;
        this.isPlaying = false;
    }

    public void setCall(int calledAmt) {
        this.betAmt = calledAmt;
        this.balance -= calledAmt;
    }

    public void setRaise(int raisedAmt) {
        this.betAmt = raisedAmt;
        this.balance -= raisedAmt;
    }

    // public Boolean setCheck(String answer) {
    //     if (answer.equals("Check")){
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    // public double  calculateChenScore (List<Card> hand){
    //     chenscore = 0;

    //     Card card1 = hand.get(0);
    //     Card card2 = hand.get(1);
    //     int rank1 = card1.getRank();
    //     String temp_suit1 = card1.getSuit();
    //     int rank2 = card2.getRank();
    //     String temp_suit2 = card2.getSuit();
    //     int suit1;
    //     int suit2;
    //     if (temp_suit1.equals("Ace")){
    //         suit1 = 14;
    //     } else if (temp_suit1.equals("King")){
    //         suit1 = 13;
    //     } else if (temp_suit1.equals("Queen")){
    //         suit1 = 12;
    //     } else if (temp_suit1.equals("Jack")){
    //         suit1 = 11;
    //     } else {
    //         suit1 = Integer.parseInt(temp_suit1);
    //     }
    //     if (temp_suit2.equals("Ace")){
    //         suit2 = 14;
    //     } else if (temp_suit2.equals("King")){
    //         suit2 = 13;
    //     } else if (temp_suit2.equals("Queen")){
    //         suit2 = 12;
    //     } else if (temp_suit2.equals("Jack")){
    //         suit2 = 11;
    //     } else {
    //         suit2 = Integer.parseInt(temp_suit2);
    //     }
    //     int highRank = Math.max(rank1, rank2);
    //     int lowRank = Math.min(rank1, rank2);
    //     int rankDiff = highRank - lowRank;
    //     int gap = (rankDiff > 1) ? rankDiff - 1 : 0;  
    //     boolean isPair = (rank1 == rank2);
    //     boolean isSuited = (suit1 == suit2);
        
    //     double score;
        
    //     // 1. Base score highest rank only
    //     //Ace
    //     if (highRank == 14) {
    //         score = 10.0;
    //     } else if (highRank == 13) { 
    //         score = 8.0;
    //     } else if (highRank == 12) { 
    //         score = 7.0;
    //     } else if (highRank == 11) { 
    //         score = 6.0;
    //     } else {
    //         score = (highRank + 2) / 2.0;
    //     }
        
    //     // 2. If pair, double score, with minimum score of 5. 
    //     if (isPair) {
    //         score *= 2.0;
    //         if (score < 5.0) {
    //             score = 5.0;
    //         }
    //     }
        
    //     // 3. If suited, add 2 points.
    //     if (isSuited) {
    //         score += 2.0;
    //     }
        
    //     // 4. Subtract points for gap.
    //     if (gap == 1) {
    //         score -= 1.0;
    //     } else if (gap == 2) {
    //         score -= 2.0;
    //     } else if (gap == 3) {
    //         score -= 4.0;
    //     } else if (gap > 3) {
    //         score -= 5.0;
    //     }
        
    //     // 5. Add 1 point for a 0 or 1 gap and both cards lower than a Queen.
    //     if (!isPair && gap < 2 && rank1 < 12 && rank2 < 12) {
    //         score += 1.0;
    //     }
        
    //     // Minimum score is 0.
    //     if (score < 0.0) {
    //         score = 0.0;
    //     }
        
    //     // 6. Round half point scores up.
    //     return (double) Math.round(score);        
    // }


}
