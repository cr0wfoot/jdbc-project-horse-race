/**
 * *********************************************************************************
 * HorseDAO: Horse.java Author: Hrytsiuk Purpose: Defines the Interface HorseDAO
 * *********************************************************************************
 */
package controller.dao;

import java.util.List;
import model.entities.Horse;

/**
 * Basic interface to work with objects of class Horse
 * @see Horse
 */
public interface HorseDAO {
    
    /**
     * Save the object of class Horse
     * @param horse object of class Horse
     */
    void insert(Horse horse);
    
    /**
     * Get the object of class Horse by id
     * @param id the int value of id
     * @return object of class Horse 
     */
    Horse select(int id);
    
    /**
     * Get all objects of class Horse
     * @return the list of objects
     */
    List<Horse> selectAll();
    
    /**
     * Update the object of class Horse
     * @param horse object of class Horse
     */
    void update(Horse horse);
    
    /**
     * Delete the object of class Horse by it's id
     * @param id the int value of id
     */
    void delete(int id);
    
}
