package com.epam.jmp.database.dao;

import java.util.List;

import com.epam.jmp.model.Bank;

public interface IBankDAO extends IDAO{
	public List<Bank> getList();
    public Bank fetchById(int id);
    public int save(Bank bank);  
    public void remove(Integer[] bankID);
    public List<Bank> search(String key, Object value);
   
}
