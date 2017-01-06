/**
 * ***************************************************************************
 * Module: User.java Author: Hrytsiuk Purpose: Defines the Class User
 * ***************************************************************************
 */
package model.entities;

/**
 * Describes entity User
 */
public class User {
    
    /**
     * The value of ID user
     */
    private int id;
    
    /**
     * The value of user login-email
     */
    private String login;
    
    /**
     * The value of password hash
     */
    private String password;
    
    /**
     * The value of password salt, consists of three numbers
     */
    private int salt;
    
    /**
     * The value of user's name
     */
    private String firstName;
    
    /**
     * The value of user's surname
     */
    private String secondName;
    
    /**
     * The value of user's registration date
     */
    private String regDate;
    
    /**
     * The value of user's budget
     */
    private int budget;
    
    /**
     * The total value of user's rates count
     */
    private int totalRatesCount;
    
    /**
     * The value of user's access level
     */
    private String access;

    /**
     * Get the value of user's ID
     * @return the value of {@link User#id}
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of {@link User#id}
     * @param id
     * user's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the value of user's login
     * @return the value of {@link User#login}
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the value of {@link User#login}
     * @param login
     * login-email
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get the value of user's password hash
     * @return the value of {@link User#password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the hash value of {@link User#password}
     * @param password
     * password hash
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Get the value of password salt
     * @return the value of {@link User#salt}
     */
    public int getSalt() {
        return salt;
    }

    /**
     * Set the value of {@link User#salt}
     * @param salt
     * password salt
     */
    public void setSalt(int salt) {
        this.salt = salt;
    }
    
    /**
     * Get the value of user's name
     * @return the value of {@link User#firstName}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of {@link User#firstName}
     * @param firstName
     * user's name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Get the value of user's surname
     * @return the value of {@link User#secondName}
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Set the value of {@link User#secondName}
     * @param secondName
     * user's surname
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    
    /**
     * Get the value of user's registration date
     * @return the value of {@link User#regDate}
     */
    public String getRegDate() {
        return regDate;
    }

    /**
     * Set the value of {@link User#regDate}
     * @param regDate
     * registration date
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
       
    /**
     * Get the value of user's budget
     * @return the value of {@link User#budget}
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Set the value of {@link User#budget}
     * @param budget
     * user's budget
     */
    public void setBudget(int budget) {
        this.budget = budget;
    }
    
    /**
     * Get the total value of user's rates count
     * @return the value of {@link User#totalRatesCount}
     */
    public int getTotalRatesCount() {
        return totalRatesCount;
    }

    /**
     * Set the value of {@link User#totalRatesCount}
     * @param count
     * total rates count
     */
    public void setTotalRatesCount(int count) {
        this.totalRatesCount = count;
    }
    
    /**
     * Get the value of user's access level
     * @return the value of {@link User#access}
     */
    public String getAccess() {
        return access;
    }

    /**
     * Set the value of {@link User#access}
     * @param access
     * access level
     */
    public void setAccess(String access) {
        this.access = access;
    }
 
    /**
     * Empty constructor
     */
    public User() {
        
    }
    
    /**
     * Constructor with ten arguments initialize fields 
     * {@link User#id}, {@link User#login}, {@link User#salt}, {@link User#password}
     * {@link User#firstName}, {@link User#secondName}, {@link User#regDate}
     * {@link User#budget}, {@link User#totalRatesCount}, {@link User#access}
     * 
     * @param id    the int value of user's ID
     * @param login    the String value of user's login
     * @param salt    the int value of password salt
     * @param password    the hashed String value of password
     * @param firstName    the String value of name
     * @param secondName    the String value of surname
     * @param regDate    the String value of registration date
     * @param budget    the int value of user's budget
     * @param count    the int value of total rates count
     * @param access    the int value of user's access level
     */
    public User(int id, String login, int salt, String password, String firstName,
                String secondName, String regDate, int budget, int count, String access) {
        this.id = id;
        this.login = login;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.regDate = regDate;
        this.budget = budget;
        this.totalRatesCount = count;
        this.access = access;
    }   
    
    @Override
    public String toString() {
        return "ID=" + id + ";login=" + login + ";name=" + firstName + ";surname=" +
               secondName + ";regDate=" + regDate + 
               ";budget=" + budget + ";ratesCount=" + totalRatesCount + ";access=" + access;
    }
    
}
