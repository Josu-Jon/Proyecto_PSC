package com.psc06.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Sprint serialized class
 */
public class SprintDataTest {
    
    SprintData sprintData;

    /**
     * Set up before unit tests for sprint data
     */
    @Before
    public void setUp() {
        sprintData = new SprintData();
        sprintData.setSprintNum(23);
    }

    /**
     * Test method for {@link com.psc06.pojo.SprintData#getId()}.
     */
    @Test
    public void testGetId() {
        assertEquals(23, sprintData.getSprintNum());
    }

    /**
     * Test method for {@link com.psc06.pojo.SprintData#setId(int)}.
     */
    @Test
    public void testToString() {
        assertEquals("Sprint 23. ", sprintData.toString());
    }

}
