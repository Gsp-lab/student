package com.sms.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.student.config.StudentUserDeatils;
import com.sms.student.dao.IStudentDao;
import com.sms.student.pojo.Student;
import com.sms.student.to.UserInfo;


@Service
public class StudentServiceImpl implements IStudentService, UserDetailsService {

	@Autowired
	private IStudentDao studentDao;

	@Transactional
	@Override
	public void saveStudent(Student student) {
		studentDao.saveStudent(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = studentDao.getUserByUserName(username);
		return new StudentUserDeatils(userInfo);
	}

}
