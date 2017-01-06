package model.entities.search.criteria;

public class UserSearchCriteria {
    
    private String login;
    private String firstName;
    private String secondName;
    private int minBudget;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(int minBudget) {
        this.minBudget = minBudget;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getLogin() {
        return login;
    }
    
    public boolean isEmpty() {
        return login == null && firstName == null && secondName == null && minBudget == 0.0;
    }
    
}
