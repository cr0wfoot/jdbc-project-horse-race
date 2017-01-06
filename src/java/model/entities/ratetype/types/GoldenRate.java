/**
 * **************************************************************************************
 * Module: TypeThreeRaces.java Author: Hrytsiuk Purpose: Defines the Class TypeThreeRaces
 * **************************************************************************************
 */
package model.entities.ratetype.types;

import controller.dao.DAOFactory;
import controller.dao.RaceDAO;
import java.util.List;
import model.entities.Horse;
import model.entities.Message;
import model.entities.Race;
import model.entities.Rate;
import model.entities.ratetype.factory.RateType;
import model.entities.search.criteria.RaceSearchCriteria;

/**
 * Rate Type 3: user can choose only one horse, then first three rates will be chosen
 * @see TypeInterface
 */
public class GoldenRate extends RateType {

     
    /**
     * The value of global coefficient of type 3
     */
    public int COEFF_TYPE_3 = 5;
    /**
     * boolean value of flag if rate wins
     */
    private float coeffHorse = 0;
    private Rate rate;

    @Override
    public void processRate(Rate rate, List<Horse> horses, int raceId) {
        DAOFactory factory = DAOFactory.getInstanceJDBC();
        this.rate = rate;
        boolean isWinner = checkWinner(horses) && rate.getRaces().get(2) == raceId;
        String message = payUser(coeffHorse, rate, isWinner, COEFF_TYPE_3);
        factory.createMessageDAO().insert(new Message(1, rate.getUserId(), message));
        factory.createRateDAO().delete(rate.getId());
    }

    @Override
    protected boolean checkWinner(List<Horse> horses) {
        for(Horse h : horses)
            if(h.getId() == rate.getHorses().get(0).getId())
                if(h.getResult() == 1) {
                    coeffHorse = h.getCoefficient();
                    return true;
                } else break;
        return false;
    }
    
    @Override
    public List<Integer> setRate(List<Integer> races, List<Horse> horses) {
        RaceDAO raceFactory = DAOFactory.getInstanceJDBC().createRaceDAO();
        if(horses.size() == 1) {
            RaceSearchCriteria criteria = new RaceSearchCriteria();
            criteria.setForFixRates(horses.get(0).getId(), raceFactory.select(races.get(0)).getDate());
            List<Race> listRaces = raceFactory.findByCriteria(criteria);
            if(listRaces.size() < 3)
                return null;
            races.clear();
            races.add(listRaces.get(0).getId());
            races.add(listRaces.get(1).getId());
            races.add(listRaces.get(2).getId());
            return races;
        }
        else return null;
    }

}
