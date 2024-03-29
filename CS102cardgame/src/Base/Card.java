package Base;

import java.util.ArrayList;

public class Card {
    private final String suit;
    private final int rank;

    public Card(String cardsuit, int cardrank) {
        this.suit = cardsuit;
        this.rank = cardrank;
    }

    public String getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

}
    // ♥♦♣♠
    // ┌─────────┐ ┌─────────┐ 
    // |2        | |3        | 
    // |         | |    ♥    | 
    // |    ♥    | |         | 
    // |         | |    ♥    | 
    // |    ♥    | |         | 
    // |         | |    ♥    | 
    // |        2| |        3| 
    // └─────────┘ └─────────┘ 
    // ┌─────────┐ ┌─────────┐ 
    // |4        | |5        | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |         | |         | 
    // |         | |    ♥    | 
    // |         | |         | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |        4| |        5| 
    // └─────────┘ └─────────┘ 
    // ┌─────────┐ ┌─────────┐ 
    // |6        | |7        | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |    ♥    | |  ♥   ♥  | 
    // |         | |    ♥    | 
    // |    ♥    | |         | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |        6| |        7| 
    // └─────────┘ └─────────┘ 
    // ┌─────────┐ ┌─────────┐ 
    // |8        | |9        | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |         | |    ♥    | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |        8| |        9| 
    // └─────────┘ └─────────┘ 
    // ┌─────────┐ ┌─────────┐ 
    // |10       | |J        | 
    // |  ♥   ♥  | |       ▲ | ▲ ▼
    // |  ♥   ♥  | |   ___<O | 
    // |  ♥   ♥  | |  /    | | 
    // |  ♥   ♥  | |  |____/ | 
    // |  ♥   ♥  | | O>      | 
    // |       10| | ▼      J| 
    // └─────────┘ └─────────┘   // Dune Cards??
    // ┌─────────┐ ┌─────────┐  |  ┌─────────┐ ┌─────────┐ ┌─────────┐ ┌─────────┐ 
    // |Q        | |K        |  |  |J        | |Q        | |K        | |A        | 
    // |       ^ | |       W |  |  |       ^ | |       W | |       ^ | |         | 
    // |   ___<O>| |   ___<O |  |  |   ___<O>| |   ___<O | |   ___<O>| |         | 
    // |  /    | | |  /    | |  |  |  /    | | |  /    | | |  /    | | |    ♥    | 
    // |  |____/ | |  |____/ |  |  |  |____/ | |  |____/ | |  |____/ | |         | 
    // |<O>      | | O>      |  |  |<O>      | | O>      | |<O>      | |         | 
    // | V      Q| | M      K|  |  | V      J| | M      Q| | V      K| |        A| 
    // └─────────┘ └─────────┘  |  └─────────┘ └─────────┘ └─────────┘ └─────────┘
    // ┌─────────┐ ┌─────────┐ 
    // |A        | |J        | 
    // |         | |         | 
    // |         | |  ♥   ♥  | 
    // |    ♥    | |         | 
    // |         | |  ♥   ♥  | 
    // |         | |         | 
    // |        A| |        J| 
    // └─────────┘ └─────────┘ 
    //