package com.epam.jmp.database.dao;

import java.util.List;

import com.epam.jmp.model.Person;

public interface IPersonDAO extends IDAO {
    public List<Person> getList();
    public Person fetchById(int id);
    public int save(Person person);  
    public void remove(Integer[] personID);
    public List<Person> search(String key, Object value);
}
