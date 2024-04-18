package com.psc06.pojo;

public class UserStoryData {

    private int id;
    private String userStory;
    private int estimation;
    private int pbPriority;

    public UserStoryData(){
        // Serialization
    }

    public UserStoryData(int i, String string, int j, int k) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUserStory() {
        return userStory;
    }
    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    public int getEstimation() {
        return estimation;
    }
    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }
    
    public int getPbPriority() {
        return pbPriority;
    }
    public void setPbPriority(int pbPriority) {
        this.pbPriority = pbPriority;
    }

    public String toString() {
        return String.format("User Story %s (%s): [Estimacion: %s. Prioridad: %s.]", 
        this.id, this.userStory, this.estimation, this.pbPriority);
    }

}