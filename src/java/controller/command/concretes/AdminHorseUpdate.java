/**
 * *******************************************************************************************
 * Module: AdminHorseUpdate.java Author: Hrytsiuk Purpose: Defines the Class AdminHorseUpdate
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;
import controller.validation.ParamParser;
import model.entities.Horse;

/**
 * Command: admin update or delete horse (DB changes)
 * @see ParamParser
 * @see HorseDAO
 * @see Horse
 */
public class AdminHorseUpdate extends AbstractCommand {
      
    /**
     * Validate input data, delete horse from DB or update horse in DB
     */
    @Override        
    protected void doOperation() {
        if(checkAccess(Access.ADMIN)) {
            ParamParser params = new ParamParser(request);
            if(request.getParameter(PARAMETER_DELETE) != null) {
                factory.createHorseDAO().delete(Integer.parseInt(request.getParameter(PARAMETER_ID)));
                setInfoPage(ERROR_SUCCESS, COMMAND_ALL_HRS, Mapping.ADM_HRS, 0);    
            } else {  
                try {
                       factory.createHorseDAO().update(
                               new Horse(Integer.parseInt(request.getParameter(PARAMETER_ID)), 
                                         params.text(PARAMETER_HORSE_NAME), params.fullName(PARAMETER_HORSE_RIDER), params.text(PARAMETER_HORSE_BREED),
                                         params.natural(PARAMETER_HORSE_RANK), params.natural(PARAMETER_HORSE_RACES_COUNT), 0));
                       setInfoPage(ERROR_SUCCESS, COMMAND_ALL_HRS, Mapping.ADM_HRS, 0);
                } catch(IllegalArgumentException ex) {
                    setInfoPage(ERROR_INVALID_DATA, COMMAND_ALL_HRS, Mapping.ADM_HRS, 0);
                }
            }
        } else {
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0); 
        }       
    }
    
}
