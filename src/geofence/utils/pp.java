package geofence.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class pp {
	
	public static void main(String[] args) throws Exception {
		
		 ExcelUtils.setExcelFile("/Users/shivadeep/eclipse/geo_fence/GeoFenceApiAutomation/input/input.xls","Sheet2");
		
		 ExcelUtils.setCellData("Pass", 1, 3);	

}
}