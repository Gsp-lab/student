package com.sms.student.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.student.pojo.Student;
import com.sms.student.service.IStudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins="http://localhost:4200")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@PostMapping("/savestudent")
	public ResponseEntity<String> saveStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
		return new ResponseEntity<String>("student save success", HttpStatus.OK);
	}
	
	@GetMapping("/allstudents")
	public ResponseEntity<List<Student>> getAllStudents(){
		return new ResponseEntity<List<Student>>(studentService.getAllStudents(), HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public void logout(HttpServletRequest request ,HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);  
		}
	}
}
