package inf112.skeleton.app;

public interface IBoard {

    /**
     * Returns an object on the board
     *
     * @param x the X position of the object
     * @param y the Y position of the object
     * @return The object at that position
     */
    ITile getAt(int x, int y);

    /**
     * @return the height of the board
     */
    int getHeight();

    /**
     * @return the width of the board
     */
    int getWidth();

}
