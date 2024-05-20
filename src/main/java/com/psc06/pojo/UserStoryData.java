package com.psc06.pojo;

/**
 * User Story Serialized class
 */
public class UserStoryData {

    private int id;
    private String userStory;
    private int estimation;
    private int pbPriority;

    /**
     * Constructor UserStory serialized
     */
    public UserStoryData(){
        // Serialization
    }

    /**
     * Get serialized Id
     * @return Userstory UD
     */
    public int getId() {
        return id;
    }

    /**
     * Set serialized Id
     * @param id Userstory ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get serialized User Story
     * @return User Story
     */
    public String getUserStory() {
        return userStory;
    }

    /**
     * Set serialized User Story
     * @param userStory User Story
     */
    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    /**
     * Get serialized Estimation
     * @return Estimation
     */
    public int getEstimation() {
        return estimation;
    }

    /**
     * Set serialized Estimation
     * @param estimation Estimation
     */
    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }
    
    /**
     * Get serialized Priority
     * @return Priority
     */
    public int getPbPriority() {
        return pbPriority;
    }

    /**
     * Set serialized Priority
     * @param pbPriority Priority
     */
    public void setPbPriority(int pbPriority) {
        this.pbPriority = pbPriority;
    }

    /**
     * Get serialized string
     * @return Serialized string
     */
    public String toString() {
        return String.format("User Story %s (%s): [Estimacion: %s. Prioridad: %s.]", 
        this.id, this.userStory, this.estimation, this.pbPriority);
    }

}