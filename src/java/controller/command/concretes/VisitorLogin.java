/**
 * *******************************************************************************************
 * Module: VisitorLogin.java Author: Hrytsiuk Purpose: Defines the Class VisitorLogin
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.*;
import controller.password.encryption.Encryptor;
import controller.password.encryption.MD5Encryptor;
import controller.validation.ParamParser;
import javax.servlet.http.HttpSession;
import model.entities.User;
import model.entities.search.criteria.RaceSearchCriteria;
import model.entities.search.criteria.UserSearchCriteria;

/**
 * Command: authorization (no DB changes)
 * @see UserDAO
 * @see User
 * @see ParamParser
 */
public class VisitorLogin extends AbstractCommand {
      
    /**
     * Validate input data, check user's login and password, set user's id, and role
     * in session, redirect user according to his role
     */
    @Override        
    protected void doOperation() {            
        ParamParser params = new ParamParser(request);  
        Encryptor enc = new MD5Encryptor();
        try {            
            UserSearchCriteria userCriteria = new UserSearchCriteria();
            userCriteria.setLogin(params.email(PARAMETER_LOGIN));
            User user = factory.createUserDAO().findByCriteria(userCriteria).get(0);                
            if(user == null) {                    
                setInfoPage(ERROR_LOG_IN, COMMAND_RDRCT, Mapping.INDEX, 0);               
            } else if(!user.getPassword().equals(enc.getEncryptedPassword(params.pass(PARAMETER_PASS) + user.getSalt()))) {                    
                setInfoPage(ERROR_PASS, COMMAND_RDRCT, Mapping.INDEX, 0);            
            } else {                    
                HttpSession session = request.getSession(true);                    
                session.setAttribute(SessionAttr.USER.name().toLowerCase(), String.valueOf(user.getId())); 
                session.setAttribute(SessionAttr.ACCESS.name().toLowerCase(), String.valueOf(user.getAccess())); 
                RaceSearchCriteria raceCriteria = new RaceSearchCriteria();
                switch(Access.valueOf(user.getAccess().toUpperCase())) {                        
                    case USER :    
                        resultURL = Mapping.USR_PG.getURL();  
                        raceCriteria.setFilterByStatus(RaceStatus.NEW.name().toLowerCase());
                        request.setAttribute((Attributes.RACE.name() + Attributes.LIST.name()).toLowerCase(), factory.createRaceDAO().findByCriteria(raceCriteria));                         
                        break;                        
                    case BOOKIE : 
                        resultURL = Mapping.BOO_PG.getURL(); 
                        raceCriteria.setFilterByStatus(RaceStatus.CLOSED.name().toLowerCase());
                        request.setAttribute((Attributes.RACE.name() + Attributes.LIST.name()).toLowerCase(), factory.createRaceDAO().findByCriteria(raceCriteria));                           
                        break;                        
                    case ADMIN :   
                        resultURL = Mapping.ADM_PG.getURL();                              
                        break;                    
                }     
            }            
        } catch(IllegalArgumentException ex) {                
            setInfoPage(ERROR_INVALID_DATA, COMMAND_RDRCT, Mapping.INDEX, 0);          
        }
        
    }
    
}
