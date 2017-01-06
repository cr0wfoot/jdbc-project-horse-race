/**
 * *******************************************************************************************
 * Module: GetAllRates.java Author: Hrytsiuk Purpose: Defines the Class GetAllRates
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;

/**
 * Command: get all rates (no DB changes)
 * @see RateDAO
 */
public class GetAllRates extends AbstractCommand {
    
    /**
     * Get the list of all rates from DB
     */
    @Override
    protected void doOperation() {
        request.setAttribute((Attributes.RATE.name() + Attributes.LIST.name()).toLowerCase(), factory.createRateDAO().selectAll());
        resultURL = Mapping.ADM_RT.getURL();
    }
    
}
