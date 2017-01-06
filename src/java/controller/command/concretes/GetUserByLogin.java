/**
 * *******************************************************************************************
 * Module: GetUserByLogin.java Author: Hrytsiuk Purpose: Defines the Class GetUserByLogin
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;
import controller.validation.ParamParser;
import model.entities.User;
import model.entities.search.criteria.UserSearchCriteria;

/**
 * Command: get user by login (no DB changes)
 * @see UserDAO
 * @see User
 * @see ParamParser
 */
public class GetUserByLogin extends AbstractCommand {
       
    /**
     * Validate input data, get user info from DB
     */
    @Override 
    protected void doOperation() {  
        ParamParser params = new ParamParser(request); 
        try { 
            UserSearchCriteria criteria = new UserSearchCriteria();
            criteria.setLogin(params.email(PARAMETER_LOGIN));
            User user = factory.createUserDAO().findByCriteria(criteria).get(0);
            request.setAttribute(Attributes.USER.name().toLowerCase(), user);  
            if(user == null) {
                setInfoPage(ERROR_NOT_EXIST, COMMAND_RDRCT, Mapping.ADM_PG, 0);
            }
        } catch(IllegalArgumentException ex) {    
            setInfoPage(ERROR_INVALID_DATA, COMMAND_RDRCT, Mapping.ADM_PG, 0);
        }
    }

}
