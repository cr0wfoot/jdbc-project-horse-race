package model.entities.search.criteria;

public class RateSearchCriteria {
    
    private int userId;
    private int type;
    private int raceId;
    private int minMoney;

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public boolean isEmpty() {
        return userId == 0 && type == 0 && raceId == 0 && minMoney == 0;
    }
    
}
