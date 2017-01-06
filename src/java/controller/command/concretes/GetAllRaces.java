/**
 * *******************************************************************************************
 * Module: GetAllRaces.java Author: Hrytsiuk Purpose: Defines the Class GetAllRaces
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.*;
import javax.servlet.http.HttpSession;
import model.entities.search.criteria.RaceSearchCriteria;

/**
 * Command: get all races (no DB changes)
 * @see RaceDAO
 */
public class GetAllRaces extends AbstractCommand {
    
    /**
     * Get the list of all races from DB
     */
    @Override
    protected void doOperation() {
        RaceSearchCriteria criteria = new RaceSearchCriteria();
        criteria.setFilterByStatus(filterBy());
        request.setAttribute((Attributes.RACE.name() + Attributes.LIST.name()).toLowerCase(), factory.createRaceDAO().findByCriteria(criteria));
    }
    
    /**
     * Filter the list of races and set url according to user's role 
     */
    private String filterBy() {
        HttpSession session = request.getSession(true);
        switch(Access.valueOf(session.getAttribute(SessionAttr.ACCESS.name().toLowerCase()).toString().toUpperCase())) {
            case USER  : 
                resultURL = Mapping.USR_PG.getURL();
                return RaceStatus.NEW.name().toLowerCase();
            case BOOKIE  : 
                resultURL = Mapping.BOO_PG.getURL();
                return RaceStatus.CLOSED.name().toLowerCase();
            case ADMIN :
            default    :
                resultURL = Mapping.ADM_RC.getURL();
                return null;
        }
    }
    
}
