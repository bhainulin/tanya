package com.epam.jmp.api;

import javax.ejb.Remote;

@Remote
public interface IOfficeRemote {
	
	public void addEmployeeToUnit(int employeeId, int unitId);

	public void assignEmployeeForProject(int employeeId, int projectId);

}
