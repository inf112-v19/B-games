package inf112.skeleton.app;

import java.io.File;

public interface IMapLoaderSaver {
    /**
     * The MapLoaderSaver reads and writes information about objects using IBoard to and from text files.
     * It can save any map that used the IBoard interface but returns only Board objects.
     */

    /**
     * Function to read from a text file and construct a Board object from it
     *
     * @param map the file location of the map to be loaded
     * @return a map constructed from the file
     */
    public Board load(File map);

    /**
     * Function to write a map to a text file with a specified location
     *
     * @param map any map using the IBoard interface you want to save as a text file
     * @param location the file location you want to save the map to
     */
    public void save(IBoard map, File location);
}
