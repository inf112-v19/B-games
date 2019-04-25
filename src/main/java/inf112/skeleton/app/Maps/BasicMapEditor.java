package inf112.skeleton.app.Maps;

import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.IBoard;

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
                     map = load(in.next());
                     break;
                 case 's':
                     System.out.print("write the name you want to save the file as\n");
                     //System.out.print("successful save:" + loaderSaver.save(map, "maps/" + in.next() + ".txt"));
                     System.out.print("successful save:" + loaderSaver.save(map, "maps/map.txt") + "\n");
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

    private static IBoard create(){
        return new Board();
    }

    private static IBoard load(String filename){
        return null;
    }

    private static void save(IBoard map, String filename){

    }

    private static IBoard edit(IBoard map, Scanner in){
        boolean edit = true;
        MapPointer pointer = new MapPointer(map);
        while (edit){
            for(int y = map.getHeight()-1; y >= 0; y--){
                for(int x = 0; x < map.getWidth(); x++){
                    if(pointer.getX() == x && pointer.getY() == y){
                        System.out.print("[X]");
                    } else {
                        System.out.print("[ ]");continue;
                    }
                }
                System.out.print("\n");
            }
            edit = false;
        }
        return null;
    }
}
