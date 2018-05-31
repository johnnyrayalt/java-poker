package models;

import java.util.*;

public class Poker {
    private List<String> dealersHand;
    private List<String> playersHand;
    private List<String> deck;

    public Poker() {
        this.deck = new ArrayList<>();
        this.dealersHand = new ArrayList<>();
        this.playersHand = new ArrayList<>();
        this.createDeck();
    }

     public List<String> getDeck() {
        return deck;
     }

     public List<String> getDealersHand() {
        return dealersHand;
     }

     public List<String> getPlayersHand() {
        return playersHand;
     }

     public void setPlayersHand(List<String> newHand) {
        playersHand = newHand;
     }

     public void setDealersHand(List<String> newHand) {
        dealersHand = newHand;
     }

     public void createDeck() {
        String[] faceValues = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};
         for (String faceValue: faceValues) {
             for (String suit:suits) {
                 deck.add(faceValue + " of " + suit);
             }
         }
     }

     public void dealCard(String dealtTo) {
         Random rng = new Random();
         Integer cardSelect = rng.nextInt(this.getDeck().size());


         if (dealtTo.equals("player")) {
             playersHand.add(deck.get(cardSelect));
         } else {
             dealersHand.add(deck.get(cardSelect));
         }
         deck.remove(deck.get(cardSelect));
     }

     public void redrawCard(String dealtTo, List<Integer> cardToDiscard) {
        int i = 1;

        if (dealtTo.equals("player")) {
            for (Integer card:cardToDiscard) {
                playersHand.remove(playersHand.get(card-i));
                i += 1;
                dealCard(dealtTo);
            }
        } else {
            for (Integer card:cardToDiscard) {
                dealersHand.remove(dealersHand.get(card-i));
                i += 1;
                dealCard(dealtTo);
            }
        }

     }

    public String checkHighestCard() {
        Map<String, Integer> cardList = new HashMap<String, Integer>();
        cardList.put("Ace", 14);
        cardList.put("King", 13);
        cardList.put("Queen", 12);
        cardList.put("Jack", 11);
        cardList.put("10", 10);
        cardList.put("9", 9);
        cardList.put("8", 8);
        cardList.put("7", 7);
        cardList.put("6", 6);
        cardList.put("5", 5);
        cardList.put("4", 4);
        cardList.put("3", 3);
        cardList.put("2", 2);

        ArrayList<String> handValues = new ArrayList<>();

        for (String individualCard : playersHand) {
            String[] individualCardArray = individualCard.split(" ");
            String newPlayerCardList = individualCardArray[0];
            handValues.add(newPlayerCardList);
        }

        int highestValueCardPosition = 0;
        int i = 0;
        while (i<4) {
            if (cardList.get(handValues.get(i)) > cardList.get(handValues.get(i+1))) {
                highestValueCardPosition = i;
                if (i==3) {
                    if (cardList.get(handValues.get(i)) < cardList.get(handValues.get(i+1))) {
                        highestValueCardPosition = i+1;
                    }
                }
            }
            i++;
        }

        String cardToReturn = playersHand.get(highestValueCardPosition);
        return cardToReturn;
    }


}
