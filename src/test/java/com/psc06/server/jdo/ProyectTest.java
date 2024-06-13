package com.psc06.server.jdo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Unit tests for Proyect class
 */
public class ProyectTest 
{
    private Proyect proyect;
    
    @Mock
    private Sprint sp;
    Sprint sprint;

    @Mock
    private Set<Sprint> sprints;
    
    /**
     * Set up before unit tests for sprint
     */
    @Before
    public void SetUp(){
        proyect = new Proyect();
        proyect.setId(4);
    }

    /**
     * Test method for {@link com.psc06.server.jdo.Proyect#getId()}.
     */
    @Test
    public void testGetSprintNum(){
        assertEquals(4, proyect.getId());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.Proyect#getAllSprints()}.
     */
    @Test
    public void testGetAllSprints(){
        assertTrue(proyect.getAllSprints().isEmpty());
    }
    
    /**
     * Test method for {@link com.psc06.server.jdo.Proyect#addSprint(Sprint)}.
     */
    @Test
    public void testAddSprint(){
        proyect.addSprint(sp);
        assertEquals(1, proyect.getAllSprints().size());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.Proyect#removeSprint(Sprint)}.
     */
    @Test
    public void testRemoveStory(){
        proyect.removeSprint(sp);
        assertTrue(proyect.getAllSprints().isEmpty());
    }

    /**
     * Test method for {@link com.psc06.server.jdo.Sprint#setSprintNum()}.
     */
    /*@Test
    public void testToString(){
        sprint.addStory(new UserStory(1, "test", 1, 1));
        proyect.addSprint(sprint);

        assertEquals("Proyect 4: [Sprint 5: [User Story 1 (test): [Estimacion: 1. Prioridad: 1.] --- ] --- ]", proyect.toString());
    }*/
}
