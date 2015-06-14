package com.epam.jmp.main;


import com.epam.jmp.database.dao.AccountDAO;
import com.epam.jmp.database.dao.BankDAO;
import com.epam.jmp.database.dao.CurrencyRatioDAO;
import com.epam.jmp.database.dao.IAccountDAO;
import com.epam.jmp.database.dao.IBankDAO;
import com.epam.jmp.database.dao.ICurrencyRatioDAO;
import com.epam.jmp.database.dao.IPersonDAO;
import com.epam.jmp.database.dao.PersonDAO;
import com.epam.jmp.model.Account;
import com.epam.jmp.model.Person;
import com.epam.jmp.model.CurrencyCode;
import com.epam.jmp.service.IService;
import com.epam.jmp.service.PersonService;
import com.epam.jmp.service.TransferService;



public class Runner {

	public static void main(String[] args) {
		IPersonDAO personDAO = new PersonDAO();
		IBankDAO bankDao = new BankDAO();
		ICurrencyRatioDAO currencyRatioDAO = new CurrencyRatioDAO();
		IAccountDAO accountDAO = new AccountDAO();
		
		TransferService service = new TransferService(accountDAO, bankDao, currencyRatioDAO);
		Account account = accountDAO.fetchById(1);
		//System.out.println(">>account:  " + account.toString());
		Account newAccount = service.echangeCurrency(account, CurrencyCode.USD);
		
		
		System.out.println(">>account:  " + newAccount.toString());
		
		/*IService<Person> service = new PersonService(personDAO);	
		System.out.println(">>" + service.search("firstname", "name2"));*/
		/*System.out.println(">>" + personDAO.fetchById(3).toString());
		
		Person p = new Person();
		p.setFirstname("FFFF2");
		p.setLastname("LLLL2");
		//p.setIdPerson(4);
		p.setBirthday(new java.sql.Date(96,15,20));
		//personDAO.save(p);
	   
		Integer[] ids = {5, 6};
		//personDAO.remove(ids);
		
		System.out.println(">>" + bankDao.getList().get(0).toString());
		System.out.println(">>" + currencyRatioDAO.fetchByBankId(1).size() + " >>>>>>>>> " + CurrencyCode.BYR.name());
		
		System.out.println(">>" + accountDAO.getList().get(1).toString());
		
		System.out.println(">>" +java.sql.Date.valueOf("1980-06-01"));*/
		

	}

}
