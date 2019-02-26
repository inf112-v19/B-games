package inf112.skeleton.app;

public class Wrench implements ITile {

    private boolean[] walls;

    public Wrench(boolean[] walls){
        this.walls = walls;
    }

    @Override
    public boolean hasWall(Direction direction) {
        if (direction == Direction.NORTH){
            return walls[0];
        } else if (direction == Direction.EAST) {
            return walls[1];
        } else if (direction == Direction.SOUTH){
            return walls[2];
        } else {
            return walls[3];
        }
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
    public void setHole(boolean hole) {

    }
}
