//import javax.smartcardio.Card;
import javax.swing.ImageIcon;
import java.net.URL;

public class ImageFinder {
    private static final String IMAGE_PATH_FORMAT = "../cards/%s%s.gif";
    private static final String PLACEHOLDER_PATH = "../cards/card_placeholder.png";

    public static ImageIcon getCardImage(Card card) {
        String rank = getRankString(card.getRank());
        String suit = getSuitString(card.getSuit());

        String path = String.format(IMAGE_PATH_FORMAT, rank, suit);
        return getIcon(path);
    }

    /**
     * Returns a two-character string representation of the card rank.
     * 
     * @param rank The rank of the card.
     * @return The two-character string representation.
     */
    private static String getRankString(int rank) {
        // Assuming ranks 2-10 are represented by their numbers, and others by letters
        // (J, Q, K, A)
        if (rank >= 2 && rank <= 10) {
            return String.valueOf(rank);
        } else {
            switch (rank) {
                case 11:
                    return "j";
                case 12:
                    return "q";
                case 13:
                    return "k";
                case 14:
                    return "a";
                default:
                    return "";
            }
        }
    }

    /**
     * Returns a one-character string representation of the card suit.
     * 
     * @param suit The suit of the card.
     * @return The one-character string representation.
     */
    private static String getSuitString(String suit) {
        // Assuming suits are represented by their first letter
        return suit.substring(0, 1).toLowerCase();
    }

    /**
     * Returns the ImageIcon for a given path.
     * 
     * @param path The path to the image.
     * @return The ImageIcon.
     */
    public static ImageIcon getIcon(String path) {
        // Assuming you have a method to load ImageIcon from the path
        // Example: new ImageIcon(getClass().getResource(path))
        // Make sure that the path is correct and the images are in the specified
        // location.
        URL url = ImageFinder.class.getResource(path);
        // Check if the resource is found
        if (url != null) {
            return new ImageIcon(url);
        } else {
            // Handle the case where the resource is not found
            System.err.println("Resource not found: " + path);
            return new ImageIcon(ImageFinder.class.getResource(PLACEHOLDER_PATH));
        }
    }
}
