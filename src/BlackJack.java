import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BlackJack {
    Random rand = new Random();

    Deck deckOfCards = new Deck();
    ArrayList<Card> deck = deckOfCards.getDeckOfCards();

    ArrayList<Card> userHand = new ArrayList<>();
    ArrayList<Card> dealerHand = new ArrayList<>();


    public void playGame() {
        userHand.add(drawCard());
        userHand.add(drawCard());
        System.out.println(Arrays.toString(userHand.toArray()));

        resetGame();

        userHand.add(drawCard());
        userHand.add(drawCard());
        System.out.println(Arrays.toString(userHand.toArray()));

    }


    // -------------
    // draw a random card from the deck size
    // once drawn remove it from the deck and add it to the users hand
    private Card drawCard() {
        int randomCardIndex = rand.nextInt(deck.size());
        Card randomCard = deck.get(randomCardIndex);

        deck.remove(randomCardIndex);

        return randomCard;
    }


    // -------------
    private void resetGame() {
        deckOfCards.resetDeck();
        userHand.clear();
        dealerHand.clear();
    }
}
