package inf112.skeleton.app.Cards;

/**
 * ICard represents a card the Actor uses
 * to program the movement of a robot on the Board.
 * cards are organized in different types. Priority determines
 * which card gets executed first and UnlockedStatus tells whether
 * a card is locked in register or not.
 */

public interface ICard {

    /**
     * @return type for a given card
     */
    CardType getType();

    /**
     * @return priority for a given card
     */
    int getPriority();

    /**
     * @return UnlockedStatus for for a given card
     */
    boolean getUnlockedStatus();

    /**
     * Set a card as unlocked
     */
    void setUnlocked();

    /**
     * Set a card as locked
     */
    void setLocked();
}
