/**
 * *****************************************************************************************
 * Module: JDBCConnection.java Author: Hrytsiuk Purpose: Defines the Class JDBCConnection
 * *****************************************************************************************
 */
package controller.dao.jdbc;

import controller.exception.handler.ExceptionHandler;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Singleton for pool connection to DB
 */
public class JDBCConnection {
    
    /**
     * MySql driver
     */
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    /**
     * JNDI name
     */
    private static final String JNDI = "jdbc/mysql-horserace";

    /**
     * An instance of this object
     */
    private volatile static JDBCConnection instance;
    
    /**
     * The value of connection
     */
    private Connection connection;
    
    /**
     * Private constructor without parameters initialize field {@link JDBCConnection#connection}
     */
    private JDBCConnection() {
        try {
            connection = createConnection();
        } catch (Exception ex) {
            ExceptionHandler.instance().handleException(ex, JDBCConnection.class.getName());
        }
    }
    
    /**
     * Get the instance of this object
     * @return the value of {@link JDBCConnection#instance}
     */
    public static JDBCConnection getInstance() {
        JDBCConnection localInstance = instance;
        if (localInstance == null)
            synchronized (JDBCConnection.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new JDBCConnection();
            }
        return localInstance;
    }
    
    /**
     * Get connection
     * @return the value of {@link JDBCConnection#connection}
     */
    public Connection getConnection(){
        try {
            if(connection == null || connection.isClosed()) {
                try {
                    connection = createConnection();
                } catch (Exception ex) {
                    ExceptionHandler.instance().handleException(ex, JDBCConnection.class.getName());
                }
            }
        } catch (SQLException ex) {
            ExceptionHandler.instance().handleException(ex, JDBCConnection.class.getName());
        }
        return connection;
    }

    /**
     * Create and get connection from pool
     * @return the value of {@link JDBCConnection#connection}
     */
    private Connection createConnection() 
            throws ClassNotFoundException, InstantiationException, 
                   IllegalAccessException, NamingException, SQLException  {
        
            Class.forName(DRIVER).newInstance();
            InitialContext context = new javax.naming.InitialContext();
            DataSource ds = (javax.sql.DataSource)context.lookup(JNDI);
            Connection con = ds.getConnection();
            return con;
    }
    
}