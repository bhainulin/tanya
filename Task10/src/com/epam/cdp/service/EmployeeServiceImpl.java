package com.epam.cdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.cdp.dao.IEmployeeDao;
import com.epam.cdp.entity.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao emDao;

	@Transactional
	public List<Employee> getEmployeeList() {
		return emDao.getAll();
	}

	@Transactional
	public Employee getEmployee(int id) {
		return emDao.get(id);
	}

	@Transactional
	public void addEmployee(Employee em) {
		emDao.add(em);
	}

	@Transactional
	public void editEmployee(Employee em) {
		emDao.update(em);
	}

	@Transactional
	public void deleteEmployee(int id) {
		emDao.remove(id);
	}

}
