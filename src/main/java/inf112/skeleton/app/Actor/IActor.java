package inf112.skeleton.app.Actor;

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
     * The actor's current Lives
     * @return number of lives
     */
    public int getLives();

    /**
     * The actor's current HP
     * @return HP
     */
    public int getHP();

    /**
     * Moves the actor in given direction
     * @param direction
     */
    public void move(Direction direction);

}
