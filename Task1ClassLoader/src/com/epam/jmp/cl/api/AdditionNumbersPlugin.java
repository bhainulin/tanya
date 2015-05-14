package com.epam.jmp.cl.api;

public class AdditionNumbersPlugin implements ICalculatorPlugin {

	@Override
	public int run(int[] intArray) {
		int sum = 0;

		for (int i : intArray){
			sum += i;
		}
		return sum;		
	}

}
