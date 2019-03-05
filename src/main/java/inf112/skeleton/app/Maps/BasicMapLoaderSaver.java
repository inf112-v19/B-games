package inf112.skeleton.app.Maps;

import inf112.skeleton.app.Board;
import inf112.skeleton.app.IBoard;
import java.io.*;

import java.io.File;

public class BasicMapLoaderSaver implements IMapLoaderSaver {

    @Override
    public Board load(File map) {

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(map);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            map + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + map + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean save(IBoard map, File fileName) {
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            /*
            bufferedWriter.write("Hello there,");
            bufferedWriter.write(" here is some text.");
            bufferedWriter.newLine();
            bufferedWriter.write("We are writing");
            bufferedWriter.write(" the text to the file.");
            */



            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        return false;
    }
}
