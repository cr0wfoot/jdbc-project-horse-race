/**
 * ***********************************************************************************
 * Module: JDBCUserDAO.java Author: Hrytsiuk Purpose: Defines the Class JDBCUserDAO
 * ***********************************************************************************
 */
package controller.dao.jdbc;

import controller.dao.UserDAO;
import controller.exception.handler.ExceptionHandler;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.entities.User;
import model.entities.search.criteria.UserSearchCriteria;

/**
 * Class realize all methods to work with object User
 * @see User
 */
public class JDBCUserDAO implements UserDAO {
    
    private boolean continueTransaction = false;

    @Override
    public void insert(User user) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "INSERT INTO users (login, salt, password, first_name, second_name, reg_date, budget, rates_count, access) " +
                     "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? )";
        
        try {
            if(!con.getAutoCommit())
                continueTransaction = true;
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString( 1, user.getLogin());
            statement.setInt(    2, user.getSalt());
            statement.setString( 3, user.getPassword());
            statement.setString( 4, user.getFirstName());
            statement.setString( 5, user.getSecondName());
            statement.setString( 6, user.getRegDate());
            statement.setInt(    7, user.getBudget());
            statement.setInt(    8, user.getTotalRatesCount());
            statement.setString( 9, user.getAccess());
            statement.executeUpdate();    
            ResultSet key = statement.getGeneratedKeys();
            int id = 0;
            if(key.next())
                id = key.getInt(1);
            user.setId(id);    
            key.close();
            statement.close();
            if(!continueTransaction)
                con.commit();
        } catch(SQLException ex) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                ExceptionHandler.instance().handleException(e, JDBCUserDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCUserDAO.class.getName());
        }
        
    }

    @Override
    public User select(int id) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "SELECT * FROM users WHERE id_user=" + id;
        
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            rs.next();
            User user = new User(id, rs.getString(2), rs.getInt(3),    rs.getString(4), 
                                     rs.getString(5), rs.getString(6), rs.getDate(7).toString(), 
                                     rs.getInt(8),    rs.getInt(9),    rs.getString(10));
            rs.close();
            query.close();
            return user;
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCUserDAO.class.getName());
            return null;
        }

    }
    
    @Override
    public List<User> findByCriteria(UserSearchCriteria criteria) {

        List<User> users = new LinkedList<User>();
        Connection con = JDBCConnection.getInstance().getConnection();
        if(criteria.isEmpty())
            return selectAll();
        String sql = "SELECT * FROM users WHERE true";
        if(criteria.getFirstName() != null)
            sql += " AND first_name = '" + criteria.getFirstName() + "'";
        if(criteria.getLogin() != null)
            sql += " AND login = '" + criteria.getLogin() + "'";
        if(criteria.getSecondName() != null)
            sql += " AND second_name = '" + criteria.getSecondName() + "'";
        if(criteria.getMinBudget() != 0)
            sql += " AND users.budget >= '" + criteria.getMinBudget() + "'" + " ORDER BY users.budget";
        
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            while (rs.next())
            users.add(new User(rs.getInt(1),             rs.getString(2), rs.getInt(3), 
                               rs.getString(4),          rs.getString(5), rs.getString(6), 
                               rs.getDate(7).toString(), rs.getInt(8),    rs.getInt(9), 
                               rs.getString(10)));
            rs.close();
            query.close();
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCUserDAO.class.getName());
        }
        return users;
         
    }

    @Override
    public List<User> selectAll() {

        List<User> users = new LinkedList<User>();
        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "SELECT * FROM users";
        
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            while (rs.next())
                users.add(new User(rs.getInt(1),             rs.getString(2), rs.getInt(3), 
                                   rs.getString(4),          rs.getString(5), rs.getString(6), 
                                   rs.getDate(7).toString(), rs.getInt(8),    rs.getInt(9), 
                                   rs.getString(10)));
            rs.close();
            query.close();
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCUserDAO.class.getName());
        } 
        return users;
        
    }

    @Override
    public void update(User user) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "UPDATE users " +
                     "SET login       = ?, salt =     ?, password = ?, first_name =  ?, " +
                     "    second_name = ?, reg_date = ?, budget =   ?, rates_count = ?, access = ? " +
                     "WHERE id_user = ? ";
        
        try {
            if(!con.getAutoCommit())
                continueTransaction = true;
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString( 1, user.getLogin());
            statement.setInt(    2, user.getSalt());
            statement.setString( 3, user.getPassword());
            statement.setString( 4, user.getFirstName());
            statement.setString( 5, user.getSecondName());
            statement.setString( 6, user.getRegDate());
            statement.setInt(    7, user.getBudget());
            statement.setInt(    8, user.getTotalRatesCount());
            statement.setString( 9, user.getAccess());
            statement.setInt(    10, user.getId());
            statement.executeUpdate();
            statement.close();
            if(!continueTransaction)
                con.commit();
        } catch(SQLException ex) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                ExceptionHandler.instance().handleException(e, JDBCUserDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCUserDAO.class.getName());
        }
        
    }

    @Override
    public void delete(int id) {
        
        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "DELETE FROM users WHERE id_user = ? ";

        try {
            if(!con.getAutoCommit())
                continueTransaction = true;
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt( 1, id);
            statement.executeUpdate();
            statement.close();
            if(!continueTransaction)
                con.commit();
        } catch(SQLException ex) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                ExceptionHandler.instance().handleException(e, JDBCUserDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCUserDAO.class.getName());
        }
        
    }
    
}
