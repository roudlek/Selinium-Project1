package com.sqli.testauto.nespresso.manageExcelData;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {
    private WebDriver driver;
    private WebDriverWait wait;
    static final String filePath = "src/test/java/com/sqli/testauto/products/productslist.xlsx";

    public ReadExcel() {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public static String readProductDataFromExcel(String filePath, String sheetName, int rowNumber, int cellNumber) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        XSSFRow firstRow = sheet.getRow(rowNumber);
        String productName = WordUtils.capitalize(firstRow.getCell(cellNumber).getStringCellValue());

        workbook.close();
        fileInputStream.close();

        return productName;
    }
    public static String getProductNameFromExcelFile(int rowNumber) {
        try {
            return readProductDataFromExcel(filePath, "sheet1", rowNumber, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getValidQuantityFromExcelFile(int rowNumber){
        try {
            return readProductDataFromExcel(filePath, "sheet1", rowNumber, 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getInvalidQuantityFromExcelFile(){
        try {
            return readProductDataFromExcel(filePath, "sheet1", 1, 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
