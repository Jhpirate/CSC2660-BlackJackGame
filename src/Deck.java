import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>(); // List of 52 cards
    private final String[] suites = {"spades", "hearts", "diamonds", "clubs"};
    private final String[] cardValues = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
    private Random rand = new Random();

    // -------------
    // Default no-arg constructor to build a deck and shuffle it
    public Deck() {
        resetDeck();
    }

    // -------------
    // Build a standard card deck (52 cards no jokers)
    // Optional Parameter: deckCount will determine how many 52 card decks are being played with
    private void buildDeck(int deckCount) {
        for (int decks = 0; decks < deckCount; decks++) {
            for (int i = 0; i < suites.length; i++) {
                for (int j = 0; j < cardValues.length; j++) {
                    deck.add(new Card(suites[i], cardValues[j]));
                }
            }
        }
    }

    // -------------
    // Helper method to shuffle the deck
    private void shuffleDeck() {
        for (int i = deck.size() - 1; i > 0; i--) {
            int randomIndex = rand.nextInt(i);

            Card temp = deck.get(i);
            deck.set(i, deck.get(randomIndex));
            deck.set(randomIndex, temp);
        }
    }

    public void resetDeck() {
        deck.clear();
        buildDeck(1);
        shuffleDeck();
    }

    // -------------
    // Accessors & Mutators
    public ArrayList<Card> getDeckOfCards() {
        return deck;
    }
}
