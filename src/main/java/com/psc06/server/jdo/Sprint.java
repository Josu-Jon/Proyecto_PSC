package com.psc06.server.jdo;

import java.util.List;
import java.util.*;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.psc06.server.jdo.UserStory;

/**
 * Sprint class
 */
@PersistenceCapable
public class Sprint {
    
    @PrimaryKey
    int num;
    
    @Persistent(mappedBy="sprint", dependentElement="false")
	@Join
    Set<UserStory> stories = new LinkedHashSet<>();

    /**
     * Default constructor
     
     */
    Sprint (){
    }

    /**
     * Constructor
     * @param num Sprint number
     */
    public Sprint (int num) {
        this.num = num;
    }

    /**
     * Get the sprint number
     * @return Sprint number
     */
    public int getSprintNum(){
        return this.num;
    }

    /**
     * Set the sprint number
     * @param num Sprint number
     */
    public void setSprintNum(int num){
        this.num = num;
    }

    /**
     * Add a story to the sprint
     * @param story User story
     */
    public void addStory(UserStory story){
        stories.add(story);
    }

    /**
     * Remove a story from the sprint
     * @param story User story
     */
    public void removeStory(UserStory story){
        stories.remove(story);
    }

    /**
     * Get all stories in the sprint
     * @return Set of user stories
     */
    public Set<UserStory> getAllStories() {
        return this.stories;
    }

    /**
     * Get the string of the list
     * @return String of the list of stories
     */
    public String toString(){
        StringBuilder sprintStories = new StringBuilder();
        for (UserStory story: this.stories) {
            sprintStories.append(story.toString() + " --- ");
        }

        return String.format("Sprint %s: [%s]", num, sprintStories);
    }

}
