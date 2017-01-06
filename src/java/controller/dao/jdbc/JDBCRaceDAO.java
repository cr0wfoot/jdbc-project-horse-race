/**
 * ***********************************************************************************
 * Module: JDBCRaceDAO.java Author: Hrytsiuk Purpose: Defines the Class JDBCRaceDAO
 * ***********************************************************************************
 */
package controller.dao.jdbc;

import controller.config.constants.RaceStatus;
import controller.dao.RaceDAO;
import controller.exception.handler.ExceptionHandler;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.entities.Horse;
import model.entities.Race;
import model.entities.search.criteria.RaceSearchCriteria;

/**
 * Class realize all methods to work with object Race
 * @see Race
 */
public class JDBCRaceDAO implements RaceDAO {
    
    private boolean continueTransaction = false;
    
    @Override
    public void insert(Race race) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "INSERT INTO races (place, distance, horses_count, date, status) VALUES ( ? , ? , ? , ? , ? )";

        try {
            if(!con.getAutoCommit())
                continueTransaction = true;
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString( 1, race.getPlace());
            statement.setInt(    2, race.getDistance());
            statement.setInt(    3, race.getHorseCount());
            statement.setString( 4, race.getDate());
            statement.setString( 5, race.getStatus().name().toLowerCase());
            statement.executeUpdate();  
            ResultSet key = statement.getGeneratedKeys();
            int id = 0;
            if(key.next())
                id = key.getInt(1);
            key.close();
            race.setId(id);
            insertHorsesIntoRaceHorse(con, race.getHorses(), id);
            statement.close();
            if(!continueTransaction)
                con.commit();
        } catch(SQLException ex) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                ExceptionHandler.instance().handleException(e, JDBCRaceDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCRaceDAO.class.getName());
        }
        
    }

    @Override
    public Race select(int id) {
        
        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "SELECT * FROM races WHERE id_race = " + id + " ORDER BY date";
        
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            rs.next();
            Race race = new Race(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(5).toString(), RaceStatus.valueOf(rs.getString(6)), selectHorsesForRace(con, id));
            rs.close();
            query.close();
            return race;
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCRaceDAO.class.getName());
            return null;
        }
        
    }
    
    @Override
    public List<Race> findByCriteria(RaceSearchCriteria criteria) {

        List<Race> races = new LinkedList<Race>();
        Connection con = JDBCConnection.getInstance().getConnection();
        if(criteria.isEmpty())
            return selectAll();
        String sql = "SELECT * FROM races WHERE true";
        try {
            Statement query = con.createStatement();
            ResultSet rs;
            if(criteria.isForFixRates()) {
                sql =  " SELECT race_horse.id_race,races.date FROM races" +
                    " INNER JOIN race_horse" +
                    " ON race_horse.id_race = races.id_race WHERE id_horse = " + criteria.getHorseId() + 
                    " AND races.date >= '" + criteria.getMinDate() + "'" +
                    " ORDER BY races.date";
                rs = query.executeQuery(sql);
                Race race;
                while (rs.next()) {
                    race = new Race();
                    race.setId(rs.getInt("id_race"));
                    race.setDate(rs.getString("date"));
                    races.add(race);
                }
            } else {
                if(criteria.getFilterByStatus() != null)
                    sql += " AND races.status != '" + criteria.getFilterByStatus() + "'";
                if(criteria.getDistance() != 0)
                    sql += " AND races.distance != '" + criteria.getDistance() + "'";
                if(criteria.getMinDate() != null)
                    sql += " AND races.date >= '" + criteria.getMinDate() + "' ORDER BY races.date";
                rs = query.executeQuery(sql);
                while (rs.next())     
                    races.add(new Race(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(5).toString(), RaceStatus.valueOf(rs.getString(6)), selectHorsesForRace(con, rs.getInt(1))));
            }
            rs.close();
            query.close();
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCRaceDAO.class.getName());
        }
        return races;
        
    }
    
    @Override
    public List<Race> selectAll() {

        List<Race> races = new LinkedList<Race>();
        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "SELECT * FROM races";
        
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);
            while (rs.next())
                races.add(new Race(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(5).toString(), RaceStatus.valueOf(rs.getString(6)), selectHorsesForRace(con, rs.getInt(1))));
            rs.close();
            query.close();
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCRaceDAO.class.getName());
        }
        return races;
        
    }

    @Override
    public void update(Race race) {
        
        Connection con = JDBCConnection.getInstance().getConnection();
        String sqlUpd = "UPDATE races SET place = ?, distance = ?, horses_count = ?, date = ?, status = ? WHERE id_race = ? ";
        String sqlDelHorses = "DELETE FROM race_horse WHERE id_race = ? ";
        try {
            if(!con.getAutoCommit())
                continueTransaction = true;
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(sqlUpd); 
            statement.setString( 1, race.getPlace());
            statement.setInt(    2, race.getDistance());
            statement.setInt(    3, race.getHorseCount());
            statement.setString( 4, race.getDate());
            statement.setString( 5, race.getStatus().name().toLowerCase());
            statement.setInt(    6, race.getId());
            statement.executeUpdate();
            statement = con.prepareStatement(sqlDelHorses);
            statement.setInt( 1, race.getId());
            statement.executeUpdate();
            insertHorsesIntoRaceHorse(con, race.getHorses(), race.getId());
            statement.close();
            if(!continueTransaction)
                con.commit();
        } catch(SQLException ex) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                ExceptionHandler.instance().handleException(e, JDBCRaceDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCRaceDAO.class.getName());
        }
        
    }
    
    @Override
    public void delete(int id) {

        Connection con = JDBCConnection.getInstance().getConnection();
        String sql = "DELETE FROM races WHERE id_race = ? ";

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
                ExceptionHandler.instance().handleException(e, JDBCRaceDAO.class.getName());
            }
            ExceptionHandler.instance().handleException(ex, JDBCRaceDAO.class.getName());
        }
        
    }
    
    private void insertHorsesIntoRaceHorse(Connection con, List<Horse> horses, int id) throws SQLException {
        String sql = "INSERT INTO race_horse (id_race, id_horse, coefficient, result) VALUES ( ? , ? , ? , ? )";
        PreparedStatement statement = con.prepareStatement(sql);
        for(Horse horse : horses) {
            statement.setInt(   1, id);
            statement.setInt(   2, horse.getId());
            statement.setFloat( 3, horse.getCoefficient());
            statement.setInt(   4, horse.getResult());
            statement.addBatch();
        }
        statement.executeBatch();
        statement.close();
    }
    
    private List<Horse> selectHorsesForRace(Connection con, int id) throws SQLException {
        List<Horse> horses = new LinkedList<Horse>();
        String sql = "SELECT * FROM horses INNER JOIN race_horse " +
                     "ON race_horse.id_horse = horses.id_horse WHERE id_race = " + id;
        Statement query = con.createStatement();
        ResultSet rs = query.executeQuery(sql);
        Horse horse;
        while (rs.next()) {
            horse = new Horse();
            horse.setId(          rs.getInt(    1 ));
            horse.setName(        rs.getString( 2 ));
            horse.setRider(       rs.getString( 3 ));
            horse.setBreed(       rs.getString( 4 ));
            horse.setRank(        rs.getInt(    5 ));
            horse.setRacesCount(  rs.getInt(    6 ));
            horse.setCoefficient( rs.getFloat("coefficient"));
            horse.setResult(      rs.getInt("result"));
            horses.add(horse);
        }
        rs.close();
        query.close();
        return horses;
    }
    
}