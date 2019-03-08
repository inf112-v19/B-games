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

    /**
     * Function to change a tile at a specific position.
     * Throws an out of bounds exception if X or Y coordinates are outside of the map.
     *
     * @param x x position of the tile
     * @param y y position of the tile
     * @param tile a tile that extends ITile you want to override the position with
     */
    void setTile(int x, int y, ITile tile);
}
