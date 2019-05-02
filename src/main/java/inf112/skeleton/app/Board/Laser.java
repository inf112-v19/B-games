package inf112.skeleton.app.Board;

import inf112.skeleton.app.Actor.Direction;

public class Laser extends Tile implements ILaser {
    private Direction laserDirection;

    public Laser(Direction laserDirection){
        this.laserDirection = laserDirection;
    }

    @Override
    public Direction getLaser() {
        return laserDirection;
    }

    @Override
    public boolean hasWall(Direction direction) {
        if(direction == laserDirection){
            return true;
        }
        return super.hasWall(direction);
    }

    @Override
    public boolean isHole() {
        return false;
    }

    @Override
    public Conveyor hasConveyor() {
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
    public Item getItem() {
        return null;
    }

    @Override
    public String toString(){
        String returnString = "type:Laser\n";
        returnString += "laserDirection:" + laserDirection + "\n";
        return returnString;
    }
}
