package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*So we have to read the Data from Excel Sheet 
Q:What we have to do ?
A:In Java & Selenium we don't hve libraries ..for it
In this case we have add some external library  Apache Poi
In pom.xmlI can add dependecy for Apache poi

Now U have to create utility also to read  data from excel sheet
Utility Should be created under util Section
*/
public class ExcelUtil {

	// So we have to read data from Excel Sheet
	// where is your Excel Sheet five the path here
	private static final String TEST_DATA_SHEET_PATH = ".src/test/resources/testData/OpenCartAppTestData.xlsx";
	private static Workbook book;
	private static org.apache.poi.ss.usermodel.Sheet sheet;
	// This method is Static I don't want to create Object for Excel_Utility Class

	public static void getTestData(String sheetName) {
		Object[][] data = null;
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			// workBookFactory is the Apache Poi code
			book = WorkbookFactory.create(ip); // give me the input stream file ref object
			// So what will happened this ip stream will make a Connection with Excel Sheet
			sheet = book.getSheet(TEST_DATA_SHEET_PATH);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		// Q:which Data Structure I will use here to convert into Excel in to [][]
		// Array?
		// A: Object[][] data=new Object[][];
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {

			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString(); // Prashanth data coming from excel row
			} // Convert that in to toString() method

		}

	}

}
