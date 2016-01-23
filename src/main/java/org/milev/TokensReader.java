package org.milev;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by vmmilev on 1/23/16.
 */
public class TokensReader extends BaseReader {
    private Map<String, String> map;

    public TokensReader(String file) throws FileNotFoundException {
        super(file);
    }

    /**
     * This is the function which creates HashTables with the rows and columns to fetch.
     * @return HashTable with values which we need to substitute in the filter
     */
    Map<String, String> fetchToMap() throws IOException {
        map = new HashMap<String, String>();

        String[] tokens;
        while((tokens =readRow()) != null) {
            map.put(tokens[0], tokens[1]);
        }

        return map;
    }
}
