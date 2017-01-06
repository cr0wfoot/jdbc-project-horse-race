/**
 * *********************************************************************************
 * MessageDAO: Horse.java Author: Hrytsiuk Purpose: Defines the Interface MessageDAO
 * *********************************************************************************
 */
package controller.dao;

import java.util.List;
import model.entities.Message;
import model.entities.search.criteria.MessageSearchCriteria;

/**
 * Basic interface to work with objects of class Message
 * @see Message
 */
public interface MessageDAO {
    
    /**
     * Save the object of class Message
     * @param message object of class Message
     */
    void insert(Message message);
 
    Message select(int id);
    
    /**
     * Get all objects of class Message
     * @return the list of objects
     */
    List<Message> selectAll();
    
    /**
     * Delete the object of class Message by it's id
     * @param id the int value of id
     */
    void delete(int id);
    
    /**
     * Get the object of class Message by id
     * @param id the int value of id
     * @return object of class Message 
     */
    List<Message> findByCriteria(MessageSearchCriteria criteria);
    
}
