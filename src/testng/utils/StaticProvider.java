package testng.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import geofence.fixtures.Test1;
import geofence.handlers.AutomationPropertyHandler;

public class StaticProvider {
	
	
	 
	
	 public static HSSFWorkbook workbook;
	    public static HSSFSheet worksheet;
	    public static DataFormatter formatter= new DataFormatter();
	  //  public static String file_location = System.getProperty("user.dir")+"/Akeneo_product";
	   // public static String SheetName; //= "Sheet1";
	    
	  @DataProvider(name = "create")
	  public static Object[][] createData() {
	    return new Object[][] {
	      new Object[] { new Integer(42) }
	    };
	  }
	  
	
	  

	  @DataProvider
	  public static Iterator<Test1> ReadData_Sheet1() throws IOException
	  {
		  FileInputStream fileInputStream= new FileInputStream(AutomationPropertyHandler.getInstance().getValue("xlsFileName")); //Excel sheet file location get mentioned here
		  String SheetName=AutomationPropertyHandler.getInstance().getValue("xlsSheetName");

	      workbook = new HSSFWorkbook (fileInputStream); //get my workbook 
	      worksheet=workbook.getSheet(SheetName);// get my sheet from workbook
	      HSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0   
	   
	      int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
	      int ColNum= Row.getLastCellNum(); // get last ColNum 
	       
	      Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
	      
	      List<Test1> listOfTests=new ArrayList<Test1>();
	      
	          for(int i=0; i<RowNum-1; i++) //Loop work for Rows
	          {  
	              HSSFRow row= worksheet.getRow(i+1);
	              Test1 test1 = new Test1();
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
	                          System.out.print(" i and j  ==== "+i+"  "+j+"   ");
	                          Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
	                          
	                         
	                              if(j==0) {
	                                  test1.setCol1(value);
	                                  }
	                              if(j==1) {
	                                  test1.setCol2(value);
	                                  }
	                              if(j==2) {
	                                  test1.setCol3(value);
	                                  }
	                              if(j==3) {
	                                  test1.setCol4(value);
	                                  }
	                              if(j==4) {
	                                  test1.setCol5(value);
	                                  }
	                      }
	                  }
	                //  System.out.println(Data[i][j]+"  POMPU -------------------------------POMPU");
	                  
	                  
	              }
	              listOfTests.add(test1);
	          }

	    //  return test1;
	          
	           return listOfTests.iterator();
	      
			/*
			 * return new Object[][] {{listOfTests.get(0)}, {listOfTests.get(1)},
			 * {listOfTests.get(2)}
			 * 
			 * };
			 */
	          //return new Object[][] {{Data}};
	  }
	  
	  @DataProvider(name="ReadData_Sheet2")
	  public static Iterator<Test1> ReadData_Sheet2(ITestContext context) throws IOException
	  {
		  FileInputStream fileInputStream= new FileInputStream(AutomationPropertyHandler.getInstance().getValue("xlsFileName")); //Excel sheet file location get mentioned here
		  String SheetName=AutomationPropertyHandler.getInstance().getValue("xlsSheetName");

	      workbook = new HSSFWorkbook (fileInputStream); //get my workbook 
	      worksheet=workbook.getSheet(SheetName);// get my sheet from workbook
	      HSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0   
	      
	      System.out.println(context.getIncludedGroups());
	      
	      for (String group : context.getIncludedGroups()) {

				System.out.println("group : " + group);
	      }
	   
	      int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
	      int ColNum= Row.getLastCellNum(); // get last ColNum 
	       
	      Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
	      
	      List<Test1> listOfTests=new ArrayList<Test1>();
	      
	          for(int i=0; i<RowNum-1; i++) //Loop work for Rows
	          {  
	              HSSFRow row= worksheet.getRow(i+1);
	              Test1 test1 = new Test1();
	              for (int j=0; j<ColNum; j++) //Loop work for colNum
	              {
	            	  
	                  if(row==null) {
	                      Data[i][j]= "";
	                      System.out.print(" i and j  ==== "+i+"  "+j+"   ");
	                  }
	                  else
	                  {
	                      HSSFCell cell= row.getCell(j);
	                      if(cell==null)
	                          Data[i][j]= ""; //if it get Null value it pass no data 
	                      else 
	                      {
	                          String value=formatter.formatCellValue(cell);
	                       //   System.out.print(" i and j  ==== "+i+"  "+j+"   ");
	                          Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
	                          
	                          System.out.println(Data[i][j]+" ============= pompu");
	                          System.out.print(" i and j  ==== "+i+"  "+j+"   ");
	                         
	                              if(j==0) {
	                                  test1.setCol1(value);
	                                  }
	                              if(j==1) {
	                                  test1.setCol2(value);
	                                  }
	                              if(j==2) {
	                                  test1.setCol3(value);
	                                  }
	                              if(j==3) {
	                                  test1.setCol4(value);
	                                  }
	                              if(j==4) {
	                                  test1.setCol5(value);
	                                  }
	                      }
	                  }
	                //  System.out.println(Data[i][j]+"  POMPU -------------------------------POMPU");
	                  
	                  
	              }
	              listOfTests.add(test1);
	          }

	    //  return test1;
	          
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
