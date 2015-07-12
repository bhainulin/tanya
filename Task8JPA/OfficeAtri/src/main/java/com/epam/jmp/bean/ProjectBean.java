package com.epam.jmp.bean;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jmp.api.IProjectRemote;
import com.epam.jmp.model.Employee;
import com.epam.jmp.model.Project;

@Stateless
public class ProjectBean implements IProjectRemote {

	@PersistenceContext
	private EntityManager em;
	
	
	public List<Project> getList() {
		Query query = em.createQuery("from Project");
		return query.getResultList();
	}

	public Project fetchById(int id) {
		Project project = em.find(Project.class, id);
		return project;
	}

	public int save(Project project) {
		if(project.getId()== 0){
			em.persist(project);
		} else {
			project = em.merge(project);
		}
		return project.getId();
	}

	public void remove(int id) {
		Project project = fetchById(id);
		em.remove(project);
		
	}

	public Collection<Employee> getEmployees(int id) {
		Project project = fetchById(id);
		Collection<Employee> employees = project.getEmployees();
		return employees;
	}

}
