/**
 * **************************************************************************************
 * Module: JDBCMessageDAO.java Author: Hrytsiuk Purpose: Defines the Class JDBCMessageDAO
 * **************************************************************************************
 */
package controller.dao.jdbc;

import controller.dao.MessageDAO;
import controller.exception.handler.ExceptionHandler;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.entities.Message;
import model.entities.search.criteria.MessageSearchCriteria;

/**
 * Class realize all methods to work with object Message
 * @see Message
 */
public class JDBCMessageDAO implements MessageDAO {

    private boolean continueTransaction = false;

    @Override
    public void insert(Message message) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "INSERT INTO messages (id_user, content) VALUES ( ? , ? )";

        try {
            if(!con.getAutoCommit())
                continueTransaction = true;
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(    1, message.getIdUser());
            statement.setString( 2, message.getContent());
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            int id = 0;
            if(key.next())
                id = key.getInt(1);
            message.setId(id);
            key.close();
            statement.close();
            if(!continueTransaction)
                con.commit();
        } catch(SQLException ex) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                ExceptionHandler.instance().handleException(e, JDBCMessageDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCMessageDAO.class.getName());
        }
        
    }

    @Override
    public Message select(int id) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "SELECT * FROM messages WHERE id=" + id;
        
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            rs.next();
            Message message = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3));
            rs.close();
            query.close();
            return message;
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCUserDAO.class.getName());
            return null;
        }
    }
    
    @Override
    public List<Message> findByCriteria(MessageSearchCriteria criteria) {

        List<Message> messages = new LinkedList<Message>();
        Connection con = JDBCConnection.getInstance().getConnection();
        if(criteria.isEmpty())
            return selectAll();
        String sql = "SELECT * FROM messages WHERE true";
        if(criteria.getUserId() != 0)
            sql += " AND id_user = '" + criteria.getUserId() + "'";  
        
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            while (rs.next())
                messages.add(new Message(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            rs.close();
            query.close();
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCMessageDAO.class.getName());
        }
        return messages;
        
    }

    @Override
    public List<Message> selectAll() {

        List<Message> messages = new LinkedList<Message>();
        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "SELECT * FROM messages";
        
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            while (rs.next())
                messages.add(new Message(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            rs.close();
            query.close();
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCMessageDAO.class.getName());
        }
        return messages;
        
    }

    @Override
    public void delete(int id) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "DELETE FROM messages WHERE id = ? ";

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
                ExceptionHandler.instance().handleException(e, JDBCMessageDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCMessageDAO.class.getName());
        }
        
    }
    
}
