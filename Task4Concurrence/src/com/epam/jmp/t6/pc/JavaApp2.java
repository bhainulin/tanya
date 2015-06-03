package com.epam.jmp.t6.pc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class JavaApp2 {
	
	static Logger log = Logger.getLogger(JavaApp2.class);
	// private static Vector<Object> data = new Vector<Object>();
	 
	    public static void main(String[] args) {
	    	
	    	Producer producer = new Producer();
	    	new Thread(producer).start();
	    	
	    	Consumer consumer1 = new Consumer("1", producer);
	    	new Thread(consumer1).start();
	    	
	    	Consumer consumer2 = new Consumer("2", producer);
	    	new Thread(consumer2).start();
	    }
	 
	    public static class Consumer implements Runnable {
	    	
	    	Producer producer;
	    	String name;
	    	
	        Consumer(String name, Producer producer) {
	        	this.name = name;
	            this.producer = producer;
	        }
	 
	        @Override
	        public void run() {
	            while (true) {
	            	String message = producer.getMessage();
	            	log.info("-Consumer "+name+" get " + message);
	                try {
	                    Thread.sleep(2000);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }	               
	            }
	        }
	    }
	 
	    public static class Producer implements Runnable  {
	    	static final int MAX = 5;
	    	private List<String> messageList = new ArrayList<String>();
	    		 
	        @Override
	        public void run() {
	            while (true) {
	            	putMessage();
	                try {
	                    Thread.sleep(1000);
	                }catch (InterruptedException e) {						
						e.printStackTrace();
					}	                    
	            }
	        }
	        
	        private synchronized void putMessage(){
	        	while(messageList.size() >= MAX){
	        		try {
						wait();
					} catch (InterruptedException e) {						
						e.printStackTrace();
					}
	        	}
	        	String message = new java.util.Date().toString();
	        	log.info("+Producer put " + message);
	        	messageList.add(message);
	        }
	        
	        public synchronized String getMessage(){
	        	while(messageList.size() == 0){
	        		notify();
	        		try {
						wait();
					} catch (InterruptedException e) {						
						e.printStackTrace();
					}
	        	}
	        	
	        	String message = messageList.remove(0);
	        	notify();
	        	return message;	        	
	        }
	        
	    }
}