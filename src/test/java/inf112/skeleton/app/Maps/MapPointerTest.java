package inf112.skeleton.app.Maps;

import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.IBoard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MapPointerTest {

    @Test
    public void moveTest(){
        IBoard board = new Board();
        MapPointer pointer = new MapPointer(board);
        pointer.move(Direction.WEST);
        assertEquals(pointer.getTile(), board.getAt(0, 0));
        assertEquals(pointer.getX(), 0);
        assertEquals(pointer.getY(), 0);
        pointer.move(Direction.EAST);
        assertEquals(pointer.getTile(), board.getAt(1,0));
        assertEquals(pointer.getX(), 1);
        assertEquals(pointer.getY(), 0);
        pointer.move(Direction.NORTH);
        pointer.move(Direction.NORTH);
        pointer.move(Direction.SOUTH);
        pointer.move(Direction.WEST);
        assertEquals(pointer.getTile(), board.getAt(0, 1));
        assertEquals(pointer.getX(), 0);
        assertEquals(pointer.getY(), 1);
    }
}
