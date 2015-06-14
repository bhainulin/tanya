package com.epam.jmp.database.dao;

import java.util.List;

import com.epam.jmp.model.Account;


public interface IAccountDAO extends IDAO {
    public List<Account> getList();
    public Account fetchById(int id);
    public int save(Account account);  
    public void remove(Integer[] accountID);
    public List<Account> search(String key, Object value);
}
