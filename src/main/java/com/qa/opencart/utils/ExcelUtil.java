package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static String TEST_DATA_SHEET_PATH = "./src/test/resource/testdata/OpenCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		System.out.println("Reading test data from :" + sheetName);
		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			
			int row = sheet.getLastRowNum();
			int column = sheet.getRow(0).getLastCellNum();
			data = new Object[row][column];
			
			
			
			for(int i=0; i<sheet.getLastRowNum(); i++) {
				for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j]= sheet.getRow(i+1).getCell(j).toString();
				}
				
			}

		} catch (FileNotFoundException e) {
		} catch (InvalidFormatException e) {
		} catch (IOException e) {
		}
		return data;

	}

}
