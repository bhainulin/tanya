package com.epam.jmp.mm.t2;

import org.apache.log4j.Logger;

public class JavaApp {
	
	
	private static Logger theLogger = Logger.getLogger(JavaApp.class);
	
	public static void main(String[] args) {
		
		Integer a1 = 10;
		theLogger.info("a1 in main: " + a1);
		method1(a1);		
		theLogger.info("a1 in main: " + a1);
		
		Integer a2 = 10;
		theLogger.info("a2 in main: " + a2);
		theLogger.info("method returns a2: " + method1WithRV(a2));
		theLogger.info("a2 in main: " + a2);
		
		theLogger.info("--------------------");

		int[] b1 = new int[1];
		b1[0] = 100;
		theLogger.info("b1 in main: " + b1[0]);
		method2(b1);
		theLogger.info("b1 in main: " + b1[0]);
		
		int[] b2 = new int[1];
		b2[0] = 100;
		theLogger.info("b2 in main: " + b2[0]);
		theLogger.info("method returns b2: " + method2WithRV(b2));
		theLogger.info("b2 in main: " + b2[0]);
		
		theLogger.info("--------------------");
		
		int c = 0;
		int[] d = {20};
		theLogger.info("c: " + c +" d:" + d[0]);
		/*
		| Stack       |   Heap  |
		---------------------
		//stack frame |
		c = 0
		d ------------->  20
		*/
		
		f1(c,d);
		//stack frame method f1 is removed
		
		/*
		| Stack       |   Heap  |
		---------------------
		//stack frame |
		c = 0
		d ------------->  40
		*/
		theLogger.info("c: " + c +" d:" + d[0]);
		f2(c,d);
		//stack frame method f2 is removed
		
		/*
		| Stack       |   Heap  |
		---------------------
		//stack frame |
		c = 0
		d ------------->  40
		*/
		theLogger.info("c: " + c +" d:" + d[0]);		

	}
	private static void method1(Integer ob) {
		ob = new Integer(20);
		theLogger.debug(">>method1 : " + ob);
	}
	
	private static Integer method1WithRV(Integer ob) {
		ob = new Integer(20);
		theLogger.debug(">>method1WithRV : " + ob);
		return ob;
	}
	
	private static void method2(int[] mass) {
		mass[0] = 200;
		theLogger.debug(">>method2 : " + mass[0]);
	}
	
	private static Integer method2WithRV(int[] mass) {
		mass[0] = 200;
		theLogger.debug(">>method2WithRV : " + mass[0]);
		return mass[0];
	}
	
	private static void f1(int c, int[] d){
		c = 30;
		d[0] = 40;
		
		/*
		| Stack          |   Heap  |
		-------------------------
		//stack frame f1 |
		c = 30
		d ----------------->  40
		*/
	}

	private static void f2(int c, int[] d){
		c = 50;
		d = new int[] {60};
		
		/*
		| Stack          |   Heap  |
		-------------------------
		//stack frame f2 |
		c = 50
		d --------XXXXXXX---->  40
		d ------------------->  60
		*/
	}


}
