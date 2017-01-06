/**
 * *****************************************************************************************
 * Module: CommandFactory.java Author: Hrytsiuk Purpose: Defines the Class CommandFactory
 * *****************************************************************************************
 */
package controller.command;

import controller.command.concretes.*;
import controller.exception.handler.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Factory of commands
 * @see Command
 */
public class CommandFactory {
    
    /**
     * An instance of this object
     */
    protected static CommandFactory instance = null;
    
    /**
     * Get the instance of this object
     * @return the value of {@link JDBCConnection#instance}
     */
    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }
    
    /**
     * Enumeration of commands
     */
    enum CommandType { 
        
        /**
         * Store "redirect" command
         * @see GetRedirect
         */
        REDIRECT(            new GetRedirect()           ), 
        
        /**
         * Store "log in" command
         * @see VisitorLogin
         */
        LOGIN(               new VisitorLogin()          ), 
        
        /**
         * Store "register" command
         * @see VisitorRegister
         */
        REGISTER(            new VisitorRegister()       ),
        
        /**
         * Store "user set rate" command
         * @see UserSetRate
         */
        USR_BET(             new UserSetRate()           ), 
        
        /**
         * Store "show user's profile" command
         * @see UserAccount
         */
        USR_ACCOUNT(         new UserAccount()           ), 
        
        /**
         * Store "user refill budget" command
         * @see UserRefillBalance
         */
        USR_REFILL(          new UserRefillBalance()     ),
        
        /**
         * Store "user delete message" command
         * @see UserDeleteMessage
         */
        USR_DEL_MESSAGE(     new UserDeleteMessage()     ), 
        
        /**
         * Store "bookie set coefficients" command
         * @see BookieSetCoefficients
         */
        BOO_COEFF(           new BookieSetCoefficients() ),
        
        /**
         * Store "get all races" command
         * @see GetAllRaces
         */
        GET_ALL_RACES(       new GetAllRaces()           ), 
        
        /**
         * Store "get all horses" command
         * @see GetAllHorses
         */
        GET_ALL_HORSES(      new GetAllHorses()          ),
        
        /**
         * Store "get all users" command
         * @see GetAllUsers
         */
        GET_ALL_USERS(       new GetAllUsers()           ), 
        
        /**
         * Store "get all rates" command
         * @see GetAllRates
         */
        GET_ALL_RATES(       new GetAllRates()           ), 
        
        /**
         * Store "get race by id" command
         * @see GetRaceById
         */
        GET_RACE_BY_ID(      new GetRaceById()           ),
        
        /**
         * Store "get horse by id" command
         * @see GetHorseById
         */
        GET_HORSE_BY_ID(     new GetHorseById()          ), 
        
        /**
         * Store "get user by id" command
         * @see GetUserById
         */
        GET_USER_BY_ID(      new GetUserById()           ),
        
        /**
         * Store "get user by login" command
         * @see GetUserByLogin
         */
        GET_USER_BY_LOGIN(   new GetUserByLogin()        ),
        
        /**
         * Store "get rate by id" command
         * @see GetRateById
         */
        GET_RATE_BY_ID(      new GetRateById()           ),
        
        /**
         * Store "get rate by user's id" command
         * @see GetRateByIdUser
         */
        GET_RATE_BY_USER_ID( new GetRateByIdUser()       ),
        
        /**
         * Store "get race and all horses for admin" command
         * @see AdminRaceGet
         */
        ADM_RACE_GET(        new AdminRaceGet()          ),
        
        /**
         * Store "fix race and set results" command
         * @see AdminRaceFixResults
         */
        ADM_RACE_FIX(        new AdminRaceFixResults()   ),
        
        /**
         * Store "update race" command
         * @see AdminRaceUpdate
         */
        ADM_RACE_UPDATE(     new AdminRaceUpdate()       ),
        
        /**
         * Store "delete race" command
         * @see AdminRaceDelete
         */
        ADM_RACE_DELETE(     new AdminRaceDelete()       ),
        
        /**
         * Store "insert race" command
         * @see AdminRaceNew
         */
        ADM_RACE_NEW(        new AdminRaceNew()          ),
        
        /**
         * Store "insert horse" command
         * @see AdminHorseNew
         */
        ADM_HORSE_NEW(       new AdminHorseNew()         ),
        
        /**
         * Store "delete or update horse" command
         * @see AdminHorseUpdate
         */
        ADM_HORSE_UPDATE(    new AdminHorseUpdate()      ),
        
        /**
         * Store "delete or update user" command
         * @see AdminUserUpdate
         */
        ADM_USER_UPDATE(     new AdminUserUpdate()       ); 
       
        /**
         * An object of type Command
         */
        private final Command command;
        
        /**
         * Constructor with one argument initialize field {@link CommandType#command}
         * @param command    an object of type Command
         */
        CommandType(Command command) {
            this.command = command;
        }
        
    }
    
    /**
     * Get an object of type Command
     * @param command
     * the name of command
     * @return the value of {@link CommandType#command}
     */
    public Command getCommand(String command) {
        try {
            CommandType ct = CommandType.valueOf(command.toUpperCase());
            return ct.command;
        } catch(IllegalArgumentException ex) {
            return new Command() {
                @Override
                public void execute(HttpServletRequest request, HttpServletResponse response) {
                    //сделать выполнение команды
                }
            };
        }
    }
    
}
