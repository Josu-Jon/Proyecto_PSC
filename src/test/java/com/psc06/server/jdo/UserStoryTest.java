package com.psc06.server.jdo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class UserStoryTest {
    
    private UserStory us1;
    private UserStory us2;

    @Mock
    Sprint sprint;

    @Before
    public void SetUp() {
        us1 = new UserStory(1, "Test", 1, 1);
        us2 = new UserStory(2, null, 2, 2, sprint);
    }

    @Test
    public void testSetId() {
        us1.setId(3);
        assertEquals(us1.getId(), 3);
    }

    @Test
    public void testGetId() {
        assertEquals(us2.getId(), 2);
    }

    @Test
    public void testSetUserStory() {
        us1.setUserStory("StringChange");
        assertEquals("StringChange", us1.getUserStory());
    }

    @Test
    public void testGetUserStory() {
        assertEquals(null, us2.getUserStory());
    }

    @Test
    public void testSetEstimation() {
        us1.setEstimation(4);
        assertEquals(4, us1.getEstimation());
    }

    @Test
    public void testGetEstimation() {
        assertEquals(2, us2.getEstimation());
    }

    @Test
    public void testSetPbPriority() {
        us1.setPbPriority(5);
        assertEquals(5, us1.getPbPriority());
    }

    @Test
    public void testGetPbPriority() {
        assertEquals(2, us2.getPbPriority());
    }

    @Test
    public void testSetSprint() {
        us1.setSprint(sprint);
        assertEquals(us1.getSprint(), sprint);
    }

    @Test
    public void testDeleteSprint() {
        us1.deleteSprint();
        assertNull(us1.getSprint());
    }

    @Test
    public void testGetSprint() {
        assertEquals(sprint, us2.getSprint());
    }

    @Test
    public void testToString() {
        us2.setUserStory("Test");
        assertEquals("User Story 2 (Test): [Estimacion: 2. Prioridad: 2.]", us2.toString());
    }



}
