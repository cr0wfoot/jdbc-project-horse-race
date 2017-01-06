/**
 * *******************************************************************************************
 * Module: GetAllHorses.java Author: Hrytsiuk Purpose: Defines the Class GetAllHorses
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Attributes;

/**
 * Command: get all horses (no DB changes)
 * @see HorseDAO
 */
public class GetAllHorses extends AbstractCommand {
    
    /**
     * Get the list of all horses from DB
     */
    @Override
    protected void doOperation() {
        request.setAttribute((Attributes.HORSE.name() + Attributes.LIST.name()).toLowerCase(), factory.createHorseDAO().selectAll()); 
        resultURL = getURL();
    }
    
}
