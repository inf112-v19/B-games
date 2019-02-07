package inf112.skeleton.app;

import static org.junit.Assert.*;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.gui.Actor;
import org.junit.Before;
import org.junit.Test;

public class ActorTest {
    Texture texture;
    Actor actor;

    @Before
    public void setup(){
        actor = new Actor(200, 200, texture);
    }


    @Test
    public void xyCoordinatesShouldBeSameAsInitializationCoordinates(){
        assertEquals(actor.getX(), 200);
        assertEquals(actor.getX(), 200);
    }

    @Test
    public void goNorthShouldIncrementYCoordinate(){
        actor.goNorth();
        assertEquals(actor.getY(), 200+actor.getMoveDistance());
    }

    @Test
    public void goSouthShouldDecrementYCoordinate(){
        actor.goSouth();
        assertEquals(actor.getY(), 200-actor.getMoveDistance());
    }

    @Test
    public void goWestShouldDecrementXCoordinate(){
        actor.goWest();
        assertEquals(actor.getX(), 200-actor.getMoveDistance());
    }

    @Test
    public void goEastShouldIncrementXCoordinate(){
        actor.goEast();
        assertEquals(actor.getX(), 200+actor.getMoveDistance());
    }
}