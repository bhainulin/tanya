package com.epam.jmp.model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRatio {
	
	private int id;
	private int idBank;
	private CurrencyCode initial;
	private CurrencyCode result;
	private double ratio;
	
    private static List<String> availableSK = new ArrayList<String>();
	
	static{
		availableSK.add("id");
		availableSK.add("idBank");
		availableSK.add("initial");
		availableSK.add("result");
		availableSK.add("ratio");
	}
	
	public static List<String> getAvailableSK() {
		return availableSK;
	}
	
	public CurrencyRatio(){
		
	}
	
	public CurrencyRatio(int idBank, CurrencyCode initial,
			CurrencyCode result, double ratio) {
		super();
		this.idBank = idBank;
		this.initial = initial;
		this.result = result;
		this.ratio = ratio;
	}
	
	public CurrencyRatio(int id, int idBank, CurrencyCode initial,
			CurrencyCode result, double ratio) {
		super();
		this.id = id;
		this.idBank = idBank;
		this.initial = initial;
		this.result = result;
		this.ratio = ratio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBank() {
		return idBank;
	}

	public void setIdBank(int idBank) {
		this.idBank = idBank;
	}

	public CurrencyCode getInitial() {
		return initial;
	}

	public void setInitial(CurrencyCode initial) {
		this.initial = initial;
	}

	public CurrencyCode getResult() {
		return result;
	}

	public void setResult(CurrencyCode result) {
		this.result = result;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idBank;
		result = prime * result + ((initial == null) ? 0 : initial.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
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
		CurrencyRatio other = (CurrencyRatio) obj;
		if (id != other.id)
			return false;
		if (idBank != other.idBank)
			return false;
		if (initial != other.initial)
			return false;
		if (Double.doubleToLongBits(ratio) != Double
				.doubleToLongBits(other.ratio))
			return false;
		if (result != other.result)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CurrencyRatio [id=" + id + ", idBank=" + idBank + ", initial="
				+ initial + ", result=" + result + ", ratio=" + ratio + "]";
	}
}
