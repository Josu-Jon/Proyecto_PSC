package com.psc06.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserStoryDataTest {
    
    UserStoryData userStory;

    @Before
    public void SetUp(){
        userStory = new UserStoryData();
        userStory.setId(1);
        userStory.setUserStory("Crear Test Unitarios");
        userStory.setEstimation(10);
        userStory.setPbPriority(2);
    }

    @Test
    public void testGetId(){
        assertEquals(1, userStory.getId());
    }

    @Test
    public void testGetUserStory(){
        assertEquals("Crear Test Unitarios", userStory.getUserStory());
    }

    @Test
    public void testGetEstimation(){
        assertEquals(10, userStory.getEstimation());
    }

    @Test
    public void testGetPbPriority(){
        assertEquals(2, userStory.getPbPriority());
    }

    @Test
    public void testToString(){
        assertEquals("User Story 1 (Crear Test Unitarios): [Estimacion: 10. Prioridad: 2.]", userStory.toString());
    }

}
