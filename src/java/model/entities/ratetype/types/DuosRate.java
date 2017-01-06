/**
 * ************************************************************************************
 * Module: TypeTwoHorses.java Author: Hrytsiuk Purpose: Defines the Class TypeTwoHorses
 * ************************************************************************************
 */
package model.entities.ratetype.types;

import controller.dao.DAOFactory;
import java.util.List;
import model.entities.Horse;
import model.entities.Message;
import model.entities.Rate;
import model.entities.ratetype.factory.RateType;

/**
 * Rate Type 2: user can choose only two horses in one race
 * @see TypeInterface
 */
public class DuosRate extends RateType {
    
    /**
     * The value of global coefficient of type 2
     */
    public float COEFF_TYPE_2 = 2;
    /**
     * int value of flag: 2 - win, others - lose
     */
    private Rate rate;
    private float coeffHorse = 0;

    @Override
    public void processRate(Rate rate, List<Horse> horses, int raceId) {
        DAOFactory factory = DAOFactory.getInstanceJDBC();
        this.rate = rate;        
        boolean isWinner = checkWinner(horses);
        String message = payUser(coeffHorse, rate, isWinner, COEFF_TYPE_2);
        factory.createMessageDAO().insert(new Message(1, rate.getUserId(), message));
        factory.createRateDAO().delete(rate.getId());       
    }

    @Override
    protected boolean checkWinner(List<Horse> horses) {
        int isWinner = 0;
        for(Horse h : horses) {
            if( (h.getId() == rate.getHorses().get(0).getId()  ||
                 h.getId() == rate.getHorses().get(1).getId()))
                if (h.getResult() == 1 || h.getResult() == 2) { 
                    isWinner++;
                    coeffHorse += h.getCoefficient();
                } else break;
        }
        if(isWinner == 2) return true;
        return false;
    }
    
    @Override
    public List<Integer> setRate(List<Integer> races, List<Horse> horses) {
        if(horses.size() == 2) return races;
        else return null;
    }
    
}
