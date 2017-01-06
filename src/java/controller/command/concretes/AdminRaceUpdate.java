/**
 * *******************************************************************************************
 * Module: AdminRaceUpdate.java Author: Hrytsiuk Purpose: Defines the Class AdminRaceUpdate
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
 * Command: admin update race (DB changes)
 * @see RaceDAO
 * @see Race
 * @see ParamParser
 */
public class AdminRaceUpdate extends AbstractCommand {
        
    /**
     * Validate input data, update race in DB
     */
    @Override        
    protected void doOperation() {
        if(checkAccess(Access.ADMIN)) {    
            ParamParser params = new ParamParser(request);
            int id = Integer.parseInt(request.getParameter(PARAMETER_ID));
            try {
                if(request.getParameterValues(PARAMETER_RACE_HORSES) == null)
                    throw new IllegalArgumentException();
                List<Horse> horses = getHorsesFromRequest();
                RaceStatus status;
                if(RaceStatus.OPEN.equals(status = RaceStatus.valueOf(request.getParameter(PARAMETER_STATUS)))) {
                    status = RaceStatus.NEW; 
                }
                factory.createRaceDAO().update(new Race(id, params.text(PARAMETER_RACE_PLACE), params.natural(PARAMETER_RACE_DISTANCE),
                                                        params.date(PARAMETER_DATE), status, horses));
                setInfoPage(ERROR_SUCCESS, COMMAND_ADM_RC, Mapping.ADM_CHNG_RC, id);
            } catch(IllegalArgumentException ex) {
                setInfoPage(ERROR_INVALID_DATA, COMMAND_ADM_RC, Mapping.ADM_CHNG_RC, id);
            }
        } else {
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0);
        }        
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
