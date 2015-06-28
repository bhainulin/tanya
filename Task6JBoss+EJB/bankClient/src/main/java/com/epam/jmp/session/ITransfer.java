package com.epam.jmp.session;

import com.epam.jmp.model.Account;
import com.epam.jmp.model.CurrencyRatio;

public interface ITransfer {
	
	public Account exchange(Account account, CurrencyRatio currencyRatio);

}
