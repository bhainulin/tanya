package com.epam.jmp.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jmp.api.IPersonalInfoRemote;
import com.epam.jmp.model.PersonalInfo;

@Stateless
public class PersonalInfoBean implements IPersonalInfoRemote{

	@PersistenceContext
	private EntityManager em;
	
	public List<PersonalInfo> getList() {
		Query query = em.createQuery("from PersonalInfo");
		return query.getResultList();
	}

	public PersonalInfo fetchById(int id) {
		PersonalInfo object = em.find(PersonalInfo.class, id);
		return object;
	}

	public int save(PersonalInfo object) {
		if(object.getId()== 0){
			em.persist(object);
		} else {
			object = em.merge(object);
		}
		return object.getId();
	}

	public void remove(int id) {
		PersonalInfo object = fetchById(id);
		em.remove(object);
	}

}
