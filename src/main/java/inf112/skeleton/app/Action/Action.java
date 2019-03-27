package inf112.skeleton.app.Action;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Actor.DirectionHelpers;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.ITile;
import inf112.skeleton.app.Cards.CardType;

/**
 * The big action class.
 *
 */
public class Action implements IAction {

    private int roundCounter;
    private int phaseCounter;
    private boolean debug = true;
    Board board;

    public Action(Board board){
        this.board = board;
    }

    public void updatePhase() {
        phaseCounter += 1;
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

