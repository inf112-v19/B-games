package inf112.skeleton.app;

/**
 * The big action class.
 *
 */
public class Action implements IAction {

    private int roundCounter;

    @Override
    public void updateRound() {
        roundCounter = roundCounter + 1;
    }



}
