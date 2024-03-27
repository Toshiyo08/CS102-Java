public class Blind {

    private boolean bigBlind;
    private boolean smallBlind;
    private boolean blinded;

    public void setBigBlind (Boolean status) {
        this.bigBlind = status;
    }
    
    public Boolean getBigBlind() {
        return this.bigBlind;
    }


    public static void moveBlind(Player[] turnOrder) {
        Player temp = turnOrder[0];
        for (int i = 1; i < turnOrder.length; i++) {
            turnOrder[i - 1] = turnOrder[i];
            turnOrder[i].setBlinded(false);
            turnOrder[i].setBigBlind(false);
            turnOrder[i].setSmallgBlind(false);
        }

        turnOrder[turnOrder.length - 1] = temp;
        turnOrder[0].setBigBlind(true);
        turnOrder[1].setSmallgBlind(true);
    }
}
