/**
 * *********************************************************************************
 * DAOFactory: Horse.java Author: Hrytsiuk Purpose: Defines the Class DAOFactory
 * *********************************************************************************
 */
package controller.dao;

import controller.exception.handler.ExceptionHandler;

/**
 * This is highest level of abstraction for all Data Access Objects with an associated response object
 * Every data access object must register with a service before it can be used
 * @see HorseDAO
 * @see RaceDAO
 * @see RateDAO
 * @see UserDAO
 * @see MessageDAO
 * @see JDBCDaoFactory
 */
public abstract class DAOFactory {

    /**
     * return the HorseSAO Object
     */
    public abstract HorseDAO createHorseDAO();
    
    /**
     * return the RaceDAO Object
     */
    public abstract RaceDAO createRaceDAO();
    
    /**
     * return the RateDAO Object
     */
    public abstract RateDAO createRateDAO();
    
    /**
     * return the UserDAO Object
     */
    public abstract UserDAO createUserDAO();
    
    /**
     * return the MessageDAO Object
     */
    public abstract MessageDAO createMessageDAO();
    
    /**
     * gets an instance of JDBCDaoFactory
     */
    public static DAOFactory getInstanceJDBC(){
        try {
            return (DAOFactory)Class.forName("controller.dao.jdbc.JDBCDaoFactory").newInstance();
        } catch (Exception ex) {
            ExceptionHandler.instance().handleException(ex, DAOFactory.class.getName());
            return null;
        }
    }
    
}
