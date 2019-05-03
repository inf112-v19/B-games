package inf112.skeleton.app;

import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.*;

import java.util.Random;

public class Prototyping {

    public static Board generateRandomBoard() {
        return generateRandomBoard(10, 10);
    }

    public static Board generateRandomBoard(int width, int height) {
        Board board = new Board(width, height);
        Random r = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int random = r.nextInt(30);
                ITile tile;
                switch (random) {
                    case 0:
                        tile = new Tile(new boolean[]{false, false, false, false}, new Conveyor(Direction.NORTH, false));
                        break;
                    case 1:
                        tile = new Tile(new boolean[]{false, false, false, false}, new Conveyor(Direction.SOUTH, false));
                        break;
                    case 2:
                        tile = new Tile(new boolean[]{false, false, false, false}, new Conveyor(Direction.WEST, false));
                        break;
                    case 3:
                        tile = new Tile(new boolean[]{false, false, false, false}, new Conveyor(Direction.EAST, false));
                        break;
                    case 4:
                        tile = new Laser(Direction.NORTH);
                        break;
                    case 5:
                        tile = new Laser(Direction.WEST);
                        break;
                    case 6:
                        tile = new Laser(Direction.EAST);
                        break;
                    case 7:
                        tile = new Laser(Direction.SOUTH);
                        break;
                    case 8:
                        tile = new Tile();
                        tile.setHole(true);
                        break;
                    case 9:
                        tile = new Tile(new boolean[]{false, false, false, false}, new Conveyor(Direction.NORTH, true));
                        break;
                    case 10:
                        tile = new Tile(new boolean[]{false, false, false, false}, new Conveyor(Direction.SOUTH, true));
                        break;
                    case 11:
                        tile = new Tile(new boolean[]{false, false, false, false}, new Conveyor(Direction.WEST, true));
                        break;
                    case 12:
                        tile = new Tile(new boolean[]{false, false, false, false}, new Conveyor(Direction.EAST, true));
                        break;
                    default:
                        tile = new Tile(new boolean[]{false, false, false, false});
                }
                board.setTile(x, y, tile);
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // TODO mirror walls
                ITile tile = board.getAt(x, y);
                if (y == 0) {
                    tile.setWall(Direction.SOUTH, true);
                }
                if (y == height - 1) {
                    tile.setWall(Direction.NORTH, true);
                }
                if (x == 0) {
                    tile.setWall(Direction.WEST, true);
                }
                if (x == width - 1) {
                    tile.setWall(Direction.EAST, true);
                }

                int wallChance = 8; // 1 / wallchance
                if (r.nextInt(wallChance) == 0) {
                    tile.setWall(Direction.NORTH, true);
                }
                if (r.nextInt(wallChance) == 0) {
                    tile.setWall(Direction.SOUTH, true);
                }
                if (r.nextInt(wallChance) == 0) {
                    tile.setWall(Direction.WEST, true);
                }
                if (r.nextInt(wallChance) == 0) {
                    tile.setWall(Direction.EAST, true);
                }
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ITile tile = board.getAt(x, y);
                if (tile instanceof Laser) {
                    tile.setWall(((Laser) tile).getLaser(), true);
                }
            }
        }




        return board;
    }
}