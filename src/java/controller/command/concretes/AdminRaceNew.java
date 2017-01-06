/**
 * *******************************************************************************************
 * Module: AdminRaceNew.java Author: Hrytsiuk Purpose: Defines the Class AdminRaceNew
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;
import controller.config.constants.RaceStatus;
import controller.validation.ParamParser;
import java.util.LinkedList;
import java.util.List;
import model.entities.Horse;
import model.entities.Race;

/**
 * Command: admin insert new race (DB changes)
 * @see RaceDAO
 * @see ParamParser
 */
public class AdminRaceNew extends AbstractCommand {
      
    /**
     * Validate input data, insert new Race into DB
     */
    @Override        
    protected void doOperation() { 
        if(checkAccess(Access.ADMIN)) {
            ParamParser params = new ParamParser(request);
            try {
                params.checkNotNull(PARAMETER_RACE_HORSES);
                List<Horse> horses = getHorsesFromRequest();
                factory.createRaceDAO().insert(new Race(1, params.text(PARAMETER_RACE_PLACE), params.natural(PARAMETER_RACE_DISTANCE), 
                                                           params.date(PARAMETER_DATE), RaceStatus.NEW, horses));
                setInfoPage(ERROR_SUCCESS, COMMAND_ALL_RC, Mapping.ADM_RC, 0);
            } catch(IllegalArgumentException e) {
                setInfoPage(ERROR_INVALID_DATA, COMMAND_ALL_HRS, Mapping.ADM_NEWR, 0);
            }
        } else {
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0); 
        }       
    }
    
    /**
     * Get the list of horses from request, only with id
     * @return the list of horses
     */
    private List<Horse> getHorsesFromRequest() {
        List<Horse> horses = new LinkedList<Horse>();
        String[] horsesId = request.getParameterValues(PARAMETER_RACE_HORSES);
        for(int i = 0; i < horsesId.length; i++) {
            horses.add(new Horse());
            horses.get(i).setId(Integer.parseInt(horsesId[i]));
        }
        return horses;
    }
    
}
