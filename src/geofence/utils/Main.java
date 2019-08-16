package geofence.utils;

import java.util.*;

import java.lang.*;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main



{
	
	//private static MissingCellPolicy xRow;

    public static void main (String[] args) throws java.lang.Exception
     { 
    	
    	//Set<String> keyset = testresultdata.keySet();
	   FileInputStream myxls = new FileInputStream("./input/input.xls");
       HSSFWorkbook studentsSheet = new HSSFWorkbook(myxls);
       HSSFSheet worksheet = studentsSheet.getSheet("Sheet1");
       int lastRow=worksheet.getLastRowNum();
       System.out.println(lastRow);
       
     
       
      int rownum= worksheet.getFirstRowNum();
      // Row row = worksheet.createRow(++lastRow);
      Iterator<Row> iterator = worksheet.iterator();
      // for (String key : keyset) {
      
      while(iterator.hasNext()) {
    	   Row currentRow = iterator.next();
	       //Row row = worksheet.createRow(rownum++);
	    //   Object [] objArr = testresultdata.get(currentRow.getCell(5));
      // row.createCell(5).setCellValue((String)objArr[0]);
      // row.createCell(6).setCellValue((String)objArr[1]);
	       
	       Cell cell = currentRow.createCell(currentRow.getLastCellNum(), CellType.STRING);
	      // cell.setCellValue((String)objArr[0]);
	       
	       System.out.println("++++++++++       "+currentRow.getCell(0));
	       
       }
       myxls.close();
       FileOutputStream output_file =new FileOutputStream(new File("./input/input.xls"));  
       //write changes
       studentsSheet.write(output_file);
       output_file.close();
       System.out.println(" is successfully written");}
}
