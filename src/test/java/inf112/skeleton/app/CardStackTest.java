package inf112.skeleton.app;

import org.junit.Test;


import static inf112.skeleton.app.CardType.*;

import static org.junit.Assert.*;

public class CardStackTest {
    private CardStack cardStack;
    private CardStack cardStack2;

    @Test
    public void testSize() {
        cardStack = new CardStack();
        cardStack.addCardToStack(new Card(MOVE_1_FORWARD, 490, true));
        cardStack.addCardToStack(new Card(MOVE_2_FORWARD, 670, true));
        cardStack.addCardToStack(new Card(MOVE_3_FORWARD, 790, true));
        assertEquals(3, cardStack.size());
    }

    @Test
    public void testAddCardToStack() {
        cardStack = new CardStack();
        Card firstCard = new Card(MOVE_1_FORWARD, 490, true);
        Card secondCard = new Card(MOVE_2_FORWARD, 670, true);
        Card thirdCard = new Card(MOVE_3_FORWARD, 790, true);
        cardStack.addCardToStack(firstCard);
        cardStack.addCardToStack(secondCard);
        cardStack.addCardToStack(thirdCard);
        assertEquals(firstCard, cardStack.getCardFromStack());
        assertEquals(secondCard, cardStack.getCardFromStack());
        assertEquals(thirdCard, cardStack.getCardFromStack());
    }

    @Test
    public void testInitializeCardStack() {
        cardStack = new CardStack();
        cardStack.initializeCardStack();

        assertEquals(84, cardStack.size());
    }

    @Test
    public void randomizeCardStack() {
        cardStack = new CardStack();
        cardStack.initializeCardStack();
        cardStack2 = new CardStack();
        for (int i = 0; i < cardStack.size(); i++) {
            Card card = cardStack.getCardFromStack();
            cardStack.addCardToStack(card);
            cardStack2.addCardToStack(card);
        }
        cardStack2.randomizeCardStack();
        for (int i = 0; i < cardStack.size(); i++) {
                Card cardFromCardStack1 = cardStack.getCardFromStack();
                Card cardFromCardStack2 = cardStack2.getCardFromStack();

                if (cardFromCardStack1 == cardFromCardStack2){
                    cardStack2.addCardToStack(cardFromCardStack2);
                    cardStack2.randomizeCardStack();
                    cardFromCardStack2 = cardStack2.getCardFromStack();
                }
            cardsNotEqual(cardFromCardStack1, cardFromCardStack2);
        }
    }

    private void cardsNotEqual(Card c1, Card c2) {
        assertNotEquals(c1, c2);
    }

    @Test
    public void TestGetCardFromStack() {
        cardStack = new CardStack();
        Card firstCard = new Card(MOVE_1_FORWARD, 490, true);
        Card secondCard = new Card(MOVE_2_FORWARD, 670, true);
        Card thirdCard = new Card(MOVE_3_FORWARD, 790, true);
        cardStack.addCardToStack(firstCard);
        cardStack.addCardToStack(secondCard);
        cardStack.addCardToStack(thirdCard);
        assertEquals(firstCard, cardStack.getCardFromStack());
        assertEquals(2, cardStack.size());
        assertEquals(secondCard, cardStack.getCardFromStack());
        assertEquals(1, cardStack.size());
        assertEquals(thirdCard, cardStack.getCardFromStack());
        assertEquals(0, cardStack.size());
    }
}