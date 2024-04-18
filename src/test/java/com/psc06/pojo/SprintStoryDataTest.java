package com.psc06.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class SprintStoryDataTest {
    
    SprintStoryData sprintStoryData;

    @Mock
    SprintData sp;

    @Mock
    UserStoryData us;

    @Before
    public void SetUp(){
        sprintStoryData = new SprintStoryData();
        sprintStoryData.setSprintData(sp);
        sprintStoryData.setUserStoryData(us);
    }

    @Test
    public void testGetSprintData(){
        assertEquals(sp, sprintStoryData.getSprintData());
    }


    @Test
    public void testGetUserStoryData(){
        assertEquals(us, sprintStoryData.getUserStoryData());
    }


}
