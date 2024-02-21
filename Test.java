import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

public class Test {
    public static void main(String[] args) {
        // JFrame frame = new JFrame("Testing");
        // frame.setVisible(true);
        // frame.setSize(600, 600);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel panel = new JPanel();
        // panel.setLayout(new BorderLayout());
        // panel.setBackground(new Color(53, 101, 77));
        // frame.add(panel);
        Deck deck = new Deck();
        deck.shuffle();
        for (int i = 0; i < 52; i++) {
            Card currnetCard = deck.getCard(i);
            System.out.println(currnetCard.getValue() + " of " + currnetCard.getSuit());
        }
        
    }
}

