package inf112.skeleton.app;

import java.util.Random;

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


    public void generateRandom(){
        Random r = new Random();
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                int random = r.nextInt(10);
                switch (random){
                    case 0:
                        tiles[x][y] = new Tile(new boolean[]{}, Facing.NORTH);
                        break;
                    case 1:
                        tiles[x][y] = new Tile(new boolean[]{}, Facing.SOUTH);
                        break;
                    case 2:
                        tiles[x][y] = new Tile(new boolean[]{}, Facing.WEST);
                        break;
                    case 3:
                        tiles[x][y] = new Tile(new boolean[]{}, Facing.EAST);
                        break;
                        default:
                            tiles[x][y] = new Tile();

                }
            }
        }
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
