/**
 * ***************************************************************************
 * Module: Race.java Author: Hrytsiuk Purpose: Defines the Class Race
 * ***************************************************************************
 */
package model.entities;

import controller.config.constants.RaceStatus;
import java.util.List;

/**
 * Describes entity Race
 * @see Horse
 */
public class Race {
    
    /**
     * The value of ID race
     */
    private int id;
    
    /**
     * The value of place where race is
     */
    private String place;
    
    /**
     * The value of distance
     */
    private int distance;
    
    /**
     * The value of date
     */
    private String date;
    
    /**
     * The value of race status: closed, open, new
     */
    private RaceStatus status;
    
    /**
     * The list of horses participated in this race
     */
    private List<Horse> horses;

    /**
     * Get the value of race's ID
     * @return the value of {@link Race#id}
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of {@link Race#id}
     * @param id
     * the value of race's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the value of race's place
     * @return the value of {@link Race#place}
     */
    public String getPlace() {
        return place;
    }

    /**
     * Set the value of {@link Race#place}
     * @param place
     * the value of place
     */
    public void setPlace(String place) {
        this.place = place;
    }
    
    /**
     * Get the value of race's distance
     * @return the value of {@link Race#distance}
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Set the value of {@link Race#distance}
     * @param distance
     * the value of distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Get the value of race's date
     * @return the value of {@link Race#date}
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the value of {@link Race#date}
     * @param date
     * the value of date
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * Get the value of race status
     * @return the value of {@link Race#status}
     */
    public RaceStatus getStatus() {
        return status;
    }

    /**
     * Set the value of {@link Race#status}
     * @param status
     * the value of status
     */
    public void setStatus(RaceStatus status) {
        this.status = status;
    }
    
    /**
     * Get the list of horses
     * @return the list of {@link Race#horses}
     */
    public List<Horse> getHorses() {
        return horses;
    }

    /**
     * Set the value of {@link Race#horses}
     * @param horses
     * the list of horses
     */
    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }

    /**
     * Get the size of list of horses
     * @return the value of list size
     */
    public int getHorseCount() {
        return horses.size();
    }

    /**
     * Empty constructor
     */
    public Race() {
        
    }
    
    /**
     * Constructor with six arguments initialize fields 
     * {@link Race#id}, {@link Race#place}, {@link Race#distance}
     * {@link Race#date}, {@link Race#status}, {@link Race#horses}
     * 
     * @param id    the int value of race's ID
     * @param place    the String value of place
     * @param distance    the int value of distance
     * @param date    the String value of date
     * @param status    the String value of status
     * @param horses    the list of horses
     */
    public Race(int id, String place, int distance, String date, RaceStatus status, List<Horse> horses) {
        this.id = id;
        this.place = place;
        this.distance = distance;
        this.date = date;
        this.status = status;
        this.horses = horses;
    }   
    
    @Override
    public String toString() {
        return "ID=" + id + ";place=" + place + ";distance=" + distance + 
               ";date=" + date + ";status=" + status + ";horses=" + horses;
    }
    
}
