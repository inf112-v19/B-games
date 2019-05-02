package inf112.skeleton.app.Maps;

import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.*;
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
        Tile northTile = new Tile(walls, new Conveyor(Direction.NORTH, false));
        assertEquals(northTile.hasConveyor().direction, Direction.NORTH);
        Tile southTile = new Tile(walls, new Conveyor(Direction.SOUTH, false));
        assertEquals(southTile.hasConveyor().direction, Direction.SOUTH);
        Tile eastTile = new Tile(walls, new Conveyor(Direction.EAST, false));
        assertEquals(eastTile.hasConveyor().direction, Direction.EAST);
        Tile westTile = new Tile(walls, new Conveyor(Direction.WEST, false));
        assertEquals(westTile.hasConveyor().direction, Direction.WEST);
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

    @Test
    public void linkedTest(){
        ITile tile1 = new Tile();
        ITile tile2 = new Tile();
        tile1.setLinked(Direction.EAST, tile2);
        tile2.setLinked(Direction.WEST, tile1);
        assertEquals(tile1, tile1.getLinked(Direction.EAST).getLinked(Direction.WEST));
        assertEquals(tile2, tile2.getLinked(Direction.WEST).getLinked(Direction.EAST));
        assertEquals(tile1, tile2.getLinked(Direction.WEST));
        assertEquals(tile2, tile1.getLinked(Direction.EAST));
    }
}