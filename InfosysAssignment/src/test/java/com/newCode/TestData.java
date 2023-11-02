package com.newCode;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TestData {

	// Define a Data Provider method named "registrationData"
	@DataProvider(name = "registrationData")

	public Object[][] getRegistrationTestData() throws IOException {

		// Open the Excel file for reading
		FileInputStream file = new FileInputStream("C:/Users/ashish.mohurle/Documents/TestData.xlsx");

		// Create a Workbook instance for Excel file
		Workbook workbook = new XSSFWorkbook(file);

		// Get the sheet named "Sheet1" from the workbook
		Sheet sheet = workbook.getSheet("Sheet1");

		// Get Row count & column count for 1st row
		int rowCount = sheet.getPhysicalNumberOfRows();
		Row firstRow = sheet.getRow(0);
		int columnCount = firstRow.getLastCellNum();

		// [rowCount - 1] is used to exclude the first row, which is the header row.
		Object[][] registrationData = new Object[rowCount - 1][columnCount];

		for (int i = 1; i < rowCount; i++) {

			Row row = sheet.getRow(i);

			String username = row.getCell(0).getStringCellValue();
			String password = row.getCell(1).getStringCellValue();

			registrationData[i - 1][0] = username;
			registrationData[i - 1][1] = password;

		}
		workbook.close();
		file.close();
		return registrationData;
	}
}
