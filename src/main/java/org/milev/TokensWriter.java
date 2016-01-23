package org.milev;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by vmmilev on 1/23/16.
 */
public class TokensWriter {
    private String file;
    BufferedWriter bw;

    public TokensWriter(String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        bw = new BufferedWriter(fw);
    }

    /**
     * @param line to write
     * @return TRUE on success
     */
    Boolean writeLine(String line) throws IOException {
        bw.write(line);
        bw.newLine();
        return Boolean.TRUE;
    }

    public void closeWriter() throws IOException {
        bw.close();
    }
}
