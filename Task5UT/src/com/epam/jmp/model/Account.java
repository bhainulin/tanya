package com.epam.jmp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAccount;
	private int idBank;
	private int idPerson;
	private CurrencyCode currencyCode;
	private double value;
	
    private static List<String> availableSK = new ArrayList<String>();
	
	static{
		availableSK.add("idAccount");
		availableSK.add("idBank");
		availableSK.add("idPerson");
		availableSK.add("currencyCode");
		availableSK.add("value");
	}
	
	public static List<String> getAvailableSK() {
		return availableSK;
	}
	
	public Account(){
		
	}
	
	public Account(int idAccount, int idBank, int idPerson,
			CurrencyCode currencyCode, double value) {
		super();
		this.idAccount = idAccount;
		this.idBank = idBank;
		this.idPerson = idPerson;
		this.currencyCode = currencyCode;
		this.value = value;
	}
	
	public Account(int idBank, int idPerson,
			CurrencyCode currencyCode, double value) {
		super();
		this.idBank = idBank;
		this.idPerson = idPerson;
		this.currencyCode = currencyCode;
		this.value = value;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public int getIdBank() {
		return idBank;
	}

	public void setIdBank(int idBank) {
		this.idBank = idBank;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public CurrencyCode getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(CurrencyCode currencyCode) {
		this.currencyCode = currencyCode;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + idAccount;
		result = prime * result + idBank;
		result = prime * result + idPerson;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Account other = (Account) obj;
		if (currencyCode != other.currencyCode)
			return false;
		if (idAccount != other.idAccount)
			return false;
		if (idBank != other.idBank)
			return false;
		if (idPerson != other.idPerson)
			return false;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [idAccount=" + idAccount + ", idBank=" + idBank
				+ ", idPerson=" + idPerson + ", currencyCode=" + currencyCode
				+ ", value=" + value + "]";
	}
}
