package Actions;

public class BetAction extends Action {
    public BetAction(int amount){
        super("Bet","Bets", amount);
    }

    public String toString(){
        return String.format("Bet(%d)", getAmount());
    }
    
}
