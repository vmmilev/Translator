package org.milev;

import java.io.IOException;


/**
 * Created by vmmilev on 1/23/16.
 */
public class Translator {
    private TokensReader reader;
    private TokensWriter writer;
    private CriteriaFilter filter;

    public Translator(TokensReader reader, CriteriaFilter filter, TokensWriter writer) {
        this.reader = reader;
        this.writer = writer;
        this.filter = filter;
    }

    void run() throws IOException {
        String line;

        while ((line = reader.readLine()) != null) {
            // filter the columns which we don't need
            line = filter.filter(line);

            // write the line to the output
            if(line != null)
                writer.writeLine(line);
        }

        reader.closeReader();
        writer.closeWriter();
    }
}
