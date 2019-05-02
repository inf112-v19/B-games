package inf112.skeleton.app.Action;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Actor.DirectionHelpers;
import inf112.skeleton.app.Actor.Player;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.ITile;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardType;
import inf112.skeleton.app.gui.GameScreen;

import java.util.*;
import java.util.function.Function;

/**
 * The big action class.
 *
 */
public class Action implements IAction {

    private int roundCounter;
    private int phaseCounter;
    private boolean debug = true;
    Board board;
    ArrayList<Player> actors;

    public Action(Board board, ArrayList<Player> actors) {
        this.board = board;
        this.actors = actors;
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

    public void cardResolver(ArrayList<Player> players) {
        //for (int i = 0; i < 5; i++) {
        //final int temp = i;
            ArrayList<Player> ps = new ArrayList<>(players);
        Collections.sort(ps, Comparator.comparing((Player player) -> player.getRegister().get(phaseCounter)));
            for (Player p : ps) {
                playCard(p, p.getRegister().get(phaseCounter).getType());
            }
        //}

        /*
        int lowestPriority;
        int indexToBePlayed = 0;
        ArrayList<Card> CardsToBePlayed = new ArrayList<Card>();                                    //Temporary cardlist.
        ArrayList<Card> CardsPlayed = new ArrayList<Card>();
        for(int i = 0; i < players.size(); i++) {
            CardsToBePlayed.add(players.get(i).getCardsOnHand().get(getPhase()));                    //Fetches a card from each player to be played this phase.
        }
        for(int i = 0; i < CardsToBePlayed.size(); i++) {
            lowestPriority = 2000;
            for (int j = 0; j < CardsToBePlayed.size(); j++) {
                if (CardsToBePlayed.get(j).getPriority() < lowestPriority && !(CardsPlayed.contains(CardsToBePlayed.get(j)))) {
                    lowestPriority = CardsToBePlayed.get(j).getPriority();
                    indexToBePlayed = j;
                }
            }
            CardsPlayed.add(CardsToBePlayed.get(indexToBePlayed));
            playCard(players.get(indexToBePlayed), CardsToBePlayed.get(indexToBePlayed).getType());
        }*/
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
        int oldX = player.getX();
        int oldY = player.getY();
        if(current == null) return; // TEMPORARY
        if (current.hasWall(direction)){
            return;
        }
        player.move(direction);
        ITile newCurrent = board.getAt(player.getX(), player.getY());
        for(Actor actor : actors){
            ITile actorPosition = board.getAt(actor.getX(), actor.getY());
            if(actor != player && newCurrent == actorPosition){
                System.out.println("move i action");
                if(!newCurrent.hasWall(direction)) {
                    actor.move(direction);
                }else{
                    player.setX(oldX);
                    player.setY(oldY);
                }
            }
        }
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


