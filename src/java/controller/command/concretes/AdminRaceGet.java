/**
 * *******************************************************************************************
 * Module: AdminRaceGet.java Author: Hrytsiuk Purpose: Defines the Class AdminRaceGet
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;
import java.util.List;
import model.entities.Horse;
import model.entities.Race;

/**
 * Command: admin get race info and all horses (no DB changes)
 * @see HorseDAO
 * @see RaceDAO
 * @see Horse
 * @see Race
 */
public class AdminRaceGet extends AbstractCommand {
    
    /**
     * Get race and all horses exept for horses belong to selected race
     */
    @Override        
    protected void doOperation() {
        if(checkAccess(Access.ADMIN)) {   
            List<Horse> horses = factory.createHorseDAO().selectAll(); 
            Race race = factory.createRaceDAO().select(Integer.parseInt(request.getParameter(PARAMETER_ID)));
            horses.removeAll(race.getHorses());
            request.setAttribute(Attributes.HORSE.name().toLowerCase() + Attributes.LIST.name().toLowerCase(), horses);
            request.setAttribute(Attributes.RACE.name().toLowerCase(), race);
            resultURL = Mapping.ADM_CHNG_RC.getURL();
        } else {
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0); 
        }        
    }
    
}
