package inf112.skeleton.app;

public interface IActor {

    /**
     * The actor's x-position
     * @return actor's x-position on the board
     */
    public int getX();

    /**
     * The actor's y-position
     * @return actor's y-position on the board
     */
    public int getY();

    /**
     * The distance the actor moves when moved
     * @return distance
     */
    public int getMoveDistance();

    /**
     * Moves the actor in given direction
     * @param direction
     */
    public void move(Direction direction);

}
