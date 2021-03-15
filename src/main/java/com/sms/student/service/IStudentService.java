package com.sms.student.service;

import java.util.List;

import com.sms.student.pojo.Student;

public interface IStudentService {
	
	public void saveStudent(Student student);

	public List<Student> getAllStudents();

}
