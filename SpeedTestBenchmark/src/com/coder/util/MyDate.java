package com.coder.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyDate {
	
private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
public static Date getDate(){
	Date now=new Date();   
	   return now;
}

public static String getStrDate(Date date) {
	
	return formatter.format(date);
}
	 
}
