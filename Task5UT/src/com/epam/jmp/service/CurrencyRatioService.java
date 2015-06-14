package com.epam.jmp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.database.dao.CurrencyRatioDAO;
import com.epam.jmp.database.dao.ICurrencyRatioDAO;
import com.epam.jmp.model.CurrencyRatio;


public class CurrencyRatioService implements IService<CurrencyRatio> {

	private ICurrencyRatioDAO dao;

	public CurrencyRatioService(ICurrencyRatioDAO dao) {
		this.dao = dao;
	}

	public CurrencyRatioService() throws SQLException {
		this(new CurrencyRatioDAO());
	}
	
	@Override
	public List<CurrencyRatio> getList() {
		List<CurrencyRatio> list;
		list = dao.getList();
		if (list == null) {
			list = new ArrayList<CurrencyRatio>();
		}
		return list;
	}
	
	@Override
	public CurrencyRatio getValue(int id) {
		return dao.fetchById(id);
	}

	@Override
	public int save(CurrencyRatio object) {		
		return dao.save(object);
	}

	@Override
	public void remove(Integer[] ids) {
		dao.remove(ids);	
	}
	
	@Override
	public List<CurrencyRatio> search(String key, Object value) throws IllegalArgumentException{
		if (! CurrencyRatio.getAvailableSK().contains(key)){
			throw new IllegalArgumentException("Incorrect search key");
		}
		
		return dao.search(key, value);
	}
}
