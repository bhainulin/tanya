package com.epam.jmp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.database.dao.IPersonDAO;
import com.epam.jmp.database.dao.PersonDAO;
import com.epam.jmp.model.Person;

public class PersonService implements IService<Person> {

	private IPersonDAO personDAO;

	public PersonService(IPersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public PersonService() throws SQLException {
		this(new PersonDAO());
	}
	
	@Override
	public List<Person> getList() {
		List<Person> personList;
		personList = personDAO.getList();
		if (personList == null) {
			personList = new ArrayList<Person>();
		}
		return personList;
	}
	
	@Override
	public Person getValue(int id) {
		return personDAO.fetchById(id);
	}

	@Override
	public int save(Person person) {		
		return personDAO.save(person);
	}

	@Override
	public void remove(Integer[] ids) {
		personDAO.remove(ids);	
	}
	
	@Override
	public List<Person> search(String key, Object value) throws IllegalArgumentException{
		if (! Person.getAvailableSK().contains(key)){
			throw new IllegalArgumentException("Incorrect search key");
		}
		
		return personDAO.search(key, value);
	}
}
