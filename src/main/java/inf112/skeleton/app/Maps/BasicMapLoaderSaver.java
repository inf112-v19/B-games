package inf112.skeleton.app.Maps;

import inf112.skeleton.app.Board;
import inf112.skeleton.app.IBoard;
import java.io.*;

import java.io.File;

public class BasicMapLoaderSaver implements IMapLoaderSaver {

    @Override
    public Board load(File map) {

        String line = null;

        try {
            FileReader fileReader = new FileReader(map);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int width = Integer.parseInt(bufferedReader.readLine());
            int height = Integer.parseInt(bufferedReader.readLine());
            Board board = new Board(width, height);

            while((line = bufferedReader.readLine()) != null) {
                if(line == "type:Tile"){

                } else if(line == "type:Laser"){

                } else {

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

        return null;
    }

    @Override
    public Boolean save(IBoard map, String fileName) {
        try {
            int width = map.getWidth();
            int height = map.getHeight();

            FileWriter fileWriter =
                    new FileWriter(fileName);

            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.

            //bufferedWriter.newLine();


            bufferedWriter.write(width + "\n" + height + "\n");
            //loop for Y coordinates
            for (int i = 0; i < height; i++){
                //loop for X coordinates
                for (int j = 0; j <width; j++){
                    bufferedWriter.write(map.getAt(i, j).toString());
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

    public Enum parseEnum(String input){
        return null;
    }

    /*
    Clunky way of parsing a series of bool values from a line from a saved map.
    Loops through the string until it finds a ':' and uses the next letter to decide it it should add true of false
    to the walls table.
    */
    public Boolean[] parseWalls(String input){
        Boolean[] walls = new Boolean[4];
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
    public Boolean parseBoolean(String input){
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
