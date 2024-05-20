package com.psc06.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Unit tests for SprintStoryData serialized class
 */
public class SprintStoryDataTest {
    
    SprintStoryData sprintStoryData;

    @Mock
    SprintData sp;

    @Mock
    UserStoryData us;

    /**
     * Set up before unit tests for sprint story data
     */
    @Before
    public void SetUp(){
        sprintStoryData = new SprintStoryData();
        sprintStoryData.setSprintData(sp);
        sprintStoryData.setUserStoryData(us);
    }

    /**
     * Test method for {@link com.psc06.pojo.SprintStoryData#getSprintData()}.
     */
    @Test
    public void testGetSprintData(){
        assertEquals(sp, sprintStoryData.getSprintData());
    }

    /**
     * Test method for {@link com.psc06.pojo.SprintStoryData#getUserStoryData()}.
     */
    @Test
    public void testGetUserStoryData(){
        assertEquals(us, sprintStoryData.getUserStoryData());
    }


}
