package inf112.skeleton.app.Maps;

import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.IBoard;
import inf112.skeleton.app.Board.ITile;

public class MapPointer {
    private int x = 0;
    private int y = 0;
    private ITile tile;
    private IBoard board;

    /**
     * This class is a pointer meant to be used in map editing.
     * It keeps a reference to the board it's editing, as well as a tile on that board
     * that it's currently pointing to and the coordinates of that tile.
     *
     * The direct editing of the board is meant to go through this class though it does not need to.
     * To edit the board use the setTile(ITile tile) function to place a tile where the pointer is pointing,
     * and the move(Direction direction) function to change what tile the pointer is pointing to.
     *
     * @param board the board the pointer should point to.
     */
    public MapPointer(IBoard board){
        this.tile = board.getAt(x, y);
        this.board = board;
    }

    /**
     * moves the pointer on the board.
     * prints error message if it can't move in the specified direction.
     *
     * NOTE: this does not affect the board in any way.
     *
     * @param direction the direction to move the pointer
     */
    public void move(Direction direction){
        switch (direction){
            case NORTH:
                if(tile.getLinked(Direction.NORTH) != null) {
                    y++;
                    tile = tile.getLinked(Direction.NORTH);
                } else {moveError();}
                break;
            case EAST:
                if(tile.getLinked(Direction.EAST) != null) {
                    x++;
                    tile = tile.getLinked(Direction.EAST);
                } else {moveError();}
                break;
            case SOUTH:
                if(tile.getLinked(Direction.SOUTH) != null) {
                    y--;
                    tile = tile.getLinked(Direction.SOUTH);
                } else {moveError();}
                break;
            case WEST:
                if(tile.getLinked(Direction.WEST) != null) {
                    x--;
                    tile = tile.getLinked(Direction.WEST);
                } else {moveError();}
                break;
        }
    }

    /**
     * overrides the tile on the board in the pointer's current position.
     *
     * NOTE: this overrides a tile on the linked board and not just in the pointer.
     *
     * @param tile the tile you want to place on the board
     */
    public void setTile(ITile tile) {
        this.tile = tile;
        board.setTile(x, y, tile);
    }

    public ITile getTile() {
        return tile;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    //sends move error message through some UI
    private static void moveError(){
        System.out.print("Illegal move");
    }

    /**
     * Function to be used for testing only.
     *
     * @return the board the pointer is on
     */
    public IBoard getBoard() {
        return board;
    }

    /**
     * Function to be used for testing only.
     *
     * @param board
     */
    public void setBoard(IBoard board) {
        this.board = board;
    }
}
