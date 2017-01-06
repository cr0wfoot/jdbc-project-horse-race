/**
 * *******************************************************************************************
 * Module: GetRateByIdUser.java Author: Hrytsiuk Purpose: Defines the Class GetRateByIdUser
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Attributes;
import controller.config.constants.Mapping;
import controller.validation.ParamParser;
import java.util.List;
import model.entities.Rate;
import model.entities.search.criteria.RateSearchCriteria;

/**
 * Command: get rates by user's id (no DB changes)
 * @see RateDAO
 * @see Rate
 * @see ParamParser
 */
public class GetRateByIdUser extends AbstractCommand {
   
    /**
     * Validate input data, get the list of rates from DB by user's id
     */
    @Override
    protected void doOperation() {
        ParamParser params = new ParamParser(request); 
        try {
            RateSearchCriteria criteria = new RateSearchCriteria();
            criteria.setUserId(params.natural(PARAMETER_ID));
            List<Rate> rates = factory.createRateDAO().findByCriteria(criteria);
            request.setAttribute((Attributes.RATE.name() + Attributes.LIST.name()).toLowerCase(), rates);   
            if(rates.isEmpty()) {
                setInfoPage(ERROR_NOT_EXIST, COMMAND_RDRCT, Mapping.ADM_PG, 0);
            }
        } catch(IllegalArgumentException ex) {
            setInfoPage(ERROR_INVALID_DATA, COMMAND_RDRCT, Mapping.ADM_PG, 0);
        }
    }
    
}
