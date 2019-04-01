package inf112.skeleton.app.Action;

import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Cards.CardType;

public interface IAction {


    /**
     * Updates round
     */
    public void updateRound();

    /**
     * Updates phase, after 5 phases calls updateRound()
     */
    public void updatePhase();


    /**
     * Retrieve value of roundCounter
     * @return roundCounter
     */
    public int getRound();

    /**
     * Plays the the given card on given player
     * @param player
     * @param card
     * @return true
     */
    boolean playCard(Actor player, CardType card);

    /**
     * Retrieve value of phaseCounter
     * @return phaseCounter
     */
    public int getPhase();

    /**
     * Moves actor one tile forward
     * @param player
     */
    void moveForward(Actor player);

    /**
     * Moves actor one tile backwards
     * @param player
     */
    void moveBackwards(Actor player);

    /**
     * Moves player in given direction
     * @param player
     * @param direction
     */
    void move(Actor player, Direction direction);

    /**
     * Rotates actor clockwise
     * @param player
     */
    void rotateClockwise(Actor player);

    /**
     * Rotates actor counterclockwise
     * @param player
     */
    void rotateAntiClockwise(Actor player);
}
