package com.assignment.employees.model;

import java.io.Serializable;

public class ResponseDto implements Serializable{

    private static final long serialVersionUID = 63483676580561317L;
    
    private String firstEmployeeId;
    
    private String secondEmployeeId;
    
    private String projectId;
    
    private long daysWorked;
    
    

    /**
     * default constructor
     */
    public ResponseDto() {
	
    }

    public ResponseDto(String firstEmployeeId, String secondEmployeeId, String projectId, long daysWorked) {
	this.firstEmployeeId = firstEmployeeId;
	this.secondEmployeeId = secondEmployeeId;
	this.projectId = projectId;
	this.daysWorked = daysWorked;
    }



    public String getFirstEmployeeId() {
        return firstEmployeeId;
    }

    public void setFirstEmployeeId(String firstEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
    }

    public String getSecondEmployeeId() {
        return secondEmployeeId;
    }

    public void setSecondEmployeeId(String secondEmployeeId) {
        this.secondEmployeeId = secondEmployeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public long getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(long daysWorked) {
        this.daysWorked = daysWorked;
    }

}
