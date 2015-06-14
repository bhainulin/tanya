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

import com.epam.jmp.database.dao.CurrencyRatioDAO;
import com.epam.jmp.database.dao.ICurrencyRatioDAO;
import com.epam.jmp.model.CurrencyCode;
import com.epam.jmp.model.CurrencyRatio;
import com.epam.jmp.service.CurrencyRatioService;
import com.epam.jmp.service.IService;


@RunWith(MockitoJUnitRunner.class)
public class CurrecyRatioTest {
	
	private static ICurrencyRatioDAO daoMock;
	private static List<CurrencyRatio> list;
	
	@BeforeClass
	public static void init() throws SQLException {
		list = new ArrayList<CurrencyRatio>();

		list.add(new CurrencyRatio(1, 1,CurrencyCode.BYR, CurrencyCode.USD, 15300));
		list.add(new CurrencyRatio(12, 2,CurrencyCode.EUR, CurrencyCode.RUR, 59.35));

		daoMock = Mockito.mock(CurrencyRatioDAO.class);
	}
	
	@AfterClass
	public static void destroy() throws SQLException {
		list = null;
		daoMock = null;
	}
	
	private void checkDBResult(CurrencyRatio expected, CurrencyRatio actual){
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getIdBank(), actual.getIdBank());
		assertEquals(expected.getInitial(), actual.getInitial());
		assertEquals(expected.getResult(), actual.getResult());
		assertEquals(expected.getRatio(), actual.getRatio(), 0);
	}
	
	@Test
	public final void getList() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(list);
		IService<CurrencyRatio> service = new CurrencyRatioService(daoMock);		
		List<CurrencyRatio> dbResult = service.getList();
		assertEquals(2, dbResult.size());
		checkDBResult(list.get(0), dbResult.get(0));
		checkDBResult(list.get(1), dbResult.get(1));
	}
	
	@Test
	public final void getCurrencyRatio() throws SQLException {
		Mockito.when(daoMock.fetchById(1)).thenReturn(list.get(0));
		IService<CurrencyRatio> service = new CurrencyRatioService(daoMock);	
		int id = list.get(0).getId();
		CurrencyRatio dbResult = service.getValue(id);
		checkDBResult(list.get(0), dbResult);
	}
	
	@Test
	public final void searchCurrencyRatioByInitialCurrecyCode() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(list);
		IService<CurrencyRatio> service = new CurrencyRatioService(daoMock);
		service.search("initial", CurrencyCode.USD.name());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void searchByIncorrectField() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(list);
		IService<CurrencyRatio> service = new CurrencyRatioService(daoMock);
		service.search("INCORRECT_FIELD", "USSSD");
	}	

}
