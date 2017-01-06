/**
 * *******************************************************************************************
 * Module: GetRaceById.java Author: Hrytsiuk Purpose: Defines the Class GetRaceById
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;
import controller.validation.ParamParser;
import model.entities.Race;

/**
 * Command: get race by id (no DB changes)
 * @see RaceDAO
 * @see Race
 * @see ParamParser
 */
public class GetRaceById extends AbstractCommand {
     
    /**
     * Validate input data, get race info from DB
     */
    @Override
    protected void doOperation() {
        ParamParser params = new ParamParser(request);
        try {  
            Race race = factory.createRaceDAO().select(params.natural(PARAMETER_ID));
            request.setAttribute(Attributes.RACE.name().toLowerCase(), race);  
            resultURL = getURL();
            if(race == null) {
                setInfoPage(ERROR_NOT_EXIST, COMMAND_RDRCT, Mapping.ADM_PG, 0);
            }
        } catch(IllegalArgumentException ex) {    
            setInfoPage(ERROR_INVALID_DATA, COMMAND_RDRCT, Mapping.ADM_PG, 0);   
        }
    }
    
}
