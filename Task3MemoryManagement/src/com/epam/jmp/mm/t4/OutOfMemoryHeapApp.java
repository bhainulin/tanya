package com.epam.jmp.mm.t4;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class OutOfMemoryHeapApp {	
	
	private static Logger theLogger = Logger.getLogger(OutOfMemoryHeapApp.class);
	
	private static int M1 = 1048576;//1024*1024
	
	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();
		try{			
			while (true) {
				byte[] oneMB = new byte[M1];
				list.add(oneMB);
			}			
		} catch (OutOfMemoryError error){
			theLogger.error(error.getMessage());
			theLogger.error("Created: " + list.size());
		}		
	}
	
	
	
}
