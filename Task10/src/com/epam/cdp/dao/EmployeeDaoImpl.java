package com.epam.cdp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.cdp.entity.Employee;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAll() {
		return getSession().createQuery("from employee").list();
	}

	@Override
	public Employee get(int id) {
		return (Employee) getSession().get(Employee.class, id);
	}

	@Override
	public void add(Employee em) {
		getSession().save(em);
	}

	@Override
	public void update(Employee em) {
		getSession().update(em);
	}

	@Override
	public void remove(int id) {
		Employee em = (Employee) getSession().load(Employee.class, id);
		if (null != em) {
			getSession().delete(em);
		}
	}

}
