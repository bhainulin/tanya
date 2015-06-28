package com.epam.jmp.bean;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jmp.api.IAccountEJBLocal;
import com.epam.jmp.api.IAccountEJBRemote;
import com.epam.jmp.model.Account;

@Stateless
@DeclareRoles("bean")
public class AccountBean implements IAccountEJBLocal, IAccountEJBRemote{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getList() {
		Query query = em.createQuery("from Account");
		return query.getResultList();
	}

	@Override
	public Account fetchById(int id) {
		return em.find(Account.class, id);
	}

	@Override
	@RolesAllowed("bean")
	public int save(Account account) {
		if(account.getIdAccount() == 0){
			em.persist(account);
		} else {
			account = em.merge(account);
		}
		return account.getIdAccount();
	}

	@Override
	public void remove(Integer[] accountID) {
		// TODO Auto-generated method stub
		
	}

}
