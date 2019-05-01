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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int random = r.nextInt(14);
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
                        tile = new Tile(new boolean[]{false, false, false, false}, new Conveyor(Direction.EAST, true));
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
                    default:
                        int wallChance = 8; // 1 / wallchance
                        tile = new Tile(new boolean[]{r.nextInt(wallChance) == 0, r.nextInt(wallChance) == 0, r.nextInt(wallChance) == 0, r.nextInt(wallChance) == 0});
                }
                board.setTile(x, y, tile);
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // TODO mirror walls
            }
        }

        return board;
    }
}