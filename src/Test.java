import java.util.ArrayList;

public class Test {

    // -----------------
    // Inner class for storing a card object
    // Needed when building using buildDeck()
    public static class Card {
        public String suite;
        public String rank;
        public int cardValue;

        public Card(String suite, String rank) {
            this.suite = suite;
            this.rank = rank;
            storeCardValue();
        }

        private void storeCardValue() {
            // For simplicity, we are going to treat Ace as 11 all the time
            // here we are just converting the string version of the rank into a numeric value
            switch (rank) {
                case "1" -> cardValue = 1;
                case "2" -> cardValue = 2;
                case "3" -> cardValue = 3;
                case "4" -> cardValue = 4;
                case "5" -> cardValue = 5;
                case "6" -> cardValue = 6;
                case "7" -> cardValue = 7;
                case "8" -> cardValue = 8;
                case "9" -> cardValue = 9;
                case "10" -> cardValue = 10;
                case "Jack" -> cardValue = 10;
                case "Queen" -> cardValue = 10;
                case "King" -> cardValue = 10;
                case "Ace" -> cardValue = 11;
            }
        }

        @Override
        public String toString() {
            return (this.rank + " of " + this.suite);
        }
    }
    // -----------------


    private ArrayList<Card> deck = new ArrayList<>(); //ArrayList of type Card
    private ArrayList<Card> userHand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();

    // store suites and pip values
    private final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
//    private final int[] RANKS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};


    public void buildDeck() {
        // A deck consists of 4*13 cards

        // repeat first for loop for however many elements are in SUITES
        for (int i = 0; i < this.SUITS.length; i++) {
            // repeat second for loop for however many element are in RANKS
            for (int j = 0; j < this.RANKS.length; j++) {
                // Add a new Card to the deck with the rank and suite combination
                deck.add(new Card(SUITS[i], RANKS[j]));
            }
        }
        // deck built and stored under deck variable above
    }

    public void shuffleDeck() {
        // Add shuffle method here
    }


    private void printDeck(ArrayList<Card> p_ListToPrint) {
        // This is the same method as before
        // just prints the deck 1 card at a time
        for (Card currentCard : deck) {
            System.out.println(currentCard);
        } //enhanced for-loop
    }

    public void drawCard() {
        //pick a random card from the deck variable
        //remove the card from the deck

        // You will call this method from playGame() like this:
        // userHand.add(drawCard()) or dealerHand.add(drawCard())
    }


    private int getCardValue(Card cardToGetValueOf) {
        //get the card value and return it as in integer
        return cardToGetValueOf.cardValue;
    }

    public void playGame() {
        // This is the main game flow

        // 1. Build the deck
        // buildDeck()
        buildDeck();

        // 2. Shuffle the deck
        // shuffleDeck()

        // 3. Begin the game flow
        // draw 2 cards for the dealer list
        // draw 2 cards or the player list

        //print dealer list
        //print user list


        // ask the user if they want to hit or stand
        // while they want to hit & their total is less than 21,
        // add a new card to the user deck
        printDeck(deck);


    }


}
