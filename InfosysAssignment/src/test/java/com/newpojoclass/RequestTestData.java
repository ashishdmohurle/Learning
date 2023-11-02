package com.newpojoclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class RequestTestData {

    
    public List<RegisterSuccessfullPojoClassRequest> testData() throws IOException {
    	
    	List<RegisterSuccessfullPojoClassRequest> testData = new ArrayList<>();

        FileInputStream file = new FileInputStream("C:/Users/ashish.mohurle/Documents/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheet("Sheet2");

        int rowCount = sheet.getPhysicalNumberOfRows();
        Row firstRow = sheet.getRow(0);
        short columnCount = firstRow.getLastCellNum();

       

        for (int i = 1; i < rowCount; i++) {

            Row row = sheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();

            RegisterSuccessfullPojoClassRequest requestData = new RegisterSuccessfullPojoClassRequest();

            requestData.setEmail(username);
            requestData.setPassword(password);

            System.out.println(username);
            System.out.println(password);

            testData.add(requestData);

        }
		return testData;
       

    }

}
