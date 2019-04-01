package inf112.skeleton.app;

import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.Item;
import inf112.skeleton.app.Board.RotationDirection;
import inf112.skeleton.app.Board.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {

    Board board;
    boolean[] walls;

    @Before
    public void setup(){
        this.board = new Board();
        this.walls = new boolean[]{false, false, false, false};
    }

    @Test
    public void hasWall() {
        Tile tile = new Tile(walls);
        assertEquals(tile.hasWall(Direction.NORTH), false);
        assertEquals(tile.hasWall(Direction.SOUTH), false);
        assertEquals(tile.hasWall(Direction.EAST), false);
        assertEquals(tile.hasWall(Direction.WEST), false);
        walls = new boolean[]{true, true, true, true};
        Tile otherTile = new Tile(walls);
        assertEquals(otherTile.hasWall(Direction.NORTH), true);
        assertEquals(otherTile.hasWall(Direction.SOUTH), true);
        assertEquals(otherTile.hasWall(Direction.EAST), true);
        assertEquals(otherTile.hasWall(Direction.WEST), true);
    }

    @Test
    public void isHole() {
        Tile tile = new Tile(walls);
        assertEquals(tile.isHole(), false);
        tile.setHole(true);
        assertEquals(tile.isHole(), true);
    }

    @Test
    public void setHole() {
        Tile tile = new Tile(walls);
        assertEquals(tile.isHole(), false);
        tile.setHole(true);
        assertEquals(tile.isHole(), true);
        tile.setHole(false);
        assertEquals(tile.isHole(), false);
    }

    @Test
    public void hasConveyor() {
        Tile northTile = new Tile(walls, Direction.NORTH);
        assertEquals(northTile.hasConveyor(), Direction.NORTH);
        Tile southTile = new Tile(walls, Direction.SOUTH);
        assertEquals(southTile.hasConveyor(), Direction.SOUTH);
        Tile eastTile = new Tile(walls, Direction.EAST);
        assertEquals(eastTile.hasConveyor(), Direction.EAST);
        Tile westTile = new Tile(walls, Direction.WEST);
        assertEquals(westTile.hasConveyor(), Direction.WEST);
    }

    @Test
    public void hasCog() {
        Tile clockwiseTile = new Tile(walls, RotationDirection.CLOCKWISE);
        assertEquals(clockwiseTile.hasCog(), RotationDirection.CLOCKWISE);
        Tile counterClockwiseTile = new Tile(walls, RotationDirection.COUNTERCLOCKWISE);
        assertEquals(counterClockwiseTile.hasCog(), RotationDirection.COUNTERCLOCKWISE);
    }

    @Test
    public void getAndSetItem() {
        Tile tile = new Tile();
        assertEquals(tile.getItem(), null);
        tile.setItem(Item.FLAG);
        assertEquals(tile.getItem(), Item.FLAG);
        tile.setItem(null);
        assertEquals(tile.getItem(), null);
        tile.setItem(Item.WRENCH);
        assertEquals(tile.getItem(), Item.WRENCH);
    }
}