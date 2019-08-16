package testng.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

//import javafx.scene.paint.Color;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public  class PoiWriter
    {
       // static XSSFWorkbook workbook = new XSSFWorkbook();
        
	

	static Workbook workbook; //= WorkbookFactory.create(file);
	
        static Sheet    worksheet=null;
        static Row      row=null;
        static Cell     cell=null;
        
        
        public PoiWriter() {
    		
    	}
        
        
        
        public static void appendDataToFile(String fileName, String sheetName, int rowIndex, int cellIndex, String value)
            {
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        workbook=  WorkbookFactory.create(inp);
                        worksheet = workbook.getSheet(sheetName);
                        if (!(worksheet.getRow(rowIndex) == null))
                            {
                                row = worksheet.getRow(rowIndex);
                            } else
                            {
                                row = worksheet.createRow(rowIndex);
                            }
                        cell = row.createCell(cellIndex);
                        cell.setCellValue(value);
                        // Write the output to a file
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        workbook.write(fileOut);
                        fileOut.close();
                    } catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
        
        public static void appendDataToFile(String fileName, String sheetName, int rowIndex, int cellIndex, String value, boolean isHeaderRow)
            {
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        CellStyle cellStyle = null;
                        // InputStream inp = new
                        // FileInputStream("workbook.xlsx");
                        workbook=  WorkbookFactory.create(inp);
                        worksheet = workbook.getSheet(sheetName);
                        if (!(worksheet.getRow(rowIndex) == null))
                            {
                                row = worksheet.getRow(rowIndex);
                            } else
                            {
                                row = worksheet.createRow(rowIndex);
                            }
                        cell = row.createCell(cellIndex);
                        if (isHeaderRow)
                            {
                                cellStyle = workbook.createCellStyle();
                                cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                              //  cellStyle.setFillPattern(cellStyle.SOLID_FOREGROUND);
                                Font font = workbook.createFont();
                                font.setFontHeightInPoints((short) 12);
                                font.setFontName("Calibri");
                               // font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                                cellStyle.setFont(font);
                            }
                      //  cellStyle.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
                      //  cellStyle.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
                       // cellStyle.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
                      //  cellStyle.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
                        cell.setCellValue(value);
                        cell.setCellStyle(cellStyle);
                        // Write the output to a file
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        workbook.write(fileOut);
                        fileOut.close();
                    } catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
        
        public static int getLastColumnOfSheet(String fileName, String sheetName, int rowIndex)
            {
                int lastCellindex = 0;
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        // InputStream inp = new
                        // FileInputStream("workbook.xlsx");
                        workbook=  WorkbookFactory.create(inp);
                        worksheet = workbook.getSheet(sheetName);
                        if (!(worksheet.getRow(rowIndex) == null))
                            {
                                row = worksheet.getRow(rowIndex);
                                lastCellindex = row.getLastCellNum();
                            }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                return lastCellindex;
            }
        
        public static boolean isRowExists(String fileName, String sheetName, int rowIndex)
            {
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        // InputStream inp = new
                        // FileInputStream("workbook.xlsx");
                        workbook=  WorkbookFactory.create(inp);
                        worksheet = workbook.getSheet(sheetName);
                        if (!(worksheet.getRow(rowIndex) == null))
                            {
                                return true;
                            }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                return false;
            }
        
        public static int findRow(String fileName, String sheetName, String value, int cellindex)
            {
                int rowNum = 0;
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        
                        workbook=  WorkbookFactory.create(inp);
                        
                      //  workbook = new XSSFWorkbook(inp);
                        worksheet = workbook.getSheet(sheetName);
                        int rowcount = worksheet.getLastRowNum();
                        for (int i = 1; i <= rowcount; i++)
                            {
                                // System.out.println(worksheet.getRow(i).getCell(cellindex).getRichStringCellValue().getString());
                                if (!(worksheet.getRow(i).getCell(cellindex)==null)){
                                        if (worksheet.getRow(i).getCell(cellindex).getRichStringCellValue().getString().contains(value))
                                            {
                                                return i;
                                            }
                                }
                                else 
                                    break;
                                
                            }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                return rowNum;
            }

        public static String getCellValue(String fileName, String sheetName, int row, int column)
            {
                try
                    {
                        InputStream inp = new FileInputStream(fileName);
                        workbook=  WorkbookFactory.create(inp);
                        worksheet = workbook.getSheet(sheetName);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                return worksheet.getRow(row).getCell(column).getStringCellValue();
            }
    }
