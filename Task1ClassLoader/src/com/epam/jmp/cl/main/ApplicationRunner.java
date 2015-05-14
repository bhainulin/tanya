package com.epam.jmp.cl.main;

import java.lang.reflect.Method;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.jmp.cl.api.ICalculatorPlugin;
import com.epam.jmp.cl.api.NumbersPlugin;
import com.epam.jmp.cl.loader.PluginLoader;

public class ApplicationRunner {
	
	private static Logger theLogger = Logger.getLogger(ApplicationRunner.class);
	private static final String WORSPACE_PATH = "D:\\git\\tanya";
    //private Pattern pattern = Pattern.compile("[0-9]+");

	public static void main(String[] args) throws Exception {
		theLogger.info("START of program");
		PluginLoader jar1Loader = new PluginLoader(ApplicationRunner.class.getClassLoader());
		PluginLoader jar2Loader = new PluginLoader(ApplicationRunner.class.getClassLoader());
		Scanner scanner = new Scanner(System.in);		
	    boolean loop = true;
		while (loop) {
			printMainMenu();		
			int chosen = getMenuPoint(scanner);
			switch (chosen) {
			case 1:
				printSubMenu("addition");
				int[] intArrayToAddition = getNumbersInput(scanner);
				
				jar1Loader.loadPlugin(WORSPACE_PATH+"\\Task1ClassLoader\\plugins\\jar1.jar");				
				Class<?> addClass = jar1Loader.loadClass("com.epam.jmp.cl.api.NumbersPlugin");
				Object addInstance = addClass.newInstance();
				//theLogger.info(">>>>MMMM:" + addClass.getMethods()[0].getParameterTypes() );
				Class<?>[] addParamTypes = new Class[] {  int[].class };
				Method addRunMethod = addClass.getMethod("run", addParamTypes);
				Integer resultAdd = (Integer)addRunMethod.invoke(addInstance, new Object[] {intArrayToAddition});
				theLogger.info(">>>>Addition Result: " + resultAdd);
				
				break;
			case 2:
				printSubMenu("multiplication");
				int[] intArrayToMutlipl = getNumbersInput(scanner);
				
				jar2Loader.loadPlugin(WORSPACE_PATH+"\\Task1ClassLoader\\plugins\\jar2.jar");
				Class<?> multiClass = jar2Loader.loadClass("com.epam.jmp.cl.api.NumbersPlugin");
				Object multiInstance = multiClass.newInstance();
				Class<?>[] multiParamTypes = new Class[] {  int[].class };
				Method multiRunMethod = multiClass.getMethod("run", multiParamTypes);
				Integer resultMulti = (Integer)multiRunMethod.invoke(multiInstance, new Object[] {intArrayToMutlipl});
				
				theLogger.info(">>>>Multiplication Result: " + resultMulti);
				
				break;
			case 0:
				loop = false;
				break;
			}
		}
		scanner.close();
		theLogger.info("END of program");			
	}
		
	private static void printMainMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------\n");      
        sb.append("1.").append("addition (jar1)").append("\n");
        sb.append("2.").append("multiplication (jar2)").append("\n");        
        sb.append("0.").append("exit\nPlease, select from menu(enter a number):");
        theLogger.info(sb.toString());
    }
	
	private static void printSubMenu(String operationName) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(operationName).append(". Please type Numbers:");
        theLogger.info(sb.toString());
    }
	
	private static int getMenuPoint(Scanner scanner) {       
        int menuPoint = scanner.nextInt();
        scanner.nextLine();       
        return menuPoint;
	}
	
	private static int[] getNumbersInput(Scanner scanner){		
		String input = scanner.nextLine();
		
		String[] strArray = input.split(" ");
		int[] intArray = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++) {
		    intArray[i] = Integer.parseInt(strArray[i]);
		}
	
		return intArray;
	}
}
