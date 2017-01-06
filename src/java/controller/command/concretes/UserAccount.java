/**
 * *******************************************************************************************
 * Module: UserAccount.java Author: Hrytsiuk Purpose: Defines the Class UserAccount
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;
import model.entities.search.criteria.MessageSearchCriteria;
import model.entities.search.criteria.RateSearchCriteria;

/**
 * Command: redirect user on his profile page (no DB changes)
 * @see UserDAO
 * @see MessageDAO
 * @see RateDAO
 */
public class UserAccount extends AbstractCommand {
      
    /**
     * Get user's data from DB and redirect
     */
    @Override        
    protected void doOperation() {       
        if(checkAccess(Access.USER)) {  
            MessageSearchCriteria criteriaMessage = new MessageSearchCriteria();
            criteriaMessage.setUserId(getUserId());
            RateSearchCriteria criteriaRate = new RateSearchCriteria();
            criteriaRate.setUserId(getUserId());
            request.setAttribute(Attributes.USER.name().toLowerCase(), factory.createUserDAO().select(getUserId()));
            request.setAttribute((Attributes.MESSAGE.name() + Attributes.LIST.name()).toLowerCase(), factory.createMessageDAO().findByCriteria(criteriaMessage));  
            request.setAttribute((Attributes.RATE.name() + Attributes.LIST.name()).toLowerCase(), factory.createRateDAO().findByCriteria(criteriaRate));  
            resultURL = Mapping.USR_ACC.getURL();
        } else {       
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0);      
        }     
    }
    
}
