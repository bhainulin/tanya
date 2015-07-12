package com.epam.jmp.api;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.epam.jmp.model.Employee;
import com.epam.jmp.model.Project;

@Remote
public interface IEmployeeRemote {
	
	public List<Employee> getList();

	public Employee fetchById(int id);
	    
	public int save(Employee object);  
	
	public void remove(int id);
	
	public Collection<Project> getProjectsByEmployeeId(int id);

}
