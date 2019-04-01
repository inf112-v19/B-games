package inf112.skeleton.app;

import static inf112.skeleton.app.Cards.CardType.*;
import static org.junit.Assert.*;

import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardType;
import org.junit.Test;

public class CardTest {

    @Test
    public void getType(){
        Card card = new Card(MOVE_1_FORWARD, 490, true);
        assertEquals(CardType.MOVE_1_FORWARD, card.getType());
    }

    @Test
    public void getPriority() {
        Card card = new Card(MOVE_1_FORWARD, 490, true);
        assertEquals(490, card.getPriority());
    }

    @Test
    public void getUnlockedStatus() {
        Card card = new Card(MOVE_1_FORWARD, 490, true);
        assertTrue(card.getUnlockedStatus());
    }

    @Test
    public void setUnlocked() {
        Card card = new Card(MOVE_1_FORWARD, 490, false);
        card.setUnlocked();
        assertTrue(card.getUnlockedStatus());
    }

    @Test
    public void setLocked() {
        Card card = new Card(MOVE_1_FORWARD, 490, true);
        card.setLocked();
        assertFalse(card.getUnlockedStatus());
    }
}
