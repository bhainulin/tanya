package com.epam.jmp.cl.api;

public class MultiplyingNumbersPlugin implements ICalculatorPlugin {

	@Override
	public int run(int[] intArray) {
		int multi = 1;

		for (int i : intArray){
			multi *= i;
		}
		return multi;	
	}

}
