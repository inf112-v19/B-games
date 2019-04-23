package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Action.Action;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Actor.Player;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardStack;
import inf112.skeleton.app.Cards.CardType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ActionTest {
    Board board;
    Action action;
    Actor actor;
    Player player1;
    Player player2;
    CardStack cardStack;
    CardStack player1hand;
    CardStack player2hand;

    @Before
    public void setup(){
        board = new Board();
        cardStack = new CardStack();
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 500, true)); //1
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 610, true)); //2
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 520, true)); //3
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 620, true)); //4
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 540, true)); //5
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 550, true)); //6
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 560, true)); //7
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 570, true)); //8
        cardStack.addCardToStack(new Card(CardType.MOVE_1_FORWARD, 680, true)); //9
        cardStack.addCardToStack(new Card(CardType.MOVE_1_BACKWARD, 690, true)); //1
        cardStack.addCardToStack(new Card(CardType.MOVE_1_BACKWARD, 600, true)); //2
        cardStack.addCardToStack(new Card(CardType.MOVE_1_BACKWARD, 510, true)); //3
        cardStack.addCardToStack(new Card(CardType.MOVE_1_BACKWARD, 630, true)); //4
        cardStack.addCardToStack(new Card(CardType.MOVE_1_BACKWARD, 530, true)); //5
        cardStack.addCardToStack(new Card(CardType.MOVE_1_BACKWARD, 640, true)); //6
        cardStack.addCardToStack(new Card(CardType.MOVE_1_BACKWARD, 650, true)); //7
        cardStack.addCardToStack(new Card(CardType.MOVE_1_BACKWARD, 560, true)); //8
        cardStack.addCardToStack(new Card(CardType.MOVE_1_BACKWARD, 570, true)); //9
        actor = new Actor(5, 5, Color.GREEN, board, 1);
        player1 = new Player(5,5,Color.GREEN,board,1, cardStack, false);
        player2 = new Player(4,5,Color.RED,board,2, cardStack,false);
        action = new Action(board);
    }

    @Test
    public void updatePhaseIncrementsPhaseCounter() {
        assertEquals(action.getPhase(), 0);
        action.updatePhase();
        assertEquals(action.getPhase(), 1);
        action.updatePhase();
        assertEquals(action.getPhase(), 2);
    }

    @Test
    public void updateRoundIncrementsRoundCounter() {
        assertEquals(action.getRound(), 0);
        action.updateRound();
        assertEquals(action.getRound(), 1);
        action.updateRound();
        assertEquals(action.getRound(), 2);
    }

    @Test
    public void fiveRoundUpdatesIncrementsRoundCounter(){
        assertEquals(action.getRound(), 0);
        for(int i=0; i<5; i++){
            action.updatePhase();
        }
        assertEquals(action.getRound(), 1);
    }

    @Test
    public void moveForwardMovesPlayerOneTile(){
        board.generateRandom();
        assertEquals(actor.getX(), 5);
        assertEquals(actor.getY(), 5);
        action.moveForward(actor);
        assertEquals(actor.getX(), 5);
        assertEquals(actor.getY(), 6);
    }

    @Test
    public void moveBackwardsMovesPlayerOneTileBackward(){
        board.generateRandom();
        assertEquals(actor.getY(), 5);
        assertEquals(actor.getX(), 5);
        action.moveBackwards(actor);
        assertEquals(actor.getX(), 5);
        assertEquals(actor.getY(), 4);
    }

    @Test
    public void cardHandleTest(){
        assertEquals(0, action.getPhase());
        assertEquals(18, cardStack.size());
        assertEquals(10,player1.getHP());
        assertEquals(10, player2.getHP());


        ArrayList<Player> players = new ArrayList<Player>();
        player1.drawCards();
        assertEquals(5,player1.getRegister().size());
        assertEquals(9, cardStack.size());
        player2.drawCards();
        assertEquals(5,player2.getRegister().size());
        assertEquals(0, cardStack.size());

        players.add(player1);
        players.add(player2);
        // player1hand = new CardStack();
        // player2hand = new CardStack();


        board.generateRandom();

        assertEquals(player1.getY(), 5);
        assertEquals(player1.getX(), 5);
        assertEquals(player2.getY(), 5);
        assertEquals(player2.getX(), 4);
      /*  player1hand.addCardToStack(cardStack.getCardFromStack());
        player2hand.addCardToStack(cardStack.getCardFromStack());
        player1hand.addCardToStack(cardStack.getCardFromStack());
        player2hand.addCardToStack(cardStack.getCardFromStack());
        player2hand.addCardToStack(cardStack.getCardFromStack());
        player1hand.addCardToStack(cardStack.getCardFromStack());
        player2hand.addCardToStack(cardStack.getCardFromStack());
        player1hand.addCardToStack(cardStack.getCardFromStack());
        player2hand.addCardToStack(cardStack.getCardFromStack());
        player1hand.addCardToStack(cardStack.getCardFromStack()); */



        action.cardResolver(players); //Forward først
        action.updatePhase();
        assertEquals(1, action.getPhase());
        action.cardResolver(players); //Backward først
        action.updatePhase();
        assertEquals(2, action.getPhase());
        action.cardResolver(players);
        action.updatePhase();
        assertEquals(3, action.getPhase());
        action.cardResolver(players);
        action.updatePhase();
        assertEquals(4, action.getPhase());
    }
}

