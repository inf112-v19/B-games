package inf112.skeleton.app.gui;

import inf112.skeleton.app.*;

public class ActionResolver {

    private boolean debug = true;
    Board board;
    public ActionResolver(Board board){
        this.board = board;
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
