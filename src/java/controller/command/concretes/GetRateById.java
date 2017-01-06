/**
 * *******************************************************************************************
 * Module: GetRateById.java Author: Hrytsiuk Purpose: Defines the Class GetRateById
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;
import controller.validation.ParamParser;
import model.entities.Rate;

/**
 * Command: get rate by id (no DB changes)
 * @see RateDAO
 * @see Rate
 * @see ParamParser
 */
public class GetRateById extends AbstractCommand {
     
    /**
     * Validate input data, get the list of rates from DB
     */
    @Override    
    protected void doOperation() {     
        ParamParser params = new ParamParser(request);
        try {      
            Rate rate = factory.createRateDAO().select(params.natural(PARAMETER_ID));
            if(rate == null) {
                setInfoPage(ERROR_NOT_EXIST, COMMAND_RDRCT, Mapping.ADM_PG, 0);
            } else {
                request.setAttribute(Attributes.RATE.name().toLowerCase(), rate);  
            }
        } catch(IllegalArgumentException ex) {       
            setInfoPage(ERROR_INVALID_DATA, COMMAND_RDRCT, Mapping.ADM_PG, 0);       
        }       
    }
    
}
