/**
 * *******************************************************************************************
 * Module: GetAllUsers.java Author: Hrytsiuk Purpose: Defines the Class GetAllUsers
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;

/**
 * Command: get all users (no DB changes)
 * @see UserDAO
 */
public class GetAllUsers extends AbstractCommand {
       
    /**
     * Get the list of all users from DB
     */
    @Override
    protected void doOperation() {
        request.setAttribute((Attributes.USER.name() + Attributes.LIST.name()).toLowerCase(), factory.createUserDAO().selectAll());
        resultURL = Mapping.ADM_USR.getURL();
    }
    
}
