package org.milev;

import java.util.AbstractList;
import java.util.Map;
import java.util.Vector;

/**
 * Created by vmmilev on 1/23/16.
 */
public class ColumnFilter implements CriteriaFilter {
    private Map<String, String> columnsMap;
    private Boolean firstCall;
    private AbstractList<Integer> columns;
    private CriteriaFilter chainedFilter;

    public ColumnFilter(Map<String, String> columnsMap) {
        this.columnsMap = columnsMap;
        this.firstCall = Boolean.TRUE;
        this.columns = new Vector<Integer>();
        this.chainedFilter = null;
    }

    /**
     * @param line to filter
     * @return filtered result
     */
    public String filter(String line) {
        StringBuilder result = new StringBuilder("");
        String[] tokens = line.split("\t");

        // on the first call remember the columns which we need filtered
        if(firstCall) {
            int tokenNum = 0;
            for(String token : tokens) {
                String column = columnsMap.get(token);
                if(column != null) {
                    columns.add(tokenNum);
                    if(result.length() > 0) {
                        result.append("\t");
                    }
                    result.append(column);
                }

                tokenNum++;
            }
            firstCall = Boolean.FALSE;
        } else { // just remove columns which we don't need
            for(Integer i : columns) {
                if(result.length() > 0) {
                    result.append("\t");
                }
                result.append(tokens[i]);
            }
        }

        // return null if this row is filtered, else return the row
        return (chainedFilter != null) ? chainedFilter.filter(result.toString()) : result.toString();
    }

    /**
     * @param filter to chain
     * @return TRUE on success
     */
    public Boolean chainFilter(CriteriaFilter filter) {
        this.chainedFilter = filter;
        return Boolean.TRUE;
    }
}
