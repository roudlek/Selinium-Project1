package com.sqli.testauto.products.capsules;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadCapsuleData {
    static final String filePath = "src/test/java/com/sqli/testauto/products/productslist.xlsx";
    @DataProvider(name = "getCapsulesAndValidQuantity")
    public Object[][] getCapsulesAndQuantityData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("sheet1");
        int rowCount = sheet.getPhysicalNumberOfRows();

        int validRowCount = 0;
        for (int rowNumber = 1; rowNumber < rowCount; rowNumber++) {
            XSSFRow row = sheet.getRow(rowNumber);
            if (row.getCell(0) != null && row.getCell(1) != null) {
                validRowCount++;
            }
        }

        Object[][] data = new Object[validRowCount][2];
        int dataIndex = 0;

        for (int rowNumber = 1; rowNumber < validRowCount + 1; rowNumber++) {
            XSSFRow row = sheet.getRow(rowNumber);
            if (row.getCell(0) != null && row.getCell(1) != null) {
                data[dataIndex][0] = row.getCell(0).toString();
                System.out.println("product name at index: " + dataIndex + " is " + data[dataIndex][0]);
                data[dataIndex][1] = row.getCell(1).toString();
                System.out.println("valid quantity at index: " + dataIndex + " is " + data[dataIndex][1]);
                dataIndex++;
            }
        }
        workbook.close();
        fileInputStream.close();
        return data;
    }
}
