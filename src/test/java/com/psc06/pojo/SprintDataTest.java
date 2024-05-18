package com.psc06.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SprintDataTest {
    
    SprintData sprintData;

    @Before
    public void setUp() {
        sprintData = new SprintData();
        sprintData.setSprintNum(23);
        sprintData.setStartDate("2024-05-20");
        sprintData.setEndDate("2024-06-10");
    }

    @Test
    public void testGetSprintNum() {
        assertEquals(23, sprintData.getSprintNum());
    }

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
        assertEquals("Sprint 23. Start date: 2024-05-20, End date: 2024-06-10", sprintData.toString());
    }
}
