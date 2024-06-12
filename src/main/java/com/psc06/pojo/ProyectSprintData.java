package com.psc06.pojo;

/**
 * ProyectSprintData Serialized class
 */
public class ProyectSprintData 
{
    private ProyectData proyectData;
    private SprintData sprintData;

    /**
     * Constructor sprint and proyect assigned
     */
    public ProyectSprintData() {
        // Required for serialization
    }

    /**
     * Get the proyect data
     * @return proyectData
     */
    public ProyectData getProyectData() {
        return proyectData;
    }

    /**
     * Set the proyect data
     * @param proyectData proyect data
     */
    public void setProyectData(ProyectData proyectData) {
        this.proyectData = proyectData;
    }

    /**
     * Get the sprint data
     * @return sprintData
     */
    public SprintData getSprintData() {
        return sprintData;
    }

    /**
     * Set the sprint data
     * @param sprintData sprint data
     */
    public void setSprintData(SprintData sprintData) {
        this.sprintData = sprintData;
    }
    
}
