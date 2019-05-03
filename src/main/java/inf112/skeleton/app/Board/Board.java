package inf112.skeleton.app.Board;

import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.gui.GameScreen;

public class Board implements IBoard {
    /*
    This board keeps width x height amount of objects that implement ITile.
    Functions that use coordinates should assume that x=0 y=0 is the south-west corner tile.
    TODO make sure this works properly in map generation and graphics.
     */

    public ITile[][] tiles;
    private int width;
    private int height;

    public Board() {
        this(10, 10);
    }

    public Board(int width, int height) {
        // Create a width*height array of empty tiles
        this.width = width;
        this.height = height;
        tiles = new ITile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile();
            }
        }

        // Initialize links
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x > 0) {
                    tiles[x][y].setLinked(Direction.WEST, tiles[x - 1][y]);
                }
                if (x < width - 1) {
                    tiles[x][y].setLinked(Direction.EAST, tiles[x + 1][y]);
                }
                if (y > 0) {
                    tiles[x][y].setLinked(Direction.SOUTH, tiles[x][y - 1]);
                }
                if (y < height - 1) {
                    tiles[x][y].setLinked(Direction.NORTH, tiles[x][y + 1]);
                }
            }
        }
    }

    @Override
    public ITile getAt(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
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
    public void setTile(int x, int y, ITile tile) {
        if (x < 0 || x > width) {
            throw new IndexOutOfBoundsException();
        }
        if (y < 0 || y > height) {
            throw new IndexOutOfBoundsException();
        }

        if (x > 0) {
            tile.setLinked(Direction.WEST, tiles[x][y].getLinked(Direction.WEST));
            tile.getLinked(Direction.WEST).setLinked(Direction.EAST, tile);
            tile.getLinked(Direction.WEST).setWall(Direction.EAST, tile.hasWall(Direction.WEST));
        }
        if (x < width - 1) {
            tile.setLinked(Direction.EAST, tiles[x][y].getLinked(Direction.EAST));
            tile.getLinked(Direction.EAST).setLinked(Direction.WEST, tile);
            tile.getLinked(Direction.EAST).setWall(Direction.WEST, tile.hasWall(Direction.EAST));
        }
        if (y > 0) {
            tile.setLinked(Direction.SOUTH, tiles[x][y].getLinked(Direction.SOUTH));
            tile.getLinked(Direction.SOUTH).setLinked(Direction.NORTH, tile);
            tile.getLinked(Direction.SOUTH).setWall(Direction.NORTH, tile.hasWall(Direction.SOUTH));
        }
        if (y < height - 1) {
            tile.setLinked(Direction.NORTH, tiles[x][y].getLinked(Direction.NORTH));
            tile.getLinked(Direction.NORTH).setLinked(Direction.SOUTH, tile);
            tile.getLinked(Direction.NORTH).setWall(Direction.SOUTH, tile.hasWall(Direction.NORTH));
        }

        tiles[x][y] = tile;
    }
}