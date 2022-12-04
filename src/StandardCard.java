public class StandardCard {
    private int value;
    private String suit;
    private String color;

    public StandardCard(int value, String suit) {
        this.value = value;
        this.suit = suit;
        if (this.suit.equals("Hearts") || this.suit.equals("Diamonds")) {
            this.color = "Red";
        }
        if (this.suit.equals("Clubs") || this.suit.equals("Spades")) {
            this.color = "Black";
        }
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getColor() {
        return color;
    }

    public String convertValueToName() {
        switch (value) {
            case 1:
                return "Ace";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            case 14:
                return "Ace";
            default:
                return "Value not valid";
        }
    }

    @Override
    public String toString() {
        return (convertValueToName() + " of " + suit);
    }
}
