package com.psc06.pojo;

public class SprintData {
    
    private int sprintNum;
    private String startDate;
    private String endDate;

    public SprintData(){
        // Required for serialization
    }

    public int getSprintNum() {
        return sprintNum;
    }

    public void setSprintNum(int sprintNum) {
        this.sprintNum = sprintNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String toString(){
        return String.format("Sprint %s. Start date: %s, End date: %s", this.sprintNum, this.startDate, this.endDate);
    }

}
