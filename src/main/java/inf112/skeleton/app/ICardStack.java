package inf112.skeleton.app;

import java.util.ArrayList;

/**
 * ICardStack represents the card stack that
 * will control randomizing, issuing cards and collecting cards
 * at start or end of a turn.
 */

public interface ICardStack {

    /**
     * fill the stack with cards
     */
    void initializeCardStack();

    /**
     * randomize stack of cards
     */
    void randomizeCardStack();

    /**
     * @return top card of the stack. Remove that card from stack
     */
   Card getCardFromStack();

    /**
     * Add a card to the stack
     * @param card card from Card class
     */
   void addCardToStack(Card card);

    /**
     * @return the number of cards in the stack
     */
    int size();

}
