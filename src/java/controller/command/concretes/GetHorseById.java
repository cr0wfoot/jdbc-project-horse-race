/**
 * *******************************************************************************************
 * Module: GetHorseById.java Author: Hrytsiuk Purpose: Defines the Class GetHorseById
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;
import controller.validation.ParamParser;
import model.entities.Horse;

/**
 * Command: get horse by id (no DB changes)
 * @see HorseDAO
 * @see Horse
 * @see ParamParser
 */
public class GetHorseById extends AbstractCommand {
     
    /**
     * Validate input data, get horse info from DB
     */
    @Override    
    protected void doOperation() {    
        ParamParser params = new ParamParser(request);
        try {   
            Horse horse = factory.createHorseDAO().select(params.natural(PARAMETER_ID));
            request.setAttribute(Attributes.HORSE.name().toLowerCase(), horse);  
            if(horse == null) {
                setInfoPage(ERROR_NOT_EXIST, COMMAND_RDRCT, Mapping.ADM_PG, 0);
            }
        } catch(IllegalArgumentException ex) {    
            setInfoPage(ERROR_INVALID_DATA, COMMAND_RDRCT, Mapping.ADM_PG, 0);
        }
    }
    
}
