package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.IBoard;
import org.junit.Test;

public class BoardTest {
    IBoard board = new Board(12, 12);

    @Test
    public void constructorLinkedTest(){
        for(int x = 1; x < board.getWidth()-1; x++){
            for(int y = 1; y < board.getHeight()-1; y++){
                assertEquals(board.getAt(x, y), board.getAt(x, y-1).getLinked(Direction.NORTH));
                assertEquals(board.getAt(x, y), board.getAt(x-1, y).getLinked(Direction.EAST));
                assertEquals(board.getAt(x, y), board.getAt(x, y+1).getLinked(Direction.SOUTH));
                assertEquals(board.getAt(x, y), board.getAt(x+1, y).getLinked(Direction.WEST));
                /*
                assertEquals(board.getAt(x, y), board.getAt(x, y).getLinked(Direction.NORTH).getLinked(Direction.SOUTH));
                assertEquals(board.getAt(x, y), board.getAt(x, y).getLinked(Direction.EAST).getLinked(Direction.WEST));
                assertEquals(board.getAt(x, y), board.getAt(x, y).getLinked(Direction.SOUTH).getLinked(Direction.NORTH));
                assertEquals(board.getAt(x, y), board.getAt(x, y).getLinked(Direction.WEST).getLinked(Direction.EAST));
                */
            }
        }
    }

    @Test
    public void specificLinkedTest(){
        assertEquals(board.getAt(5,5), board.getAt(5,4).getLinked(Direction.NORTH));
        assertEquals(board.getAt(5, 5), board.getAt(5, 5).getLinked(Direction.NORTH).getLinked(Direction.SOUTH));
    }
}
