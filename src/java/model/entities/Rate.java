/**
 * ***************************************************************************
 * Module: Rate.java Author: Hrytsiuk Purpose: Defines the Class Rate
 * ***************************************************************************
 */
package model.entities;

import java.util.List;

/**
 * Describes entity Rate
 * @see Horse
 * @see Race
 */
public class Rate {
   
    /**
     * The value of ID rate
     */
    private int id;
    
    /**
     * The value of ID user which rate belongs to
     */
    private int userId;
    
    /**
     * The value of rate type: 1, 2, 3
     */
    private String type;
    
    /**
     * The among of money
     */
    private int money;
    
    /**
     * The list of horses
     */
    private List<Horse> horses;
    
    /**
     * The list of races' ID
     */
    private List<Integer> racesId;

    /**
     * Get the value of rate's ID
     * @return the value of {@link Rate#id}
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of {@link Rate#id}
     * @param id
     * the value of rate's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the value of user's ID
     * @return the value of {@link Rate#userId}
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set the value of {@link Rate#userId}
     * @param id
     * the value of user's ID
     */
    public void setUserId(int id) {
        this.userId = id;
    }

    /**
     * Get the value of rate type
     * @return the value of {@link Rate#type}
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of {@link Rate#type}
     * @param type
     * the value of rate type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Get the value of money
     * @return the value of {@link Rate#money}
     */
    public int getMoney() {
        return money;
    }

    /**
     * Set the value of {@link Rate#money}
     * @param money
     * the among of money
     */
    public void setMoney(int money) {
        this.money = money;
    }
    
    /**
     * Get the list of horses
     * @return the value of {@link Rate#horses}
     */
    public List<Horse> getHorses() {
        return horses;
    }

    /**
     * Set the value of {@link Rate#horses}
     * @param horses
     * the list of horses
     */
    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }
    
    /**
     * Get the list of races' ID
     * @return the value of {@link Rate#racesId}
     */
    public List<Integer> getRaces() {
        return racesId;
    }

    /**
     * Set the value of {@link Rate#racesId}
     * @param racesId
     * the list of races' ID
     */
    public void setRaces(List<Integer> racesId) {
        this.racesId = racesId;
    }
       
    /**
     * Empty constructor
     */
    public Rate() {
        
    }
    
    /**
     * Constructor with six arguments initialize fields 
     * {@link Rate#id}, {@link Rate#userId}, {@link Rate#type}
     * {@link Rate#money}, {@link Rate#horses}, {@link Rate#racesId}
     * 
     * @param id    the int value of rate's ID
     * @param userId    the int value of user's ID
     * @param type    the int value of rate type
     * @param money    the int value of money
     * @param horses    the list of horses
     * @param racesId    the list of races' ID
     */
    public Rate(int id, int userId, String type, int money, List<Horse> horses, List<Integer> racesId) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.money = money;
        this.horses = horses;
        this.racesId = racesId;
    }   
    
    @Override
    public String toString() {
        return "ID=" + id + ";user=" + userId + ";type=" + type +
               ";money=" + money + ";horses=" + horses + ";races=" + racesId;
    }
    
}
