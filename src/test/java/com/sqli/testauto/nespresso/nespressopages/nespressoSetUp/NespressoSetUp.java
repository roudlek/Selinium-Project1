package com.sqli.testauto.nespresso.nespressopages.nespressoSetUp;

import com.sqli.testauto.nespresso.manageExcelData.ReadExcel;
import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NesspressoProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
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
    public void addProductToCartWithValidQuantity(int rowNumber){
        nesspressoProductsPage.addProductToCartWithValidQuantity
                (ReadExcel.getProductNameFromExcelFile(rowNumber),
                        String.valueOf(Integer.parseInt(ReadExcel.getValidQuantityFromExcelFile(rowNumber))));
    }
    public int GetQuantityOfSelectedProductInCartSpan(int rowNumber){
        return Integer.parseInt(nesspressoProductsPage.GetQuantityOfSelectedProductInCartSpan(ReadExcel.getProductNameFromExcelFile(rowNumber)));
    }

    public int getValidQuantityFromExcelFile(int rowNumber){
        return Integer.parseInt(ReadExcel.getValidQuantityFromExcelFile(rowNumber));
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
