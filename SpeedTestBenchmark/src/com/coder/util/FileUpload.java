package com.coder.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class FileUpload {

	public static void uploadFile(XSSFWorkbook wb,String fileName) throws FileNotFoundException {
		
		String serverPath=ServerPath.getPath();
		
		
		String url=serverPath+File.separator+fileName+".xlsx";
		FileOutputStream fileOut = new FileOutputStream(url);
	
         try
         {
             wb.write(fileOut);
           
             
         } catch (IOException e) 
         {
             e.printStackTrace();
         }
         
		
	}
	
	public static String getXlsxUrl(String fileName)
	{
		String serverPath=ServerPath.getPath();
		 
		
		String url=serverPath+File.separator+fileName+".xlsx";
		
		
		
		return url;
	}


	

}
