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
import com.epam.jmp.database.dao.IAccountDAO;
import com.epam.jmp.model.Account;
import com.epam.jmp.model.CurrencyCode;
import com.epam.jmp.service.AccountService;
import com.epam.jmp.service.IService;


@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
	
	private static IAccountDAO daoMock;
	private static List<Account> list;
	
	@BeforeClass
	public static void init() throws SQLException {
		list = new ArrayList<Account>();

		list.add(new Account(1, 1, 1, CurrencyCode.BYR, 25000000));
		list.add(new Account(2, 1, 1, CurrencyCode.USD, 1500));
		list.add(new Account(3, 1, 3, CurrencyCode.EUR, 900));

		daoMock = Mockito.mock(AccountDAO.class);
	}
	
	@AfterClass
	public static void destroy() throws SQLException {
		list = null;
		daoMock = null;
	}
	
	private void checkDBResult(Account expected, Account actual){
		assertEquals(expected.getIdAccount(), actual.getIdAccount());
		assertEquals(expected.getIdBank(), actual.getIdBank());
		assertEquals(expected.getIdPerson(), actual.getIdPerson());
		assertEquals(expected.getCurrencyCode(), actual.getCurrencyCode());
		assertEquals(expected.getValue(), actual.getValue(), 0);
	}
	
	@Test
	public final void getList() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(list);
		IService<Account> service = new AccountService(daoMock);		
		List<Account> dbResult = service.getList();
		assertEquals(3, dbResult.size());
		checkDBResult(list.get(0), dbResult.get(0));
		checkDBResult(list.get(1), dbResult.get(1));
		checkDBResult(list.get(2), dbResult.get(2));
	}
	
	@Test
	public final void getAccount() throws SQLException {
		Mockito.when(daoMock.fetchById(1)).thenReturn(list.get(0));
		IService<Account> service = new AccountService(daoMock);	
		int id = list.get(0).getIdBank();
		Account dbResult = service.getValue(id);
		checkDBResult(list.get(0), dbResult);
	}
	
	@Test
	public final void searchAccountByPersonID() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(list);
		IService<Account> service = new AccountService(daoMock);
		service.search("idPerson", 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void searchByIncorrectField() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(list);
		IService<Account> service = new AccountService(daoMock);
		service.search("INCORRECT_FIELD", "USSSD");
	}	

}
