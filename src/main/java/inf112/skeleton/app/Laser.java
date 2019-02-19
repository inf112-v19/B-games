package inf112.skeleton.app;

public class Laser implements ILaser {
    private Facing laserDirection;

    public Laser(Facing laserDirection){
        this.laserDirection = laserDirection;
    }

    @Override
    public Facing getLaser() {
        return laserDirection;
    }

    @Override
    public boolean hasWall(Facing direction) {
        return true;
    }

    @Override
    public boolean isHole() {
        return false;
    }
}
