package com.epam.jmp.bean;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jmp.api.IEmployeeRemote;
import com.epam.jmp.model.Employee;
import com.epam.jmp.model.Project;

@Stateless
public class EmployeeBean implements IEmployeeRemote {
	
	@PersistenceContext
	private EntityManager em;

	public List<Employee> getList() {
		Query query = em.createQuery("from Employee");
		return query.getResultList();
	}

	public Employee fetchById(int id) {
		Employee object = em.find(Employee.class, id);
		return object;
	}

	public int save(Employee object) {
		if(object.getId()== 0){
			em.persist(object);
		} else {
			object = em.merge(object);
		}
		return object.getId();
	}

	public void remove(int id) {
		Employee object = fetchById(id);
		em.remove(object);		
	}

	public Collection<Project> getProjectsByEmployeeId(int id) {
		Employee employee = fetchById(id);
		return employee.getProjects();
	}

}
