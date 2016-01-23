package org.milev;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;


/**
 * Created by vmmilev on 1/23/16.
 */
public class Translate {

    /**
     * This is the main method which makes use of Translator.
     * @param args Unused.
     * @return Nothing.
     * @exception FileNotFoundException On File missing.
     * @see FileNotFoundException
     * @exception IOException On input error.
     * @see IOException
     */
    public static void main(String[] args) {
        Map<String, String> columnsMap;
        Map<String, String> rowsMap;

        if(args.length < 3) {
            System.out.println("Please specify input files!");
            System.out.println("Example: java -jar Translator-1.0.jar input.conf columns.conf rows.conf");
            System.out.println("The outpul will be writen in output.out");
            System.exit(-1);
        }

        try {
            // Create classes which will read the input and write to output
            TokensReader rowsReader = new TokensReader(args[2]);
            TokensReader columnsReader = new TokensReader(args[1]);
            TokensReader inputReader = new TokensReader(args[0]);
            TokensWriter outputWriter = new TokensWriter("output.out");

            // Create the maps which will be used for filtering and substitution
            columnsMap = columnsReader.fetchToMap();
            columnsReader.closeReader();

            rowsMap = rowsReader.fetchToMap();
            rowsReader.closeReader();

            // create two filters - one for columns which we will fetch
            // and one for rows
            RowFilter rowFilter = new RowFilter(rowsMap);
            ColumnFilter columnFilter = new ColumnFilter(columnsMap);

            // chain the filters
            columnFilter.chainFilter(rowFilter);

            // create translator which will use the filter to output the result
            Translator translator = new Translator(inputReader, columnFilter, outputWriter);

            // do the job
            translator.run();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Can't read from file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
