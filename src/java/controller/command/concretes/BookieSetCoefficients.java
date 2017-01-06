/**
 * ****************************************************************************************************
 * Module: BookieSetCoefficients.java Author: Hrytsiuk Purpose: Defines the Class BookieSetCoefficients
 * ****************************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;
import controller.config.constants.RaceStatus;
import controller.dao.RaceDAO;
import controller.validation.ParamParser;
import model.entities.Horse;
import model.entities.Race;

/**
 * Command: bookie set coefficients (DB changes)
 * @see RaceDAO
 * @see Race
 * @see ParamParser
 */
public class BookieSetCoefficients extends AbstractCommand {
        
    /**
     * Validate input data, update race in DB
     * into DB, update user in DB
     */
    @Override        
    protected void doOperation() {
        if(checkAccess(Access.BOOKIE)) {    
            
            RaceDAO raceFactory = factory.createRaceDAO();
            Race race = raceFactory.select(Integer.parseInt(request.getParameter(PARAMETER_ID)));
            getCoefficientsFromRequest(race);
            race.setStatus(RaceStatus.OPEN);
            raceFactory.update(race);
            setInfoPage(ERROR_SUCCESS, COMMAND_RC_ID, Mapping.BOO_CFF, race.getId());
        } else {
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0);
        }        
    }
    
    /**
     * Get coefficients from request and set them
     * @return boolean value
     */
    private void getCoefficientsFromRequest(Race race) {
        ParamParser params = new ParamParser(request);
        try {
            for(Horse horse : race.getHorses())
                horse.setCoefficient(params.getFloat(String.valueOf(horse.getId())));
        } catch(IllegalArgumentException ex) {
                setInfoPage(ERROR_INVALID_DATA, COMMAND_RC_ID, Mapping.BOO_CFF, race.getId());
        }
    }
    
}
