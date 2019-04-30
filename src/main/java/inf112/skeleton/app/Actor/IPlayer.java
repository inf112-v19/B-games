package inf112.skeleton.app.Actor;

import inf112.skeleton.app.Actor.IActor;
import inf112.skeleton.app.Cards.Card;

import java.util.ArrayList;
import java.util.List;

public interface IPlayer extends IActor {


    /**
     * Player starts with empty hand (null) that can fit up to 9 cards
     */
    void initializeHand();

    /**
     * Player has a register that can fit up to 5 cards
     */
    void initializeRegister();

    /**
     * Player draws cards based on how much HP his robot has
     */
    void drawCards();

    /**
     * Add a card from card in hand to the register
     */
    void addCardToRegister(int indexFrom, int indexTo, boolean player) throws Exception;

    /**
     * Take a card from register and add it to first available index in hand
     */
    void addCardToHand(int indexFrom)throws Exception;

    /**
     * Put all cards that aren't locked back into cardstack.
     */
    void putCardsBackIntoCardStack();

    /**
     * Lock cards in register numbers based on robotHP.
     */
    void lockCardsInRegisters();

    /**
     * Get the register.
     *
     * @return List<Card>
     */
    List<Card> getRegister ();

    /**
     * Get the cards you have on hand.
     *
     * @return List<Card>
     */
    List<Card> getCardsOnHand();

    /**
     * Confirm you want to power down the robot on next turn
     *
     * @param bool
     */
    void setPowerDown(boolean bool);

    /**
     * Set ConfirmAction to bool
     ** @param bool
     */
    void setConfirmAction(boolean bool);

    /**
     * Return whether player has confirmed action or not
     ** @return boolean
     */

    boolean getConfirmAction();

    /**
     * Check if conditions for confirmed an action are present
     *
     */
    void confirmAction();

    /**
     * Check if all registers have cards in them
     *
     * @return boolean
     */
    boolean fiveCardsInRegister();

    /**
     * List of which numbered registers needs to be locked based on robotHP
     *
     * @return ArrayList<Integer>
     */
    ArrayList<Integer> lockedRegistersNumbers();


}
