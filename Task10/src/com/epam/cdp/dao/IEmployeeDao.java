package com.epam.cdp.dao;

import java.util.List;

import com.epam.cdp.entity.Employee;

public interface IEmployeeDao {
	
	public List<Employee> getAll();
	
	public Employee get(int id);
	
	public void add(Employee em);
	
	public void update(Employee em);
	
	public void remove(int id);
}
