package inf112.skeleton.app;

public class Tile implements ITile {
    private boolean isHole;
    private boolean[] walls;

    public Tile(){
        this.isHole = false;
        this.walls = new boolean[]{false, false, false, false};
    }

    public Tile(boolean[] walls){
        this.isHole = false;
        this.walls = walls;
    }

    @Override
    public boolean hasWall(Facing direction) {
        if (direction == Facing.NORTH){
            return walls[0];
        } else if (direction == Facing.EAST) {
            return walls[1];
        } else if (direction == Facing.SOUTH){
            return walls[2];
        } else {
            return walls[3];
        }
    }

    @Override
    public boolean isHole() {
        return isHole;
    }
}
