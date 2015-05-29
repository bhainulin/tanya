package com.epam.jmp.mm.t4;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class OutOfMemoryPermGenApp {	
	
	private static Logger theLogger = Logger.getLogger(OutOfMemoryPermGenApp.class);
	private static final String WORSPACE_PATH = "D:\\git\\tanya";
	
	
	public static void main(String[] args) throws Exception {
		List<Class<?>> list = new ArrayList<Class<?>>();
		
		try{			
			while (true) {
			   PluginLoader jar1Loader = new PluginLoader(OutOfMemoryPermGenApp.class.getClassLoader());
			   jar1Loader.loadPlugin(WORSPACE_PATH+"\\Task1ClassLoader\\plugins\\jar1.jar");				
			   Class<?> addClass = jar1Loader.loadClass("com.epam.jmp.cl.api.NumbersPlugin");
			   list.add(addClass);
			}
		} catch (OutOfMemoryError error){
			theLogger.error(error.getMessage());
			theLogger.error("Created: " + list.size());
		}	
		
	}
	
	
	
}
