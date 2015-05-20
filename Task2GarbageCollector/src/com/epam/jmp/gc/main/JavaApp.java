package com.epam.jmp.gc.main;


import java.math.BigInteger;
import java.util.Scanner;

import org.apache.log4j.Logger;


public class JavaApp {
	
	private static Logger theLogger = Logger.getLogger(JavaApp.class);
	

	public static void main(String[] args) throws Exception {
		theLogger.info("START of program");
		long startTime = System.currentTimeMillis();
		if(args.length > 0){
			theLogger.info(">>>>>: " +args[0]);
		}
	
		for(int j = 0; j < 600000L; j++){
			//cыграть в лотерее, в которой нужно угадать 60 чисел из 490 возможных
			int k = 60;
			int n = 490;
			
			BigInteger lotteryOdds = BigInteger.valueOf(1);
			for(int i = 1; i <= k; i++){
				lotteryOdds = lotteryOdds
						.multiply(BigInteger.valueOf(n-i+1))
						.divide(BigInteger.valueOf(i));
			}
		}
		
		long endTime = System.currentTimeMillis();
		theLogger.info("***run time: " + (endTime - startTime));
		theLogger.info("END of program");	
		
		/*Scanner scanner = new Scanner(System.in);
		int menuPoint = scanner.nextInt();
        scanner.nextLine();
        scanner.close();*/
	}
		
	
}
