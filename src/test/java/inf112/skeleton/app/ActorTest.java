package inf112.skeleton.app;

import static org.junit.Assert.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import org.junit.Before;
import org.junit.Test;

public class ActorTest {
    Texture texture;
    Actor actor;

    @Before
    public void setup(){
        actor = new Actor(200, 200, Color.GREEN);
    }


    @Test
    public void xyCoordinatesShouldBeSameAsInitializationCoordinates(){
        assertEquals(actor.getX(), 200);
        assertEquals(actor.getX(), 200);
    }
}