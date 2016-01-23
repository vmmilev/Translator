package org.milev;

import java.util.Map;


/**
 * Created by vmmilev on 1/23/16.
 */
public class RowFilter implements CriteriaFilter {
    private Map<String, String> rowsMap;
    private Boolean firstCall;

    public RowFilter(Map<String, String> rowsMap) {
        this.rowsMap = rowsMap;
        this.firstCall = Boolean.TRUE;
    }

    /**
     * @param line to filter
     * @return filtered result
     */
    public String filter(String line) {
        // first line is colmn names so we don't handle it here
        if(firstCall) {
            firstCall = Boolean.FALSE;
            return line;
        }

        String[] tokens = line.split("\t");
        String token = rowsMap.get(tokens[0]);

        if(token == null)
            return null;

        StringBuilder result = new StringBuilder(token);
        for(int i=1;i<tokens.length;i++) {
            result.append("\t");
            result.append(tokens[i]);
        }

        return result.toString();
    }

    public Boolean chainFilter(CriteriaFilter filter) {
        // not needed for now
        return Boolean.TRUE;
    }
}
