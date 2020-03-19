package com.assignment.employees.model;

import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeDto implements Serializable{

    private static final long serialVersionUID = 4545573902447218367L;
    
    private String employeeId;
    
    private String projectId;
    
    private LocalDate dateFrom;
    
    private LocalDate dateTo;
    
    
    
    /**
     * default constructor
     */
    public EmployeeDto() {
	
    }

    public EmployeeDto(String employeeId, String projectId, LocalDate dateFrom, LocalDate dateTo) {
	this.employeeId = employeeId;
	this.projectId = projectId;
	this.dateFrom = dateFrom;
	this.dateTo = dateTo;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
    
    
}
