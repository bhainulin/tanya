package com.epam.jmp.cl.api;

import org.apache.log4j.Logger;

public class NumbersPlugin {
	
	private static Logger theLogger = Logger.getLogger(NumbersPlugin.class);
	
	static {
		theLogger.info("Loading the class that multiplies.");
	}
	
/*	static {
		theLogger.info("Loading the class that adds.");
	}*/
	
	/*public int run(int[] intArray) {
		int sum = 0;

		for (int i : intArray){
			sum += i;
		}
		return sum;		
	}*/
	
	public int run(int[] intArray) {
		int multi = 1;

		for (int i : intArray){
			multi *= i;
		}
		return multi;	
	}

}
