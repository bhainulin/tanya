package com.epam.jmp.t4.cars;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Car implements Runnable {
    private static final long MAX_DISTANCE = 10000;//10000;
    Logger log = Logger.getLogger(getClass());
    private int friction; 
    private long distance; 
    private String name;
    
    static int counter1 = 0;
    static boolean isWinnerExist = false;
    static boolean isFirstCarDisqualified = false;
    private static ThreadLocal<Integer> counter = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        } 
    };
    public Car(String name, int friction) {
        this.name = name;
        this.friction = friction; //100;
    }
    @Override
    public void run() {
        try {        	
        	String threadName = Thread.currentThread().getName();
            while (distance < MAX_DISTANCE) { 
            	//System.out.println(isWinnerExist);
            	if(isWinnerExist){
                	break;
                }
            	
                Thread.sleep(friction);
                distance += 100;
                counter1+=friction;
                counter.set(counter.get() + friction);
                System.out.println(">>>>>>>>>>>>>>" + counter.get() + " for " +threadName +"_"+ counter1);
                log.info(name + " " + distance);
                if(counter.get() >= 5000){
                	if(!isFirstCarDisqualified){
                		System.out.println("*****************Disqualified " + name + " in thread " + threadName);
                		Thread.currentThread().interrupt();
                		isFirstCarDisqualified = true;
                	}
                }
            }
            if(distance >= MAX_DISTANCE){
            	System.out.println("THe WINNER IS " + name + " in thread " + threadName);
            	isWinnerExist = true;
            	//interraptAllThreads();
            }
        } catch (InterruptedException e) {
            //log.error(e);
        }
    }
    
    
    static Thread car1, car2, car3, car4, car5;
    static List<Thread> allcars = new ArrayList<Thread>();
    
    public static void main(String[] args) throws InterruptedException {
    	
    	car1 = new Thread(new Car("car1", 100)/*, "car1"*/);
    	car2 = new Thread(new Car("car2", 180));
    	car3 = new Thread(new Car("car3", 150));
    	car4 = new Thread(new Car("car4", 90));
    	car5 = new Thread(new Car("car5", 115));
    	allcars.add(car1);
    	allcars.add(car2);
    	allcars.add(car3);
    	allcars.add(car4);    	
    	allcars.add(car5);
    	
    	for(Thread car : allcars){
    		car.start();    		
    	}
    }
    
    static void interraptAllThreads(){
    	for(Thread car : allcars){
    		if(car.isAlive()){
    			car.interrupt();
    		}
    	}
    }
    

}