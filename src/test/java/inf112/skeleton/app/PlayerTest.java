package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Actor.Player;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardStack;
import org.junit.Test;


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

        assertTrue(player.getCardsOnHand().size() == 9);
        for (Card card : player.getCardsOnHand()){
            assertTrue(card == null);
        }
    }

    @Test
    public void initializeRegister() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, cardstack, false);

        assertTrue(player.getRegister().size() == 5);
        for (Card card : player.getRegister()){
            assertTrue(card == null);
        }
    }


    @Test
    public void drawCards() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, cardstack, false);
        cardstack.initializeCardStack();

        //case 1: Player draws cards at full HP
        player.drawCards();
        assertTrue(player.getCardsOnHand().size() == 9);
        for (Card card : player.getCardsOnHand()){
            assertTrue(card instanceof Card); // all should be Cards
        }

        //case 2: Player draws cards after receiving 1 point of damage
        player = new Player(6, 6, Color.RED, board, 2, cardstack, false);

        player.receiveDamage();
        player.drawCards();

        for (int i=0; i < player.getCardsOnHand().size()-1; i++){
            assertTrue(player.getCardsOnHand().get(i) instanceof Card); // first 8 should be Cards
        }

        int lastCard = player.getCardsOnHand().size()-1;
        assertTrue(player.getCardsOnHand().get(lastCard) == null); // last one should be null

        //case 3: Player(tries to) draw cards after receiving enough damage to not get any cards
        player = new Player(7, 7, Color.RED, board, 3, cardstack, false);
        for (int i=0; i < player.getCardsOnHand().size(); i++){
            player.receiveDamage();
        }
        player.drawCards();
        for (int i=0; i < player.getCardsOnHand().size(); i++){
            assertTrue(player.getCardsOnHand().get(i) == null); // all should be null
        }
    }

    @Test
    public void addCardToRegister() {
        board = new Board();
        cardstack = new CardStack();
        player = new Player(5, 5, Color.GREEN, board, 1, cardstack, false);
        cardstack.initializeCardStack();
        //test for Exceptions first
        try {
            player.addCardToRegister(1, 1);
        }
        catch (Exception e){
            assertEquals("No card in your hand at that number", e.getMessage());
        }
        try {
            player.addCardToRegister(0, 1);
        }
        catch (Exception e){
            assertEquals("Number for hand needs to be between 1 and 9.", e.getMessage());
        }
        try {
            player.addCardToRegister(10, 1);
        }
        catch (Exception e){
            assertEquals("Number for hand needs to be between 1 and 9.", e.getMessage());
        }


    }
}