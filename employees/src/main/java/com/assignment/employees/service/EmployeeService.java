package com.assignment.employees.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.employees.exception.model.FileEmptyException;
import com.assignment.employees.exception.model.NotSupportedMimeTypeException;
import com.assignment.employees.model.EmployeeDto;
import com.assignment.employees.model.ResponseDto;



@Service
public class EmployeeService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    public final DateTimeFormatter parser = DateTimeFormatter.ofPattern("[yyyy-MM-dd]" + "[yyyy/MM/dd]" + "[ddMMMyyyy]");

    public ResponseDto findTeamWithLongestPeriodInCooperation(final MultipartFile file) throws IOException, NotSupportedMimeTypeException, FileEmptyException {
	// check if file type allowed and if file empty
	checkFileTypeAndSize(file);
	// get file content
	List<EmployeeDto> employees = getFileContent(file);
	// find team with longest period in cooperation
	ResponseDto response = findTeamWithLongestPeriod(employees);
	logger.info("Employee ID #1: {}, Employee ID #2: {}, Project ID: {}, Days Worked: {}", 
		response.getFirstEmployeeId(),response.getSecondEmployeeId(), response.getProjectId(), response.getDaysWorked());
	return response;
    }
    
    //@formatter:off
    private ResponseDto findTeamWithLongestPeriod(final List<EmployeeDto> employees) {
	ResponseDto response = new ResponseDto();
	for (int i = 0; i < employees.size() - 1; i++) {
	    for (int j = i + 1; j < employees.size(); j++) {
		if (employees.get(i).getProjectId().equals(employees.get(j).getProjectId())) {
		    if(isEmployeesWorkedInSamePeriod(employees.get(i), employees.get(j))) {
			    LocalDate startDate = employees.get(i).getDateFrom().isBefore(employees.get(j).getDateFrom())
				    ? employees.get(j).getDateFrom() : employees.get(i).getDateFrom();
			    LocalDate finalDate = employees.get(i).getDateTo().isBefore(employees.get(j).getDateTo())
				    ? employees.get(i).getDateTo() : employees.get(j).getDateTo();
				    long duration = ChronoUnit.DAYS.between(startDate, finalDate);
				     if (duration > response.getDaysWorked()) {
					response = new ResponseDto(employees.get(i).getEmployeeId(),
						employees.get(j).getEmployeeId(), employees.get(i).getProjectId(), duration);
				    }
			}
		    }
		}
	    }
	return response;
    }
    
    private boolean isEmployeesWorkedInSamePeriod(EmployeeDto employee1, EmployeeDto employee2) {
	return ((employee1.getDateFrom().isBefore(employee2.getDateFrom()) && employee2.getDateFrom().isBefore(employee1.getDateTo()))
		|| (employee2.getDateFrom().isBefore(employee1.getDateFrom()) && employee1.getDateFrom().isBefore(employee2.getDateTo()))
		|| (employee1.getDateFrom().equals(employee2.getDateFrom())));
    }
    
    private List<EmployeeDto> getFileContent(final MultipartFile file) throws IOException {
	String content = IOUtils.toString(file.getInputStream(), StandardCharsets.UTF_8);
	String[] lines = content.split("\\r?\\n");
	List<EmployeeDto> employees = new ArrayList<>();
	for (String line : lines) {
	    String[] tokens = line.trim().split(", ");
	    if (tokens.length < 4) {
		logger.info("Record with missing data: {}", line);
		continue;
	    }
	    if ("NULL".equalsIgnoreCase(tokens[3])) {
		employees.add(new EmployeeDto(tokens[0], tokens[1], LocalDate.parse(tokens[2],parser), LocalDate.now()));
	    } else {
		employees.add(new EmployeeDto(tokens[0], tokens[1], LocalDate.parse(tokens[2],parser), LocalDate.parse(tokens[3],parser)));
	    }
	}
	return employees;
    }
    
    private void checkFileTypeAndSize(final MultipartFile file) throws IOException, NotSupportedMimeTypeException, FileEmptyException {
	if (!"txt".equals(StringUtils.substringAfterLast(file.getOriginalFilename(), "."))) {
	    throw new NotSupportedMimeTypeException("Mime type is not supported!");
	}
	if (file.isEmpty()) {
	    throw new  FileEmptyException("File is empty! Please choose another one!");
	}
    }
}
