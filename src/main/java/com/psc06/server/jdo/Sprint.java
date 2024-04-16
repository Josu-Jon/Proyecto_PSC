package com.psc06.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import java.util.LinkedHashSet;
import com.psc06.server.jdo.UserStory;


@PersistenceCapable
public class Sprint {
    
    @PrimaryKey
    int num;
    
    @Persistent(mappedBy="sprint", dependentElement="false")
	@Join
    Set<UserStory> stories = new LinkedHashSet<>();

    public Sprint (){
    }

    public Sprint (int num) {
        this.num = num;
    }

    public int getSprintNum(){
        return this.num;
    }

    public void setSprintNum(int num){
        this.num = num;
    }

    public void addStory(UserStory story){
        stories.add(story);
    }

    public void removeStory(UserStory story){
        stories.remove(Story);
    }

    public Set<UserStory> getAllStories() {
        return this.stories;
    }

    public String toString(){
        StringBuilder sprintStories = new StringBuilder();
        for (UserStory story: this.stories) {
            sprintStories.append(story.toString() + " --- ");
        }

        return "Sprint nº: " + this.num + ", User stories --> [" + sprintStories + "]";
    }

}
