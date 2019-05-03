package inf112.skeleton.app.Maps;

import com.badlogic.gdx.utils.Json;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Board.*;

import java.io.*;

public class BasicMapLoaderSaver implements IMapLoaderSaver {

    @Override
    public Board load(String map) {

        String line; //keeps the most recent line from the map file when needed
        Board board;

        /*
        This function goes through (hopefully) every line in the text file of a saved map.
        Every call of bufferedReader.readLine() reads a new line from the text file
         */
        try {
            FileReader fileReader = new FileReader(map);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //The two first lines of the text file is the width and height of the map
            int width = Integer.parseInt(bufferedReader.readLine());
            int height = Integer.parseInt(bufferedReader.readLine());
            board = new Board(width, height);

            /*
            After the first two lines comes the information about the tiles like this:

            ...
            type:<type>
            variable:<value>
            ...

            for example a laser might look like this:

            ...
            type:Laser
            laserDirection:Direction.NORTH
            ...

            the function reads the line with the type and then read as many line as it expects that type
            of tile to have with relevant information.
            And then is reads a new type
             */
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    line = bufferedReader.readLine();
                    if(line.equals("type:Tile")){
                        board.setTile(j, i, new Tile(parseBoolean(bufferedReader.readLine()),
                                parseWalls(bufferedReader.readLine()),
                                parseConveyor(bufferedReader.readLine()),
                                (RotationDirection)parseEnum(bufferedReader.readLine()),
                                (Item)parseEnum(bufferedReader.readLine())));
                    } else if(line.equals("type:Laser")){
                        board.setTile(j, i, new Laser((Direction)parseEnum(bufferedReader.readLine())));
                    }
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + map + "'");
            return null;
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + map + "'");
            return null;
        }

        return board;
    }

    private Conveyor parseConveyor(String readLine) {
        String[] lines = readLine.split(" ");
        if (lines[0].substring(9).startsWith("nul")) {
            return null;
        }
        Direction direction = Direction.valueOf(lines[0].substring(19));
        boolean fast = Boolean.parseBoolean(lines[1].substring(5));
        if (direction == null) return null;
        return new Conveyor(direction, fast);
    }

    //simple function to write a board to a text file
    @Override
    public Boolean save(IBoard map, String fileName) {
        try {
            int width = map.getWidth();
            int height = map.getHeight();

            FileWriter fileWriter =
                    new FileWriter(fileName);

            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            bufferedWriter.write(width + "\n" + height + "\n"); //the first two lines is writes are the width and height of the board
            //loop for Y coordinates
            for (int i = 0; i < height; i++){
                //loop for X coordinates
                for (int j = 0; j <width; j++){
                    bufferedWriter.write(map.getAt(j, i).toString());
                }
            }

            bufferedWriter.close();
            return true;
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
            return false;
        }
    }

    /*
    Clunky way of parsing an Enum from a line from a saved map.
    It first checks what type of enum it needs to parse by looking at the first 3 letters,
    then it looks at an identifying letter in the Enum value.
    If no Enum is found it returns null.
     */
    public Enum parseEnum(String input){
        if(input.substring(0,3).equals("con")){
            if(input.charAt(9) == 'N'){
                return Direction.NORTH;
            } else if(input.charAt(9) == 'E'){
                return Direction.EAST;
            } else if(input.charAt(9) == 'S'){
                return Direction.SOUTH;
            } else if(input.charAt(9) == 'W'){
                return Direction.WEST;
            }
        } else if(input.substring(0,3).equals("cog")){
            if(input.charAt(5) == 'L'){
                return RotationDirection.CLOCKWISE;
            } else if(input.charAt(5) == 'O'){
                return RotationDirection.COUNTERCLOCKWISE;
            }
        } else if(input.substring(0,3).equals("ite")){
            if(input.charAt(5) == 'W'){
                return Item.WRENCH;
            }
        } else if(input.substring(0,3).equals("las")){
            if(input.charAt(15) == 'N'){
                return Direction.NORTH;
            } else if(input.charAt(15) == 'E'){
                return Direction.EAST;
            } else if(input.charAt(15) == 'S'){
                return Direction.SOUTH;
            } else if(input.charAt(15) == 'W'){
                return Direction.WEST;
            }
        }
        return null;
    }

    /*
    Clunky way of parsing a series of bool values from a line from a saved map.
    Loops through the string until it finds a ':' and uses the next letter to decide it it should add true of false
    to the walls table.
    */
    public boolean[] parseWalls(String input){
        boolean[] walls = new boolean[4];
        int counter = 0;
        for(int i = 10; i < input.length(); i++){
            if(input.charAt(i) == ':'){
                if(input.charAt(i+1) == 't'){
                    walls[counter++] = true;
                } else {
                    walls[counter++] = false;
                }
            }
        }
        return walls;
    }

    /*
    Clunky way of parsing a bool value from a line on a saved map.
    For the time being the only saved bool value is isHole, and it returns false if it doesn't
    recognize that specific substring.
     */
    public boolean parseBoolean(String input){
        if(input.substring(0, 6).equals("isHole")){
            if(input.charAt(7) == 't'){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
