package com.coder.util;

import java.io.File;

public class ServerPath {
public static String getPath()
{
	  String tomcatServerPath=System.getProperty("catalina.home")+File.separator;
	     tomcatServerPath+="SpeedTestBenchmark";
	     File file=new File(tomcatServerPath);
	     if(!file.exists())
	     {
	    	 file.mkdir();
	    	
	    	 }
	     
	 return file.getPath(); 
}

public static String createFloder(String url) {
	
	 File file=new File(url);
     if(!file.exists())
     {
    	 file.mkdir();
    	
    	 }
     
 return file.getPath(); 
}
}
