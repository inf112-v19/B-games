package inf112.skeleton.app.Board;

import inf112.skeleton.app.Actor.Direction;

public class Tile implements ITile {
    private boolean isHole;
    private boolean[] walls;
    private Direction conveyor;
    private RotationDirection cog;
    private Item item;
    private ITile linkedNorth;
    private ITile linkedEast;
    private ITile linkedSouth;
    private ITile linkedWest;

    public Tile(){
        this.isHole = false;
        this.walls = new boolean[]{false, false, false, false};
    }

    public Tile(boolean[] walls){
        this.isHole = false;
        this.walls = walls;
    }

    public Tile(boolean[] walls, Direction conveyor){
        this.isHole = false;
        this.walls = walls;
        this.conveyor = conveyor;
    }

    public Tile(boolean[] walls, RotationDirection cog){
        this.isHole = false;
        this.walls = walls;
        this.cog = cog;
    }

    public Tile(boolean isHole, boolean[] walls, Direction conveyor, RotationDirection cog, Item item){
        this.isHole = isHole;
        this.walls = walls;
        this.conveyor = conveyor;
        this.cog = cog;
        this.item =item;
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
        return isHole;
    }

    public void setHole(boolean hole) {isHole = hole;}

    @Override
    public Direction hasConveyor() {
        return conveyor;
    }

    @Override
    public RotationDirection hasCog() {
        return cog;
    }

    @Override
    public void setItem(Item item){
        this.item = item;
    }

    @Override
    public Item getItem(){
        return item;
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
    public String toString(){
        String returnString = "type:Tile\n";
        returnString += "isHole:" + isHole + "\n";
        returnString += "walls:North:" + walls[0] + " East:" + walls[1] + " South:" + walls[2] + " West:" + walls[3] + "\n";
        returnString += "conveyor:" + conveyor + "\n";
        returnString += "cog:" + cog + "\n";
        returnString += "item:" + item + "\n";
        return returnString;
    }
}
