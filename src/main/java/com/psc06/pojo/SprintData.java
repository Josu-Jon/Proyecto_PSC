package com.psc06.pojo;

import java.util.ArrayList;
import java.util.List;

public class SprintData {
    
    private int sprintNum;
    private String startDate;
    private String endDate;
    private List<UserStoryData> userStories;

    public SprintData() {
        // Required for serialization
        this.userStories = new ArrayList<>();
    }

    public int getSprintNum() {
        return sprintNum;
    }

    public void setSprintNum(int sprintNum) {
        this.sprintNum = sprintNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<UserStoryData> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStoryData> userStories) {
        this.userStories = userStories;
    }

    public void addUserStory(UserStoryData userStory) {
        this.userStories.add(userStory);
    }
    public void clearUserStories() {
        userStories.clear();
    }

    @Override
    public String toString() {
        StringBuilder sprintStories = new StringBuilder();
        for (UserStoryData story : this.userStories) {
            sprintStories.append(story.toString()).append(" --- ");
        }

        return String.format("Sprint %s. Start date: %s, End date: %s, Stories: [%s]", this.sprintNum, this.startDate, this.endDate, sprintStories);
    }
}
