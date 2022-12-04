import java.util.Arrays;

public class Player {
    private String name;
    private StandardCard[] holeCards = new StandardCard[2];

    public Player(String name, StandardCard[] holeCards) {
        this.name = name;
        this.holeCards = holeCards;
    }

    public String getName() {
        return name;
    }

    public StandardCard[] getHoleCards() {
        return holeCards;
    }

    @Override
    public String toString() {
        return "Player name: " + name +
                ". Cards in " + name + " hand: " + holeCards[0] + " and " + holeCards[1];
    }
}
