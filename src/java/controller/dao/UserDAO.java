/**
 * *********************************************************************************
 * UserDAO: Horse.java Author: Hrytsiuk Purpose: Defines the Interface UserDAO
 * *********************************************************************************
 */
package controller.dao;

import java.util.List;
import model.entities.User;
import model.entities.search.criteria.UserSearchCriteria;

/**
 * Basic interface to work with objects of class User
 * @see User
 */
public interface UserDAO {
    
    /**
     * Save the object of class User
     * @param user object of class User
     */
    void insert(User user);
    
    /**
     * Get the object of class User by id.
     * @param id the int value of id
     * @return object of class User 
     */
    User select(int id);
    
    /**
     * Get all objects of class User
     * @return the list of objects
     */
    List<User> selectAll();
    
    /**
     * Update the object of class User
     * @param user object of class User
     */
    void update(User user);
    
    /**
     * Delete the object of class User by it's id
     * @param id the int value of id
     */
    void delete(int id);
    
    /**
     * Get the object of class User by login.
     * @param login the String value of login
     * @return object of class User 
     */
    List<User> findByCriteria(UserSearchCriteria criteria);
    
}
