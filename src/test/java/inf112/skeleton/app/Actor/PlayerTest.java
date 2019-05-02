package inf112.skeleton.app.Actor;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Action.Action;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardStack;
import inf112.skeleton.app.Cards.CardType;
import org.junit.Test;


import java.util.ArrayList;

import static inf112.skeleton.app.Cards.CardType.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    private Player player;
    private Board board;
    private CardStack cardstack;


    @Test
    public void initializeHand() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, 1, cardstack, false);

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
        player = new Player(5, 5, Color.GREEN, board, 1, 1, cardstack, false);

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
        player = new Player(5, 5, Color.GREEN, board, 1, 1, cardstack, false);
        cardstack.initializeCardStack();

        //case 1: Player draws 9 cards at full HP
        player.drawCards();
        assertTrue(player.getCardsOnHand().size() == 9);
        // all should be Cards
        for (Card card : player.getCardsOnHand()) {
            assertTrue(card instanceof Card);
        }

        //case 2: Player draws cards after receiving 1 point of damage
        player = new Player(6, 6, Color.RED, board, 2, 1,  cardstack, false);

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
        player = new Player(7, 7, Color.RED, board, 3,1,  cardstack, false);
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
        player = new Player(5, 5, Color.GREEN, board, 1,1,  cardstack, false);
        cardstack.initializeCardStack();
        //case 1: No cards to add to register
        try {
            player.addCardToRegister(1, 1, true);
        } catch (Exception e) {
            assertEquals("No card in your hand at that number", e.getMessage());
        }
        player.drawCards();
        //case 2: Try to add cards outside index for hand
        try {
            player.addCardToRegister(-1, 1,true);
        } catch (Exception e) {
            assertEquals("Number for hand needs to be between 1 and 9.", e.getMessage());
        }
        try {
            player.addCardToRegister(10, 1, true);
        } catch (Exception e) {
            assertEquals("Number for hand needs to be between 1 and 9.", e.getMessage());
        }
        //case 2: Try to add cards outside index for register
        try {
            player.addCardToRegister(1, -1, true);
        } catch (Exception e) {
            assertEquals("Number for register needs to be between 1 and 5.", e.getMessage());
        }

        try {
            player.addCardToRegister(1, 6, true);
        } catch (Exception e) {
            assertEquals("Number for register needs to be between 1 and 5.", e.getMessage());
        }

        //case 3: Try to add cards to an already full register slot
        try {
            player.addCardToRegister(1, 1, true);
            player.addCardToRegister(1, 1, true);
        } catch (Exception e) {
            assertEquals("That register number is not empty", e.getMessage());
        }

        // case 4: Assert card from hand does get added to register
        Card cardInHand = player.getCardsOnHand().get(1);
        try {
            player.addCardToRegister(1, 2, true);
        } catch (Exception e) {
        }
        Card cardInRegister = player.getRegister().get(2);
        assertEquals(cardInHand, cardInRegister);
    }

    @Test
    public void addCardToHand() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, 1, cardstack, false);
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
            player.addCardToRegister(1, 1, true);
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
        player = new Player(5, 5, Color.GREEN, board, 1,1,  cardstack, false);

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
        player = new Player(5, 5, Color.GREEN, board, 1,1, cardstack, false);
        cardstack.initializeCardStack();


        //case 1: Draw cards at full robotHP, no cards in registers
        player.drawCards();
        player.lockCardsInRegisters();
        for (int i = 0; i < player.getRegister().size(); i++){
            assertEquals(player.getRegister().get(i), null);
        }
        //case 2: Draw cards at 5 robotHP, card added to register number 5 and is locked
        for (int i = 0; i < 5; i++){
            player.receiveDamage();
        }
        player.lockCardsInRegisters();

        //first 4 are empty
        for (int i = 0; i < player.getRegister().size()-1; i++){
            assertEquals(player.getRegister().get(i), null);
        }
        //card in register 5 and is locked
        Card testCard = new Card(MOVE_1_FORWARD, 490, true);
        assertEquals(testCard.getClass(), player.getRegister().get(4).getClass());
        assertFalse(player.getRegister().get(4).getUnlockedStatus());

        //case 3: Draw cards at 4 robotHP, card added to register numbers 4, 5 and are locked
        player.receiveDamage();
        player.lockCardsInRegisters();

        assertFalse(player.getRegister().get(4).getUnlockedStatus());
        assertFalse(player.getRegister().get(3).getUnlockedStatus());
        assertEquals(null, player.getRegister().get(2));
        assertEquals(null, player.getRegister().get(1));
        assertEquals(null, player.getRegister().get(0));

        //case 4: Draw cards at 3 robotHP, card added to register numbers 3, 4, 5 and are locked
        player.receiveDamage();
        player.lockCardsInRegisters();

        assertFalse(player.getRegister().get(4).getUnlockedStatus());
        assertFalse(player.getRegister().get(3).getUnlockedStatus());
        assertFalse(player.getRegister().get(2).getUnlockedStatus());
        assertEquals(null, player.getRegister().get(1));
        assertEquals(null, player.getRegister().get(0));

        //case 5: Draw cards at 2 robotHP, card added to register number 2, 3, 4 and are locked
        player.receiveDamage();
        player.lockCardsInRegisters();
        assertFalse(player.getRegister().get(4).getUnlockedStatus());
        assertFalse(player.getRegister().get(3).getUnlockedStatus());
        assertFalse(player.getRegister().get(2).getUnlockedStatus());
        assertFalse(player.getRegister().get(1).getUnlockedStatus());
        assertEquals(null, player.getRegister().get(0));

        //case 6: Draw cards at 1 robotHP, card added to register number 1, 2, 3, 4 and are locked
        player.receiveDamage();
        player.lockCardsInRegisters();
        assertFalse(player.getRegister().get(4).getUnlockedStatus());
        assertFalse(player.getRegister().get(3).getUnlockedStatus());
        assertFalse(player.getRegister().get(2).getUnlockedStatus());
        assertFalse(player.getRegister().get(1).getUnlockedStatus());
        assertFalse(player.getRegister().get(0).getUnlockedStatus());

        //case 7; Robot restores HP, all registers unlocked
        player.robotPowerDown();
        player.lockCardsInRegisters();
        assertTrue(player.getRegister().get(4).getUnlockedStatus());
        assertTrue(player.getRegister().get(3).getUnlockedStatus());
        assertTrue(player.getRegister().get(2).getUnlockedStatus());
        assertTrue(player.getRegister().get(1).getUnlockedStatus());
        assertTrue(player.getRegister().get(0).getUnlockedStatus());
    }


    @Test
    public void putCardsBackIntoCardStackTest() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1,1, cardstack, false);
        cardstack.initializeCardStack();

        assertEquals(84 , cardstack.size());

        //case 1: Draw 9 cards and put all back into stack
        player.drawCards();
        assertEquals(75 , cardstack.size());
        player.putCardsBackIntoCardStack();
        assertEquals(84 , cardstack.size());

        for (int i = 0; i < player.getCardsOnHand().size(); i++){
            assertTrue(player.getCardsOnHand().get(i)== null);
        }
        for (int i = 0; i < cardstack.size(); i++){
            Card card = cardstack.getCardFromStack();
            assertTrue(card instanceof Card);
            cardstack.addCardToStack(card);
        }

        //case 2: Locked cards in register don't get put back into stack, 1 locked card
        player.drawCards();
        for (int i = 0; i < 5; i++){
            player.receiveDamage();
        }
        player.lockCardsInRegisters();
        player.putCardsBackIntoCardStack();
        assertEquals(83 , cardstack.size());

        //case 3: Locked cards in register don't get put back into stack, 5 locked card
        player.drawCards();
        for (int i = 0; i < 4; i++){
            player.receiveDamage();
        }
        player.lockCardsInRegisters();
        player.putCardsBackIntoCardStack();
        assertEquals(79 , cardstack.size());
    }

    @Test
    public void fiveCardsInRegister() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1,1, cardstack, false);
        cardstack.initializeCardStack();
        player.drawCards();

        //case 1: No cards in registers
        assertFalse(player.fiveCardsInRegister());

        //case 2: 4 cards in register
        try {
            player.addCardToRegister(0, 0, true);
            player.addCardToRegister(0, 1, true);
            player.addCardToRegister(0, 2, true);
            player.addCardToRegister(0, 3, true);
        }
        catch(Exception e){
            e.getMessage();
        }
        assertFalse(player.fiveCardsInRegister());

        //case 2: 5 cards in register
        try {
            player.addCardToRegister(0, 4, true);
        }
        catch(Exception e){
            e.getMessage();
        }

        assertTrue(player.fiveCardsInRegister());
    }

    @Test
    public void confirmActionTest() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1,1, cardstack, false);
        cardstack.initializeCardStack();

        //case 1: No action
        assertFalse(player.getConfirmAction());

        //case 2: Player sets to powerdown
        player.setPowerDown(true);
        player.confirmAction();
        assertTrue(player.getConfirmAction());

        //case 3: 5 cards in registers sets to true
        player.setConfirmAction(false);
        player.drawCards();
        try {
            player.addCardToRegister(0, 0, true);
            player.addCardToRegister(0, 1, true);
            player.addCardToRegister(0, 2, true);
            player.addCardToRegister(0, 3, true);
            player.addCardToRegister(0, 4, true);
        }
        catch(Exception e){
            e.getMessage();
        }
        player.confirmAction();
        assertTrue(player.getConfirmAction());
    }

    @Test
    public void playerCollisionWithPlayerTest(){
        CardStack cardStack = new CardStack();
        Board board = new Board();
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 500, true)); //1
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 610, true)); //2
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 520, true)); //3
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 620, true)); //4
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 540, true)); //5
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 550, true)); //6
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 560, true)); //7
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 570, true)); //8
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 680, true)); //9
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 690, true)); //1
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 600, true)); //2
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 510, true)); //3
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 630, true)); //4
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 530, true)); //5
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 640, true)); //6
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 650, true)); //7
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 560, true)); //8
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 570, true)); //9
        Player player1 = new Player(5,5,Color.GREEN,board,1,1,  cardStack, false);
        Player player2 = new Player(5,4,Color.RED,board,2,1,  cardStack,false);
        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(player1);
        actors.add(player2);
        Action action = new Action(board, actors);


        ArrayList<Player> players = new ArrayList<Player>();
        player1.drawCards();
        player2.drawCards();
        players.add(player1);
        players.add(player2);
        action.cardResolver(players);
        assertEquals(5,player2.getY());
        assertEquals(6,player1.getY());
    }
}