package inf112.skeleton.app.Board;

import inf112.skeleton.app.Actor.Direction;

import java.util.Random;

public class Board implements IBoard {
    /*
    This board keeps width x height amount of objects that implement ITile.
    Functions that use coordinates should assume that x=0 y=0 is the south-west corner tile.
    TODO make sure this works properly in map generation and graphics.
     */

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
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                tiles[x][y] = new Tile();
            }
        }
        for (int x = 0; x < width; x++){
            for (int y = 0; y < width; y++){
                tiles[x][y].setLinked(Direction.NORTH, tiles[x][y+1]);
                tiles[x][y].setLinked(Direction.EAST, tiles[x+1][y]);
                tiles[x][y].setLinked(Direction.SOUTH, tiles[x][y-1]);
                tiles[x][y].setLinked(Direction.WEST, tiles[x-1][y]);
            }
        }
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

        //copying all the previous tile's links to the new one
        tile.setLinked(Direction.NORTH, tiles[x][y].getLinked(Direction.NORTH));
        tile.setLinked(Direction.EAST, tiles[x][y].getLinked(Direction.EAST));
        tile.setLinked(Direction.SOUTH, tiles[x][y].getLinked(Direction.SOUTH));
        tile.setLinked(Direction.WEST, tiles[x][y].getLinked(Direction.WEST));
        //linking the new tile to all it's linked tiles
        tile.getLinked(Direction.NORTH).setLinked(Direction.SOUTH, tile);
        tile.getLinked(Direction.EAST).setLinked(Direction.WEST, tile);
        tile.getLinked(Direction.SOUTH).setLinked(Direction.NORTH, tile);
        tile.getLinked(Direction.WEST).setLinked(Direction.EAST, tile);

        tiles[x][y] = tile;
    }
}