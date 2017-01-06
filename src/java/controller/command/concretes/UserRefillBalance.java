/**
 * ********************************************************************************************
 * Module: UserRefillBalance.java Author: Hrytsiuk Purpose: Defines the Class UserRefillBalance
 * ********************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;
import controller.dao.UserDAO;
import controller.validation.ParamParser;
import model.entities.User;

/**
 * Command: user refill his balance (DB changes)
 * @see UserDAO
 * @see User
 * @see ParamParser
 */
public class UserRefillBalance extends AbstractCommand {
      
    /**
     * Validate input data, update User's budget in DB
     */
    @Override       
    protected void doOperation() {           
        if(checkAccess(Access.USER)) {        
            ParamParser params = new ParamParser(request);        
            try {        
                UserDAO userFactory = factory.createUserDAO();        
                User user = userFactory.select(getUserId());         
                user.setBudget(user.getBudget() + params.natural(PARAMETER_USER_MONEY));          
                userFactory.update(user);                          
                setInfoPage(ERROR_SUCCESS, COMMAND_USR_ACC, null, 0);                
            } catch(IllegalArgumentException ex) {                
                setInfoPage(ERROR_INVALID_DATA, COMMAND_USR_ACC, null, 0);               
            }            
        } else {                
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0);            
        }         
    }
    
}
