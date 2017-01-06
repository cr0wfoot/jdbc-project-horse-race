/**
 * ***********************************************************************************
 * Module: JDBCRateDAO.java Author: Hrytsiuk Purpose: Defines the Class JDBCRateDAO
 * ***********************************************************************************
 */
package controller.dao.jdbc;

import controller.dao.RateDAO;
import controller.exception.handler.ExceptionHandler;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.entities.Horse;
import model.entities.Rate;
import model.entities.search.criteria.RateSearchCriteria;

/**
 * Class realize all methods to work with object Rate
 * @see Rate
 */
public class JDBCRateDAO implements RateDAO {
    
    private boolean continueTransaction = false;
    
    @Override
    public void insert(Rate rate) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sqlRates = "INSERT INTO rates (id_user, type, money) VALUES ( ? , ? , ? )";
        String sqlRateHorse = "INSERT INTO rate_horse (id_rate, id_horse, coefficient) VALUES ( ? , ? , ? )";
        String sqlRateRace = "INSERT INTO rate_race (id_rate, id_race) VALUES ( ? , ? )";
 
        try {
            if(!con.getAutoCommit())
                continueTransaction = true;
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(sqlRates, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(    1, rate.getUserId());
            statement.setString( 2, rate.getType());
            statement.setInt(    3, rate.getMoney());
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            int id = 0;
            if(key.next())
                id = key.getInt(1);
            rate.setId(id);
            key.close();
            statement = con.prepareStatement(sqlRateHorse);
            for(Horse horse : rate.getHorses()) {
                statement.setInt(   1, id);
                statement.setInt(   2, horse.getId());
                statement.setFloat( 3, horse.getCoefficient());
                statement.addBatch();
            }
            statement.executeBatch();
            statement = con.prepareStatement(sqlRateRace);
            for(Integer raceId : rate.getRaces()) {
                statement.setInt( 1, id);
                statement.setInt( 2, raceId);
                statement.addBatch();
            }
            statement.executeBatch();
            statement.close();
            if(!continueTransaction)
                con.commit();
        } catch(SQLException ex) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                ExceptionHandler.instance().handleException(e, JDBCRateDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCRateDAO.class.getName());
        }
        
    }

    @Override
    public Rate select(int id) {
                
        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "SELECT * FROM rates";
        
        try { 
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            rs.next();
            Rate rate = new Rate(rs.getInt(1),    rs.getInt(2), 
                                 rs.getString(3), rs.getInt(4), 
                                 selectHorsesForRate(con, rs.getInt(1)), 
                                 selectRacesIdForRate(con, rs.getInt(1)));
            rs.close();
            query.close();
            return rate;
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCRateDAO.class.getName());
            return null;
        }
        
    }
    
    @Override
    public List<Rate> findByCriteria(RateSearchCriteria criteria) {
        
        List<Rate> rates = new LinkedList<Rate>();
        Connection con = JDBCConnection.getInstance().getConnection();
        if(criteria.isEmpty())
            return selectAll();
        String sql = "SELECT rates.id_rate,rates.id_user,rates.type,rates.money FROM rates";
        if(criteria.getRaceId() != 0)
            sql += " INNER JOIN rate_race ON rate_race.id_rate = rates.id_rate WHERE id_race = '" + criteria.getRaceId() + "'";
        else if(criteria.getUserId() != 0)
            sql += " WHERE id_user = '" + criteria.getUserId() + "'";
        else if(criteria.getType() != 0)
            sql += " WHERE type = '" + criteria.getType() + "'";
        else
            sql += " WHERE true";
        if(criteria.getMinMoney() != 0)
            sql += " AND rates.money >= '" + criteria.getMinMoney() + "' ORDER BY rates.money";
            
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            while (rs.next())
                rates.add(new Rate(rs.getInt(1),    rs.getInt(2), 
                                   rs.getString(3), rs.getInt(4), 
                                   selectHorsesForRate(con, rs.getInt(1)), 
                                   selectRacesIdForRate(con, rs.getInt(1))));
            rs.close();
            query.close();
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCRateDAO.class.getName());
        }
        return rates;
        
    }
    
    @Override
    public List<Rate> selectAll() {

        List<Rate> rates = new LinkedList<Rate>();
        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "SELECT * FROM rates";
        
        try { 
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            while (rs.next())
                rates.add(new Rate(rs.getInt(1),    rs.getInt(2), 
                                   rs.getString(3), rs.getInt(4), 
                                   selectHorsesForRate(con, rs.getInt(1)), 
                                   selectRacesIdForRate(con, rs.getInt(1))));
            rs.close();
            query.close();
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCRateDAO.class.getName());
        }
        return rates;
        
    }

    @Override
    public void delete(int id) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "DELETE FROM rates WHERE id_rate = ? ";

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
                ExceptionHandler.instance().handleException(e, JDBCRateDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCRateDAO.class.getName());
        }
        
    }
    
    private List<Horse> selectHorsesForRate(Connection con, int id) throws SQLException {
        List<Horse> horses = new LinkedList<Horse>();
        String sql = "SELECT id_horse,coefficient FROM rate_horse WHERE id_rate = " + id;
        Statement query = con.createStatement();
        ResultSet rs = query.executeQuery(sql); 
        int i = 0;
        while (rs.next()) {
             horses.add(new Horse());
             horses.get(i).setId(rs.getInt(1));
             horses.get(i++).setCoefficient(rs.getFloat(2));
        }
        rs.close();
        query.close();
        return horses;
    }
    
    private List<Integer> selectRacesIdForRate(Connection con, int id) throws SQLException {
        List<Integer> racesId = new LinkedList<Integer>();
        String sql = "SELECT id_race FROM rate_race WHERE id_rate = " + id;
        Statement query = con.createStatement();
        ResultSet rs = query.executeQuery(sql);
        while (rs.next())
             racesId.add(rs.getInt(1));
        rs.close();
        query.close();
        return racesId;
    }
    
}
