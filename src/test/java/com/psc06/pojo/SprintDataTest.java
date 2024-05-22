package com.psc06.pojo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Sprint serialized class
 */
public class SprintDataTest {
    
    SprintData sprintData;
    private UserStoryData userStory;
    private List<UserStoryData> userStories;

    /**
     * Set up before unit tests for sprint data
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

        sprintData = new SprintData();
        sprintData.setSprintNum(23);
        sprintData.setStartDate("2024-05-20");
        sprintData.setEndDate("2024-06-10");
        sprintData.setUserStories(userStories);
    }

    /**
     * Test method for {@link com.psc06.pojo.SprintData#getId()}.
     */
    @Test
    public void testGetSprintNum() {
        assertEquals(23, sprintData.getSprintNum());
    }

    /**
     * Test method for {@link com.psc06.pojo.SprintData#setId(int)}.
     */
    @Test
    public void testGetStartDate() {
        assertEquals("2024-05-20", sprintData.getStartDate());
    }

    @Test
    public void testGetEndDate() {
        assertEquals("2024-06-10", sprintData.getEndDate());
    }

    @Test
    public void testToString() {
        assertEquals("Sprint 23. Start date: 2024-05-20, End date: 2024-06-10, Stories: [User Story 0 (userStory): [Estimacion: 2. Prioridad: 3.] --- ]", sprintData.toString());
    }
}
