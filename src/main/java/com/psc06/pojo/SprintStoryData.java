package com.psc06.pojo;

/**
 * SprintStoryData Serialized class
 */
public class SprintStoryData {
    
    private SprintData sprintData;
    private UserStoryData userStoryData;

    /**
     * Constructor sprint and user story assigned
     */
    public SprintStoryData() {
        // Required for serialization
    }

    /**
     * Constructor sprint and user story assigned
     * @return SprintData
     */
    public SprintData getSprintData() {
        return sprintData;
    }

    /**
     * Get the sprint data
     * @param sprintData Sprint data
     */
    public void setSprintData(SprintData sprintData) {
        this.sprintData = sprintData;
    }

    /**
     * Get the user story data
     * @return UserStoryData
     */
    public UserStoryData getUserStoryData() {
        return userStoryData;
    }

    /**
     * Set the user story data
     * @param userStoryData User story data
     */
    public void setUserStoryData(UserStoryData userStoryData) {
        this.userStoryData = userStoryData;
    }

}
