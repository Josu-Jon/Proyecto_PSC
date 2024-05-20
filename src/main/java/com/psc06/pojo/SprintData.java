package com.psc06.pojo;

/**
 * Sprint Serialized class
 */
public class SprintData {
    
    int sprintNum;

    /**
     * Constructor
     */
    public SprintData(){
        // Required for serialization
    }

    /**
     * Constructor
     * @param sprintNum Sprint number
     */
    public int getSprintNum() {
        return sprintNum;
    }

    /**
     * Get the sprint number
     * @return Sprint number
     */
    public void setSprintNum(int sprintNum) {
        this.sprintNum = sprintNum;
    }

    /**
     * Get the sprint serialized string
     * @return Sprint string
     */
    public String toString(){
        return String.format("Sprint %s. ", this.sprintNum);
    }

}
