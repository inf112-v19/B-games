package inf112.skeleton.app.Action;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Actor.DirectionHelpers;
import inf112.skeleton.app.Actor.Player;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.ITile;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardType;

import java.util.ArrayList;

/**
 * The big action class.
 *
 */
public class Action implements IAction {

    private int roundCounter;
    private ArrayList<Player> players;
    private int phaseCounter;
    private boolean debug = true;
    Board board;

    public Action(Board board){
        this.board = board;
    }

    @Override
    public void updatePhase() {
        phaseCounter ++;
        if (phaseCounter % 5 == 0 && phaseCounter != 0) {
            updateRound();
        }
    }

    @Override
    public void updateRound() {
        roundCounter++;
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
    }


    @Override
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

    @Override
    public int getPhase(){
        return phaseCounter;
    }

    @Override
    public int getRound(){
        return roundCounter;
    }

    @Override
    public void moveForward(Actor player){
        move(player, player.direction);
    }

    @Override
    public void moveBackwards(Actor player){
        move(player, DirectionHelpers.reverse(player.direction));
    }

    @Override
    public void move(Actor player, Direction direction){
        ITile current = board.getAt(player.getX(), player.getY());
        if(current == null) return; // TEMPORARY
        if (current.hasWall(direction)){
            return;
        }
        player.move(direction);
    }

    @Override
    public void rotateClockwise(Actor player){
        player.direction = DirectionHelpers.rotateClockwise(player.direction);
    }

    @Override
    public void rotateAntiClockwise(Actor player) {
        player.direction = DirectionHelpers.rotateAntiClockwise(player.direction);
    }
}

