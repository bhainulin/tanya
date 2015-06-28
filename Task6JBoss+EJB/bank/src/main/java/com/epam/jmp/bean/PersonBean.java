package com.epam.jmp.bean;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jmp.api.IPersonEJBLocal;
import com.epam.jmp.api.IPersonEJBRemote;
import com.epam.jmp.model.Person;

@Stateless
@DeclareRoles("bean")
public class PersonBean implements IPersonEJBLocal, IPersonEJBRemote {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getList() {
		Query query = em.createQuery("from Person");
		return query.getResultList();
	}

	@Override
	public Person fetchById(int id) {
		Person person = em.find(Person.class, id);
		return person;
	}

	@Override
	@RolesAllowed("bean")
	public int save(Person person) {
		if(person.getIdPerson() == 0){
			em.persist(person);
		} else {
			person = em.merge(person);
		}
		return person.getIdPerson();
	}

	@Override
	@RolesAllowed("bean")
	public void remove(Integer[] personID) {
		// TODO Auto-generated method stub		
	}

	/*//@Override
	public List<Person> search(String key, Object value) {
		// TODO Auto-generated method stub
		if(! (value instanceof Integer)){
        	value = "'" + value + "'";
        }
        statement = connection.prepareStatement("SELECT * FROM `task5`.Person WHERE `"+key+"` = " + value);
        
        Query q = em
				.createQuery("s");
		q.setParameter(1, bankId);
		q.setParameter(2, from);
		q.setParameter(3, to);
		return q.getResultList();
		//http://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html
	}*/

}
