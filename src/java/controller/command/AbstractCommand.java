/**
 * *****************************************************************************************
 * Module: AbstractCommand.java Author: Hrytsiuk Purpose: Defines the Class AbstractCommand
 * *****************************************************************************************
 */
package controller.command;

import controller.config.constants.Access;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;
import controller.config.constants.SessionAttr;
import controller.dao.DAOFactory;
import controller.dao.jdbc.JDBCConnection;
import controller.exception.handler.ExceptionHandler;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Abstract class for concrete command, contains all constants, enumeration of access roles,
 * enumeration of URLs and some useful methods
 */
public abstract class AbstractCommand implements Command {

        /**
         * The name of propertie for SQL error message
         */
        protected static String ERROR_SQL = "SQL";
        
        /**
         * The name of propertie for object not exist error message 
         */
        protected static String ERROR_NOT_EXIST = "notExist";
        
        /**
         * The name of propertie for invalid data error message
         */
        protected static String ERROR_INVALID_DATA = "invalid";
        
        /**
         * The name of propertie for forbidden operation error message
         */
        protected static String ERROR_FORBIDDEN_OPERATION = "forbidden";
        
        /**
         * The name of propertie for forbidden access error message
         */
        protected static String ERROR_ACCESS = "access";
        
        /**
         * The name of propertie for incorrect login error message
         */
        protected static String ERROR_LOG_IN = "login";
        
        /**
         * The name of propertie for incorrect password error message
         */
        protected static String ERROR_PASS = "pass";
        
        /**
         * The name of propertie for login is already used error message
         */
        protected static String ERROR_REGISTER_LOGIN = "alreadyUse";
        
        /**
         * The name of propertie for not enough money error message
         */
        protected static String ERROR_USER_MONEY = "money";
        
        /**
         * The name of propertie for incorrect rate type error message
         */
        protected static String ERROR_RATE_TYPE = "rate";
        
        /**
         * The name of propertie for successful message
         */
        protected static String ERROR_SUCCESS = "success";
        
        
        /**
         * The name of parameter for login
         */
        protected final static String PARAMETER_LOGIN = "login";
        
        /**
         * The name of parameter for password
         */
        protected final static String PARAMETER_PASS = "pass";
        
        /**
         * The name of parameter for id
         */
        protected final static String PARAMETER_ID = "id";
        
        /**
         * The name of parameter for submit
         */
        protected final static String PARAMETER_SUBMIT = "submit";
        
        /**
         * The name of parameter for delete
         */
        protected final static String PARAMETER_DELETE = "delete";
        
        /**
         * The name of parameter for status
         */
        protected final static String PARAMETER_STATUS = "status";
        
        /**
         * The name of parameter for type
         */
        protected final static String PARAMETER_TYPE = "type";
        
        /**
         * The name of parameter for date
         */
        protected final static String PARAMETER_DATE = "date";
        
        /**
         * The name of parameter for name
         */
        protected final static String PARAMETER_USER_FIRST_NAME = "first";
        
        /**
         * The name of parameter for surname
         */
        protected final static String PARAMETER_USER_SECOND_NAME = "second";
        
        /**
         * The name of parameter for money
         */
        protected final static String PARAMETER_USER_MONEY = "money";
        
        /**
         * The name of parameter for place
         */
        protected final static String PARAMETER_RACE_PLACE = "place";
        
        /**
         * The name of parameter for distance
         */
        protected final static String PARAMETER_RACE_DISTANCE = "distance";
        
        /**
         * The name of parameter for horses
         */
        protected final static String PARAMETER_RACE_HORSES = "horses";
        
        /**
         * The name of parameter for name
         */
        protected final static String PARAMETER_HORSE_NAME = "name";
        
        /**
         * The name of parameter for rider
         */
        protected final static String PARAMETER_HORSE_RIDER = "rider";
        
        /**
         * The name of parameter for breed
         */
        protected final static String PARAMETER_HORSE_BREED = "breed";
        
        /**
         * The name of parameter for rank
         */
        protected final static String PARAMETER_HORSE_RANK = "rank";
        
        /**
         * The name of parameter for total races count
         */
        protected final static String PARAMETER_HORSE_RACES_COUNT = "rc";
        
        /**
         * The String value of parameter values delimiter
         */
        protected final static String DELIMITER_FOR_VALUES = " ";
        
        /**
         * The int value of password salt left border
         */
        protected final static int PASSWORD_SALT_FROM = 100;
        
        /**
         * The int value of password salt right border
         */
        protected final static int PASSWORD_SALT_TO = 1000;
        
        /**
         * The int value of max score
         */
        protected final static int MAX_SCORE = 10;
        
        /**
         * The value of date format
         */
        protected final static String DATE_FORMAT = "yyyy-MM-dd";
        
        
        
        /**
         * The value of DB delete
         */
        protected final static String DB_DELETE = "delete";
        
        /**
         * The value of DB user
         */
        protected final static String DB_ID_USER = "user";
        
        /**
         * The value of DB delete
         */
        protected final static String DB_ID_RACE = "race";
        
        /**
         * The value of DB rate
         */
        protected final static String DB_ID_RATE = "rate";
        
