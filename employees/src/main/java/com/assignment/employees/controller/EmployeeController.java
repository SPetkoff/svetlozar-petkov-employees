package com.assignment.employees.controller;

import java.io.IOException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.employees.exception.model.FileEmptyException;
import com.assignment.employees.exception.model.NotSupportedMimeTypeException;
import com.assignment.employees.model.ResponseDto;
import com.assignment.employees.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    

    private EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService) {
	this.employeeService = employeeService;
    }
    
    @PostMapping(consumes = "multipart/form-data")
    public String findTeamWithLongestPeriodInCooperation(@RequestParam("file") MultipartFile file, Model model) throws IOException, NotSupportedMimeTypeException, FileEmptyException {
	ResponseDto response = employeeService.findTeamWithLongestPeriodInCooperation(file);
	if (ObjectUtils.anyNotNull(response.getFirstEmployeeId(), response.getProjectId(), response.getSecondEmployeeId())) {
	    model.addAttribute("response", response);
	    return "employees/team";
	}
	return "employees/no-candidates";
    }
    
}
