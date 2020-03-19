package com.assignment.employees.exception.model;

public class NotSupportedMimeTypeException extends Exception{

    private static final long serialVersionUID = 5838788205123836207L;
    
    private String message;

    
    /**
     * default constructor
     */
    public NotSupportedMimeTypeException() {
	
    }

    public NotSupportedMimeTypeException(String message) {
	this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
