/**
 * *******************************************************************************************
 * Module: VisitorRegister.java Author: Hrytsiuk Purpose: Defines the Class VisitorRegister
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;
import controller.dao.UserDAO;
import controller.password.encryption.Encryptor;
import controller.password.encryption.MD5Encryptor;
import controller.validation.ParamParser;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.entities.User;
import model.entities.search.criteria.UserSearchCriteria;

/**
 * Command: register visitors (DB changes)
 * @see UserDAO
 * @see User
 * @see ParamParser
 */
public class VisitorRegister extends AbstractCommand {
     
    /**
     * Validate input data, hash password, create salt for password to hash,
     * insert new user into DB
     */
    @Override        
    protected void doOperation() {            
        ParamParser params = new ParamParser(request);
        Encryptor enc = new MD5Encryptor();
        try {    
            String login = params.email(PARAMETER_LOGIN);
            UserSearchCriteria criteria = new UserSearchCriteria();
            criteria.setLogin(login);
            UserDAO userFactory = factory.createUserDAO();                
            User user = userFactory.findByCriteria(criteria).get(0);                   
            int salt = createSalt(PASSWORD_SALT_FROM, PASSWORD_SALT_TO);   
            if(user == null) {                    
                userFactory.insert(new User(1, login, salt, enc.getEncryptedPassword(params.pass(PARAMETER_PASS) + salt),                   
                                               params.name(PARAMETER_USER_FIRST_NAME), params.name(PARAMETER_USER_SECOND_NAME),                   
                                               new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),                 
                                               0, 0, Access.USER.toString().toLowerCase()));                    
                setInfoPage(ERROR_SUCCESS, COMMAND_RDRCT, Mapping.INDEX, 0);            
            } else {                    
                setInfoPage(ERROR_REGISTER_LOGIN, COMMAND_RDRCT, Mapping.REG, 0);       
            }             
        } catch(IllegalArgumentException ex) {                
            setInfoPage(ERROR_INVALID_DATA, COMMAND_RDRCT, Mapping.REG, 0);           
        }        
    }
    
    /**
     * Create and get the int value of password salt
     * @param from
     * the left border of random number for salt
     * @param to
     * the right border of random number for salt
     * @return the int value of password salt
     */
    private int createSalt(int from, int to) {
        return (int)(Math.random() * (to - from) + from);
    }
    
}
