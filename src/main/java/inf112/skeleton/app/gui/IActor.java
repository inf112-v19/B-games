package inf112.skeleton.app.gui;

import com.badlogic.gdx.graphics.Texture;

public interface IActor {

    /**
     * The actor's x-position
     * @return actor's x-position in pixels
     */
    public int getX();

    /**
     * The actor's y-position
     * @return actor's y-position in pixels
     */
    public int getY();

    /**
     * The distance the actor moves when moved
     * @return distance in pixels
     */
    public int getMoveDistance();

    /**
     * Texture used to display actor
     * @return actor's texture
     */
    //public Texture getTexture();


    /**
     * Moves the actor in given direction
     * @param direction
     */
    public void move(Enum direction);

    /**
     * Call to move actor northwards
     */
    public void goNorth();

    /**
     * Call to move actor southwards
     */
    public void goSouth();

    /**
     * Call to move actor eastwards
     */
    public void goEast();

    /**
     * Call to move actor westwards
     */
    public void goWest();

}
