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

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
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
}
