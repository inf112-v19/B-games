package inf112.skeleton.app;

import static org.junit.Assert.*;

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
        loaderSaver.save(board2, "C:/Users/Eier/IdeaProjects/map dump/map.txt");
    }

    @Test
    public void readTest(){
        loaderSaver.load("C:/Users/Eier/IdeaProjects/map dump/map.txt");
    }

    @Test
    public void boardDimentionsTest(){

    }

    @Test
    public void boardComparisonTest(){
        board1 = loaderSaver.load("C:/Users/Eier/IdeaProjects/map dump/map.txt");
        testTile1 = board1.getAt(1,1);
        testTile2 = board1.getAt(1,1);
        assertEquals(testTile1.hasConveyor(), testTile2.hasConveyor());
    }

}
