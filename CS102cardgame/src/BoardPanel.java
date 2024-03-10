import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            gc.fill = GridBagConstraints.CENTER;
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
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.NORTH;
        add(label, gc);

    }

    // public void update(List<Card> cards) {
    // int noOfCards = (cards == null) ? 0 : cards.size();
    // for (int i = 0; i < NO_OF_CARDS; i++) {
    // if (i < noOfCards) {
    // cardLabels[i].setIcon(ImageFinder.getCardImage(cards.get(i)));
    // } else {
    // cardLabels[i].setIcon(ImageFinder.getIcon("/images/card_placeholder.png"));
    // }
    // }
    // }

    public static void main(String[] args) {
        // Example usage: Create a shuffled deck
        Deck deck = new Deck();
        ArrayList<Card> shuffledDeck = deck.getCards();
        Deck.shuffleDeck(shuffledDeck);

        // FOR A SHUFFLED CARD (only 1 hand(for community card))
        // List<Card> cardsList = new ArrayList<>();
        // for (int i = 0; i < 5; i++) {
        //     Card dealtCard = deck.dealCard();
        //     if (dealtCard != null) {
        //         cardsList.add(dealtCard);
        //     }
        // }

        // 5 different hand cards
        List<List<Card>> hands = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Card> cardsList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {  // Assuming each hand has 5 cards
                Card dealtCard = deck.dealCard();
                if (dealtCard != null) {
                    cardsList.add(dealtCard);
                }
            }
            hands.add(cardsList);
        }

        JFrame frame = new JFrame("Testing");
        frame.setSize(1300, 800);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BoardPanel boardPanelCommunityCards = new BoardPanel(hands.get(0));  // Display community cards

        BoardPanel boardPanel1 = new BoardPanel(hands.get(1));
        BoardPanel boardPanel2 = new BoardPanel(hands.get(2));
        BoardPanel boardPanel3 = new BoardPanel(hands.get(3));
        BoardPanel boardPanel4 = new BoardPanel(hands.get(4));

        JPanel actionLayout = new JPanel();
        actionLayout.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        actionLayout.setPreferredSize(new Dimension(500, 100));
        actionLayout.setBackground(new Color(53, 101, 77));

        JButton foldButton = new JButton("Fold");
        JButton checkButton = new JButton("Check");
        JButton betButton = new JButton("Bet");
        JButton callButton = new JButton("Call");
        JButton raiseButton = new JButton("Raise");

        actionLayout.add(foldButton);
        actionLayout.add(checkButton);
        actionLayout.add(betButton);
        actionLayout.add(callButton);
        actionLayout.add(raiseButton);

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        // gc.anchor = GridBagConstraints.NORTH;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;

        JPanel centerPanel = new JPanel(new GridBagLayout());
        gc.gridy = 1;
        gc.gridheight = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        centerPanel.add(boardPanelCommunityCards, gc);

        JLabel actionTextLabel = new JLabel("Action performed");
        actionTextLabel.setBackground(new Color(53, 101, 77));
        actionTextLabel.setOpaque(true);
        actionTextLabel.setHorizontalAlignment(JLabel.CENTER);
        actionTextLabel.setPreferredSize(new Dimension(100, 30));
        gc.gridy = 2;
        gc.weighty = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        centerPanel.add(actionTextLabel, gc);

        gc.gridy = 4;
        gc.weighty = 0.0;
        gc.insets = new Insets(0, 0, 0, 0);
        centerPanel.add(actionLayout, gc);

        foldButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionTextLabel.setText("Fold");
            }
        });

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionTextLabel.setText("Check");
            }
        });

        betButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionTextLabel.setText("Bet");
            }
        });

        callButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionTextLabel.setText("Call");
            }
        });

        raiseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionTextLabel.setText("Raise");
            }
        });

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(boardPanel1, BorderLayout.SOUTH);
        frame.add(boardPanel2, BorderLayout.WEST);
        frame.add(boardPanel3, BorderLayout.NORTH);
        frame.add(boardPanel4, BorderLayout.EAST);

        frame.setVisible(true);
    }
}
