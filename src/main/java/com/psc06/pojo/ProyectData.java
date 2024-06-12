package com.psc06.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * ProyectData class
 * Representa un proyecto con sus sprints
 */
public class ProyectData 
{
    private int idProyect;
    private String startDate;
    private String endDate;
    private List<SprintData> sprints;
    
    /**
     * Constructor
     */
    public ProyectData() 
    {
        this.sprints = new ArrayList<>();
    }

    /**
     * Get serialized Id
     * @return Proyect ID
     */
    public int getIdProyect() {
        return this.idProyect;
    }

    /**
     * Set serialized Id
     * @param idProyect Userstory ID
     */
    public void setIdProyect(int idProyect) {
        this.idProyect = idProyect;
    }

    /**
     * Pone la fecha de inicio
     * @param startDate Start date
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Consigue la fecha de fin
     * @return End date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Consigue la fecha de fin
     * @return End date
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * Pone la fecha de fin
     * @param endDate End date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Consigue los sprints
     * @return Sprints
     */
    public List<SprintData> getSprints() {
        return this.sprints;
    }

    /**
     * Pone los sprints
     * @param sprints Sprints
     */
    public void setSprints(List<SprintData> sprints) {
        this.sprints = sprints;
    }

    /**
     * Añade un sprint
     * @param sprints Sprint
     */
    public void addUserStory(SprintData sprint) {
        this.sprints.add(sprint);
    }

    /**
     * Limpia los sprints
     */
    public void clearSprints() {
        sprints.clear();
    }

    /**
     * Devuelve la representación en cadena de texto del sprint
     * @return String representation
     */
    @Override
    public String toString() {
        return "ProyectData [idProyect=" + idProyect + ", startDate=" + startDate + ", endDate=" + endDate
                + ", sprints=" + sprints + "]";
    }
}
