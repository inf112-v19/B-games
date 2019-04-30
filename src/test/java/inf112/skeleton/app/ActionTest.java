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
    Player player3;
    CardStack cardStack;

    @Before
    public void setup(){
        board = new Board();
        cardStack = new CardStack(); //Only putting MOVE_1_FORWARD into player1, and MOVE_1_BACKWARD into player2, and ROTATE_90_LEFT into player3 for testing.
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
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 290, true)); //1
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 200, true)); //2
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 710, true)); //3
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 730, true)); //4
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 780, true)); //5
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 740, true)); //6
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 750, true)); //7
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 760, true)); //8
        cardStack.addCardToStack(new Card(CardType.ROTATE_90_LEFT, 770, true)); //9
        actor = new Actor(5, 5, Color.GREEN, board, 1);
        player1 = new Player(5,5,Color.GREEN,board,1,1,  cardStack, false);
        player2 = new Player(4,5,Color.RED,board,2,1,  cardStack,false);
        player3 = new Player(3,5,Color.BLUE,board,3, 1,  cardStack,false);
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
        board = Prototyping.generateRandomBoard();
        assertEquals(actor.getX(), 5);
        assertEquals(actor.getY(), 5);
        action.moveForward(actor);
        assertEquals(actor.getX(), 5);
        assertEquals(actor.getY(), 6);
    }

    @Test
    public void moveBackwardsMovesPlayerOneTileBackward(){
        board = Prototyping.generateRandomBoard();
        assertEquals(actor.getY(), 5);
        assertEquals(actor.getX(), 5);
        action.moveBackwards(actor);
        assertEquals(actor.getX(), 5);
        assertEquals(actor.getY(), 4);
    }

    @Test
    public void cardHandleTestTwoPlayers(){
        ArrayList<Player> players = new ArrayList<Player>();
        player1.drawCards();
        assertEquals(5,player1.getRegister().size());
        assertEquals(18, cardStack.size());
        player2.drawCards();
        assertEquals(5,player2.getRegister().size());
        assertEquals(9, cardStack.size());
        players.add(player1);
        players.add(player2);

        board = Prototyping.generateRandomBoard();
        assert(player1.getCardsOnHand().get(action.getPhase()).getPriority() < player2.getCardsOnHand().get(action.getPhase()).getPriority());
        action.cardResolver(players);
        System.out.print("Expected: 00ff00ff MOVE_1_FORWARD" + "\n");
        System.out.print("Expected: ff0000ff MOVE_1_BACKWARD" + "\n");
        action.updatePhase();
        assert(player1.getCardsOnHand().get(action.getPhase()).getPriority() > player2.getCardsOnHand().get(action.getPhase()).getPriority());
        action.cardResolver(players);
        System.out.print("Expected ff0000ff MOVE_1_BACKWARD" + "\n");
        System.out.print("Expected 00ff00ff MOVE_1_FORWARD" + "\n");
        action.updatePhase();
        assert(player1.getCardsOnHand().get(action.getPhase()).getPriority() > player2.getCardsOnHand().get(action.getPhase()).getPriority());
        action.cardResolver(players);
        System.out.print("Expected ff0000ff MOVE_1_BACKWARD" + "\n");
        System.out.print("Expected 00ff00ff MOVE_1_FORWARD" + "\n");
        action.updatePhase();
        assert(player1.getCardsOnHand().get(action.getPhase()).getPriority() < player2.getCardsOnHand().get(action.getPhase()).getPriority());
        action.cardResolver(players);
        System.out.print("Expected 00ff00ff MOVE_1_FORWARD" + "\n");
        System.out.print("Expected ff0000ff MOVE_1_BACKWARD" + "\n");
        action.updatePhase();
    }
    @Test
    public void cardHandleTestThreePlayers(){
        ArrayList<Player> players = new ArrayList<Player>();
        player1.drawCards();
        assertEquals(5,player1.getRegister().size());
        assertEquals(18, cardStack.size());
        player2.drawCards();
        assertEquals(5,player2.getRegister().size());
        assertEquals(9, cardStack.size());
        player3.drawCards();
        assertEquals(5,player3.getRegister().size());
        assertEquals(0, cardStack.size());
        players.add(player1);
        players.add(player2);
        players.add(player3);
        board = Prototyping.generateRandomBoard();
        action.cardResolver(players); //Expected RotateLeft, Forward then Backward
        System.out.print("Expected: 0000ffff ROTATE_90_LEFT" + "\n");
        System.out.print("Expected: 00ff00ff MOVE_1_FORWARD" + "\n");
        System.out.print("Expected: ff0000ff MOVE_1_BACKWARD" + "\n");
        action.updatePhase();
        action.cardResolver(players); //Expected RotateLeft, Backward then Forward
        System.out.print("Expected: 0000ffff ROTATE_90_LEFT" + "\n");
        System.out.print("Expected: ff0000ff MOVE_1_BACKWARD" + "\n");
        System.out.print("Expected: 00ff00ff MOVE_1_FORWARD" + "\n");
        action.updatePhase();
        action.cardResolver(players); //Expected Backward, Forward then RotateLeft
        System.out.print("Expected: ff0000ff MOVE_1_BACKWARD" + "\n");
        System.out.print("Expected: 00ff00ff MOVE_1_FORWARD" + "\n");
        System.out.print("Expected: 0000ffff ROTATE_90_LEFT" + "\n");
        action.updatePhase();
        action.cardResolver(players); //Expected Forward, Backward then RotateLeft
        System.out.print("Expected: 00ff00ff MOVE_1_FORWARD" + "\n");
        System.out.print("Expected: ff0000ff MOVE_1_BACKWARD" + "\n");
        System.out.print("Expected: 0000ffff ROTATE_90_LEFT" + "\n");
        action.updatePhase();
    }
}

