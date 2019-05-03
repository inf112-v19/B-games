package inf112.skeleton.app.Maps;

import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.*;
import org.lwjgl.Sys;

import javax.swing.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BasicMapEditor {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        IMapLoaderSaver loaderSaver = new BasicMapLoaderSaver();
        String input;
        IBoard map = new Board();
        boolean quit = false;
        int x;
        int y;
        while(!quit){
             System.out.print(
                     "Basic map editor for B-Games roborally\n\n" +
                             " commands:\n" +
                             " -Create - creates a new blank map\n" +
                             " -Load - loads a map from file\n" +
                             " -Save - saves the current map to a file\n" +
                             " -Edit - start editing current map\n" +
                             " -Quit - exit the current operation\n"
             );
             input = in.next();
             switch (input.charAt(0)){
                 case 'c':
                     System.out.print("what length?: ");
                     x = Integer.parseInt(in.next());
                     System.out.print("what height?: ");
                     y = Integer.parseInt(in.next());
                     System.out.print("\n");
                     map = new Board(x, y);
                     break;
                 case 'l':
                     System.out.print("write the name of the file you want to load\n");
                     map =loaderSaver.load("maps/" + in.next() + ".txt");
                     if(map == null){
                         System.out.println("load unsuccessful");
                     }  else {
                         System.out.println("load successful");
                     }
                     break;
                 case 's':
                     System.out.print("write the name you want to save the file as\n");
                     if(loaderSaver.save(map, "maps/" + in.next() + ".txt")){
                         System.out.println("save successful");
                     }  else {
                         System.out.println("save unsuccessful");
                     }
                     break;
                 case 'e':
                     map = edit(map, in);
                     break;
                 case 'q':
                     quit = true;
                     break;
             }
        }
        in.close();
    }

    private static IBoard edit(IBoard map, Scanner in){
        boolean edit = true;
        String input;
        MapPointer pointer = new MapPointer(map);
        //map editing loop
        while (edit){
            for(int y = map.getHeight()-1; y >= 0; y--){
                for(int x = 0; x < map.getWidth(); x++){
                    if(pointer.getX() == x && pointer.getY() == y){
                        System.out.print("[X]");
                    } else if(map.getAt(x, y).isHole()) {
                        System.out.print("[O]");
                    } else if(map.getAt(x, y).hasConveyor() != null){
                        switch (map.getAt(x, y).hasConveyor().direction){
                            case NORTH:
                                System.out.print("[^]");
                                break;
                            case EAST:
                                System.out.print("[>]");
                                break;
                            case WEST:
                                System.out.print("[<]");
                                break;
                            case SOUTH:
                                System.out.print("[7]");
                                break;
                        }
                    } else if(map.getAt(x, y).hasCog()!= null){
                        System.out.print("[ø]");
                    } else {
                        System.out.print("[ ]");
                    }
                }
                System.out.print("\n");
            }
            System.out.println("WAST - move, e - edit, g - get, q - exit");
            input = in.next();
            switch (input.charAt(0)){
                case ('q'):
                    edit = false;
                    break;
                case ('g'):
                    System.out.println(pointer.getTile().toString());
                    break;
                case ('w'):
                    pointer.move(Direction.NORTH);
                    break;
                case ('a'):
                    pointer.move(Direction.WEST);
                    break;
                case ('s'):
                    pointer.move(Direction.SOUTH);
                    break;
                case ('d'):
                    pointer.move(Direction.EAST);
                    break;
                case ('e'):
                    System.out.println("r - conveyor\nc - cog\nh - hole\nl - laser\nw - wall");
                    input = in.next();
                    switch (input.charAt(0)){
                        case ('r'):
                            System.out.println("Direction?");
                            input = in.next();
                            switch (input.charAt(0)){
                                case ('n'):
                                    pointer.getTile().setConveyor(new Conveyor(Direction.NORTH, false));
                                    break;
                                case ('e'):
                                    pointer.getTile().setConveyor(new Conveyor(Direction.EAST, false));
                                    break;
                                case ('s'):
                                    pointer.getTile().setConveyor(new Conveyor(Direction.SOUTH, false));
                                    break;
                                case ('w'):
                                    pointer.getTile().setConveyor(new Conveyor(Direction.WEST,false));
                                    break;
                            }
                            break;
                        case ('c'):
                            System.out.println("Direction? clockwise, counterclockwise");
                            input = in.next();
                            switch (input.substring(0, 2)){
                                case ("cl"):
                                    pointer.getTile().setCogwheel(RotationDirection.CLOCKWISE);
                                    break;
                                case ("co"):
                                    pointer.getTile().setCogwheel(RotationDirection.COUNTERCLOCKWISE);
                                    break;
                            }
                            break;
                        case ('h'):
                            pointer.getTile().setHole(true);
                            break;
                        case ('l'):
                            System.out.println("Direction?");
                            input = in.next();
                            switch (input.charAt(0)){
                                case ('n'):
                                    pointer.setTile(new Laser(Direction.NORTH));
                                    break;
                                case ('e'):
                                    pointer.setTile(new Laser(Direction.EAST));
                                    break;
                                case ('s'):
                                    pointer.setTile(new Laser(Direction.SOUTH));
                                    break;
                                case ('w'):
                                    pointer.setTile(new Laser(Direction.WEST));
                                    break;
                            }
                            break;
                        case ('w'):
                            System.out.println("Direction?");
                            input = in.next();
                            switch (input.charAt(0)){
                                case ('n'):
                                    pointer.getTile().setWall(Direction.NORTH, true);
                                    break;
                                case ('e'):
                                    pointer.getTile().setWall(Direction.EAST, true);
                                    break;
                                case ('s'):
                                    pointer.getTile().setWall(Direction.SOUTH, true);
                                    break;
                                case ('w'):
                                    pointer.getTile().setWall(Direction.WEST, true);
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
        return map;
    }
}
