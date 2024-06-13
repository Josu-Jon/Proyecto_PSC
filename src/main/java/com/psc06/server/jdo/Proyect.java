package com.psc06.server.jdo;

import java.util.*;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Proyect class
 */
@PersistenceCapable
public class Proyect 
{
    @PrimaryKey
    private int id;
    
    @Persistent(mappedBy="proyect", dependentElement="false")
	@Join
    Set<Sprint> sprints = new LinkedHashSet<>();

    /**
     * Default constructor
     
     */
    Proyect() {
    }

    /**
     * Constructor
     * @param id id Proyecto
     */
    public Proyect(int id) {
        this.id = id;
    }

    /**
     * Get the id
     * @return project id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id
     * @param id project id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Add a sprint to the project
     * @param sprint sprint
     */
    public void addSprint(Sprint sprint){
        sprints.add(sprint);
    }

    /**
     * Remove a sprint from the project
     * @param sprint sprint
     */
    public void removeStory(UserStory sprint){
        sprints.remove(sprint);
    }

    /**
     * Get all sprints in the project
     * @return Set of sprints
     */
    public Set<Sprint> getAllStories() {
        return this.sprints;
    }

    /**
     * Get the string of the list
     * @return String of the list of sprints
     */
    public String toString(){
        StringBuilder proyectSprints = new StringBuilder();
        for (Sprint sprint: this.sprints) {
            proyectSprints.append(sprint.toString() + " --- ");
        }

        return String.format("Sprint %s: [%s]", id, proyectSprints);
    }
    
}
