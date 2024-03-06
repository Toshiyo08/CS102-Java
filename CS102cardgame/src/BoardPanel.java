import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {

    private static final int NO_OF_CARDS = 5;
    private JLabel[] cardLabels;
    private List<Card> cards; // Assuming you have a List<Card> named cards

    public BoardPanel(List<Card> cards) {
        this.cards = cards;
        setLayout(new GridBagLayout());
        setBackground(new Color(53, 101, 77));
        GridBagConstraints gc = new GridBagConstraints();

        // Initialize 5 card positions
        cardLabels = new JLabel[NO_OF_CARDS];
        for (int i = 0; i < NO_OF_CARDS; i++) {
            cardLabels[i] = new JLabel(ImageFinder.getIcon("./cards/card_placeholder.png"));
            // cardLabels[i] = new JLabel(ImageFinder.getCardImage(cards.get(i)));
          
            gc.gridx = i;
            gc.gridy = 2;
            gc.gridwidth = 1;
            gc.gridheight = 1;
            gc.anchor = GridBagConstraints.CENTER;
            gc.fill = GridBagConstraints.NONE;
            gc.weightx = 0.0;
            gc.weighty = 0.0;
            gc.insets = new Insets(10, 1, 5, 1);

            if (i < cards.size()) {
                cardLabels[i].setIcon(ImageFinder.getCardImage(cards.get(i)));
            }

            add(cardLabels[i], gc);
        }

        JLabel label = new JLabel();
        label.setText("Texas Hold Em");

        // Add label to the panel
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.NORTH;
        add(label, gc);
    }

    // public void update(List<Card> cards) {
    //     int noOfCards = (cards == null) ? 0 : cards.size();
    //     for (int i = 0; i < NO_OF_CARDS; i++) {
    //         if (i < noOfCards) {
    //             cardLabels[i].setIcon(ImageFinder.getCardImage(cards.get(i)));
    //         } else {
    //             cardLabels[i].setIcon(ImageFinder.getIcon("/images/card_placeholder.png"));
    //         }
    //     }
    // }

    public static void main(String[] args) {
        // Example usage: Create a list of cards
        List<Card> cardsList = new ArrayList<>();
        cardsList.add(new Card("h", 2));
        cardsList.add(new Card("d", 7)); // Populate this list with Card objects

        JFrame frame = new JFrame("Testing");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BoardPanel boardPanel = new BoardPanel(cardsList);
        frame.add(boardPanel);

        frame.setVisible(true);
    }
}
