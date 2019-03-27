package inf112.skeleton.app.Maps;

import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Board.IBoard;

public interface IMapLoaderSaver {
    /**
     * The MapLoaderSaver reads and writes information about objects using IBoard to and from text files.
     * It can save any map that used the IBoard interface but returns only Board objects.
     */

    /**
     * Function to read from a text file and construct a Board object from it.
     * Returns null and prints an error message if it couldn't read from the specified file
     *
     * @param map the file location of the map to be loaded
     * @return a map constructed from the file
     */
    public Board load(String map);

    /**
     * Function to write a map to a text file with a specified location.
     * Returns false and prints an error message if it couldn't write to the specified file
     *
     * @param map any map using the IBoard interface you want to save as a text file
     * @param fileName the file location you want to save the map to
     * @return true if the save was successful, and false if it wasn't
     */
    public Boolean save(IBoard map, String fileName);
}
