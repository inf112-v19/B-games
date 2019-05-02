package inf112.skeleton.app.Actor;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.ITile;
import inf112.skeleton.app.Board.Item;
import inf112.skeleton.app.Board.RotationDirection;

import java.util.ArrayList;

public class Actor implements IActor {
    private int xPos;
    private int yPos;
    private int xPosArchivePoint;
    private int yPosArchivePoint;
    private Color color;
    private int robotLives;
    private int robotHP;
    public Direction direction = Direction.NORTH;
    private int onConveyorCount;
    private int flagsVisited = 0;
    private Board board;
    private int dockingAssignment;
    private boolean isDead = false;


    public Actor(int x, int y, Color color, Board board, int dockingAssignment){
        this.color = color;
        this.board = board;
        if(x<board.getWidth() && y<board.getHeight() && x>=0 && y>=0) {
            this.xPos = x;
            this.yPos = y;
        }else{
            throw new IllegalArgumentException("cannot instantiate actor outside the board");
        }
        this.dockingAssignment = dockingAssignment;
        robotHP = 10;
        robotLives = 3;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public int getX(){
        return this.xPos;
    }

    @Override
    public int getY(){
        return this.yPos;
    }

    public void setX(int x){
        if(x<board.getHeight() && x>=0) {
            this.xPos = x;
        }
    }

    public void setY(int y){
        if(y<board.getHeight() && y>=0) {
            this.yPos = y;
        }
    }

    @Override
    public int getHP(){
        return this.robotHP;
    }

    @Override
    public int getLives(){
        return this.robotLives;
    }

    public int getFlagsVisited(){
        return this.flagsVisited;
    }

    @Override
    public void move(Direction direction) {
        int temporaryX = xPos;
        int temporaryY = yPos;
        switch (direction){
            case NORTH:
                temporaryY += 1;
                break;
            case EAST:
                temporaryX += 1;
                break;
            case SOUTH:
                temporaryY -= 1;
                break;
            case WEST:
                temporaryX -= 1;
                break;
        }
        if(temporaryX<board.getWidth() && temporaryX >=0
                && temporaryY<board.getHeight() && temporaryY>=0){ //checking if temporary position is outside the map
            this.xPos = temporaryX;
            this.yPos = temporaryY;
        }

    }

    /*Checks what type of tile actor is standing on and
    what items are in it and calls appropriate methods.
    Taking damage from laser beam is not yet implemented*/
    public void tileCheck(ArrayList<Actor> actors){
        if(!isDead) {
            ITile tile = board.getAt(xPos, yPos);
            int oldX = xPos;
            int oldY = yPos;
            Item item = tile.getItem();
            if (tile.isHole()) {
                robotDestroyed();
            }
            if (tile.hasCog() != null) {
                rotate(tile.hasCog());
            }
            if (tile.hasConveyor() != null) {

                if (tile.hasConveyor() != null) {
                    onConveyorCount += 1;
                } else {
                    onConveyorCount = 0;
                }
                move(tile.hasConveyor().direction);
                ITile currentTile = board.getAt(xPos, yPos);
                for(Actor actor : actors){
                    ITile actorPosition = board.getAt(actor.getX(), actor.getY());
                    if(actor != this && currentTile == actorPosition){
                        System.out.println("tilecheck i actor");
                        if(!currentTile.hasWall(direction)) {
                            actor.move(tile.hasConveyor().direction);
                        }else{
                            setX(oldX);
                            setY(oldY);
                        }
                    }
                }
                //If robot has gone on 2 or more conveyors in a row, then change its direction to last conveyorbelt.
                if (onConveyorCount >= 2) {
                    direction = tile.hasConveyor().direction;
                    onConveyorCount = 0;
                }

            }
            if (item != null) {
                if (item == Item.WRENCH) {
                    repairDamage();
                    updateRestorationPoint();
                }
                if (item == Item.FLAG) {
                    updateRestorationPoint();
                }
            }
        }
    }

    //rotates actor either clockwise or counterclockwise
    public void rotate(RotationDirection rotationDirection) {
        if(rotationDirection.equals(RotationDirection.CLOCKWISE)){
            direction = DirectionHelpers.rotateClockwise(direction);
        }else{
            direction = DirectionHelpers.rotateAntiClockwise(direction);
        }
    }

    public void updateRestorationPoint(){
        xPosArchivePoint = xPos;
        yPosArchivePoint = yPos;
    }

    public void restoreRobot(){
        xPos = xPosArchivePoint;
        yPos = yPosArchivePoint;
        robotHP = 8;
        direction = Direction.NORTH;
    }

    public void receiveDamage(){
        robotHP--;
        if(robotHP<1){
            robotDestroyed();
        }
    }

    public void repairDamage(){
        if(robotHP<10) robotHP++;
    }

    public void robotDestroyed(){
        robotLives--;
        if(robotLives > 0){
            restoreRobot();
        }else{
            this.xPos = 1000;
            this.yPos = 1000;
            System.out.println(this.color + "actor died"); //don't know what to do with robot when it dies yet
            this.isDead = true;
        }
    }

    public void robotPowerDown(){
        robotHP = 10;
    }


    public void flagVisited(int flagNumber){
        if(flagNumber == flagsVisited+1){
            flagsVisited++;
            if(flagsVisited == 5){ //don't know how many flags are on the map, so this number must be changed to number of flags.
                System.out.println("player " + this + " wins!"); //not yet implemented what to do when win condition is reached.
            }
        }
    }
}
