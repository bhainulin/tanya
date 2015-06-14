package com.epam.jmp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epam.jmp.database.dao.IAccountDAO;
import com.epam.jmp.database.dao.IBankDAO;
import com.epam.jmp.database.dao.ICurrencyRatioDAO;
import com.epam.jmp.model.Account;
import com.epam.jmp.model.Bank;
import com.epam.jmp.model.CurrencyCode;
import com.epam.jmp.model.CurrencyRatio;

public class TransferService {
	
	private IAccountDAO accaountDao;
	private IBankDAO bankDao;
	private ICurrencyRatioDAO currencyRatioDAO;

	public TransferService(IAccountDAO dao, IBankDAO bankDao, ICurrencyRatioDAO currencyRatioDAO) {
		this.accaountDao = dao;
		this.bankDao = bankDao;
		this.currencyRatioDAO = currencyRatioDAO;
	}
	
	public Account echangeCurrency(Account account, CurrencyCode toCurrency) throws IllegalArgumentException{
		if(account != null && toCurrency != null){
			Bank bank = checkBank(account.getIdBank());
			List<CurrencyRatio> allCurrencies = currencyRatioDAO.fetchByBankId(bank.getIdBank());
			
			Set<CurrencyCode> fromCurrencySet = getInitialCurrency(allCurrencies);
			Set<CurrencyCode> toCurrencySet = getResultCurrency(allCurrencies);
			
			CurrencyCode accountCode = account.getCurrencyCode();			
			if(!fromCurrencySet.contains(accountCode)){
				throw new IllegalArgumentException("Bank " + bank.getName() + " does not convert FROM " + accountCode.name());
			}
			if(!toCurrencySet.contains(toCurrency)){
				throw new IllegalArgumentException("Bank " + bank.getName() + " does not convert TO " + toCurrency.name());
			}
			
			double ratio = searchRatio(allCurrencies, accountCode, toCurrency);
			double newResult = account.getValue() / ratio;
			account.setCurrencyCode(toCurrency);
			account.setValue(newResult);
			accaountDao.save(account);
		}
		
		
		return account;
	}
	
	private Bank checkBank(int bankID) throws IllegalArgumentException{
		Bank bank = bankDao.fetchById(bankID);
		if(bank == null){
			throw new IllegalArgumentException("Incorrect bank key");
		}
		return bank;
	}
	
	private Set<CurrencyCode> getInitialCurrency(List<CurrencyRatio> all){
		Set<CurrencyCode> fromCurrencySet = new HashSet<CurrencyCode>();
		for(CurrencyRatio one : all){
			fromCurrencySet.add(one.getInitial());
		}
		return fromCurrencySet;	
	}
	
	private Set<CurrencyCode> getResultCurrency(List<CurrencyRatio> all){
		Set<CurrencyCode> fromCurrencySet = new HashSet<CurrencyCode>();
		for(CurrencyRatio one : all){
			fromCurrencySet.add(one.getResult());
		}
		return fromCurrencySet;	
	}
	
	private double searchRatio(List<CurrencyRatio> allCurrencies, CurrencyCode from, CurrencyCode to) throws IllegalArgumentException{
		for(CurrencyRatio currencyRatio : allCurrencies){
			if(currencyRatio.getInitial().equals(from) && currencyRatio.getResult().equals(to)){
				return currencyRatio.getRatio();
			}
		}
		throw new IllegalArgumentException("Ratio is not found");
	}

}
