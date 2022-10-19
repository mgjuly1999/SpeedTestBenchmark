package com.coder.util;

import java.text.DecimalFormat;
import java.util.Random;

public class GeneratedRandom {


private static Random rand = new Random(); 
private static DecimalFormat df = new DecimalFormat();
public static float getRandomValue(){
	
	float min=1;
	float max=1000;
	Random rand = new Random();
	
	df.setMaximumFractionDigits(2);
	
    return Float.parseFloat(df.format(rand.nextFloat() * (max - min) + min));
   
}
    
   
  }
	

