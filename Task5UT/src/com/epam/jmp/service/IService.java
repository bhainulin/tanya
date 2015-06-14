package com.epam.jmp.service;

import java.util.List;

public interface IService<T> {
	
	public List<T> getList();
	public T getValue(int id);
    public int save(T person);  
	public void remove(Integer[] ids);
	public List<T> search(String key, Object value) throws IllegalArgumentException;
}
