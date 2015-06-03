package com.epam.jmp.t7.pc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.Logger;


public class JavaApp {
	/*
	static Logger log = Logger.getLogger(JavaApp.class);
	 private static Vector<Object> data = new Vector<Object>();
	 
	    public static void main(String[] args) {
	        new Producer().start();
	        new Consumer().start();
	    }
	 
	    public static class Consumer extends Thread {
	        Consumer() {
	            super("Consumer");
	        }
	 
	        @Override
	        public void run() {
	            for (;;) {
	                try {
	                    Thread.sleep(500);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                synchronized(data){
	                Iterator it = data.iterator();
	                while (it.hasNext())
	                	log.info("Consumer get " + it.next());
	                }
	            }
	        }
	    }
	 
	    public static class Producer extends Thread {
	        Producer() {
	            super("Producer");
	        }
	 
	        @Override
	        public void run() {
	        	Integer i =0;
	            for (;;) {
	                try {
	                    Thread.sleep(500);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                Integer newValue = ++i; 
	                data.add(newValue);
	                log.info("+Produced add " + newValue + " size of vector: "+data.size());
	                if (data.size() > 5){
	                	data.remove(data.size() - 1);
	                	log.info("-Produced removed data " + "size of vector: "+data.size());
	                }
	                    
	            }
	        }
	    }*/
}