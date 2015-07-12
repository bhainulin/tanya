package com.epam.jmp.bean;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.jmp.api.IOfficeRemote;
import com.epam.jmp.model.Employee;
import com.epam.jmp.model.Project;
import com.epam.jmp.model.Unit;

@Stateless
public class OfficeBean implements IOfficeRemote{
	
	@PersistenceContext
	private EntityManager em;
	
	public void addEmployeeToUnit(int employeeId, int unitId) {
		
		Unit unit = em.find(Unit.class, unitId);
		Employee employee = em.find(Employee.class, employeeId);
		
		if(unit == null){
			throw new IllegalArgumentException("Unit " + unitId + " does not exist!");
		}
		
		if(employee == null){
			throw new IllegalArgumentException("Employee " + employeeId + " does not exist!");
		}
		
		if (unit != null && employee != null) {
			employee.setUnit(unit);
			em.persist(employee);
		}
	}

	public void assignEmployeeForProject(int employeeId, int projectId) {
		
		Employee employee = em.find(Employee.class, employeeId);
		Project project = em.find(Project.class, projectId);
		
		if(employee == null){
			throw new IllegalArgumentException("Employee " + employeeId + " does not exist!");
		}
		
		if(project == null){
			throw new IllegalArgumentException("Project " + projectId + " does not exist!");
		}
		
		if (project != null && employee != null) {
			Collection<Project> employeeProjectList = employee.getProjects();
			if(employeeProjectList.contains(project)){
				System.out.println("Employee already was assaign to project "+ project.getName());
			} else {
				employeeProjectList.add(project);
				employee.setProjects(employeeProjectList);
				em.merge(employee);
			}
		}
	}

}
