import java.security.AllPermission;
import java.util.*;

public class GamePirmasBandymas {
    private ArrayList<Player> playerList;
    private StandardDeck gameDeck;
    private StandardCard[] communityCards;
    Scanner scan = new Scanner(System.in);

    public GamePirmasBandymas() {
        playerList = new ArrayList<Player>();
        gameDeck = new StandardDeck();
        communityCards = new StandardCard[5];
        addPlayer();
        dealNextCommunityCards();
        findWinner();
    }

    public void addPlayer() {
        System.out.println("Enter how many players will play: ");
        try {
            int playerCount = scan.nextInt();
            for (int i = 0; i < playerCount; i++) {
                System.out.println("Enter " + (i + 1) + " player name: ");
                String tempName = scan.next();
                StandardCard[] tempHoleCards = {gameDeck.getNextCard(), gameDeck.getNextCard()};
                System.out.println(tempName + " Cards are: " + Arrays.toString(tempHoleCards));
                playerList.add(new Player(tempName, tempHoleCards));
            }
        } catch (Exception InputMismatchException) {
            System.out.println("Please enter a number!");
        }
    }

    public void dealNextCommunityCards() {
        if (communityCards[0] == null) {
            communityCards[0] = gameDeck.getNextCard();
            communityCards[1] = gameDeck.getNextCard();
            communityCards[2] = gameDeck.getNextCard();
            communityCards[3] = gameDeck.getNextCard();
            communityCards[4] = gameDeck.getNextCard();
        }
        System.out.println("Final five cards are: " + Arrays.toString(communityCards));
    }

