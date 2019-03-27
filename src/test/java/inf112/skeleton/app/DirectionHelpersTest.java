package inf112.skeleton.app;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.DISCARDING;

import static org.junit.Assert.*;

public class DirectionHelpersTest {

    Direction north;
    Direction south;
    Direction west;
    Direction east;


    @Before
    public void setup(){
        north = Direction.NORTH;
        south = Direction.SOUTH;
        east = Direction.EAST;
        west = Direction.WEST;
    }


    @Test
    public void rotationFromDirectionTest() {
        assertEquals(DirectionHelpers.rotationFromDirection(north), 0, 0);
        assertEquals(DirectionHelpers.rotationFromDirection(east), 270, 0);
        assertEquals(DirectionHelpers.rotationFromDirection(south), 180, 0);
        assertEquals(DirectionHelpers.rotationFromDirection(west), 90, 0);
    }

    @Test
    public void reverse() {
        assertEquals(DirectionHelpers.reverse(north), Direction.SOUTH);
        assertEquals(DirectionHelpers.reverse(east), Direction.WEST);
        assertEquals(DirectionHelpers.reverse(west), Direction.EAST);
        assertEquals(DirectionHelpers.reverse(south), Direction.NORTH);
    }

    @Test
    public void rotateClockwise() {
        assertEquals(DirectionHelpers.rotateClockwise(north), east);
        assertEquals(DirectionHelpers.rotateClockwise(east), south);
        assertEquals(DirectionHelpers.rotateClockwise(south), west);
        assertEquals(DirectionHelpers.rotateClockwise(west), north);
    }

    @Test
    public void rotateAntiClockwise() {
        assertEquals(DirectionHelpers.rotateAntiClockwise(north), west);
        assertEquals(DirectionHelpers.rotateAntiClockwise(west), south);
        assertEquals(DirectionHelpers.rotateAntiClockwise(south), east);
        assertEquals(DirectionHelpers.rotateAntiClockwise(east), north);
    }
}