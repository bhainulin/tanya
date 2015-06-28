package com.epam.jmp.bean;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jmp.api.ICurrencyRatioEJBLocal;
import com.epam.jmp.api.ICurrencyRatioEJBRemote;
import com.epam.jmp.model.CurrencyRatio;

@Stateless
@DeclareRoles("bean")
public class CurrencyRatioBean implements ICurrencyRatioEJBLocal, ICurrencyRatioEJBRemote{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CurrencyRatio> getList() {
		Query query = em.createQuery("from CurrencyRatio");
		return query.getResultList();
	}

	@Override
	public CurrencyRatio fetchById(int id) {
		return em.find(CurrencyRatio.class, id);
	}

	@Override
	public List<CurrencyRatio> fetchByBankId(int id) {
		Query q = em
				.createQuery("select c from CurrencyRatio c where c.idBank=?1");
		q.setParameter(1, id);
		List<CurrencyRatio> resultList = (List<CurrencyRatio>) q.getResultList();
		return resultList;
	}

	@Override
	@RolesAllowed("bean")
	public int save(CurrencyRatio currencyRatio) {
		if(currencyRatio.getId() == 0){
			em.persist(currencyRatio);
		} else {
			currencyRatio = em.merge(currencyRatio);
		}
		return currencyRatio.getId();
	}

	@Override
	public void remove(Integer[] currencyRatioID) {
		// TODO Auto-generated method stub
	}

}