    public ArrayList<Player> findWinner() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        winningPlayers = royalFlush();
        if (winningPlayers.size() > 0) {
            return winningPlayers;
        }
        winningPlayers = straightFlush();
        if (winningPlayers.size() > 0) {
            return winningPlayers;
        }
        winningPlayers = fourOfAKind();
        if (winningPlayers.size() > 0) {
            return winningPlayers;
        }
        winningPlayers = fullHouse();
        if (winningPlayers.size() > 0) {
            return winningPlayers;
        }
        winningPlayers = flush();
        if (winningPlayers.size() > 0) {
            return winningPlayers;
        }
        winningPlayers = straight();
        if (winningPlayers.size() > 0) {
            return winningPlayers;
        }
        winningPlayers = threeOfAKind();
        if (winningPlayers.size() > 0) {
            return winningPlayers;
        }
        winningPlayers = twoPair();
        if (winningPlayers.size() > 0) {
            return winningPlayers;
        }
        winningPlayers = onePair();
        if (winningPlayers.size() > 0) {
            return winningPlayers;
        }
        winningPlayers = highCard();
        if (winningPlayers.size() > 0) {
            return winningPlayers;
        }
        return winningPlayers;
    }

    public ArrayList<Player> royalFlush() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();

        for (int i = 0; i < playerList.size(); i++) {
            int hearts = 0;
            int diamonds = 0;
            int spades = 0;
            int clubs = 0;
            Player tempPlayer = playerList.get(i);
            ArrayList<Integer> tempPlayerCards = new ArrayList<Integer>();
            ArrayList<StandardCard> tempPlayersCardAll = tempPlayerCardsAll(tempPlayer);

            for (int l = 0; l < tempPlayersCardAll.size(); l++) {
                if (tempPlayersCardAll.get(l).getSuit().contains("Hearts")) {
                    hearts++;
                }
                if (tempPlayersCardAll.get(l).getSuit().contains("Diamonds")) {
                    diamonds++;
                }
                if (tempPlayersCardAll.get(l).getSuit().contains("Spades")) {
                    spades++;
                }
                if (tempPlayersCardAll.get(l).getSuit().contains("Clubs")) {
                    clubs++;
                }
            }
            if (hearts >= 5) {
                tempPlayerCards = getValuesOfSuit(tempPlayersCardAll, "Hearts");
            } else if (diamonds >= 5) {
                tempPlayerCards = getValuesOfSuit(tempPlayersCardAll, "Diamonds");
            } else if (spades >= 5) {
                tempPlayerCards = getValuesOfSuit(tempPlayersCardAll, "Spades");
            } else if (clubs >= 5) {
                tempPlayerCards = getValuesOfSuit(tempPlayersCardAll, "Clubs");
            }

            if (tempPlayerCards.contains(10) && tempPlayerCards.contains(11)
                    && tempPlayerCards.contains(12) && tempPlayerCards.contains(13)
                    && tempPlayerCards.contains(14)) {
                winningPlayers.add(tempPlayer);
                System.out.println("Winner");
            }
        }
        return winningPlayers;
    }

    public ArrayList<Player> straightFlush() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        for (int i = 0; i < playerList.size(); i++) {
            int hearts = 0;
            int diamonds = 0;
            int spades = 0;
            int clubs = 0;
            Player tempPlayer = playerList.get(i);
            ArrayList<Integer> tempPlayerCards = new ArrayList<Integer>();
            ArrayList<StandardCard> tempPlayersCardAll = tempPlayerCardsAll(tempPlayer);
            for (int l = 0; l < tempPlayersCardAll.size(); l++) {
                if (tempPlayersCardAll.get(l).getSuit().contains("Hearts")) {
                    hearts++;
                }
                if (tempPlayersCardAll.get(l).getSuit().contains("Diamonds")) {
                    diamonds++;
                }
                if (tempPlayersCardAll.get(l).getSuit().contains("Spades")) {
                    spades++;
                }
                if (tempPlayersCardAll.get(l).getSuit().contains("Clubs")) {
                    clubs++;
                }
            }
            if (hearts >= 5) {
                tempPlayerCards = getValuesOfSuit(tempPlayersCardAll, "Hearts");
            } else if (diamonds >= 5) {
                tempPlayerCards = getValuesOfSuit(tempPlayersCardAll, "Diamonds");
            } else if (spades >= 5) {
                tempPlayerCards = getValuesOfSuit(tempPlayersCardAll, "Spades");
            } else if (clubs >= 5) {
                tempPlayerCards = getValuesOfSuit(tempPlayersCardAll, "Clubs");
            }
            Collections.sort(tempPlayerCards);
            int lowestValue = 15;
            int secondHighestValue = 15;
            int thirdHighestValue = 15;
            if (tempPlayerCards.size() >= 5) {
                lowestValue = tempPlayerCards.get(0);
                secondHighestValue = tempPlayerCards.get(1);
                thirdHighestValue = tempPlayerCards.get(2);
                if (tempPlayerCards.contains(lowestValue + 1) &&
                        tempPlayerCards.contains(lowestValue + 2) &&
                        tempPlayerCards.contains(lowestValue + 3) &&
                        tempPlayerCards.contains(lowestValue + 4)) {
                    winningPlayers.add(tempPlayer);
                }
            }
            if (tempPlayerCards.size() >= 6) {
                if (tempPlayerCards.contains(secondHighestValue + 1) &&
                        tempPlayerCards.contains(secondHighestValue + 2) &&
                        tempPlayerCards.contains(secondHighestValue + 3) &&
                        tempPlayerCards.contains(secondHighestValue + 4)) {
                    winningPlayers.add(tempPlayer);
                }
            }
            if (tempPlayerCards.size() >= 7) {
                if (tempPlayerCards.contains(thirdHighestValue + 1) &&
                        tempPlayerCards.contains(thirdHighestValue + 2) &&
                        tempPlayerCards.contains(thirdHighestValue + 3) &&
                        tempPlayerCards.contains(thirdHighestValue + 4)) {
                    winningPlayers.add(tempPlayer);
                }
            }
        }
        return winningPlayers;
    }

    public ArrayList<Player> fourOfAKind() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        for (int i = 0; i < playerList.size(); i++) {
            Player tempPlayer = playerList.get(i);
            int[] allValues = new int[13];
            ArrayList<StandardCard> tempPlayersCardsAll = tempPlayerCardsAll(tempPlayer);
            for (int j = 0; j < tempPlayersCardsAll.size(); j++) {
                allValues[tempPlayersCardsAll.get(j).getValue() - 2]++;
            }
            for (int k = 0; k < allValues.length; k++) {
                if (allValues[k] >= 4) {
                    winningPlayers.add(tempPlayer);
                }
            }
        }
        return winningPlayers;
    }

    public ArrayList<Player> fullHouse() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        for (int i = 0; i < playerList.size(); i++) {
            Player tempPlayer = playerList.get(i);
            int[] allValues = new int[13];
            boolean threeCard = false;
            boolean twoCard = false;

            ArrayList<StandardCard> tempPlayersCardsAll = tempPlayerCardsAll(tempPlayer);
            for (int j = 0; j < tempPlayersCardsAll.size(); j++) {
                allValues[tempPlayersCardsAll.get(j).getValue() - 2]++;
            }
            for (int k = 0; k < allValues.length; k++) {
                if (allValues[k] >= 3) {
                    threeCard = true;
                } else if (allValues[k] >= 2) {
                    twoCard = true;
                }
            }
            if (threeCard && twoCard) {
                winningPlayers.add(tempPlayer);
            }
        }
        return winningPlayers;
    }

    public ArrayList<Player> flush() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        for (int i = 0; i < playerList.size(); i++) {
            int hearts = 0;
            int diamonds = 0;
            int spades = 0;
            int clubs = 0;
            Player tempPlayer = playerList.get(i);
            ArrayList<StandardCard> tempPlayersCardAll = tempPlayerCardsAll(tempPlayer);
            for (int l = 0; l < tempPlayersCardAll.size(); l++) {
                if (tempPlayersCardAll.get(l).getSuit().contains("Hearts")) {
                    hearts++;
                }
                if (tempPlayersCardAll.get(l).getSuit().contains("Diamonds")) {
                    diamonds++;
                }
                if (tempPlayersCardAll.get(l).getSuit().contains("Spades")) {
                    spades++;
                }
                if (tempPlayersCardAll.get(l).getSuit().contains("Clubs")) {
                    clubs++;
                }
            }
            if (hearts >= 5 || diamonds >= 5 || spades >= 5 || clubs >= 5) {
                winningPlayers.add(tempPlayer);
            }
        }
        return winningPlayers;
    }

    public ArrayList<Player> straight() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        for (int i = 0; i < playerList.size(); i++) {
            Player tempPlayer = playerList.get(i);
            ArrayList<StandardCard> tempPlayersCardAll = tempPlayerCardsAll(tempPlayer);
            ArrayList<Integer> tempPlayerCards = getValuesOfAllCards(tempPlayersCardAll);

            Collections.sort(tempPlayerCards);
            int lowestValue = 15;
            int secondHighestValue = 15;
            int thirdHighestValue = 15;
            if (tempPlayerCards.size() >= 5) {
                lowestValue = tempPlayerCards.get(0);
                secondHighestValue = tempPlayerCards.get(1);
                thirdHighestValue = tempPlayerCards.get(2);
                if (tempPlayerCards.contains(lowestValue + 1) &&
                        tempPlayerCards.contains(lowestValue + 2) &&
                        tempPlayerCards.contains(lowestValue + 3) &&
                        tempPlayerCards.contains(lowestValue + 4)) {
                    winningPlayers.add(tempPlayer);
                }
            }
            if (tempPlayerCards.size() >= 6) {
                if (tempPlayerCards.contains(secondHighestValue + 1) &&
                        tempPlayerCards.contains(secondHighestValue + 2) &&
                        tempPlayerCards.contains(secondHighestValue + 3) &&
                        tempPlayerCards.contains(secondHighestValue + 4)) {
                    winningPlayers.add(tempPlayer);
                }
            }
            if (tempPlayerCards.size() >= 7) {
                if (tempPlayerCards.contains(thirdHighestValue + 1) &&
                        tempPlayerCards.contains(thirdHighestValue + 2) &&
                        tempPlayerCards.contains(thirdHighestValue + 3) &&
                        tempPlayerCards.contains(thirdHighestValue + 4)) {
                    winningPlayers.add(tempPlayer);
                }
            }
        }
        return winningPlayers;
    }

    public ArrayList<Player> threeOfAKind() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        for (int i = 0; i < playerList.size(); i++) {
            Player tempPlayer = playerList.get(i);
            int[] allValues = new int[13];
            ArrayList<StandardCard> tempPlayersCardsAll = tempPlayerCardsAll(tempPlayer);
            for (int j = 0; j < tempPlayersCardsAll.size(); j++) {
                allValues[tempPlayersCardsAll.get(j).getValue() - 2]++;
            }
            for (int k = 0; k < allValues.length; k++) {
                if (allValues[k] >= 3) {
                    winningPlayers.add(tempPlayer);
                }
            }
        }
        return winningPlayers;
    }

    public ArrayList<Player> twoPair() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        for (int i = 0; i < playerList.size(); i++) {
            Player tempPlayer = playerList.get(i);
            int[] allValues = new int[13];
            int pairCount = 0;
            ArrayList<StandardCard> tempPlayersCardsAll = tempPlayerCardsAll(tempPlayer);
            for (int j = 0; j < tempPlayersCardsAll.size(); j++) {
                allValues[tempPlayersCardsAll.get(j).getValue() - 2]++;
            }
            for (int k = 0; k < allValues.length; k++) {
                if (allValues[k] >= 2) {
                    pairCount++;
                    if (pairCount == 2) {
                        winningPlayers.add(tempPlayer);
                    }
                }
            }
        }
        return winningPlayers;
    }

    public ArrayList<Player> onePair() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        for (int i = 0; i < playerList.size(); i++) {
            Player tempPlayer = playerList.get(i);
            int[] allValues = new int[13];
            ArrayList<StandardCard> tempPlayersCardsAll = tempPlayerCardsAll(tempPlayer);
            for (int j = 0; j < tempPlayersCardsAll.size(); j++) {
                allValues[tempPlayersCardsAll.get(j).getValue() - 2]++;
            }
            for (int k = 0; k < allValues.length; k++) {
                if (allValues[k] >= 2 && !winningPlayers.contains(tempPlayer)) {
                    winningPlayers.add(tempPlayer);
                }
            }
        }
        return winningPlayers;
    }

    public ArrayList<Player> highCard() {
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        int[][] sortedPlayersHoleCards = new int[playerList.size()][2];
        int highestCardColTwo = 0;
        int highestCardColOne = 0;
        boolean tie = false;
        for (int i = 0; i < playerList.size(); i++) {
            Player tempPlayer = playerList.get(i);
            ArrayList<Integer> tempHoleCards = new ArrayList<Integer>();
            tempHoleCards.add(tempPlayer.getHoleCards()[0].getValue());
            tempHoleCards.add(tempPlayer.getHoleCards()[1].getValue());
            Collections.sort(tempHoleCards);
            if (tempHoleCards.get(1) > highestCardColTwo) {
                highestCardColTwo = tempHoleCards.get(1);
                winningPlayers.clear();
                winningPlayers.add(tempPlayer);
                sortedPlayersHoleCards = new int[playerList.size()][2];
                sortedPlayersHoleCards[i][0] = i;
                sortedPlayersHoleCards[i][1] = tempHoleCards.get(0);
            } else if (tempHoleCards.get(1) == highestCardColTwo) {
                winningPlayers.add(tempPlayer);
                tie = true;
                sortedPlayersHoleCards[i][0] = i;
                sortedPlayersHoleCards[i][1] = tempHoleCards.get(0);
            }
        }
        if (tie) {
            winningPlayers.clear();
            for (int k = 0; k < sortedPlayersHoleCards.length; k++) {
                if (sortedPlayersHoleCards[k][1] > highestCardColOne) {
                    highestCardColOne = sortedPlayersHoleCards[k][1];
                }
            }
            for (int l = 0; l < sortedPlayersHoleCards.length; l++) {
                if (sortedPlayersHoleCards[l][1] == highestCardColOne) {
                    winningPlayers.add(playerList.get(l));
                }
            }
        }
        return winningPlayers;
    }

    public ArrayList<StandardCard> tempPlayerCardsAll(Player player) {
        ArrayList<StandardCard> result = new ArrayList<StandardCard>();
        for (int i = 0; i < communityCards.length; i++) {
            result.add(communityCards[i]);
        }
        result.add(player.getHoleCards()[0]);
        result.add(player.getHoleCards()[1]);
        return result;
    }

    public ArrayList<Integer> getValuesOfSuit(ArrayList<StandardCard> playersCardsAll, String suit) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < playersCardsAll.size(); i++) {
            if (playersCardsAll.get(i).getSuit().contains(suit)) {
                result.add(playersCardsAll.get(i).getValue());
            }
        }
        return result;
    }

    public ArrayList<Integer> getValuesOfAllCards(ArrayList<StandardCard> playersCardsAll) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < playersCardsAll.size(); i++) {
            result.add(playersCardsAll.get(i).getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        GamePirmasBandymas pg = new GamePirmasBandymas();
    }
}
