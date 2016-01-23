package org.milev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by vmmilev on 1/23/16.
 * Thss class provides basic functionality to read tab separates tokens from file
 */
public class BaseReader {

    private FileReader reader;
    private BufferedReader bufferedReader;

    /**
     * @param file to read from
     * @throws FileNotFoundException
     * @see FileNotFoundException
     */
    public BaseReader(String file) throws FileNotFoundException {
        reader = new FileReader(file);
        bufferedReader = new BufferedReader(reader);
    }

    /**
     * Read line from the file and returns tab separated tokens
     * @return tokens as array of Strings
     * @throws IOException
     * @see IOException
     */
    String[] readRow() throws IOException {
        String line = bufferedReader.readLine();
        return line != null ? line.split("\t") : null;
    }

    /**
     * Read line from file as String
     * @return line
     * @throws IOException
     * @see IOException
     */
    String readLine() throws IOException {
        return bufferedReader.readLine();
    }

    public void closeReader() throws IOException {
        bufferedReader.close();
    }
}
