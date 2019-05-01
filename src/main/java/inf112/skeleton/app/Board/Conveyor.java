package inf112.skeleton.app.Board;

import inf112.skeleton.app.Actor.Direction;

public class Conveyor {

    public Direction direction;
    public boolean fast;

    public Conveyor(Direction direction, boolean fast) {
        this.direction = direction;
        this.fast = fast;
    }

    @Override
    public String toString() {
        return "direction:" + direction.toString() + " fast:" + fast;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Conveyor)) {
            return false;
        }
        Conveyor conveyor = (Conveyor) obj;
        if (conveyor.fast != this.fast) return false;
        if (conveyor.direction != this.direction) return false;
        return true;
    }
}
