package inf112.skeleton.app;
import java.util.ArrayList;
import java.util.List;

import inf112.skeleton.app.*;

/**
 * The big action class.
 *
 */
public class Action implements IAction {

    private int roundCounter;
    private int phaseCounter;
    private List<Player> players;
    private boolean debug = true;
    Board board;

    public Action(Board board){

        this.board = board;
    }

    public void updatePhase() {

        phaseCounter += 1;
    }
    public void cardResolver() {
        int lowestPriority = 0;
        int indexToBePlayed = 0;
        ArrayList<Card> CardsToBePlayed = new ArrayList<Card>();                                    //Temporary cardlist.
        for(int i = 0; i <= players.size(); i++) {
            CardsToBePlayed.add(players.get(i).getRegister().get(phaseCounter));                    //Fetches a card from each player to be played this phase.
        }
        for(int i = 0; i <= CardsToBePlayed.size(); i++) {
            for (int j = 0; j <= CardsToBePlayed.size(); j++) {
                if (CardsToBePlayed.get(j).getPriority() < lowestPriority) {
                    lowestPriority = CardsToBePlayed.get(j).getPriority();
                    indexToBePlayed = i;
                }
            }
            playCard(players.get(indexToBePlayed), CardsToBePlayed.get(indexToBePlayed).getType());
        }
        updatePhase();
        updateRound();
    }
    @Override
    public void updateRound() {
        if (phaseCounter % 5 == 0 && phaseCounter != 0) {
            roundCounter += 1;
        }

    }

    public boolean playCard(Actor player, CardType card){
        if(debug) {
            System.out.println(String.format("%s - %s", player.getColor(), card.name()));
        }

        switch (card) {
            case MOVE_3_FORWARD:
                moveForward(player);
            case MOVE_2_FORWARD:
                moveForward(player);
            case MOVE_1_FORWARD:
                moveForward(player);
                break;
            case MOVE_1_BACKWARD:
                moveBackwards(player);
                break;
            case ROTATE_90_LEFT:
                rotateAntiClockwise(player);
                break;
            case ROTATE_180:
                rotateClockwise(player);
            case ROTATE_90_RIGHT:
                rotateClockwise(player);
                break;
        }
        return true;
    }

    private void moveForward(Actor player){
        move(player, player.direction);
    }

    private void moveBackwards(Actor player){
        move(player, DirectionHelpers.reverse(player.direction));
    }

    private void move(Actor player, Direction direction){
        ITile current = board.getAt(player.getX(), player.getY());
        if(current == null) return; // TEMPORARY
        if (current.hasWall(direction)){
            return;
        }
        player.move(direction);
    }

    private void rotateClockwise(Actor player){
        player.direction = DirectionHelpers.rotateClockwise(player.direction);
    }

    private void rotateAntiClockwise(Actor player) {
        player.direction = DirectionHelpers.rotateAntiClockwise(player.direction);
    }
}

