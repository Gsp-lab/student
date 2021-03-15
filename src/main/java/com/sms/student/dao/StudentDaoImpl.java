package com.sms.student.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sms.student.pojo.Student;
import com.sms.student.to.UserInfo;


@Repository
public class StudentDaoImpl implements IStudentDao {
    
	@PersistenceContext (type= PersistenceContextType.EXTENDED)
	@Autowired
	private EntityManager manager;

	private Session session() {
		return manager.unwrap(Session.class);
	}

	@Override
	public void saveStudent(Student student) {
        session().save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return session().createCriteria(Student.class).list();
	}

	@Override
	public UserInfo getUserByUserName(String userName) {
		return (UserInfo) session().createCriteria(UserInfo.class).add(Restrictions.eq("email", userName)).uniqueResult();
	}

}
