/**
 * *******************************************************************************************
 * Module: GetUserById.java Author: Hrytsiuk Purpose: Defines the Class GetUserById
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;
import controller.validation.ParamParser;
import model.entities.User;

/**
 * Command: get user by id (no DB changes)
 * @see UserDAO
 * @see User
 * @see ParamParser
 */
public class GetUserById extends AbstractCommand {
      
    /**
     * Validate input data, get user info from DB
     */
    @Override
    protected void doOperation() {
        ParamParser params = new ParamParser(request);  
        try {  
            User user = factory.createUserDAO().select(params.natural(PARAMETER_ID));
            request.setAttribute(Attributes.USER.name().toLowerCase(), user);
            if(user == null) {
                setInfoPage(ERROR_NOT_EXIST, COMMAND_RDRCT, Mapping.ADM_PG, 0);
            }
        } catch(IllegalArgumentException ex) { 
            setInfoPage(ERROR_INVALID_DATA, COMMAND_RDRCT, Mapping.ADM_PG, 0);
        }
    }

}
