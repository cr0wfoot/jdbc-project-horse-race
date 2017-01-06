/**
 * ***********************************************************************************************
 * Module: TypeInterface.java Author: Hrytsiuk Purpose: Defines the Interface TypeInterface
 * ***********************************************************************************************
 */
package model.entities.ratetype.factory;

import controller.dao.DAOFactory;
import controller.dao.UserDAO;
import java.util.List;
import model.entities.Horse;
import model.entities.Rate;
import model.entities.User;

/**
 * Type Object interface
 * @see TypeOneHorse
 * @see TypeTwoHorses
 * @see TypeThreeRaces
 */
public abstract class RateType {
    
    /**
     * The head of message for user
     */
    public String MESSAGE_HEAD = "Your rate number ";
    
    /**
     * The part of message when rate wins
     */
    public String MESSAGE_WON = " has won +";
    
    /**
     * The part of message when rate fails
     */
    public String MESSAGE_FAILED = " has failed, your bet was ";
    
    
    
    
   
    
    /**
     * Proccess all rates when admin fix results, each type has its own realisation,
     * and consits of two parts: check winner horse and make some DB changes
     * @param factory    DAO factory
     * @param rate    Object Rate
     * @param horses    the list of horses
     * @param idRate    the value of rate ID
     * @return Object TypeInterface
     */
    public abstract void processRate(Rate rate, List<Horse> horses, int raceId);
    
    protected String payUser(float coeffHorse, Rate rate,boolean isWinner, float coeff) { 
        String content = MESSAGE_HEAD + rate.getId();
        if(isWinner) {
            UserDAO userFactory = DAOFactory.getInstanceJDBC().createUserDAO();
            User user = userFactory.select(rate.getUserId());
            int money = (int)(rate.getMoney() * coeffHorse * coeff);
            user.setBudget(user.getBudget() + money);
            userFactory.update(user);
            content += MESSAGE_WON + money;
        } else content += MESSAGE_FAILED + rate.getMoney();
        return content;
    }
        
    protected abstract boolean checkWinner(List<Horse> horses);
    
    /**
     * Set user's rate
     * @param factory    DAO factory
     * @param races    the list of races' ID 
     * @param horses    the list of horses
     * @return list of races' ID
     */
    public abstract List<Integer> setRate(List<Integer> races, List<Horse> horses);
    
}
