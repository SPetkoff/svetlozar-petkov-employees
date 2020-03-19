package com.assignment.employees.exception.model;

public class FileEmptyException extends Exception {

    private static final long serialVersionUID = -2283625999263667707L;
    
    private String message;
    
    
    /**
     * default constructor
     */
    public FileEmptyException() {
	super();
    }

    public FileEmptyException(String message) {
	this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
