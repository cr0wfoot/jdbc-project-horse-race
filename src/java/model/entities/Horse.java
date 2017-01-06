/**
 * ***************************************************************************
 * Module: Horse.java Author: Hrytsiuk Purpose: Defines the Class Horse
 * ***************************************************************************
 */
package model.entities;

/**
 * Describes entity Horse
 */
public class Horse {
    
    /**
     * The value of ID horse
     */
    private int id;
    
    /**
     * The name of horse
     */
    private String name;
    
    /**
     * The name of horse rider
     */
    private String rider;
    
    /**
     * The name of horse breed
     */
    private String breed;
    
    /**
     * Total horse score
     */
    private int rank;
    
    /**
     * Total races count
     */
    private int racesCount;
    
    /**
     * Horse coefficient according to race sets by bookie
     */
    private float coefficient;
    
    /**
     * Horse result according to race fixes by admin
     */
    private int result;

    /**
     * Get the value of horse's ID
     * @return the value of {@link Horse#id}
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of {@link Horse#id}
     * @param id
     * the value of ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the value of horse's name
     * @return the value of {@link Horse#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of {@link Horse#name}
     * @param name
     * the value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of horse's rider
     * @return the value of {@link Horse#rider}
     */
    public String getRider() {
        return rider;
    }

    /**
     * Set the value of {@link Horse#rider}
     * @param rider
     * the value of rider
     */
    public void setRider(String rider) {
        this.rider = rider;
    }
    
    /**
     * Get the value of horse's breed
     * @return the value of {@link Horse#breed}
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Set the value of {@link Horse#breed}
     * @param breed
     * the value of breed
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    /**
     * Get the value of horse's total score
     * @return the value of {@link Horse#rank}
     */
    public int getRank() {
        return rank;
    }

    /**
     * Set the value of {@link Horse#rank}
     * @param rank
     * the value of rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
    
    /**
     * Get the value of horse's total race count
     * @return the value of {@link Horse#racesCount}
     */
    public int getRacesCount() {
        return racesCount;
    }

    /**
     * Set the value of {@link Horse#racesCount}
     * @param count
     * the value of races count
     */
    public void setRacesCount(int count) {
        this.racesCount = count;
    }
    
    /**
     * Get the value of horse's coefficient
     * @return the value of {@link Horse#coefficient}
     */
    public float getCoefficient() {
        return coefficient;
    }

    /**
     * Set the value of {@link Horse#coefficient}
     * @param coeff
     * the value of coefficient
     */
    public void setCoefficient(float coeff) {
        this.coefficient = coeff;
    }
    
    /**
     * Get the value of horse's result
     * @return the value of {@link Horse#coefficient}
     */
    public int getResult() {
        return result;
    }

    /**
     * Set the value of {@link Horse#result}
     * @param result
     * the value of result
     */
    public void setResult(int result) {
        this.result = result;
    }
    
    /**
     * Empty constructor
     */
    public Horse() {
        
    }
    
    /**
     * Constructor with seven arguments initialize fields 
     * {@link Horse#id}, {@link Horse#name}, {@link Horse#rider}, {@link Horse#breed},
     * {@link Horse#rank}, {@link Horse#racesCount}, {@link Horse#coefficient}
     * 
     * @param id    the int value of id
     * @param name    the String value of name
     * @param rider    the String value of rider
     * @param breed    the String value of breed
     * @param rank    the int value of rank
     * @param count    the int value of total race count
     * @param coeff    the float value of coefficient
     */
    public Horse(int id, String name, String rider, String breed, int rank, int count, float coeff) {
        this.id = id;
        this.name = name;
        this.rider = rider;
        this.breed = breed;
        this.rank = rank;
        this.racesCount = count;
        this.coefficient = coeff;
    }   
    
    @Override
    public String toString() {
        return "ID=" + id + ";name=" + name + ";rider=" + rider + ";breed=" + breed + 
               ";rank=" + rank + ";racesCount=" + racesCount + ";coeff=" + coefficient +
               ";result=" + result;
    }
    
    @Override
    public boolean equals(Object horse) {
        if(((Horse)horse).id == id)
        return true;
        else return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        return hash;
    }
    
}
