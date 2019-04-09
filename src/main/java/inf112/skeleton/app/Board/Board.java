package inf112.skeleton.app.Board;

import inf112.skeleton.app.Actor.Direction;

import java.util.Random;

public class Board implements IBoard {

    public ITile[][] tiles;
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

    //TODO this function mixes up X and Y, needs to be fixed if we are to keep it.
    public void generateRandom(){
        Random r = new Random();
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                int random = r.nextInt(10);
                switch (random){
                    case 0:
                        tiles[x][y] = new Tile(new boolean[]{false, false, false, false}, Direction.NORTH);
                        break;
                    case 1:
                        tiles[x][y] = new Tile(new boolean[]{false, false, false, false}, Direction.SOUTH);
                        break;
                    case 2:
                        tiles[x][y] = new Tile(new boolean[]{false, false, false, false}, Direction.WEST);
                        break;
                    case 3:
                        tiles[x][y] = new Tile(new boolean[]{false, false, false, false}, Direction.EAST);
                        break;
                        default:
                            tiles[x][y] = new Tile();
                }
            }
        }
    }


    @Override
    public ITile getAt(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height){
            return null;
        }
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

    @Override
    public void setTile(int x, int y, ITile tile){
        if(x < 0 || x > width){
            throw new IndexOutOfBoundsException();
        }
        if(y < 0 || y > height){
            throw new IndexOutOfBoundsException();
        }
        tiles[x][y] = tile;
    }
}