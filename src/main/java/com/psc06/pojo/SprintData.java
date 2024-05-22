package com.psc06.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * SprintData class
 * Representa un sprinc on user stories
 */
public class SprintData {
    
    private int sprintNum;
    private String startDate;
    private String endDate;
    private List<UserStoryData> userStories;

    /**
     * Constructor
     */
    public SprintData() {
        this.userStories = new ArrayList<>();
    }

    /**
     * Devuelve el número de sprint
     * @return
     */
    public int getSprintNum() {
        return sprintNum;
    }

    /**
     * Establece el número de sprint
     * @param sprintNum
     */
    public void setSprintNum(int sprintNum) {
        this.sprintNum = sprintNum;
    }

    /**
     * Consigue la fecha de inicio
     * @return Fecha de inicio
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Pone la fecha de inicio
     * @param startDate Start date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Consigue la fecha de fin
     * @return End date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Pone la fecha de fin
     * @param endDate End date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Consigue las historias de usuario
     * @return User stories
     */
    public List<UserStoryData> getUserStories() {
        return userStories;
    }

    /**
     * Pone las historias de usuario
     * @param userStories User stories
     */
    public void setUserStories(List<UserStoryData> userStories) {
        this.userStories = userStories;
    }

    /**
     * Añade una historia de usuario
     * @param userStory User story
     */
    public void addUserStory(UserStoryData userStory) {
        this.userStories.add(userStory);
    }

    /**
     * Limpia las historias de usuario
     */
    public void clearUserStories() {
        userStories.clear();
    }

    /**
     * Devuelve la representación en cadena de texto del sprint
     * @return String representation
     */
    public String toString() {
        StringBuilder sprintStories = new StringBuilder();
        for (UserStoryData story : this.userStories) {
            sprintStories.append(story.toString()).append(" --- ");
        }

        return String.format("Sprint %s. Start date: %s, End date: %s, Stories: [%s]", this.sprintNum, this.startDate, this.endDate, sprintStories);
    }
}
