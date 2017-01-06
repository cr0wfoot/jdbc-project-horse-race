/**
 * *******************************************************************************************
 * Module: AdminRaceDelete.java Author: Hrytsiuk Purpose: Defines the Class AdminRaceDelete
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;
import controller.config.constants.RaceStatus;
import controller.dao.RaceDAO;

/**
 * Command: admin delete race (DB changes)
 * @see RaceDAO
 */
public class AdminRaceDelete extends AbstractCommand {
     
    /**
     * Delete race from DB
     */
    @Override        
    protected void doOperation() {
        if(checkAccess(Access.ADMIN)) {
            RaceDAO race = factory.createRaceDAO();
            int id = Integer.parseInt(request.getParameter(PARAMETER_ID));
            if(RaceStatus.OPEN.equals(race.select(id).getStatus())) {
                setInfoPage(ERROR_FORBIDDEN_OPERATION, COMMAND_ALL_RC, Mapping.ADM_RC, 0);
            } else {
                race.delete(id);
                setInfoPage(ERROR_SUCCESS, COMMAND_ALL_RC, Mapping.ADM_RC, 0);
            }
        } else {
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0);  
        }        
    }
    
}
