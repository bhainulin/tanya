package com.epam.jmp.database.dao;

import java.util.List;

import com.epam.jmp.model.CurrencyRatio;
public interface ICurrencyRatioDAO extends IDAO {
    public List<CurrencyRatio> getList();
    public CurrencyRatio fetchById(int id);
    public List<CurrencyRatio> fetchByBankId(int id);
    public int save(CurrencyRatio currencyRatio);  
    public void remove(Integer[] currencyRatioID);
    public List<CurrencyRatio> search(String key, Object value);
}
