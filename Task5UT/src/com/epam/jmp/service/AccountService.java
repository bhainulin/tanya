package com.epam.jmp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.database.dao.AccountDAO;
import com.epam.jmp.database.dao.IAccountDAO;
import com.epam.jmp.model.Account;

public class AccountService implements IService<Account> {

	private IAccountDAO dao;

	public AccountService(IAccountDAO dao) {
		this.dao = dao;
	}

	public AccountService() throws SQLException {
		this(new AccountDAO());
	}
	
	@Override
	public List<Account> getList() {
		List<Account> list;
		list = dao.getList();
		if (list == null) {
			list = new ArrayList<Account>();
		}
		return list;
	}
	
	@Override
	public Account getValue(int id) {
		return dao.fetchById(id);
	}

	@Override
	public int save(Account object) {		
		return dao.save(object);
	}

	@Override
	public void remove(Integer[] ids) {
		dao.remove(ids);	
	}
	
	@Override
	public List<Account> search(String key, Object value) throws IllegalArgumentException{
		if (! Account.getAvailableSK().contains(key)){
			throw new IllegalArgumentException("Incorrect search key");
		}
		
		return dao.search(key, value);
	}
}
