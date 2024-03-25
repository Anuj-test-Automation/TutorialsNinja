package com.QAFox.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
	public static int IMPLICIT_WAIT_TIME = 10;
	public static int PAGE_LOAD_TIMEOUT=5;
	
	
	
	public static String timeStamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}

	public static Object[][] getTestDataFromExcel(String sheetName) {
		XSSFWorkbook workbook = null;
		
		File loginExcelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\QAFox\\qa\\testdata\\testLoginData.xlsx");
		try {
			FileInputStream fis = new FileInputStream(loginExcelFile);
			 workbook = new XSSFWorkbook(fis);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum();
			
			Object[][] data = new Object[rows][cols];
			
			for(int i=0;i<rows;i++) {
				
				XSSFRow row = sheet.getRow(i+1);
				
				for(int j=0;j<cols;j++) {
					
					XSSFCell cell = row.getCell(j);
					CellType cellType = cell.getCellType();
					
					switch(cellType) {
					
					case STRING:
						data[i][j] = cell.getStringCellValue();
						break;
					case NUMERIC:
						data[i][j] = Integer.toString((int)cell.getNumericCellValue());
						break;
					case BOOLEAN:
						data[i][j] = cell.getBooleanCellValue();
						break;	
			
					}
				}
			}
		
		return data;
	}


}
