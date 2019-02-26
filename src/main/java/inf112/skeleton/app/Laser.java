package inf112.skeleton.app;

public class Laser implements ILaser {
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
    public void setHole(boolean hole) {

    }
}
