package inf112.skeleton.app.Actor;

public class DirectionHelpers {

    public static float rotationFromDirection(Direction direction) {
        switch (direction) {
            case NORTH:
                return 0;
            case EAST:
                return 270;
            case SOUTH:
                return 180;
            case WEST:
                return 90;
            default:
                return 0;
        }
    }

    public static Direction reverse(Direction direction) {
        switch (direction) {
            case NORTH:
                return Direction.SOUTH;
            case EAST:
                return Direction.WEST;
            case SOUTH:
                return Direction.NORTH;
            case WEST:
                return Direction.EAST;
            default:
                return Direction.NORTH;
        }
    }

    public static Direction rotateClockwise(Direction direction) {
        switch (direction) {
            case NORTH:
                return Direction.EAST;
            case EAST:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.WEST;
            case WEST:
                return Direction.NORTH;
            default:
                return Direction.NORTH;
        }
    }

    public static Direction rotateAntiClockwise(Direction direction) {
        switch (direction) {
            case NORTH:
                return Direction.WEST;
            case EAST:
                return Direction.NORTH;
            case SOUTH:
                return Direction.EAST;
            case WEST:
                return Direction.SOUTH;
            default:
                return Direction.NORTH;
        }
    }
}
