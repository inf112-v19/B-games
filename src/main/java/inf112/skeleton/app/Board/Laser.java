package inf112.skeleton.app.Board;

import inf112.skeleton.app.Actor.Direction;

public class Laser implements ILaser {
    private Direction laserDirection;
    private ITile linkedNorth;
    private ITile linkedEast;
    private ITile linkedSouth;
    private ITile linkedWest;

    public Laser(Direction laserDirection){
        this.laserDirection = laserDirection;
    }

    @Override
    public Direction getLaser() {
        return laserDirection;
    }

    @Override
    public boolean hasWall(Direction direction) {
        return true;
    }

    @Override
    public boolean isHole() {
        return false;
    }

    @Override
    public Direction hasConveyor() {
        return null;
    }

    @Override
    public RotationDirection hasCog() {
        return null;
    }

    @Override
    public void setItem(Item item) {
        //can't set item on laser-tile
    }

    @Override
    public void setHole(boolean hole) {

    }

    @Override
    public void setLinked(Direction direction, ITile tile) {
        switch (direction){
            case NORTH:
                linkedNorth = tile;
            case EAST:
                linkedEast = tile;
            case SOUTH:
                linkedSouth = tile;
            case WEST:
                linkedWest = tile;
        }
    }

    @Override
    public ITile getLinked(Direction direction) {
        switch (direction) {
            case NORTH:
                return linkedNorth;
            case EAST:
                return linkedEast;
            case SOUTH:
                return linkedSouth;
            case WEST:
                return linkedWest;
        }
        return null;
    }

    @Override
    public Item getItem() {
        return null;
    }

    public String toString(){
        String returnString = "type:Laser\n";
        returnString += "laserDirection:" + laserDirection + "\n";
        return returnString;
    }
}
