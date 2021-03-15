package com.sms.student.dao;

import java.util.List;

import com.sms.student.pojo.Student;
import com.sms.student.to.UserInfo;


public interface IStudentDao {

	public void saveStudent(Student student);

	public List<Student> getAllStudents();

	public UserInfo getUserByUserName(String userName);
}
