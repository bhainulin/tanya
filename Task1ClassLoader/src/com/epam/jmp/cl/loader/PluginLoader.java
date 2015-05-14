package com.epam.jmp.cl.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;


public class PluginLoader extends ClassLoader {
	
	private static Logger theLogger = Logger.getLogger(PluginLoader.class);
	
	private Map<String, Class<?>> cacheClass = new HashMap<>();
	private ClassLoader parent;
	
	
	public PluginLoader(ClassLoader parent) {
		this.parent = parent;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> result = cacheClass.get(name);

		if (result == null) {
			theLogger.info("Class " + name +" starts loading by " + parent.getClass().getName());
			result = parent.loadClass(name);
		} else {
			theLogger.info("Class " + name +" is loaded From cached");
		}
        
		return result;
	}
	
	public void loadPlugin(String jarFilePath) throws IOException {
		JarFile jarFile = new JarFile(jarFilePath);
		
		Enumeration<JarEntry> jarEntries = jarFile.entries();
		
		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = jarEntries.nextElement();
			if (jarEntry.isDirectory())
				continue;
			
			String fileName = jarEntry.getName();
			if (fileName.endsWith(".class")) {
				String className = fileName.replace('/', '.').substring(0, fileName.length() - 6);
				
				if(!cacheClass.containsKey(className)){
					byte[] classData = loadClassData(className, jarFile, jarEntry);
					if (classData != null) {					
						Class<?> clazz = defineClass(className, classData, 0, classData.length);
						cacheClass.put(className, clazz);
						theLogger.info("Class " + className +" is loaded To cached");
					}
				}
			}
		}
	}
	
	private byte[] loadClassData(String className, JarFile jarFile, JarEntry jarEntry) throws IOException {
		theLogger.info("Loading class data is started: " + className);
		long size = jarEntry.getSize();
		if (size <= 0)
			return null;
		else if (size > Integer.MAX_VALUE) {
			String errorMessage = "Class size is too long";
			theLogger.error(errorMessage);
			throw new IOException(errorMessage);
		}

		byte[] buffer = new byte[(int) size];
		InputStream is = jarFile.getInputStream(jarEntry);
		is.read(buffer);
		theLogger.info("Loading class data is finished: " + className);
		return buffer;
	}

}
