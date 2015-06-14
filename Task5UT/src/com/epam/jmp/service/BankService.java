package com.epam.jmp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.database.dao.BankDAO;
import com.epam.jmp.database.dao.IBankDAO;
import com.epam.jmp.model.Bank;

public class BankService implements IService<Bank> {

	private IBankDAO dao;

	public BankService(IBankDAO dao) {
		this.dao = dao;
	}

	public BankService() throws SQLException {
		this(new BankDAO());
	}
	
	@Override
	public List<Bank> getList() {
		List<Bank> list;
		list = dao.getList();
		if (list == null) {
			list = new ArrayList<Bank>();
		}
		return list;
	}
	
	@Override
	public Bank getValue(int id) {
		return dao.fetchById(id);
	}

	@Override
	public int save(Bank object) {		
		return dao.save(object);
	}

	@Override
	public void remove(Integer[] ids) {
		dao.remove(ids);	
	}
	
	@Override
	public List<Bank> search(String key, Object value) throws IllegalArgumentException{
		if (! Bank.getAvailableSK().contains(key)){
			throw new IllegalArgumentException("Incorrect search key");
		}
		
		return dao.search(key, value);
	}
}
