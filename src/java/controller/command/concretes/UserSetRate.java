/**
 * *******************************************************************************************
 * Module: UserSetRate.java Author: Hrytsiuk Purpose: Defines the Class UserSetRate
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;
import controller.dao.DAOFactory;
import controller.validation.ParamParser;
import java.util.LinkedList;
import java.util.List;
import model.entities.Horse;
import model.entities.Rate;
import model.entities.User;
import model.entities.ratetype.factory.RateType;
import model.entities.ratetype.factory.TypeFactory;
import model.entities.ratetype.factory.TypeFactory.Rates;

/**
 * Command: user set his rate (DB changes)
 * @see DAOFactory
 * @see UserDAO
 * @see RateDAO
 * @see User
 * @see Rate
 * @see ParamParser
 * @see TypeInterface
 */
public class UserSetRate extends AbstractCommand {
      
    /**
     * Validate input data, check user's money, check rate type, insert new Rate 
     * into DB, update user in DB
     */
    @Override        
    protected void doOperation() {
        if(checkAccess(Access.USER)) {    
            ParamParser params = new ParamParser(request);
            int idRace = Integer.parseInt(request.getParameterValues(PARAMETER_ID)[0]);
            
            try {
                params.checkNotNull(PARAMETER_ID, PARAMETER_RACE_HORSES, PARAMETER_TYPE);
                User user = factory.createUserDAO().select(getUserId());
                int money;
                Rates type;
                if(((money = params.natural(PARAMETER_USER_MONEY)) == 0) ||
                    (money > user.getBudget())) {
                    setInfoPage(ERROR_USER_MONEY, COMMAND_RC_ID, Mapping.USR_BET, idRace);
                } else { 
                    List<Integer> races = getRacesIdFromRequest();
                    List<Horse> horses = getHorsesFromRequest();
                    RateType rateType = new TypeFactory().getType(type = Rates.valueOf(request.getParameter(PARAMETER_TYPE).toUpperCase()));
                    if((races = rateType.setRate(races, horses)) != null) {
                        user.setBudget(user.getBudget() - money);
                        user.setTotalRatesCount(user.getTotalRatesCount() + 1);
                        Rate rate = new Rate(1, user.getId(), type.toString().toLowerCase(), money, horses, races);
                        
                        
                        startTransaction();                
                        factory.createUserDAO().update(user);                                        
                        factory.createRateDAO().insert(rate); 
                        commitTransaction(); 
                        
                        
                    } else {
                        setInfoPage(ERROR_RATE_TYPE, COMMAND_RC_ID, Mapping.USR_BET, idRace);
                    }
                }
            } catch(IllegalArgumentException ex) {
               setInfoPage(ERROR_INVALID_DATA, COMMAND_RC_ID, Mapping.USR_BET, idRace);
            }
        } else {
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0);
        }        
    }
     
    /**
     * Get the list of races' id from request
     * @return the list of id
     */
    private List<Integer> getRacesIdFromRequest() {
        List<Integer> races = new LinkedList<Integer>();
        String[] racesId = request.getParameterValues(PARAMETER_ID);
        for(int i = 0; i < racesId.length; i++) {
             races.add(Integer.parseInt(racesId[i]));
        }
        return races;
    }
    
    /**
     * Get the list of horses from request, only with id and coeeficients
     * @return the list of horses
     */
    private List<Horse> getHorsesFromRequest() {
        List<Horse> horses = new LinkedList<Horse>();
        String[] horsesId = request.getParameterValues(PARAMETER_RACE_HORSES);
        for(int i = 0; i < horsesId.length; i++) {
            horses.add(new Horse());
            horses.get(i).setId(Integer.parseInt(horsesId[i].split(DELIMITER_FOR_VALUES)[0]));
            horses.get(i).setCoefficient(Float.parseFloat(horsesId[i].split(DELIMITER_FOR_VALUES)[1]));
        }
        return horses;
    }
    
}
