package inf112.skeleton.app.Board;

import inf112.skeleton.app.Actor.Direction;

public interface ITile {
    /*
    ITile is the interface for the tiles on the board, a tile can be empty, have walls north, west, east or north,
    have a wrench, have a cog, have a moving conveyor belt, a wall or a hole and this information should be accessible.

    The tile doesn't for the time being need to know where it is or what's around it, it's coordinates are being
    kept by the board.
     */

    /**
     * Checks if there is a wall on a specified side of the tile.
     *
     * @param direction the side of the tile to check
     * @return true if there is a wall on the tile in the specified direction
     */
    boolean hasWall(Direction direction);

    /**
     * @return true if the tile is a hole
     */
    boolean isHole();

    /**
     * checks if there is a conveyor belt on the tile and what direction it is rolling.
     *
     * @return the direction of the conveyor belt if it has one, and null if there is none.
     */
    Direction hasConveyor();

    /**
     * checks if there is a cogwheel on the tile and what direction it is rotating
     *
     * @return the direction the cogwheel rotates
     */
    RotationDirection hasCog();

    /**
     * adds an item or switches the current item in the tile
     *
     * @param item the item you want to add to the tile
     */
    void setItem(Item item);

    /**
     * returns one of the 2-4 tiles a tile is linked to, specified by direction.
     *
     * @param direction
     * @return The linked tile in the specified direction
     */
    ITile getLinked(Direction direction);

    /**
     * sets a tiles linked tile in a specified direction.
     *
     * @param direction
     * @param tile the tile to link
     */
    void setLinked(Direction direction, ITile tile);

    /**
     * turns the tile into a hole
     *
     * @param hole
     */
    //TODO should return true if successful and false on failure, ex. laser shouldn't be turned into a hole.
    void setHole(boolean hole);

    /**
     * the items are stored as different enum values. this function returns an item enum or null.
     *
     * @return the item currently on the tile, or null if empty.
     */
    Item getItem();

    /**
     * sets the tile to have a wall in the specified direction.
     *
     * @param direction
     */
    void setWall(Direction direction);
}
