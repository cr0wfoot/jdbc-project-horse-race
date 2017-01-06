package model.entities.search.criteria;

public class MessageSearchCriteria {
    
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }
    
    public boolean isEmpty() {
        return userId == 0;
    }
    
}
