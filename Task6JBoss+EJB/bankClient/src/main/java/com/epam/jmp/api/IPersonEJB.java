package com.epam.jmp.api;

import java.util.List;

import com.epam.jmp.model.Person;

public interface IPersonEJB {
    public List<Person> getList();
    public Person fetchById(int id);
    public int save(Person person);  
    public void remove(Integer[] personID);
    //public List<Person> search(String key, Object value);
}
