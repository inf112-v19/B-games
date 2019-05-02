package inf112.skeleton.app;

import static org.junit.Assert.*;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.Conveyor;
import inf112.skeleton.app.Board.RotationDirection;
import inf112.skeleton.app.Board.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ActorTest {
    Actor actor;
    Direction direction;
    ArrayList<Actor> actors;
    Board board;

    @Before
    public void setup(){
        this.board = new Board();
        actors = new ArrayList<Actor>();
        actor = new Actor(5, 5, Color.GREEN, board, 1);

    }


    @Test
    public void xyCoordinatesShouldBeSameAsInitializationCoordinates(){
        assertEquals(actor.getX(), 5);
        assertEquals(actor.getY(), 5);
    }

    @Test
    public void setXAndYUpdatesPosition(){
        actor.setX(9);
        actor.setY(0);
        assertEquals(actor.getX(), 9);
        assertEquals(actor.getY(), 0);
    }

    @Test
    public void moveActorUpdatesPosition(){
        actor.move(Direction.NORTH);
        assertEquals(actor.getY(), 6);
        actor.move(Direction.SOUTH);
        assertEquals(actor.getY(), 5);
        actor.move(Direction.EAST);
        assertEquals(actor.getX(), 6);
        actor.move(Direction.WEST);
        assertEquals(actor.getX(), 5);
    }

    @Test
    public void actorCannotMoveOutsideBoard() {
        for (int i = 0; i < 20; i++) {
            actor.move(Direction.NORTH);
            actor.move(Direction.EAST);
        }
        assertEquals(actor.getX(), 9);
        assertEquals(actor.getY(), 9);
        for (int i = 0; i < 20; i++) {
            actor.move(Direction.SOUTH);
            actor.move(Direction.WEST);
        }
        assertEquals(actor.getX(), 0);
        assertEquals(actor.getY(), 0);

    }

    @Test
    public void rotateMethodRotatesActor(){
        actor.rotate(RotationDirection.CLOCKWISE);
        assertEquals(actor.direction, Direction.EAST);
        actor.rotate(RotationDirection.COUNTERCLOCKWISE);
        actor.rotate(RotationDirection.COUNTERCLOCKWISE);
        assertEquals(actor.direction, Direction.WEST);
    }

    @Test
    public void receiveDamageDecrementsHealth(){
        assertEquals(actor.getHP(), 10);
        actor.receiveDamage();
        assertEquals(actor.getHP(), 9);
    }

    @Test
    public void repairDamageincrementsHealth(){
        actor.receiveDamage();
        actor.receiveDamage();
        assertEquals(actor.getHP(), 8);
        actor.repairDamage();
        assertEquals(actor.getHP(), 9);
    }

    @Test
    public void repairDamageCannotExceedTenHP(){
        actor.repairDamage();
        assertEquals(actor.getHP(), 10);
    }

    @Test
    public void zeroHPDecrementsLives(){
        assertEquals(actor.getLives(), 3);
        for(int i=0; i<10; i++){
            actor.receiveDamage();
        }
        assertEquals(actor.getLives(), 2);
    }

    @Test
    public void zeroHPRestoresActorWithEightHP(){
        assertEquals(actor.getHP(), 10);
        for(int i=0; i<10; i++){
            actor.receiveDamage();
        }
        assertEquals(actor.getHP(), 8);
    }

    @Test
    public void updateRestorationPointRestoresActorAtNewPosition(){
        actor.move(Direction.NORTH);
        actor.move(Direction.WEST);
        actor.updateRestorationPoint();
        actor.move(Direction.NORTH);
        for(int i=0; i<10; i++){
            actor.receiveDamage();
        }
        assertEquals(actor.getY(), 6);
        assertEquals(actor.getX(), 4);
    }

    @Test
    public void visitingFlagsIncrementsFlagsVisited(){
        assertEquals(actor.getFlagsVisited(), 0);
        actor.flagVisited(1);
        assertEquals(actor.getFlagsVisited(), 1);
        actor.flagVisited(2);
        assertEquals(actor.getFlagsVisited(), 2);
    }

    @Test
    public void visitingWrongFlagsDoesNotIncrementFlagsVisited(){
        actor.flagVisited(2);
        assertEquals(actor.getFlagsVisited(), 0);
    }

    @Test
    public void northConveyorMovesActorNorth(){
        boolean[] walls = new boolean[]{false, false, false, false};
        Tile tile = new Tile(walls, new Conveyor(Direction.NORTH, false));
        board.setTile(5, 5, tile);
        assertEquals(actor.getY(), 5);
        actor.tileCheck(actors);
        assertEquals(actor.getY(), 6);
    }

    @Test
    public void southConveyorMovesActorSouth(){
        boolean[] walls = new boolean[]{false, false, false, false};
        Tile tile = new Tile(walls, new Conveyor(Direction.SOUTH, false));
        board.setTile(5, 5, tile);
        assertEquals(actor.getY(), 5);
        actor.tileCheck(actors);
        assertEquals(actor.getY(), 4);
    }

    @Test
    public void westConveyorMovesActorWest(){
        boolean[] walls = new boolean[]{false, false, false, false};
        Tile tile = new Tile(walls, new Conveyor(Direction.WEST, false));
        board.setTile(5, 5, tile);
        assertEquals(actor.getX(), 5);
        actor.tileCheck(actors);
        assertEquals(actor.getX(), 4);
    }

    @Test
    public void eastConveyorMovesActorEast(){
        boolean[] walls = new boolean[]{false, false, false, false};
        Tile tile = new Tile(walls, new Conveyor(Direction.EAST, false));
        board.setTile(5, 5, tile);
        assertEquals(actor.getX(), 5);
        actor.tileCheck(actors);
        assertEquals(actor.getX(), 6);
    }
}