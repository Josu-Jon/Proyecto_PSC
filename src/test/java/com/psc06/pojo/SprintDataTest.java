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
    }

    @Test
    public void testGetId() {
        assertEquals(23, sprintData.getSprintNum());
    }

    @Test
    public void testToString() {
        assertEquals("Sprint 23. ", sprintData.toString());
    }

}
