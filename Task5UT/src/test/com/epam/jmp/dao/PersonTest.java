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

import com.epam.jmp.database.dao.IPersonDAO;
import com.epam.jmp.database.dao.PersonDAO;
import com.epam.jmp.model.Person;
import com.epam.jmp.service.IService;
import com.epam.jmp.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {
	
	private static IPersonDAO daoMock;
	private static List<Person> personList;
	
	@BeforeClass
	public static void init() throws SQLException {
		personList = new ArrayList<Person>();

		personList.add(new Person(1, "name11", "last11", java.sql.Date.valueOf("1980-06-01")));
		personList.add(new Person(2, "name2", "last2", java.sql.Date.valueOf("1991-06-12")));

		daoMock = Mockito.mock(PersonDAO.class);
	}
	
	@AfterClass
	public static void destroy() throws SQLException {
		personList = null;
		daoMock = null;
	}
	
	private void checkDBResult(Person expected, Person actual){
		assertEquals(expected.getIdPerson(), actual.getIdPerson());
		assertEquals(expected.getFirstname(), actual.getFirstname());
		assertEquals(expected.getLastname(), actual.getLastname());
		assertEquals(expected.getBirthday(), actual.getBirthday());
	}
	
	@Test
	public final void getList() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(personList);
		IService<Person> service = new PersonService(daoMock);		
		List<Person> dbResult = service.getList();
		assertEquals(2, dbResult.size());
		checkDBResult(personList.get(0), dbResult.get(0));
		checkDBResult(personList.get(1), dbResult.get(1));
	}
	
	@Test
	public final void getPerson() throws SQLException {
		Mockito.when(daoMock.fetchById(1)).thenReturn(personList.get(0));
		IService<Person> service = new PersonService(daoMock);	
		int id = personList.get(0).getIdPerson();
		Person dbResult = service.getValue(id);
		checkDBResult(personList.get(0), dbResult);
	}
	
	@Test(timeout = 3000)
	public final void searchPersonByFName() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(personList);
		IService<Person> service = new PersonService(daoMock);
		service.search("firstname", "name2");
	}
	
	@Test(timeout = 3000, expected = IllegalArgumentException.class)
	public final void searchPersonByIncorrectField() throws SQLException {
		Mockito.when(daoMock.getList()).thenReturn(personList);
		IService<Person> service = new PersonService(daoMock);
		service.search("INCORRECT_FIELD", "name2");
	}	

}
