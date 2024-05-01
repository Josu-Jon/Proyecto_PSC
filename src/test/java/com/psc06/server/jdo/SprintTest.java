package com.psc06.server.jdo;

import java.util.*;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class SprintTest {

    private Sprint sprint;
    private Sprint sprint2;

    @Mock
    private UserStory us;

    @Mock
    private Set<UserStory> us2;

    @Before
    public void SetUp(){
        sprint = new Sprint();
        sprint.setSprintNum(5);
        sprint2 = new Sprint(10);
    }

    @Test
    public void testGetSprintNum(){
        assertEquals(5, sprint.getSprintNum());
    }

    @Test
    public void testGetAllStories(){
        assertTrue(sprint.getAllStories().isEmpty());
    }

    @Test
    public void testAddStory(){
        sprint.addStory(us);
        assertEquals(1, sprint.getAllStories().size());
    }

    @Test
    public void testRemoveStory(){
        sprint.removeStory(us);
        assertTrue(sprint.getAllStories().isEmpty());
    }

    @Test
    public void testToString(){
        sprint.addStory(new UserStory(1, "test", 1, 1));

        assertEquals("Sprint 5: [User Story 1 (test): [Estimacion: 1. Prioridad: 1.] --- ]", sprint.toString());
    }





}
