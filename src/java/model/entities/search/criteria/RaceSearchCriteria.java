package model.entities.search.criteria;

public class RaceSearchCriteria {
    
    private int distance;
    private int horseId;
    private String minDate;
    private String filterByStatus;
    private boolean forFixRates = false;

    public boolean isForFixRates() {
        return forFixRates;
    }

    public void setForFixRates(int horseId, String date) {
        this.horseId = horseId;
        this.minDate = date;
        this.forFixRates = true;
    }
    
    public String getFilterByStatus() {
        return filterByStatus;
    }

    public void setFilterByStatus(String status) {
        this.filterByStatus = status;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }
    
    public boolean isEmpty() {
        return !forFixRates && filterByStatus == null && minDate == null && horseId == 0 && distance == 0;
    }
    
}
