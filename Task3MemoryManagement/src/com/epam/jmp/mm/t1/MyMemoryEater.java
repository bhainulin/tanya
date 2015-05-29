package com.epam.jmp.mm.t1;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MyMemoryEater {
    static final Logger logger = Logger.getLogger(MyMemoryEater.class);
    
    private static int M1 = 1048576;//1024*1024

    public static void main(String[] args) {
        List v = new ArrayList();
        Runtime rt = Runtime.getRuntime();
        int maxFreeMemory = (int) (rt.freeMemory()/M1);
        logger.info("MAX: " +maxFreeMemory);
       //while (true) {
        for (int i = 0; i < maxFreeMemory; i++) {
            byte b[] = new byte[M1];
            v.add(b);
            
            StringBuilder sb = new StringBuilder("i: ").append(i).append(" free memory:").append(rt.freeMemory()/M1);
            logger.info(sb.toString());
            //System.out.println("free memory: " + rt.freeMemory()/M1);
            /*try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
            }*/
        }
    }
}
