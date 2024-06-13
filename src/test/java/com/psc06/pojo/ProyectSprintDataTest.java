package com.psc06.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ProyectSprintDataTest 
{
    ProyectSprintData proyectSprintData;

    @Mock
    SprintData sp;

    @Mock
    ProyectData pr;

    /**
     * Set up before unit tests for sprint story data
     */
    @Before
    public void SetUp(){
        proyectSprintData = new ProyectSprintData();
        proyectSprintData.setSprintData(sp);
        proyectSprintData.setProyectData(pr);
    }

    /**
     * Test method for {@link com.psc06.pojo.ProyectSprintData#getSprintData()}.
     */
    @Test
    public void testGetSprintData(){
        assertEquals(sp, proyectSprintData.getSprintData());
    }

    /**
     * Test method for {@link com.psc06.pojo.ProyectSprintData#getProyectData()}.
     */
    @Test
    public void testGetProyectData(){
        assertEquals(pr, proyectSprintData.getProyectData());
    }

}
