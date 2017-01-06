package controller.exception.handler;

import controller.dao.jdbc.JDBCConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class ExceptionHandler {
    
    private static volatile ExceptionHandler instance;
    
    private ExceptionHandler() {}
    
    public static ExceptionHandler instance() {
        ExceptionHandler localInstance = instance;
        if (localInstance == null)
            synchronized (ExceptionHandler.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new ExceptionHandler();
            }
        return localInstance;
    }
    
    public void handleException(Exception ex, String className) {
        org.apache.log4j.Logger.getLogger(className).error(ex);
        try {
            Connection con = JDBCConnection.getInstance().getConnection();
            if(con != null) con.close();
        } catch (SQLException e) {
            org.apache.log4j.Logger.getLogger(className).error(ex);
        }
    }
    
}
