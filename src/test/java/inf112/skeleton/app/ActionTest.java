package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Action.Action;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Actor.Player;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.ITile;
import inf112.skeleton.app.Board.Tile;
import inf112.skeleton.app.Cards.Card;
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
    ArrayList<Actor> players;
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
        ArrayList<Actor> players = new ArrayList<Actor>();
        player1 = new Player(5,5,Color.GREEN,board,1,1,  cardStack, false);
        player2 = new Player(4,5,Color.RED,board,2,1,  cardStack,false);
        player3 = new Player(3,5,Color.BLUE,board,3, 1,  cardStack,false);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        action = new Action(board, players);
//        boolean[] walls = new boolean[]{false, false, false, false};
//        ITile tile = new Tile(walls);
//        for(int i=0; i<10; i++){
//            for(int j=0; j<10; j++){
//                board.setTile(i, j, tile);
//            }
//        }
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
    public void fiveRoundsUpdatesIncrementsRoundCounter(){
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
    public void rotateClockwiseRotatesPlayer(){
        assertEquals(actor.direction, Direction.NORTH);
        action.rotateClockwise(actor);
        assertEquals(actor.direction, Direction.EAST);
        action.rotateClockwise(actor);
        assertEquals(actor.direction, Direction.SOUTH);
    }

    @Test
    public void rotateAntiClockwiseRotatesAntiClockwise(){
        assertEquals(actor.direction, Direction.NORTH);
        action.rotateAntiClockwise(actor);
        assertEquals(actor.direction, Direction.WEST);
        action.rotateAntiClockwise(actor);
        assertEquals(actor.direction, Direction.SOUTH);
    }

    @Test
    public void moveCanMovePerpendicularToDirection(){
        assertEquals(actor.getX(), 5);
        assertEquals(actor.direction, Direction.NORTH);
        action.move(actor, Direction.EAST);
        assertEquals(actor.getX(), 6);
    }

    @Test
    public void playMove1Forward(){
        assertEquals(actor.getY(), 5);
        action.playCard(actor, CardType.MOVE_1_FORWARD);
        assertEquals(actor.getY(), 6);
    }

    @Test
    public void playMove1Backward(){
        assertEquals(actor.getY(), 5);
        action.playCard(actor, CardType.MOVE_1_BACKWARD);
        assertEquals(actor.getY(), 4);
    }

    @Test
    public void playMove2Forward(){
        assertEquals(actor.getY(), 5);
        action.playCard(actor, CardType.MOVE_2_FORWARD);
        assertEquals(actor.getY(), 7);
    }

    @Test
    public void playMove3Forward(){
        assertEquals(actor.getY(), 5);
        action.playCard(actor, CardType.MOVE_3_FORWARD);
        assertEquals(actor.getY(), 8);
    }

    @Test
    public void playRotateLeft(){
        assertEquals(actor.direction, Direction.NORTH);
        action.playCard(actor, CardType.ROTATE_90_LEFT);
        assertEquals(actor.direction, Direction.WEST);
    }

    @Test
    public void playRotateRight(){
        assertEquals(actor.direction, Direction.NORTH);
        action.playCard(actor, CardType.ROTATE_90_RIGHT);
        assertEquals(actor.direction, Direction.EAST);
    }
}