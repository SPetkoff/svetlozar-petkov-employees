package com.assignment.employees.exception.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.employees.exception.model.FileEmptyException;
import com.assignment.employees.exception.model.NotSupportedMimeTypeException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(NotSupportedMimeTypeException.class)
    public String handleNotSupportedMimeTypeException(NotSupportedMimeTypeException ex, Model model) {
	model.addAttribute("error", ex);
	return "errors/not-supported-mime-type";
    }
    
    @ExceptionHandler(FileEmptyException.class)
    public String handleFileEmptyException(FileEmptyException ex, Model model) {
	model.addAttribute("error", ex);
	return "errors/not-supported-mime-type";
    }
}
