package com.epam.jmp.api;

import java.util.List;

import javax.ejb.Remote;
import com.epam.jmp.model.Unit;

@Remote
public interface IUnitRemote {
	
	public List<Unit> getList();

	public Unit fetchById(int id);
	    
	public int save(Unit object);  
	
	public void remove(int id);
}
