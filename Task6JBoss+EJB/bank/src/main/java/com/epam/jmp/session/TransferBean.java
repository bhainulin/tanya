package com.epam.jmp.session;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.epam.jmp.model.Account;
import com.epam.jmp.model.CurrencyRatio;

@Stateless
@Remote(ITransfer.class)
public class TransferBean implements ITransfer{

	@Override
	public Account exchange(Account account, CurrencyRatio currencyRatio) {
		if (account == null || currencyRatio == null) {
			throw new IllegalArgumentException("Incorrect input parameters.");
		}
		if (!account.getCurrencyCode().equals(currencyRatio.getInitial())) {
			throw new IllegalArgumentException("Bank  does not convert FROM " + currencyRatio.getInitial());
		}
		
		double newResult = account.getValue() / currencyRatio.getRatio();
		account.setCurrencyCode(currencyRatio.getResult());
		account.setValue(newResult);
		
		return account;
	}

}
