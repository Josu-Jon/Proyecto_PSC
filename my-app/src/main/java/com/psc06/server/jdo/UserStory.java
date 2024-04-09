package com.psc06.server.jdo;

public class UserStory {
    
    int id;
    String userStory;
    int estimation;
    int pbPriority;


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

}
