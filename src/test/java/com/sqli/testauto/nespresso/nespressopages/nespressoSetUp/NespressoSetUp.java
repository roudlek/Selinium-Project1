package com.sqli.testauto.nespresso.nespressopages.nespressoSetUp;

import com.sqli.testauto.nespresso.manageExcelData.ReadExcel;
import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NesspressoProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.WordUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class NespressoSetUp {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NesspressoProductsPage nesspressoProductsPage;
    String quantityInString;
    int ValidQuantityInExcelFile;
//    int InvalidQuantityInExcelFile;
    int quantityOfSelectedProduct;
    static final String filePath = "src/test/java/com/sqli/testauto/products/productslist.xlsx";

    @BeforeTest
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-blink-features=AutomationControlled");
        option.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        nespressoHomePage = new NespressoHomePage(driver);
        nesspressoProductsPage = new NesspressoProductsPage(driver);

//        ValidQuantityInExcelFile = Integer.parseInt(ReadExcel.getValidQuantityFromExcelFile(1));
//        InvalidQuantityInExcelFile = Integer.parseInt(ReadExcel.getInvalidQuantityFromExcelFile());
//        System.out.println("Quantity in Excel file in int: " + ValidQuantityInExcelFile);


//        cookieHandler = new CookieHandler(driver);

        driver.manage().window().maximize();
        String domain = "https://www.nespresso.com/fr/en";

        driver.get(domain);



//        Cookie cookie = new Cookie.Builder("myCookie", "value")
//                .domain("www.nespresso.com")
//                .expiresOn(new Date(2025, 10, 28))
//                .isSecure(false)
//                .path("/fr/en")
//                .build();
//        System.out.println(cookie.getName());
//
//        driver.manage().addCookie(cookie);

    }


    @Test(dataProvider = "productData")
    public void addProductToCartWithValidQuantity(String productName, String quantity){
        nespressoHomePage.acceptCookie();
        nespressoHomePage.goToProductsPage();
        nesspressoProductsPage.addProductToCartWithValidQuantity(productName,quantity);
        nesspressoProductsPage.clickOnFilledCart();
        String quantityInSpan = nesspressoProductsPage.GetQuantityOfSelectedProductInCartSpan(productName);
        Assert.assertEquals(quantity, quantityInSpan );
        nesspressoProductsPage.closeCart();
    }
//    @AfterTest
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    @DataProvider(name = "productData")
    public Object[][] getProductData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("sheet1");
        int rowCount = sheet.getPhysicalNumberOfRows();

        // Count the number of rows with non-empty cells
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

//        System.out.println("data is : " + data);
        return data;
    }
    @Test
    public void addProductToCartWithValidQuantityWithHardCoding(){
//        nespressoHomePage.acceptCookie();
        nespressoHomePage.goToProductsPage();
        nesspressoProductsPage.addProductToCartWithValidQuantity("Ristretto","30");
        nesspressoProductsPage.clickOnFilledCart();
        String quantityInSpan = nesspressoProductsPage.GetQuantityOfSelectedProductInCartSpan("Ristretto");
        Assert.assertEquals("30", quantityInSpan );
        nesspressoProductsPage.closeCart();
    }


    @Test
    public void addMultipleProductsToCartFRWithValidQuantity(){
        nespressoHomePage.acceptCookie();
        nespressoHomePage.goToProductsPage();
        for (int rowNumberOfData = 1;rowNumberOfData <= 10; rowNumberOfData++){
            addProductToCartWithValidQuantity(rowNumberOfData);
            nesspressoProductsPage.clickOnFilledCart();
            int quantityInSpan = GetQuantityOfSelectedProductInCartSpan(rowNumberOfData);
            int quantityInExcel = getValidQuantityFromExcelFile(rowNumberOfData);
            Assert.assertEquals(quantityInExcel, quantityInSpan );
            nesspressoProductsPage.closeCart();
        }
    }
    public void addProductToCartWithValidQuantity(int rowNumber){
        nesspressoProductsPage.addProductToCartWithValidQuantity
                (ReadExcel.getProductNameFromExcelFile(rowNumber),
                        (ReadExcel.getValidQuantityFromExcelFile(rowNumber)));
    }

    public int GetQuantityOfSelectedProductInCartSpan(int rowNumber){
        return Integer.parseInt(nesspressoProductsPage.GetQuantityOfSelectedProductInCartSpan(ReadExcel.getProductNameFromExcelFile(rowNumber)));
    }
    public int getValidQuantityFromExcelFile(int rowNumber){
        return Integer.parseInt(ReadExcel.getValidQuantityFromExcelFile(rowNumber));
    }




//    @Test
//    public void addToCartAndCheckoutInFRWithInvalidQuantityFixQuantityAndContinue(){
////        cookieHandler.acceptCookies(nespressoHomePage.cookieAcceptButtonFR,6);
//
//        nespressoHomePage.acceptCookie();
//        nespressoHomePage.goToProductsPage();
//        nesspressoProductsPage.addProductToCartWithEditedQuantity(productName, String.valueOf(InvalidQuantityInExcelFile));
//        nesspressoProductsPage.clickOnFilledCart();
//
//        quantityOfSelectedProduct = Integer.parseInt(nesspressoProductsPage.verifyQuantityOfSelectedProduct(productName));
//
////        nesspressoProductsPage.addProductToCart("Coconut Flavour Over Ice","30");
////        nesspressoProductsPage.clickOnFilledCart();
////        Assert.assertEquals("30",nesspressoProductsPage.verifyQuantityOfSelectedProduct("Coconut Flavour Over Ice"));
//        Assert.assertTrue(quantityOfSelectedProduct - InvalidQuantityInExcelFile <= 9 && quantityOfSelectedProduct - InvalidQuantityInExcelFile >= 0, "Quantity difference is not within the expected range.");
//
//
//        nesspressoProductsPage.proceedToCheckout();
//    }


//    @Test
//    public void addToCartAndCheckoutInFRWithInvalidQuantityAndStop(){
////        cookieHandler.acceptCookies(nespressoHomePage.cookieAcceptButtonFR,6);
//
//        nespressoHomePage.acceptCookie();
//        nespressoHomePage.goToProductsPage();
//        nesspressoProductsPage.addProductToCartWithInvalidQuantityAndStop(productName, String.valueOf(InvalidQuantityInExcelFile));
//        // edit this
//        Assert.assertTrue(InvalidQuantityInExcelFile ==  55 );
//
//    }

}
