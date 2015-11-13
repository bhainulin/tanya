package com.epam.sc.util;

import java.util.Random;

public class RandomUtil {
  
  private static Random random = new Random();
  
  public static boolean getRandomBoolean() {
    return random.nextBoolean();
  }
  
  public static Integer getRandomInteger(int bound){
    int randomInt = random.nextInt(bound);
    return randomInt > 0 ? randomInt : 1;
  }

}
