package com.tutorialsninja.qa.utlils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class utilities {

	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_WAIT_TIME = 5;

	public static String generateEmailwithTimeStamp() {
		Date date = new Date();

		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "amotoori" + timeStamp + "@gmail.com";
	}

	public static Object[][] getTestDataFromExcel(String sheetName)  {
	
		File file = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\Tutorialsninjatestdata.xlsx");
		XSSFWorkbook workBook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(file);
			workBook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workBook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) 
		{
			XSSFRow row = sheet.getRow(i+1);

			for (int j = 0; j < cols; j++) 
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;

				}

			}
		}

		return data;

	}
	
	public static String captureScreenShot(WebDriver driver,String testName) {
		
		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String desinationScreenShotPath = (".//Screenshots//" + testName + ".png");
		
		try {
			FileUtils.copyFile(srcScreenshot, new File(desinationScreenShotPath));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return desinationScreenShotPath;
	}
}
