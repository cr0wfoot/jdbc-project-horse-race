/**
 * *************************************************************************************
 * Module: TypeOneHorse.java Author: Hrytsiuk Purpose: Defines the Class TypeOneHorse
 * *************************************************************************************
 */
package model.entities.ratetype.types;

import controller.dao.DAOFactory;
import java.util.List;
import model.entities.Horse;
import model.entities.Message;
import model.entities.Rate;
import model.entities.ratetype.factory.RateType;

/**
 * Rate Type 1: user can choose only one horse in one race
 * @see TypeInterface
 */
public class SimpleRate extends RateType {
    
    /**
     * The value of global coefficient of type 1
     */
    public float COEFF_TYPE_1 = 1;
    /**
     * boolean value of flag if rate wins
     */
    private float coeffHorse = 0;
    private Rate rate;

    @Override
    public void processRate(Rate rate, List<Horse> horses, int raceId) {
        DAOFactory factory = DAOFactory.getInstanceJDBC();
        boolean isWinner = checkWinner(horses);
        String message = payUser(coeffHorse, rate, isWinner, COEFF_TYPE_1);
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
        if(horses.size() == 1) return races;
        else return null;
    }
    
}
