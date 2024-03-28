package Actions;
import java.math.BigDecimal;

public abstract class Action {
    
    /** Player went all-in. */
    public static final Action ALL_IN = new AllInAction();

    /** Bet. */
    public static final Action BET = new BetAction(0);
    
    /** Posting the big blind. */
    public static final Action BIG_BLIND = new BigBlindAction();
    
    /** Call. */
    public static final Action CALL = new CallAction();
    
    /** Check. */
    public static final Action CHECK = new CheckAction();
    
    /** Fold. */
    public static final Action FOLD = new FoldAction();
    
    /** Raise. */
    public static final Action RAISE = new RaiseAction(0);
    
    /** Posting the small blind. */
    public static final Action SMALL_BLIND = new SmallBlindAction();

    private final String name;
    private final String verb;
    private final int amount;

    public Action(String name, String verb) {
        this(name, verb, 0);
    }
    
    public Action(String name, String verb, int amount) {
        this.name = name;
        this.verb = verb;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getVerb() {
        return verb;
    }

    public int getAmount() {
        return amount;
    }
  
    public String toString (){
        return name;
    }
}
