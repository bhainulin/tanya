package com.epam.jmp.gc.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class JavaApp2 {
	
	private static Logger theLogger = Logger.getLogger(JavaApp2.class);
	
	//private static final String PATH = "D:\\temp\\UNIVER";
	//private static final String PATH = "D:\\Java Mentoring Program";
	private static int COUNT_FILES = 0;
	//private static List<String> contentList = new ArrayList<String>();
	
	private static final List<String> PATH_LIST = new ArrayList<String>();
	
	static {
		PATH_LIST.add("D:\\temp\\UNIVER");
		PATH_LIST.add("D:\\temp\\ContactsAfterExportImport");
		PATH_LIST.add("D:\\temp\\1");
		PATH_LIST.add("D:\\PATCHes");
		PATH_LIST.add("D:\\temp\\UNIVER");
		PATH_LIST.add("D:\\temp\\ContactsAfterExportImport");
		PATH_LIST.add("D:\\temp\\1");
		PATH_LIST.add("D:\\PATCHes");
		PATH_LIST.add("D:\\temp\\1");
		PATH_LIST.add("D:\\PATCHes");
		PATH_LIST.add("D:\\temp\\UNIVER");
	}

    public static void main (String[] arg){         
    	theLogger.info("START of program2");
		long startTime = System.currentTimeMillis();
		if(arg.length > 0){
			theLogger.info(">>>>>: " +arg[0]);
		}
		for(String path : PATH_LIST){
			theLogger.info("Count of Files in " + path + " are "+ searchFiles(path));
			COUNT_FILES = 0;
		}    	
    	long endTime = System.currentTimeMillis();
    	theLogger.info("***run time: " + (endTime - startTime));
		theLogger.info("END of program2");
    }
    
    public static int searchFiles(String parentDirectory) {
        File firstFile = new File(parentDirectory);
        File[] fileList = firstFile.listFiles();
        for (File file : fileList) {
        	/*if(file.length() > 200000){
             	continue;
             }*/
        	 /*if(COUNT_FILES > 1000){
        		 break;
        	 }*/
            if (file.isDirectory()){
            	searchFiles(file.getPath());	
            } else {read(file);
            	//contentList.add();
            }
            COUNT_FILES++;
        }
        return COUNT_FILES;
    }
    
    public static String read(File file) {     
        StringBuilder sb = new StringBuilder();
     
        try {            
            BufferedReader in = new BufferedReader(new FileReader(file));
            try {                
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {               
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
   
}
