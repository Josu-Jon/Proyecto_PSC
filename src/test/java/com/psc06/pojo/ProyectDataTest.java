package com.psc06.pojo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Proyect serialized class
 */
public class ProyectDataTest 
{
    ProyectData proyect;
    private UserStoryData userStory;
    private List<UserStoryData> userStories;
    private SprintData sprint;
    private List<SprintData> sprints;

    /**
     * Set up before unit tests for proyect data
     */
    @Before
    public void setUp() {
        userStory = new UserStoryData();
        userStory.setId(0);
		userStory.setUserStory("userStory");
		userStory.setEstimation(2);
		userStory.setPbPriority(3);

        userStories = new ArrayList<UserStoryData>();
        userStories.add(userStory);

        sprint = new SprintData();
        sprint.setSprintNum(23);
        sprint.setStartDate("2024-05-20");
        sprint.setEndDate("2024-06-10");
        sprint.setUserStories(userStories);

        sprints = new ArrayList<SprintData>();
        sprints.add(sprint);

        proyect = new ProyectData();
        proyect.setIdProyect(5);
        proyect.setStartDate("2024-05-20");
        proyect.setEndDate("2024-06-10");
        proyect.setSprints(sprints);
    }

    /**
     * Test method for {@link com.psc06.pojo.ProyectData#getIdProyect()}.
     */
    @Test
    public void testGetIdProyect() {
        assertEquals(5, proyect.getIdProyect());
    }

    /**
     * Test method for {@link com.psc06.pojo.ProyectData#getStartDate()}.
     */
    @Test
    public void testGetStartDate() {
        assertEquals("2024-05-20", proyect.getStartDate());
    }

    @Test
    public void testGetEndDate() {
        assertEquals("2024-06-10", proyect.getEndDate());
    }

    @Test
    public void testToString() {
        assertEquals("Proyect 5. Start date: 2024-05-20, End date: 2024-06-10, Sprints: [Sprint 23. Start date: 2024-05-20, End date: 2024-06-10, Stories: [User Story 0 (userStory): [Estimacion: 2. Prioridad: 3.] --- ] --- ]", proyect.toString());
    }
}
