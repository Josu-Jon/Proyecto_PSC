package com.psc06.pojo;

public class SprintStoryData {
    
    private SprintData sprintData;
    private UserStoryData userStoryData;

    public SprintStoryData() {
        // Required for serialization
    }

    public SprintData getSprintData() {
        return sprintData;
    }


    public void setSprintData(SprintData sprintData) {
        this.sprintData = sprintData;
    }


    public UserStoryData getUserStoryData() {
        return userStoryData;
    }


    public void setUserStoryData(UserStoryData userStoryData) {
        this.userStoryData = userStoryData;
    }

    

    

}
