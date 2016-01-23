package org.milev;

/**
 * Created by vmmilev on 1/23/16.
 */
public interface CriteriaFilter {
    public String filter(String line);
    public Boolean chainFilter(CriteriaFilter filter);
}
