package test.com.epam.jmp.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.jmp.database.dao.AccountDAO;
import com.epam.jmp.database.dao.BankDAO;
import com.epam.jmp.database.dao.CurrencyRatioDAO;
import com.epam.jmp.database.dao.IAccountDAO;
import com.epam.jmp.database.dao.IBankDAO;
import com.epam.jmp.database.dao.ICurrencyRatioDAO;
import com.epam.jmp.model.Account;
import com.epam.jmp.model.Bank;
import com.epam.jmp.model.CurrencyCode;
import com.epam.jmp.model.CurrencyRatio;
import com.epam.jmp.service.TransferService;


@RunWith(MockitoJUnitRunner.class)
public class TransferTest {
	
	private static IAccountDAO accaountDaoMock;
	private static IBankDAO bankDao;
	private static ICurrencyRatioDAO currencyRatioDAO;
	
	private static Account account;
	private static Account incorrectAccount;
	private static Bank bank;
	private static List<CurrencyRatio> allCurrencies;
	
	private static TransferService service;
	
	@BeforeClass
	public static void init() throws SQLException {
		
		account = new Account(1, 1, 1, CurrencyCode.BYR, 24984900);
		incorrectAccount = new Account(1, 3, 1, CurrencyCode.BYR, 24984900);
		bank = new Bank(1, "bank1", "street1", "1", "11-11-11");
		allCurrencies = new ArrayList<CurrencyRatio>();
		allCurrencies.add(new CurrencyRatio(1,1, CurrencyCode.BYR, CurrencyCode.USD, 15300));
		allCurrencies.add(new CurrencyRatio(2,1, CurrencyCode.USD, CurrencyCode.BYR, 15300));
		
		accaountDaoMock = Mockito.mock(AccountDAO.class);
		bankDao = Mockito.mock(BankDAO.class);
		currencyRatioDAO = Mockito.mock(CurrencyRatioDAO.class);
		
		service = new TransferService(accaountDaoMock, bankDao, currencyRatioDAO);
	}
	
	@AfterClass
	public static void destroy() throws SQLException {
		account = null;
		bank = null;
		accaountDaoMock = null;
		bankDao = null;
		currencyRatioDAO = null;
		service = null;
		allCurrencies = null;
	}
	
	private void initMokitoData(){
		Mockito.when(bankDao.fetchById(account.getIdBank())).thenReturn(bank);
		Mockito.when(currencyRatioDAO.fetchByBankId(account.getIdBank())).thenReturn(allCurrencies);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public final void transferToIncorrectBank(){
		initMokitoData();
		service.echangeCurrency(incorrectAccount, CurrencyCode.USD);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void transferToIncorrectCurrency(){
		initMokitoData();
		service.echangeCurrency(account, CurrencyCode.GBR);
	}
	
	@Test
	public final void transfer(){
		initMokitoData();
		//System.out.println(">>account:  " + account.toString());
		Account actual = service.echangeCurrency(account, CurrencyCode.USD);
		Account expected = new Account(1, 1, 1, CurrencyCode.USD, 1633);
		checkResult(expected, actual);
	}
	
	private void checkResult(Account expected, Account actual){
		assertEquals(expected.getIdAccount(), actual.getIdAccount());
		assertEquals(expected.getIdBank(), actual.getIdBank());
		assertEquals(expected.getIdPerson(), actual.getIdPerson());
		assertEquals(expected.getCurrencyCode(), actual.getCurrencyCode());
		assertEquals(expected.getValue(), actual.getValue(), 0);
	}

}
