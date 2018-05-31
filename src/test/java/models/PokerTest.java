package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PokerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void pokerClassInstantiatesCorrectly_true() throws Exception {
        Poker poker = setNewPoker();
        assertEquals(true, poker instanceof Poker);
    }

    @Test
    public void pokerClassInstiatesDeckCorrecly_is52() throws Exception {
        Poker poker = setNewPoker();
        assertEquals(52, poker.getDeck().size());
    }

    @Test
    public void dealerHandInstantiatesCorrectly_is5() {
        Poker poker = setNewPoker();
        ArrayList<String> expected = new ArrayList<>();
        assertTrue(poker.getDealersHand().getClass() == expected.getClass());
    }

    @Test
    public void playersHandInstantiatesCorrectly_is5() {
        Poker poker = setNewPoker();
        ArrayList<String> expected = new ArrayList<>();
        assertTrue(poker.getPlayersHand().getClass() == expected.getClass());
    }

    @Test
    public void dealCard_addsCardToPlayerHand_5() {
        Poker poker = setNewPoker();
        poker.dealCard("player");
        poker.dealCard("player");
        poker.dealCard("player");
        poker.dealCard("player");
        poker.dealCard("player");
        System.out.println("player");
        System.out.println(poker.getPlayersHand().get(0));
        System.out.println(poker.getPlayersHand().get(1));
        System.out.println(poker.getPlayersHand().get(2));
        System.out.println(poker.getPlayersHand().get(3));
        System.out.println(poker.getPlayersHand().get(4));
        assertEquals(5, poker.getPlayersHand().size());
    }

    @Test
    public void dealCard_addsCardToDealerHand_5() {
        Poker poker = setNewPoker();
        poker.dealCard("dealer");
        poker.dealCard("dealer");
        poker.dealCard("dealer");
        poker.dealCard("dealer");
        poker.dealCard("dealer");
        System.out.println("dealer");
        System.out.println(poker.getDealersHand().get(0));
        System.out.println(poker.getDealersHand().get(1));
        System.out.println(poker.getDealersHand().get(2));
        System.out.println(poker.getDealersHand().get(3));
        System.out.println(poker.getDealersHand().get(4));
        assertEquals(5, poker.getDealersHand().size());
    }

    @Test
    public void redrawCard_playerDiscardsACardAndDrawsNewCard() {
        Poker poker = setNewPoker();
        dealHand(poker);
        List<String> oldPlayerHand = new ArrayList<>();
        for (String card : poker.getPlayersHand()) {
            oldPlayerHand.add(card);
        }
        System.out.println("This should still be the old hand " + oldPlayerHand);
        List<Integer> cardsToDiscard = new ArrayList<>();
        cardsToDiscard.add(2);
        cardsToDiscard.add(3);
        poker.redrawCard("player", cardsToDiscard);
        System.out.println("New Player hand:" + poker.getPlayersHand());
        assertNotEquals(oldPlayerHand, poker.getPlayersHand());

    }

    @Test
    public void setPlayerHand_setsPlayerHand() {
        Poker poker = setNewPoker();
        List<String> newHand = new ArrayList<>();
        newHand.add("Ace of Spades");
        newHand.add("2 of Hearts");
        newHand.add("3 of Hearts");
        newHand.add("5 of Hearts");
        newHand.add("9 of Hearts");
        poker.setPlayersHand(newHand);
        assertEquals(newHand, poker.getPlayersHand());
    }

    @Test
    public void setDealerHand_setsDealerHand() {
        Poker poker = setNewPoker();
        List<String> newHand = new ArrayList<>();
        newHand.add("Ace of Spades");
        newHand.add("2 of Hearts");
        newHand.add("3 of Hearts");
        newHand.add("5 of Hearts");
        newHand.add("9 of Hearts");
        poker.setDealersHand(newHand);
        assertEquals(newHand, poker.getDealersHand());
    }

    @Test
    public void checkHighestCard_winConditionHighestCard_isTrue() {
        Poker poker = setNewPoker();
        List<String> newHand = new ArrayList<>();
        newHand.add("3 of clubs");
        newHand.add("9 of Hearts");
        newHand.add("2 of Hearts");
        newHand.add("3 of Hearts");
        newHand.add("5 of Hearts");
        poker.setPlayersHand(newHand);
        System.out.println(newHand);
        assertEquals(9, poker.checkHighestCard("player"));
    }

    @After
    public void tearDown() throws Exception {
    }

    public static Poker setNewPoker() {
        return new Poker();
    }

    public static void dealHand(Poker poker) {
        int i = 0;
        while (i<5) {
            poker.dealCard("player");
            poker.dealCard("dealer");
            i++;
        }
    }
}