        /**
         * The name of command to get all horses
         */
        protected final static String COMMAND_ALL_HRS = "get_all_horses";
        
        /**
         * The name of command to get all races
         */
        protected final static String COMMAND_ALL_RC = "get_all_races";
        
        /**
         * The name of command to get all users
         */
        protected final static String COMMAND_ALL_USR = "get_all_users";
        
        /**
         * The name of command to get race and all horses for admin
         */
        protected final static String COMMAND_ADM_RC = "adm_race_get";
        
        /**
         * The name of command to get race by id
         */
        protected final static String COMMAND_RC_ID = "get_race_by_id";
        
        /**
         * The name of command to redirect on user's profile page
         */
        protected final static String COMMAND_USR_ACC = "usr_account";
        
        /**
         * The name of command to redirect
         */
        protected final static String COMMAND_RDRCT = "redirect";
    
        /**
         * Log4j logger
         */
        protected Logger logger = Logger.getLogger(AbstractCommand.class.getName());
        
        /**
         * An object of type DAOFactory
         * @see DAOFactory
         */
        protected DAOFactory factory = DAOFactory.getInstanceJDBC();
        
        /**
         * Store HTTP servlet request
         */
        protected HttpServletRequest request;
        
        /**
         * URL
         */
        protected String resultURL;
        
        
        
        
        
        /**
         * Execute command, check locale, do concrete operation, close DB connetion, redirect
         * @param request
         * HTTP servlet request
         * @param response
         * HTTP servlet response
         */
        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            resultURL = Mapping.INFO.getURL();
            setLocale();
            doOperation();        
            try {
                closeConnection(); 
                request.getRequestDispatcher(resultURL).forward(request, response);
            } catch(Exception ex) {
                logger.error(ex);
                request.setAttribute(Attributes.MESSAGE.name().toLowerCase(), ex.getMessage());
                try {
                    request.getRequestDispatcher(Mapping.INFO.getURL()).forward(request, response);
                } catch (ServletException e) {
                    logger.error(e);
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
        
        /**
         * Set info page, fill it with data and define which page will be next
         * @param message
         * the value of message
         * @param command
         * the name of next command
         * @param page
         * next page URL
         * @param paramId
         * the value of parameter ID if necessary
         */
        protected void setInfoPage(String message, String command, Mapping page, int paramId) {
            request.setAttribute(Attributes.MESSAGE.name().toLowerCase(), message);
            if(command != null) { request.setAttribute(Attributes.COMMAND.name().toLowerCase(), command); }
            if(page != null) { request.setAttribute(Attributes.SUBMIT.name().toLowerCase(), PARAMETER_SUBMIT + "_" + page.name().toLowerCase()); }
            else { request.setAttribute(Attributes.SUBMIT.name().toLowerCase(), PARAMETER_SUBMIT); }
            request.setAttribute(PARAMETER_ID, paramId);
            resultURL = Mapping.INFO.getURL();
        }
        
        /**
         * Get user's id from session
         * @return user's id
         */
        protected int getUserId() {
            HttpSession session = request.getSession(true);
            
            return Integer.parseInt((String) session.getAttribute(SessionAttr.USER.name().toLowerCase()));
        }
        
        /**
         * Check access level
         * @return boolean value
         */
        protected boolean checkAccess(Access a) {
            HttpSession session = request.getSession(true);
            if(Access.valueOf(session.getAttribute(SessionAttr.ACCESS.name().toLowerCase()).toString().toUpperCase()) == a) {
                return true;
            }
            return false;
        }
        
        /**
         * Get URL from enumeration URLs according to submit parameter
         * @return String value of URL
         * @see PagesURL
         */
        protected String getURL() {
            for (Mapping type : Mapping.values()) {
                if (request.getParameter(PARAMETER_SUBMIT + "_" + type.name().toLowerCase()) != null) {
                    return type.getURL();
                }
            }
            return Mapping.INFO.getURL();
        }
        
        /**
         * Set locale, get from session or set by default
         */
        private void setLocale() {
            HttpSession session = request.getSession(true);
            if(request.getParameter(SessionAttr.LOCALE.name().toLowerCase()) != null) {
                session.setAttribute(SessionAttr.LOCALE.name().toLowerCase(), request.getParameter(SessionAttr.LOCALE.name().toLowerCase()));
            }
        }
        
        protected void startTransaction() {
            try {
                JDBCConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                ExceptionHandler.instance().handleException(ex, AbstractCommand.class.getName());
            }
        }
        
        protected void commitTransaction() {
            Connection con = JDBCConnection.getInstance().getConnection();
            try {
                con.commit();
            } catch (SQLException ex) {
                ExceptionHandler.instance().handleException(ex, AbstractCommand.class.getName());
            }
        }
        
        /**
         * Close DB connection
         * @see JDBCConnection
         */
        private void closeConnection() throws SQLException {
            Connection con = JDBCConnection.getInstance().getConnection();
            if(con != null)
                con.close();
        }
          
        /**
         * Do concrete operation according to command
         * @see Command
         */
        protected abstract void doOperation();
        
}
