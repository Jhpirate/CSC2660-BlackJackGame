public class Card {
    private String suite;
    private String pipValue;
    private int rawValue;
    private int altValue;

    public Card(String suite, String pipValue) {
        this.suite = suite;
        this.pipValue = pipValue;
        setCardValue();
    }

    // -------------
    // Black Jack specific method
    // Convert jack, queen, & king to their numeric values
    // Set rawValue and altValue based on card pip value
    private void setCardValue() {

        // Handle special case of an ace (1 or 11) with altValue field
        if (this.pipValue.equals("ace")) {
            this.rawValue = 11;
            this.altValue = 1;
        } else if (pipValue.equals("jack") || pipValue.equals("queen") || pipValue.equals("king")) {
            this.rawValue = 10;
        } else {
            try {
                this.rawValue = Integer.parseInt(this.pipValue);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public int getCardValue() {
        return rawValue;
    }

    // --------
    // toString()
    // Format: Card {[pipValue] OF [suite]. #: [value]}
    // Ex. Card {queen OF clubs | #: 10}
    @Override
    public String toString() {
//        return String.format("Card {%s OF %s | #: %d}", this.pipValue, this.suite, this.rawValue);
        return String.format("%s of %s", this.pipValue, this.suite);
    }
}
