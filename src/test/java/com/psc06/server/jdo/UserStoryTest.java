package com.psc06.server.jdo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Unit tests for UserStory class
 */
public class UserStoryTest {
    
    private UserStory us1;
    private UserStory us2;

    @Mock
    Sprint sprint;

    /**
     * Set up before unit tests for user story
     */
    @Before
    public void SetUp() {
        us1 = new UserStory(1, "Test", 1, 1);
        us2 = new UserStory(2, null, 2, 2, sprint);
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#setId()}.
     */
    @Test
    public void testSetId() {
        us1.setId(3);
        assertEquals(us1.getId(), 3);
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#getId()}.
     */
    @Test
    public void testGetId() {
        assertEquals(us2.getId(), 2);
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#setUserStory()}.
     */
    @Test
    public void testSetUserStory() {
        us1.setUserStory("StringChange");
        assertEquals("StringChange", us1.getUserStory());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#getUserStory()}.
     */
    @Test
    public void testGetUserStory() {
        assertEquals(null, us2.getUserStory());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#setEstimation()}.
     */
    @Test
    public void testSetEstimation() {
        us1.setEstimation(4);
        assertEquals(4, us1.getEstimation());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#getEstimation()}.
     */
    @Test
    public void testGetEstimation() {
        assertEquals(2, us2.getEstimation());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#setPbPriority()}.
     */
    @Test
    public void testSetPbPriority() {
        us1.setPbPriority(5);
        assertEquals(5, us1.getPbPriority());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#getPbPriority()}.
     */
    @Test
    public void testGetPbPriority() {
        assertEquals(2, us2.getPbPriority());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#setSprint()}.
     */
    @Test
    public void testSetSprint() {
        us1.setSprint(sprint);
        assertEquals(us1.getSprint(), sprint);
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#deleteSprint()}.
     */
    @Test
    public void testDeleteSprint() {
        us1.deleteSprint();
        assertNull(us1.getSprint());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#getSprint()}.
     */
    @Test
    public void testGetSprint() {
        assertEquals(sprint, us2.getSprint());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.UserStory#toString()}.
     */
    @Test
    public void testToString() {
        us2.setUserStory("Test");
        assertEquals("User Story 2 (Test): [Estimacion: 2. Prioridad: 2.]", us2.toString());
    }



}
