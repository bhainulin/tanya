package com.epam.jmp.bean;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jmp.api.IBankEJBLocal;
import com.epam.jmp.api.IBankEJBRemote;
import com.epam.jmp.model.Bank;

@Stateless
@DeclareRoles("bean")
public class BankBean implements IBankEJBLocal, IBankEJBRemote {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Bank> getList() {
		Query query = em.createQuery("from Bank");
		return query.getResultList();
	}

	@Override
	public Bank fetchById(int id) {
		Bank bank = em.find(Bank.class, id);
		return bank;
	}

	@Override
	@RolesAllowed("bean")
	public int save(Bank bank) {
		if(bank.getIdBank() == 0){
			em.persist(bank);
		} else {
			bank = em.merge(bank);
		}
		return bank.getIdBank();
	}

	@Override
	public void remove(Integer[] bankID) {
		// TODO Auto-generated method stub
	}

}
