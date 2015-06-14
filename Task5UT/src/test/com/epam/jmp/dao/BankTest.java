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

import com.epam.jmp.database.dao.BankDAO;
import com.epam.jmp.database.dao.IBankDAO;
import com.epam.jmp.model.Bank;
import com.epam.jmp.service.BankService;
import com.epam.jmp.service.IService;


@RunWith(MockitoJUnitRunner.class)
public class BankTest {
	
	private static IBankDAO daoMock;
	private static List<Bank> list;
	
	@BeforeClass
	public static void init() throws SQLException {
		list = new ArrayList<Bank>();

		list.add(new Bank(1, "bank1", "street1", "1", "11-11-11"));
		list.add(new Bank(1, "bank2", "street2", "2", "22-22-22"));

		daoMock = Mockito.mock(BankDAO.class);
	}
	
	@AfterClass
	public static void destroy() throws SQLException {
		list = null;
		daoMock = null;
	}
	
	private void checkDBResult(Bank expected, Bank actual){
		assertEquals(expected.getIdBank(), actual.getIdBank());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getStreet(), actual.getStreet());
		assertEquals(expected.getNumber(), actual.getNumber());
		assertEquals(expected.getPhone(), actual.getPhone());
	}
	
	@Test
	public final void getList() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(list);
		IService<Bank> service = new BankService(daoMock);		
		List<Bank> dbResult = service.getList();
		assertEquals(2, dbResult.size());
		checkDBResult(list.get(0), dbResult.get(0));
		checkDBResult(list.get(1), dbResult.get(1));
	}
	
	@Test
	public final void getBank() throws SQLException {
		Mockito.when(daoMock.fetchById(1)).thenReturn(list.get(0));
		IService<Bank> service = new BankService(daoMock);	
		int id = list.get(0).getIdBank();
		Bank dbResult = service.getValue(id);
		checkDBResult(list.get(0), dbResult);
	}
	
	@Test
	public final void searchBankByName() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(list);
		IService<Bank> service = new BankService(daoMock);
		service.search("name", "bank2");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void searchByIncorrectField() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(list);
		IService<Bank> service = new BankService(daoMock);
		service.search("INCORRECT_FIELD", "USSSD");
	}	

}
