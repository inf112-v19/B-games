package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Action.Action;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.Board;
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
}