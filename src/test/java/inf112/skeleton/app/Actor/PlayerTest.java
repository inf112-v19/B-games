package inf112.skeleton.app.Actor;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardStack;
import org.junit.Test;


import java.util.ArrayList;

import static inf112.skeleton.app.Cards.CardType.*;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;
    private Board board;
    private CardStack cardstack;


    @Test
    public void initializeHand() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, cardstack, false);

        //assert size of hand equals 9
        assertTrue(player.getCardsOnHand().size() == 9);
        //assert that none are cards, ready to draw cards
        for (Card card : player.getCardsOnHand()) {
            assertTrue(card == null);
        }
    }

    @Test
    public void initializeRegister() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, cardstack, false);

        //assert size of register equals 5
        assertTrue(player.getRegister().size() == 5);
        //assert that none are cards, ready to have cards put in register
        for (Card card : player.getRegister()) {
            assertTrue(card == null);
        }
    }


    @Test
    public void drawCards() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, cardstack, false);
        cardstack.initializeCardStack();

        //case 1: Player draws 9 cards at full HP
        player.drawCards();
        assertTrue(player.getCardsOnHand().size() == 9);
        // all should be Cards
        for (Card card : player.getCardsOnHand()) {
            assertTrue(card instanceof Card);
        }

        //case 2: Player draws cards after receiving 1 point of damage
        player = new Player(6, 6, Color.RED, board, 2, cardstack, false);

        player.receiveDamage();
        player.drawCards();

        // first 8 should be Cards
        for (int i = 0; i < player.getCardsOnHand().size() - 1; i++) {
            assertTrue(player.getCardsOnHand().get(i) instanceof Card);
        }
        // last one should be null
        int lastCard = player.getCardsOnHand().size() - 1;
        assertTrue(player.getCardsOnHand().get(lastCard) == null);

        //case 3: Player(tries to) draw cards after receiving enough damage to not get any cards
        player = new Player(7, 7, Color.RED, board, 3, cardstack, false);
        //receive 9 points of damage
        for (int i = 0; i < player.getCardsOnHand().size(); i++) {
            player.receiveDamage();
        }
        player.drawCards();
        // all should be null
        for (int i = 0; i < player.getCardsOnHand().size(); i++) {
            assertTrue(player.getCardsOnHand().get(i) == null);
        }
    }

    @Test
    public void addCardToRegister() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, cardstack, false);
        cardstack.initializeCardStack();
        //case 1: No cards to add to register
        try {
            player.addCardToRegister(1, 1);
        } catch (Exception e) {
            assertEquals("No card in your hand at that number", e.getMessage());
        }
        player.drawCards();
        //case 2: Try to add cards outside index for hand
        try {
            player.addCardToRegister(-1, 1);
        } catch (Exception e) {
            assertEquals("Number for hand needs to be between 1 and 9.", e.getMessage());
        }
        try {
            player.addCardToRegister(10, 1);
        } catch (Exception e) {
            assertEquals("Number for hand needs to be between 1 and 9.", e.getMessage());
        }
        //case 2: Try to add cards outside index for register
        try {
            player.addCardToRegister(1, -1);
        } catch (Exception e) {
            assertEquals("Number for register needs to be between 1 and 5.", e.getMessage());
        }

        try {
            player.addCardToRegister(1, 6);
        } catch (Exception e) {
            assertEquals("Number for register needs to be between 1 and 5.", e.getMessage());
        }

        //case 3: Try to add cards to an already full register slot
        try {
            player.addCardToRegister(1, 1);
            player.addCardToRegister(1, 1);
        } catch (Exception e) {
            assertEquals("That register number is not empty", e.getMessage());
        }

        // case 4: Assert card from hand does get added to register
        Card cardInHand = player.getCardsOnHand().get(1);
        try {
            player.addCardToRegister(1, 2);
        } catch (Exception e) {
        }
        Card cardInRegister = player.getRegister().get(2);
        assertEquals(cardInHand, cardInRegister);
    }

    @Test
    public void addCardToHand() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, cardstack, false);
        cardstack.initializeCardStack();
        player.drawCards();

        //case 1: Try to add cards outside register
        try {
            player.addCardToHand(-1);
        } catch (Exception e) {
            assertEquals("Number for register needs to be between 1 and 5.", e.getMessage());
        }
        try {
            player.addCardToHand(6);
        } catch (Exception e) {
            assertEquals("Number for register needs to be between 1 and 5.", e.getMessage());
        }

        //case 2: No card in register index
        try {
            player.addCardToHand(1);
        } catch (Exception e) {
            assertEquals("That register number is empty.", e.getMessage());
        }

        //case 3: Card in register is locked
        try {
            player.addCardToRegister(1, 1);
        } catch (Exception e) {
        }
        player.getRegister().get(1).setLocked();

        try {
            player.addCardToHand(1);
        } catch (Exception e) {
            assertEquals("That register number is locked.", e.getMessage());
        }

        //case 4: Card from register is added to hand
        player.getRegister().get(1).setUnlocked();
        Card card = player.getRegister().get(1);
        try {
            player.addCardToHand(1);
        } catch (Exception e) {
        }
        assertTrue(player.getCardsOnHand().contains(card));
    }

    @Test
    public void lockedRegistersNumbers() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, cardstack, false);

        // case 1: at full HP should return empty list
        ArrayList<Integer> testList = player.lockedRegistersNumbers();
        assertEquals(true, testList.isEmpty());

        for (int i = 0; i < 5; i++) {
            player.receiveDamage();
        }
        testList = player.lockedRegistersNumbers();

        // case 2: at 5 HP should return 4
        int registerNumber1 = testList.get(0);
        assertEquals(1, testList.size());
        assertEquals(4, registerNumber1);

        // case 3: at 4 HP should return 4 and 3
        player.receiveDamage();
        testList = player.lockedRegistersNumbers();
        registerNumber1 = testList.get(0);
        int registerNumber2 = testList.get(1);
        assertEquals(2, testList.size());
        assertEquals(4, registerNumber1);
        assertEquals(3, registerNumber2);

        // case 4: at 3 HP should return 4, 3 and 2
        player.receiveDamage();
        testList = player.lockedRegistersNumbers();
        registerNumber1 = testList.get(0);
        registerNumber2 = testList.get(1);
        int registerNumber3 = testList.get(2);
        assertEquals(3, testList.size());
        assertEquals(4, registerNumber1);
        assertEquals(3, registerNumber2);
        assertEquals(2, registerNumber3);

        // case 3: at 2 HP should return 4, 3, 2 and 1
        player.receiveDamage();
        testList = player.lockedRegistersNumbers();
        registerNumber1 = testList.get(0);
        registerNumber2 = testList.get(1);
        registerNumber3 = testList.get(2);
        int registerNumber4 = testList.get(3);
        assertEquals(4, testList.size());
        assertEquals(4, registerNumber1);
        assertEquals(3, registerNumber2);
        assertEquals(2, registerNumber3);
        assertEquals(1, registerNumber4);

        // case 3: at 2 HP should return 4, 3, 2, 1 and 0
        player.receiveDamage();
        testList = player.lockedRegistersNumbers();
        registerNumber1 = testList.get(0);
        registerNumber2 = testList.get(1);
        registerNumber3 = testList.get(2);
        registerNumber4 = testList.get(3);
        int registerNumber5 = testList.get(4);
        assertEquals(5, testList.size());
        assertEquals(4, registerNumber1);
        assertEquals(3, registerNumber2);
        assertEquals(2, registerNumber3);
        assertEquals(1, registerNumber4);
        assertEquals(0, registerNumber5);
    }

    @Test
    public void lockCardsInRegisters() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, cardstack, false);
        cardstack.initializeCardStack();


        //case 1: Draw cards at full robotHP, no cards in registers
        drawCards();
        for (int i = 0; i < player.getRegister().size(); i++){
            assertEquals(player.getRegister().get(i), null);
        }
        player.putCardsBackIntoCardStack();

        //case 2: Draw cards at 5 robotHP, card added to register number 4 and locked
        for (int i = 0; i < 5; i++){
            player.receiveDamage();
        }
        System.out.print(player.getHP());
        drawCards();
        //first 4 are empty
        for (int i = 0; i < player.getRegister().size()-1; i++){
            assertEquals(player.getRegister().get(i), null);
        }
        //card in register 5 and is locked
        Card testCard = new Card(MOVE_1_FORWARD, 490, true);
        assertEquals(testCard.getClass(), player.getRegister().get(4).getClass());

    }

}