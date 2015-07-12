package com.epam.jmp.api;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.epam.jmp.model.Employee;
import com.epam.jmp.model.Project;


@Remote
public interface IProjectRemote {
	
	public List<Project> getList();

	public Project fetchById(int id);
	    
	public int save(Project project);  
	
	public void remove(int id);
	    
	public Collection<Employee> getEmployees(int id);

}
