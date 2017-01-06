/**
 * *******************************************************************************************
 * Module: AdminHorseNew.java Author: Hrytsiuk Purpose: Defines the Class AdminHorseNew
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;
import controller.validation.ParamParser;
import model.entities.Horse;

/**
 * Command: insert new horse (DB changes)
 * @see ParamParser
 * @see HorseDAO
 * @see Horse
 */
public class AdminHorseNew extends AbstractCommand {
       
    /**
     * Validate input data, insert new horse into DB
     * into DB, update user in DB
     */
    @Override       
    protected void doOperation() {
        if(checkAccess(Access.ADMIN)) {
            ParamParser params = new ParamParser(request);  
            try {
                factory.createHorseDAO().insert(
                        new Horse(1, params.text(PARAMETER_HORSE_NAME), params.fullName(PARAMETER_HORSE_RIDER), 
                                     params.text(PARAMETER_HORSE_BREED), params.natural(PARAMETER_HORSE_RANK), 
                                     params.natural(PARAMETER_HORSE_RACES_COUNT), 0));
                setInfoPage(ERROR_SUCCESS, COMMAND_ALL_HRS, Mapping.ADM_HRS, 0);
            } catch(IllegalArgumentException ex) {
                setInfoPage(ERROR_INVALID_DATA, COMMAND_ALL_HRS, Mapping.ADM_HRS, 0);
            }
        } else {
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0);  
        }        
    }
    
}
