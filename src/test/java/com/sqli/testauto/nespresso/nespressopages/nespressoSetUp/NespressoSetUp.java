package com.sqli.testauto.nespresso.nespressopages.nespressoSetUp;

import com.sqli.testauto.nespresso.manageExcelData.ReadExcel;
import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NespressoMachinesPage;
import com.sqli.testauto.nespresso.nespressopages.NesspressoCapsulesPage;
import com.sqli.testauto.products.capsules.ReadCapsuleData;
import com.sqli.testauto.products.machines.ReadMachineData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class NespressoSetUp {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NesspressoCapsulesPage nesspressoCapsulesPage;
    private NespressoMachinesPage nespressoMachinesPage;
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
        option.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        nespressoHomePage = new NespressoHomePage(driver);
        nesspressoCapsulesPage = new NesspressoCapsulesPage(driver);
        nespressoMachinesPage = new NespressoMachinesPage(driver);

//        ValidQuantityInExcelFile = Integer.parseInt(ReadExcel.getValidQuantityFromExcelFile(1));
//        InvalidQuantityInExcelFile = Integer.parseInt(ReadExcel.getInvalidQuantityFromExcelFile());
//        System.out.println("Quantity in Excel file in int: " + ValidQuantityInExcelFile);


//        cookieHandler = new CookieHandler(driver);

        driver.manage().window().maximize();
        String domain = "https://www.nespresso.com/fr/en";

        driver.get(domain);
//        nespressoHomePage.acceptCookie();


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
    @Test(dataProvider = "getCapsulesAndValidQuantity", dataProviderClass = ReadCapsuleData.class)
    public void addProductToCartWithValidQuantity(String productName, String quantity) throws IOException {
        setUp();
        nespressoHomePage.acceptCookie();
        // reinistialiser la session(new solution to reduce ressources)
        nespressoHomePage.goToCapsulesPage();
        nesspressoCapsulesPage.addProductToCartWithValidQuantity(productName,quantity);
        nesspressoCapsulesPage.clickOnCart();
        String quantityInSpan = nesspressoCapsulesPage.getQuantityOfSelectedProductInCartSpan(productName);
        Assert.assertEquals(quantity, quantityInSpan,"Quantity in cart does not match expected value.");
        nesspressoCapsulesPage.closeCart();
        shutDown();
    }
//    @AfterTest
    public void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test(dataProvider = "getMachinesAndValidQuantity", dataProviderClass = ReadMachineData.class)
    public void addMachineToCartWithValidQuantity(String machineName, String quantity) throws IOException {
        setUp();
        nespressoHomePage.acceptCookie();
        nespressoHomePage.goToMachinesPage();
        nespressoMachinesPage.addMachineToCartWithValidQuantity(machineName,quantity);
        String quantityInSpan = nespressoMachinesPage.getQuantityOfSelectedProductInCartSpan(machineName);
        Assert.assertEquals(quantity, quantityInSpan,"Quantity in cart does not match expected value.");
//        nesspressoCapsulesPage.closeCart();
        shutDown();
    }
    @Test(dataProvider = "getMachinesAndValidQuantity", dataProviderClass = ReadMachineData.class)
    public void addMultipleMachineToCartWithValidQuantity(String machineName, String quantity){
//        nespressoHomePage.acceptCookie();
        nespressoHomePage.goToMachinesPage();
        nespressoMachinesPage.addMachineToCartWithValidQuantity(machineName,quantity);
        if(nespressoMachinesPage.isMachineAvailable){
            String quantityInSpan = nespressoMachinesPage.getQuantityOfSelectedProductInCartSpan(machineName);
            Assert.assertEquals(quantity, quantityInSpan,"Quantity in cart does not match expected value.");
            nesspressoCapsulesPage.closeCart();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void addProductToCartWithValidQuantityWithHardCoding(){
//        nespressoHomePage.acceptCookie();
        nespressoHomePage.goToCapsulesPage();
        nesspressoCapsulesPage.addProductToCartWithValidQuantity("Ristretto","30");
        nesspressoCapsulesPage.clickOnCart();
        String quantityInSpan = nesspressoCapsulesPage.getQuantityOfSelectedProductInCartSpan("Ristretto");
        Assert.assertEquals("30", quantityInSpan );
        nesspressoCapsulesPage.closeCart();
    }


    @Test
    public void addMultipleProductsToCartFRWithValidQuantity(){
        nespressoHomePage.acceptCookie();
        nespressoHomePage.goToCapsulesPage();
        for (int rowNumberOfData = 1;rowNumberOfData <= 10; rowNumberOfData++){
            addProductToCartWithValidQuantity(rowNumberOfData);
            nesspressoCapsulesPage.clickOnCart();
            int quantityInSpan = GetQuantityOfSelectedProductInCartSpan(rowNumberOfData);
            int quantityInExcel = getValidQuantityFromExcelFile(rowNumberOfData);
            Assert.assertEquals(quantityInExcel, quantityInSpan );
            nesspressoCapsulesPage.closeCart();
        }
    }
    public void addProductToCartWithValidQuantity(int rowNumber){
        nesspressoCapsulesPage.addProductToCartWithValidQuantity
                (ReadExcel.getProductNameFromExcelFile(rowNumber),
                        (ReadExcel.getValidQuantityFromExcelFile(rowNumber)));
    }

    public int GetQuantityOfSelectedProductInCartSpan(int rowNumber){
        return Integer.parseInt(nesspressoCapsulesPage.getQuantityOfSelectedProductInCartSpan(ReadExcel.getProductNameFromExcelFile(rowNumber)));
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
//        nesspressoCapsulesPage.addProductToCartWithEditedQuantity(productName, String.valueOf(InvalidQuantityInExcelFile));
//        nesspressoCapsulesPage.clickOnFilledCart();
//
//        quantityOfSelectedProduct = Integer.parseInt(nesspressoProductsPage.verifyQuantityOfSelectedProduct(productName));
//
////        nesspressoProductsPage.addProductToCart("Coconut Flavour Over Ice","30");
////        nesspressoProductsPage.clickOnFilledCart();
////        Assert.assertEquals("30",nesspressoProductsPage.verifyQuantityOfSelectedProduct("Coconut Flavour Over Ice"));
//        Assert.assertTrue(quantityOfSelectedProduct - InvalidQuantityInExcelFile <= 9 && quantityOfSelectedProduct - InvalidQuantityInExcelFile >= 0, "Quantity difference is not within the expected range.");
//
//
//        nesspressoCapsulesPage.proceedToCheckout();
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
