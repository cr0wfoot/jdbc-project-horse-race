/**
 * *********************************************************************************
 * RateDAO: Horse.java Author: Hrytsiuk Purpose: Defines the Interface RateDAO
 * *********************************************************************************
 */
package controller.dao;

import java.util.List;
import model.entities.Rate;
import model.entities.search.criteria.RateSearchCriteria;

/**
 * Basic interface to work with objects of class Rate
 * @see Rate
 */
public interface RateDAO {
    
    /**
     * Save the object of class Rate
     * @param rate object of class Rate
     */
    void insert(Rate rate);
    
    Rate select(int id);
    
    /**
     * Get all objects of class Rate
     * @return the list of objects
     */
    List<Rate> selectAll();
    
    /**
     * Delete the object of class Rate by it's id
     * @param id the int value of id
     */
    void delete(int id);
    
    List<Rate> findByCriteria(RateSearchCriteria criteria);
    
}
