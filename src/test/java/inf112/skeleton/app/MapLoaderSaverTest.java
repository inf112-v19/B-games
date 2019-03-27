package inf112.skeleton.app;

import static org.junit.Assert.*;

import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.ITile;
import inf112.skeleton.app.Maps.BasicMapLoaderSaver;
import inf112.skeleton.app.Maps.IMapLoaderSaver;
import org.junit.Before;
import org.junit.Test;

public class MapLoaderSaverTest {
    private Board board1;
    private Board board2;
    private IMapLoaderSaver loaderSaver = new BasicMapLoaderSaver();
    private ITile testTile1;
    private ITile testTile2;
    @Before
    public void setup(){
        board2 = new Board(10, 10);
        board2.generateRandom();
    }

    @Test
    public void writeTest(){
        loaderSaver.save(board2, "maps/map.txt");
    }

    @Test
    public void readTest(){
        loaderSaver.load("maps/map.txt");
    }

    @Test
    public void boardDimensionsTest(){
        board1 = loaderSaver.load("maps/map.txt");
        assertEquals(board1.getHeight(), 10);
        assertEquals(board1.getWidth(), 10);
    }

    @Test
    public void boardComparisonTest(){
        board2.generateRandom();
        loaderSaver.save(board2, "maps/map.txt");
        board1 = loaderSaver.load("maps/map.txt");

        for(int i = 0; i < board1.getHeight(); i++){
            for(int j = 0; j < board1.getWidth(); j++){
                testTile1 = board1.getAt(j, i);
                testTile2 = board2.getAt(j, i);
                assertEquals(testTile1.hasConveyor(), testTile2.hasConveyor());
                assertEquals(testTile1.hasWall(Direction.NORTH), testTile2.hasWall(Direction.NORTH));
                assertEquals(testTile1.hasCog(), testTile2.hasCog());
                assertEquals(testTile1.isHole(), testTile2.isHole());
            }
        }
    }

}
