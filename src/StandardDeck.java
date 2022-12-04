import java.util.ArrayList;

public class StandardDeck {
    private ArrayList<StandardCard> deck = new ArrayList<StandardCard>();

    public StandardDeck() {
        reset();
        shuffleDeck();
    }

    public void reset() {
        this.deck.clear();
        // Adding "Hearts" suit
        for (int i = 2; i < 15; i++) {
            deck.add(new StandardCard(i, "Hearts"));
        }
        // Adding "Diamonds" suit
        for (int i = 2; i < 15; i++) {
            deck.add(new StandardCard(i, "Diamonds"));
        }
        // Adding "Spades" suit
        for (int i = 2; i < 15; i++) {
            deck.add(new StandardCard(i, "Spades"));
        }
        // Adding "Clubs" suit
        for (int i = 2; i < 15; i++) {
            deck.add(new StandardCard(i, "Clubs"));
        }
    }

    public void shuffleDeck() {
        ArrayList<StandardCard> tempDeck = new ArrayList<StandardCard>();
        while (deck.size() > 0) {
            int randomIndex = ((int) (Math.random() * 100)) % this.deck.size();
            tempDeck.add(this.deck.remove(randomIndex));
        }
        deck = tempDeck;
    }

    public StandardCard getNextCard() {
        return deck.remove(deck.size()-1);
    }

    public int getRemainingCardCount() {
        return deck.size();
    }
}
