package testng.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import geofence.fixtures.Test1;
import geofence.handlers.AutomationPropertyHandler;

public class XlsMain {
	
	 public static HSSFWorkbook workbook;
	    public static HSSFSheet worksheet;
	    public static DataFormatter formatter= new DataFormatter();
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		func();
	}

	private static Iterator<Test1> func() throws FileNotFoundException, IOException {


		  FileInputStream fileInputStream= new FileInputStream(AutomationPropertyHandler.getInstance().getValue("xlsFileName")); //Excel sheet file location get mentioned here
		  String SheetName=AutomationPropertyHandler.getInstance().getValue("xlsSheetName");

	      workbook = new HSSFWorkbook (fileInputStream); //get my workbook 
	      worksheet=workbook.getSheet(SheetName);// get my sheet from workbook
	      HSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0   
	   
	      int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
	      int ColNum= Row.getLastCellNum(); // get last ColNum 
	       
	      Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
	      
	     
	      
	      List<HashMap<Integer, String>> listOfMaps=new ArrayList<HashMap<Integer,String>>();
	      
	          for(int i=0; i<RowNum-1; i++) //Loop work for Rows
	          {  
	              HSSFRow row= worksheet.getRow(i+1);
	              
	            //  if(PoiWriter.getCellValue(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), i, 3).
	            		//  equals(DataProviderUtils.resolveDataProviderArguments2(testMethod))) {
	              
	             // Test1 test1 = new Test1();
	              
	              HashMap<Integer, String> hMap=new HashMap<Integer,String>();
	             
	              for (int j=0; j<ColNum; j++) //Loop work for colNum
	              {
	            	 
	                  if(row==null)
	                      Data[i][j]= "";
	                  else
	                  {
	                      HSSFCell cell= row.getCell(j);
	                      if(cell==null)
	                          Data[i][j]= ""; //if it get Null value it pass no data 
	                      else
	                      {
	                          String value=formatter.formatCellValue(cell);
	                          System.out.println(" i and j  ==== "+i+"  "+j+"   ");
	                          Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
	                          
	                         
	                              if(j==0) {
	                           //       test1.setCol1(value);
	                                  
	                                  hMap.put(j, value);
	                                  
	                                  }
	                              if(j==1) {
	                             //     test1.setCol2(value);
	                                  hMap.put(j, value);
	                                  }
	                              if(j==2) {
	                              //    test1.setCol3(value);
	                                  hMap.put(j, value);
	                                  }
	                              if(j==3) {
	                               //   test1.setCol4(value);
	                                  hMap.put(j, value);
	                                  }
	                              if(j==4) {
	                                //  test1.setCol5(value);
	                                  hMap.put(j, value);
	                                  }
	                      }
	                  }
	                //  System.out.println(Data[i][j]+"  POMPU -------------------------------POMPU");
	                  System.out.println(" ............... J Ends ");
	              
	              }
	           //   listOfTests.add(test1);
	              
	              listOfMaps.add(i, hMap);
	              System.out.println(" ...............................I Ends ");
	          }

	    //  return test1;
	          
	          List<Test1> listOfTests=new ArrayList<Test1>(); int k=0;
	          
	          for(HashMap<Integer, String> l :listOfMaps) {
	        	  
	        	  if(l.get(3).equals("getList")) {
	        		  
	        		  System.out.print(l.get(0)); System.out.print("  "+l.get(1)); System.out.print("  "+l.get(2));  System.out.print("  "+l.get(3)); System.out.print("  "+l.get(4));
	        		 
	        	 }
	        	  
	        	  k++;
	        	  System.out.println();
	          }
	          
	          
	           return listOfTests.iterator();
	      
			/*
			 * return new Object[][] {{listOfTests.get(0)}, {listOfTests.get(1)},
			 * {listOfTests.get(2)}
			 * 
			 * };
			 */
	          //return new Object[][] {{Data}};
	  
	
		
	}

}
