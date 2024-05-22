package com.psc06.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import com.psc06.server.jdo.Sprint;

/**
 * User Story class
 */
@PersistenceCapable
public class UserStory {

    @PrimaryKey
    private int id;

    private String userStory = null;
    private int estimation = 0;
    private int pbPriority = 0;

    Sprint sprint = null;
    
    /**
     * Default constructor
     * @param id User story id
     * @param userStory User story description
     * @param estimation User story estimation
     * @param pbPriority User story priority
     */
	public UserStory(int id, String userStory, int estimation, int pbPriority) {
		this.id = id;
        this.userStory = userStory;
        this.estimation = estimation;
        this.pbPriority = pbPriority;
	}

    /**
     * Constructor
     * @param id User story id
     * @param userStory User story description
     * @param estimation User story estimation
     * @param pbPriority User story priority
     * @param sp Sprint
     */
    public UserStory(int id, String userStory, int estimation, int pbPriority, Sprint sp) {
		this.id = id;
        this.userStory = userStory;
        this.estimation = estimation;
        this.pbPriority = pbPriority;
        this.sprint = sp;
	}

    /**
     * Get the user story id
     * @return User story id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the user story id
     * @param id User story id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the user story description
     * @return User story description
     */
    public String getUserStory() {
        return userStory;
    }

    /**
     * Set the user story description
     * @param userStory User story description
     */
    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    /**
     * Get the user story estimation
     * @return User story estimation
     */
    public int getEstimation() {
        return estimation;
    }

    /**
     * Set the user story estimation
     * @param estimation User story estimation
     */
    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }
    
    /**
     * Get the user story priority
     * @return User story priority
     */
    public int getPbPriority() {
        return pbPriority;
    }

    /**
     * Set the user story priority
     * @param pbPriority User story priority
     */
    public void setPbPriority(int pbPriority) {
        this.pbPriority = pbPriority;
    }

    /**
     * Get the sprint
     * @return Sprint
     */
    public Sprint getSprint() {
        return this.sprint;
    }

    /**
     * Set the sprint
     * @param sprint Sprint
     */
    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    /**
     * Delete the sprint
     */
    public void deleteSprint() {
        this.sprint = null;
    }

    /**
     * Get the string of the user story
     * @return String of the user story
     */
    public String toString() {
        return String.format("User Story %s (%s): [Estimacion: %s. Prioridad: %s.]", 
        this.id, this.userStory, this.estimation, this.pbPriority);
    }

}
