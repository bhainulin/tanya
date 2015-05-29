package com.epam.jmp.mm.t6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import org.apache.log4j.Logger;

public class OutOfMemoryHeapApp6 {	
	
	private static Logger theLogger = Logger.getLogger(OutOfMemoryHeapApp6.class);
	
	private static int M1 = 1048576;
	
	public static void main(String[] args) throws Exception {
		long heapsize=Runtime.getRuntime().totalMemory();
	    System.out.println("heapsize is::"+(heapsize/M1));
	    
	    String path1 = "D:\\BOOKS\\Manning - iText in Action - Creating and Manipulating PDF.pdf";
	    String path2 = "D:\\Бёрдмэн(Birdman).2014.[драма, комедия].D.HDRip.avi";
	    Path path = Paths.get(path1);
	    byte[] data = Files.readAllBytes(path);
	    
	}
	
}
