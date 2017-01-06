/**
 * ********************************************************************************************
 * Module: UserDeleteMessage.java Author: Hrytsiuk Purpose: Defines the Class UserDeleteMessage
 * ********************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;

/**
 * Command: user delete his message (DB changes)
 * @see MessageDAO
 * @see Message
 */
public class UserDeleteMessage extends AbstractCommand {
       
    /**
     * Delete message from DB
     */
    @Override        
    protected void doOperation() {            
        if(checkAccess(Access.USER)) {                
            factory.createMessageDAO().delete(Integer.parseInt(request.getParameter(PARAMETER_ID)));                
            setInfoPage(ERROR_SUCCESS, COMMAND_USR_ACC, null, 0);            
        } else {                
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0);            
        }         
    }
    
}
