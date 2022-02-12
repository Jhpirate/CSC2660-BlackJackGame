import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    private Random rand = new Random();

    private Deck deckOfCards = new Deck();
    private ArrayList<Card> deck = deckOfCards.getDeckOfCards();

    private ArrayList<Card> userHand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();

    private double walletBalance = 100;


    // Main game play method
    // More time needed to extract additional methods from this main method
    // determineWinner(), getInput(), dealerPlay(), determinePayout()
    // Right now all this is done under the playGame() method
    public void playGame() {
        Scanner userInput = new Scanner(System.in);
        int userBetAmount = 0;

        String userChoice = "";
        do {
            System.out.println("\n--- WELCOME TO BLACK JACK ---");

            // Do betting stuff here
            System.out.printf("Current Balance: $%.2f\n", walletBalance);
            do {
                System.out.println("How much do you want to bet?: ");
                userBetAmount = userInput.nextInt();
                if (userBetAmount > walletBalance || userBetAmount < 0) {
                    System.out.println("Error: Invalid Bet Amount");
                }
            } while (userBetAmount > walletBalance || userBetAmount < 0);


            // User draws, then dealer, then user, then dealer
            userHand.add(drawCard());
            dealerHand.add(drawCard());

            userHand.add(drawCard());
            dealerHand.add(drawCard());


            // Print dealers face up card
            System.out.printf("Dealer Hand: ['HIDDEN', %s]\n", dealerHand.get(1));

            // --------
            // User hit or stand logic here
            int userHandTotal = 0;
            do {
                userHandTotal = getHandValue(userHand);

                System.out.println("User Hand: " + Arrays.toString(userHand.toArray()));
                System.out.println("User Total: " + userHandTotal);

                if (userHandTotal > 21) {
//                    System.out.println("BUST");
                    break;
                } else if (userHandTotal == 21) {
//                    System.out.println("BLACKJACK");
                    break;
                } else {
                    System.out.println("Hit or Stand? (H or S)");
                    userChoice = userInput.next();

                    if (userChoice.equalsIgnoreCase("H")) {
                        // give the user another card
                        userHand.add(drawCard());
                    } else {
                        System.out.println("You have stood on: " + userHandTotal);
                    }
                }

            } while (userChoice.equalsIgnoreCase("H") && userHandTotal < 21);


            // dealer logic here
            // dealer stands on 17
            // make dealer draw until 17 or over
            int dealerHandTotal = 0;
            do {
                dealerHandTotal = getHandValue(dealerHand);

                if (dealerHandTotal < 17) {
                    dealerHand.add(drawCard());
                }

            } while (dealerHandTotal < 17);

            System.out.println("\nDealer Hand: " + Arrays.toString(dealerHand.toArray()));
            System.out.println("Dealer Total: " + dealerHandTotal + "\n");


            System.out.println("----------------------------");
            // Check win conditions here and betting payout
            // Could be cleaned up and optimized to reduce validations checks
            if (dealerHandTotal > 21 && userHandTotal < 21) {
                System.out.println("Dealer Bust");
                System.out.println("User Wins!");
                walletBalance += userBetAmount;

            } else if (userHandTotal > 21 && dealerHandTotal < 21) {
                System.out.println("User Bust");
                System.out.println("Dealer Wins");
                walletBalance -= userBetAmount;

            } else if (userHandTotal > 21) {
                System.out.println("User Bust");
                walletBalance -= userBetAmount;

            } else if (dealerHandTotal > 21) {
                System.out.println("Dealer Bust");
                walletBalance += userBetAmount;

            } else if (userHandTotal == 21 && dealerHandTotal == 21) {
                System.out.println("Push (Dealer and Player Blackjack");

            } else if (userHandTotal == 21) {
                System.out.println("User Blackjack");
                walletBalance += userBetAmount * 1.5;

            } else if (dealerHandTotal == 21) {
                System.out.println("Dealer Blackjack");
                walletBalance -= userBetAmount;

            } else if (dealerHandTotal > userHandTotal) {
                System.out.println("Dealer Won");
                walletBalance -= userBetAmount;

            } else if (userHandTotal > dealerHandTotal) {
                System.out.println("User Won");
                walletBalance += userBetAmount;

            } else {
                System.out.println("Push (No Winner)");

            }


            // Play again message
            System.out.println("\nPlay Again? (Y or N):");
            userChoice = userInput.next();
            resetGame();
        } while (userChoice.equalsIgnoreCase("y"));
    }


    // -------------
    // Draw a random card from the deck size
    // Once drawn remove it from the deck
    private Card drawCard() {
        int randomCardIndex = rand.nextInt(deck.size());
        Card randomCard = deck.get(randomCardIndex);

        deck.remove(randomCardIndex);

        return randomCard;
    }


    // -------
    // Get total of current hand
    private int getHandValue(ArrayList<Card> hand) {
        int handTotal = 0;

        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);

            handTotal += currentCard.getCardValue();
        }

        // If hand total is greater than 21,
        // Check if the hand contains an ace
        // If the hand has an ance, use the ace altValue of 1
        if (handTotal > 21) {
            handTotal = 0; //reset the hand total when > 21 and recalculate the total

            for (int i = 0; i < hand.size(); i++) {
                Card currentCard = hand.get(i);

                if (currentCard.getPipValue().equalsIgnoreCase("ace")) {
                    handTotal += currentCard.getAltCardValue();
                } else {
                    handTotal += currentCard.getCardValue();
                }
            }
        }

        return handTotal;
    }

    // -------------
    // Reset the game to a playable state
    // Regenerate a new deck
    // Clears dealer and user hand
    private void resetGame() {
        deckOfCards.resetDeck();
        userHand.clear();
        dealerHand.clear();
    }
}
