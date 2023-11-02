package com.pojoclasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TestDataProviders {

	// Define a Data Provider method named "registrationData"
	@DataProvider(name = "registrationData")

	public static Iterator<Object[]> getRegistrationTestData() throws IOException {

		// Create a list to hold test data sets
		List<Object[]> testData = new ArrayList<>();

		// Open the Excel file for reading
		FileInputStream file = new FileInputStream("C:/Users/ashish.mohurle/Documents/TestData.xlsx");

		// Create a Workbook instance for Excel file
		Workbook workbook = new XSSFWorkbook(file);

		// Get the sheet named "Sheet1" from the workbook
		Sheet sheet = workbook.getSheet("Sheet1");
		

		// Create an iterator to loop through rows in the sheet
		Iterator<Row> rowIterator = sheet.iterator();


		// Skip the header row in the Excel sheet
		rowIterator.next();
		
		//sheet.getRow(1).getCell(0).getStringCellValue();

		// Loop through each row in the Excel sheet
		while (rowIterator.hasNext()) {

			// Get the current row
			Row row = rowIterator.next();

			// Get the data from the first and second cells in the row
			Cell emailCell = row.getCell(0);
			Cell passwordCell = row.getCell(1);

			// Extract the email and password values from the cells
			String email = emailCell.getStringCellValue();
			String password = passwordCell.getStringCellValue();

			// Create a RegistrationRequest class object to hold the data
			RegistrationRequest requestData = new RegistrationRequest();

			// Serialization of the request
			requestData.setEmail(email);
			requestData.setPassword(password);

			// Add the requestData object to the list of the test data sets
			testData.add(new Object[] { requestData });

		}
		// Close the workbook and file
		workbook.close();
		file.close();

		// Return an iterator for the list of test data sets
		return testData.iterator();
	}

}
