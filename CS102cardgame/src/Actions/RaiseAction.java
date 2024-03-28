package Actions;

public class RaiseAction {
    public RaiseAction(int amount){
        super("Raise", "Raises", amount);
    }

    public toString(){
        String.format("Raise(%d)", getAmount());
    }
}
