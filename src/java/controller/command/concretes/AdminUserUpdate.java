/**
 * *******************************************************************************************
 * Module: AdminUserUpdate.java Author: Hrytsiuk Purpose: Defines the Class AdminUserUpdate
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;
import controller.config.constants.Parameters;
import controller.dao.UserDAO;
import controller.validation.ParamParser;
import model.entities.User;

/**
 * Command: admin delete user or change user's access (DB changes)
 * @see UserDAO
 * @see User
 * @see ParamParser
 */
public class AdminUserUpdate extends AbstractCommand {
        
    /**
     * Validate input data, delete user from DB or update user's access in DB
     */
    @Override        
    protected void doOperation() {
        if(checkAccess(Access.ADMIN)) {
            ParamParser params = new ParamParser(request);
            UserDAO userFactory = factory.createUserDAO();
            if(request.getParameter(PARAMETER_SUBMIT + "2") != null) {
                userFactory.delete(Integer.parseInt(request.getParameter(PARAMETER_ID)));
                setInfoPage(ERROR_SUCCESS, COMMAND_ALL_USR, Mapping.ADM_USR, 0);
            } else {
                try {
                    User user = userFactory.select(Integer.parseInt(request.getParameter(PARAMETER_ID)));
                    user.setAccess(params.access(Parameters.ACCESS.name().toLowerCase()));
                    userFactory.update(user);
                    setInfoPage(ERROR_SUCCESS, COMMAND_ALL_USR, Mapping.ADM_USR, 0);
                } catch(IllegalArgumentException ex) {
                    setInfoPage(ERROR_INVALID_DATA, COMMAND_ALL_USR, Mapping.ADM_USR, 0);
                }
            }
        } else {
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0);            
        }        
    }
    
}
