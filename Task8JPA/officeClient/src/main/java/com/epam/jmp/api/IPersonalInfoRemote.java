package com.epam.jmp.api;

import java.util.List;

import javax.ejb.Remote;

import com.epam.jmp.model.PersonalInfo;


@Remote
public interface IPersonalInfoRemote {
	
	public List<PersonalInfo> getList();

	public PersonalInfo fetchById(int id);
	    
	public int save(PersonalInfo object);  
	
	public void remove(int id);

}
