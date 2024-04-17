package com.psc06.pojo;

public class SprintData {
    
    int sprintNum;

    public SprintData(){
        // Required for serialization
    }

    public int getSprintNum() {
        return sprintNum;
    }

    public void setSprintNum(int sprintNum) {
        this.sprintNum = sprintNum;
    }

    public String toString(){
        return "Sprint número " + this.sprintNum;
    }

}
