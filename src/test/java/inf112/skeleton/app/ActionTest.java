package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Action.Action;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.ITile;
import inf112.skeleton.app.Board.Tile;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActionTest {
    Board board;
    Action action;
    Actor actor;

    @Before
    public void setup(){
        board = new Board();
        actor = new Actor(5, 5, Color.GREEN, board, 1);
        action = new Action(board);
        boolean[] walls = new boolean[]{false, false, false, false};
        ITile tile = new Tile(walls);
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                board.setTile(i, j, tile);
            }
        }
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