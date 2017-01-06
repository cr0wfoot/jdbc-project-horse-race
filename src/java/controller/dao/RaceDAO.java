/**
 * *********************************************************************************
 * RaceDAO: Horse.java Author: Hrytsiuk Purpose: Defines the Interface RaceDAO
 * *********************************************************************************
 */
package controller.dao;

import java.util.List;
import model.entities.Race;
import model.entities.search.criteria.RaceSearchCriteria;

/**
 * Basic interface to work with objects of class Race
 * @see Race
 */
public interface RaceDAO {
    
    /**
     * Save the object of class Race
     * @param race object of class Race
     */
    void insert(Race race);
    
    /**
     * Get the object of class Race by id.
     * @param id the int value of id
     * @return object of class Race 
     */
    Race select(int id);
    
    /**
     * Get all objects of class Race, according to filter
     * @param filter the String value of filter, 
     * @return the list of objects
     */
    List<Race> selectAll();
    
    /**
     * Update the object of class Race
     * @param race object of class Race
     */
    void update(Race race);
    
    /**
     * Delete the object of class Race by it's id
     * @param id the int value of id
     */
    void delete(int id);
    
    List<Race> findByCriteria(RaceSearchCriteria criteria);
    
}
