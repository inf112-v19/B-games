package inf112.skeleton.app.Actor;

import inf112.skeleton.app.Actor.IActor;

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
    void addCardToRegister(int indexFrom, int indexTo) throws Exception;

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
}
