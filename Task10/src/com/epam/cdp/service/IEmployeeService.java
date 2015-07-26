package com.epam.cdp.service;

import java.util.List;

import com.epam.cdp.entity.Employee;


public interface IEmployeeService {

	public List<Employee> getEmployeeList();

	public Employee getEmployee(int id);

	public void addEmployee(Employee em);

	public void editEmployee(Employee em);

	public void deleteEmployee(int id);

}
