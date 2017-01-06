/**
 * *************************************************************************************
 * JDBCDaoFactory: Horse.java Author: Hrytsiuk Purpose: Defines the Class JDBCDaoFactory
 * *************************************************************************************
 */
package controller.dao.jdbc;

import controller.dao.*;

/**
 * Contains creating methods for all JDBC Objects
 * @see JDBCHorseDAO
 * @see JDBCRaceDAO
 * @see JDBCRateDAO
 * @see JDBCUserDAO
 * @see JDBCMessageDAO
 */
public class JDBCDaoFactory extends DAOFactory{

    @Override
    public HorseDAO createHorseDAO() {
        return new JDBCHorseDAO();
    }

    @Override
    public RaceDAO createRaceDAO() {
        return new JDBCRaceDAO();
    }
    
    @Override
    public RateDAO createRateDAO() {
        return new JDBCRateDAO();
    }
    
    @Override
    public UserDAO createUserDAO() {
        return new JDBCUserDAO();
    }
    
    @Override
    public MessageDAO createMessageDAO() {
        return new JDBCMessageDAO();
    }
    
}
