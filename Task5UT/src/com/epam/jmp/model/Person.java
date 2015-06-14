package com.epam.jmp.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idPerson;
	private String firstname;
	private String lastname;
	private Date birthday;
	
	private static List<String> availableSK = new ArrayList<String>();
	
	static{
		availableSK.add("idPerson");
		availableSK.add("firstname");
		availableSK.add("lastname");
		availableSK.add("birthday");
	}
	
	public static List<String> getAvailableSK() {
		return availableSK;
	}

	public Person() {
		
	}

	public Person(int idPerson, String firstname, String lastname, Date birthday) {
		this.idPerson = idPerson;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
	}
	
	public Person(String firstname, String lastname, Date birthday) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + idPerson;
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (idPerson != other.idPerson)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [idPerson=" + idPerson + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", birthday=" + birthday + "]";
	}
	
}
