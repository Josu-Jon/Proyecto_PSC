package com.psc06.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for UserStoryData serialized class
 */
public class UserStoryDataTest {
    
    UserStoryData userStory;
    UserStoryData userStory2;

    /**
     * Set up before unit tests for user story data
     */
    @Before
    public void SetUp(){
        userStory = new UserStoryData();
        userStory.setId(1);
        userStory.setUserStory("Crear Test Unitarios");
        userStory.setEstimation(10);
        userStory.setPbPriority(2);
    }

    /**
     * Test method for {@link com.psc06.pojo.UserStoryData#getId()}.
     */
    @Test
    public void testGetId(){
        assertEquals(1, userStory.getId());
    }

    /**
     * Test method for {@link com.psc06.pojo.UserStoryData#getUserStory()}.
     */
    @Test
    public void testGetUserStory(){
        assertEquals("Crear Test Unitarios", userStory.getUserStory());
    }

    /**
     * Test method for {@link com.psc06.pojo.UserStoryData#getEstimation()}.
     */
    @Test
    public void testGetEstimation(){
        assertEquals(10, userStory.getEstimation());
    }

    /**
     * Test method for {@link com.psc06.pojo.UserStoryData#getPbPriority()}.
     */
    @Test
    public void testGetPbPriority(){
        assertEquals(2, userStory.getPbPriority());
    }

    /**
     * Test method for {@link com.psc06.pojo.UserStoryData#toString()}.
     */
    @Test
    public void testToString(){
        assertEquals("User Story 1 (Crear Test Unitarios): [Estimacion: 10. Prioridad: 2.]", userStory.toString());
    }

}
