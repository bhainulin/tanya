package com.epam.jmp.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jmp.api.IUnitRemote;
import com.epam.jmp.model.Unit;

@Stateless
public class UnitBean implements IUnitRemote{
	
	@PersistenceContext
	private EntityManager em;

	public List<Unit> getList() {
		Query query = em.createQuery("from Unit");
		return query.getResultList();
	}

	public Unit fetchById(int id) {
		Unit unit = em.find(Unit.class, id);
		return unit;
	}

	public int save(Unit unit) {
		if(unit.getId()== 0){
			em.persist(unit);
		} else {
			unit = em.merge(unit);
		}
		return unit.getId();
	}

	public void remove(int id) {
		Unit unit = fetchById(id);
		em.remove(unit);		
	}

}
