/**
 * ************************************************************************************************
 * Module: AdminRaceFixResults.java Author: Hrytsiuk Purpose: Defines the Class AdminRaceFixResults
 * ************************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;
import controller.config.constants.Access;
import controller.config.constants.Mapping;
import controller.config.constants.RaceStatus;
import controller.dao.DAOFactory;
import controller.dao.HorseDAO;
import controller.validation.ParamParser;
import java.util.LinkedList;
import java.util.List;
import model.entities.Horse;
import model.entities.Race;
import model.entities.Rate;
import model.entities.ratetype.factory.Creator;
import model.entities.ratetype.factory.RateType;
import model.entities.ratetype.factory.TypeFactory;
import model.entities.ratetype.factory.TypeFactory.Rates;
import model.entities.search.criteria.RateSearchCriteria;

/**
 * Command: admin fix race results (DB changes)
 * @see DAOFactory
 * @see RaceDAO
 * @see RateDAO
 * @see HorseDAO
 * @see Horse
 * @see Race
 * @see Rate
 * @see ParamParser
 * @see TypeInterface
 */
public class AdminRaceFixResults extends AbstractCommand {
      
    /**
     * Validate input data, get all rates and fix results according to rate type,
     * update horses in DB, update race in DB 
     */
    @Override        
    protected void doOperation() {
        if(checkAccess(Access.ADMIN)) {    
            ParamParser params = new ParamParser(request);
            int raceId = Integer.parseInt(request.getParameter(PARAMETER_ID));
            RateSearchCriteria criteria = new RateSearchCriteria();
            criteria.setRaceId(raceId);
            List<Rate> rates = factory.createRateDAO().findByCriteria(criteria);
            List<Horse> horses = new LinkedList<Horse>();
            Race race = factory.createRaceDAO().select(raceId);
            getHorsesResultsFromRequest(race, horses, params);
            race.setStatus(RaceStatus.CLOSED);
                
                
                Creator creator = new TypeFactory();            
                RateType type;            
                startTransaction();                      
                for(Rate r : rates) {               
                    type = creator.getType(Rates.valueOf(r.getType()));               
                    type.processRate(r, race.getHorses(), raceId);                
                }               
                for(Horse h : horses) {               
                    factory.createHorseDAO().update(h);               
                }               
                factory.createRaceDAO().update(race); 
                commitTransaction();

                
        } else {
            setInfoPage(ERROR_ACCESS, COMMAND_RDRCT, Mapping.INDEX, 0);  
        }        
    }
      
    /**
     * Get horses results from request and set them
     * @param race
     * an object of class Race
     * @param horses
     * the list of horses
     * @param params
     * an object of class ParamParser to use list of parameters in it
     * @return the boolean value
     */
    private void getHorsesResultsFromRequest(Race race, List<Horse> horses, ParamParser params) {
        HorseDAO horseFactory = factory.createHorseDAO();
        Horse horse;
        int result;
        try {
            for(Horse h : race.getHorses()) {
                if((result = params.natural(String.valueOf(h.getId()))) <= 0)
                    throw new IllegalArgumentException();
                h.setResult(result);
                horse = horseFactory.select(h.getId());
                horse.setRacesCount(horse.getRacesCount() + 1);
                horse.setRank(horse.getRank() + MAX_SCORE - result);
                horses.add(horse);
            }
        } catch(IllegalArgumentException ex) {
            setInfoPage(ERROR_INVALID_DATA, COMMAND_ALL_RC, Mapping.ADM_RC, 0);            
        }
    }
    
}
