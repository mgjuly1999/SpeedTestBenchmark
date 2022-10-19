package com.coder.util;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class ParseDouble {
	
	public static double formatDouble(double averageDownloadSpeed) {
		
	     BigDecimal bd=new BigDecimal(averageDownloadSpeed).setScale(2,RoundingMode.HALF_DOWN);
	   return bd.doubleValue();
	}
     
}
