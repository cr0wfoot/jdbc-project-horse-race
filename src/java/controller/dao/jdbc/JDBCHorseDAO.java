/**
 * **************************************************************************************
 * Module: JDBCHorseDAO.java Author: Hrytsiuk Purpose: Defines the Class JDBCHorseDAO
 * **************************************************************************************
 */
package controller.dao.jdbc;

import controller.dao.HorseDAO;
import controller.exception.handler.ExceptionHandler;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.entities.Horse;

/**
 * Class realize all methods to work with object Horse
 * @see Horse
 */
public class JDBCHorseDAO implements HorseDAO {

    private boolean continueTransaction = false;
    
    @Override
    public void insert(Horse horse) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "INSERT INTO horses (name, rider, horse_breed, rank, races_count) VALUES ( ? , ? , ? , ? , ? )";

        try {
            if(!con.getAutoCommit())
                continueTransaction = true;
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString( 1, horse.getName());
            statement.setString( 2, horse.getRider());
            statement.setString( 3, horse.getBreed());
            statement.setInt(    4, horse.getRank());
            statement.setInt(    5, horse.getRacesCount());
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            int id = 0;
            if(key.next())
                id = key.getInt(1);
            horse.setId(id);
            key.close();
            statement.close();
            if(!continueTransaction)
                con.commit();
        } catch(SQLException ex) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                ExceptionHandler.instance().handleException(e, JDBCHorseDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCHorseDAO.class.getName());
        }
        
    }

    @Override
    public Horse select(int id) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "SELECT * FROM horses WHERE id_horse = " + id;
        
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            rs.next();
            Horse horse = new Horse(id, rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), 0);
            rs.close();
            query.close();
            return horse;
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCHorseDAO.class.getName());
            return null;
        }
        
    }

    @Override
    public List<Horse> selectAll() {

        List<Horse> horses = new LinkedList<Horse>();
        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "SELECT * FROM horses";
        
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            while (rs.next())
                horses.add(new Horse(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), 0));
            rs.close();
            query.close();
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCHorseDAO.class.getName());
        }
        return horses;
        
    }

    @Override
    public void update(Horse horse) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "UPDATE horses " +
                     "SET name = ?, rider =       ?, horse_breed = ?, " +
                     "    rank = ?, races_count = ? " +
                     "WHERE id_horse = ? ";
        
        try {
            if(!con.getAutoCommit())
                continueTransaction = true;
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString( 1, horse.getName());
            statement.setString( 2, horse.getRider());
            statement.setString( 3, horse.getBreed());
            statement.setInt(    4, horse.getRank());
            statement.setInt(    5, horse.getRacesCount());
            statement.setInt(    6, horse.getId());
            statement.executeUpdate();
            statement.close();
            if(!continueTransaction)
                con.commit();
        } catch(SQLException ex) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                ExceptionHandler.instance().handleException(e, JDBCHorseDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCHorseDAO.class.getName());
        }
        
    }

    @Override
    public void delete(int id) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "DELETE FROM horses WHERE id_horse = ? ";

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
                ExceptionHandler.instance().handleException(e, JDBCHorseDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCHorseDAO.class.getName());
        }
        
    }
    
}
