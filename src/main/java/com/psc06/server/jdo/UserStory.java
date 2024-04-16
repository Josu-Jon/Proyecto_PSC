package com.psc06.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Persistent;

import java.util.LinkedHashSet;

import com.psc06.server.jdo.Sprint;

@PersistenceCapable
public class UserStory {

    @PrimaryKey
    private int id;

    private String userStory = null;
    private int estimation = 0;
    private int pbPriority = 0;

    Sprint sprint = null;
    
	public UserStory(int id, String userStory, int estimation, int pbPriority) {
		this.id = id;
        this.userStory = userStory;
        this.estimation = estimation;
        this.pbPriority = pbPriority;
	}

    public UserStory(int id, String userStory, int estimation, int pbPriority, Sprint sp) {
		this.id = id;
        this.userStory = userStory;
        this.estimation = estimation;
        this.pbPriority = pbPriority;
        this.sprint = sp;
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

    public Sprint getSprint() {
        return this.sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public String toString() {
        return "User story: " + this.userStory + " [Estimación: " + this.estimation + "y prioridad: " + this.pbPriority + "]";
    }

}
