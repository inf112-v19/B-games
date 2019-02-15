package inf112.skeleton.app;

public class Board implements IBoard {

    private ITile[][] tiles;
    private int width;
    private int height;

    public Board(){
        this(10,10);
    }

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        tiles = new ITile[width][height];
    }


    @Override
    public ITile getAt(int x, int y) {
        return tiles[x][y];
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
