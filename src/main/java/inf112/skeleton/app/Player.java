package inf112.skeleton.app;

import java.util.List;

public class Player {
    private int PlayerId;
    private List<Card> cardsOnHand;
    private List<Card> cardsInRegister;
    private boolean powerDownRobot;
    private boolean confirmAction;

    public void drawCards(){
        // TODO Draw cards from CardStack based on RobotHP
        //  and add them to CardsOnHand
    }

    public void addCardToRegister(Card card){
        // TODO Move card from CardsOnHand and put it in cardsInRegister
    }

    public void addCardToHand(Card card){
        // TODO Move card from CardsInRegister and back into CardsOnHand
    }

    public void putCardsBackIntoCardStack(List<Card> cardsOnHand, List<Card> cardsInRegister){
        // TODO Collect all cards that aren't locked and
        //  put them back into card stack. Maybe be best to
        //  put this in Action class as it will
        //  be done by all players at end of turn
    }

    public void lockCardsInRegisters(){
        // TODO Locks cards in register. If robotHP at:
        //  - 5 lock register 5
        //  - 4 lock register 5 & 4
        //  - 3 lock register 5, 4 & 3
        //  - 2 lock register 5, 4, 3 & 2
        //  - 1 lock all registers
    }

    public void powerDown(){
        /*
         * TODO Robot remain inactive for 1 turn, restores all HP
         *  at end of turn (after repair but before cards are collected)
         */
    }

    public static void main(String[] args) {
        /*
         * TODO Main method that runs between every turn.
         *  Reads input from players and waits for
         *  confirmAction to be equal to true to execute actions
         */

    }
}
