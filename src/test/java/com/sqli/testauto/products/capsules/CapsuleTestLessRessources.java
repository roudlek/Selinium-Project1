package com.sqli.testauto.products.capsules;

import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NesspressoCapsulesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
public class CapsuleTestLessRessources {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NesspressoCapsulesPage nesspressoCapsulesPage;
    private boolean executeSetUp = true;
    @BeforeClass
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
//        driver.manage().window().maximize();
//        String domain = "https://www.nespresso.com/fr/en";
//        executeSetUp = true;
//        driver.get(domain);
        String domain = "https://www.google.com";
        driver.manage().window().maximize();
        driver.get(domain);
    }
    @BeforeMethod
    public void reinitializeSession() {
        String domain = "https://www.nespresso.com/fr/en";
        driver.manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.sessionStorage.clear();"); // Clear session storage
        js.executeScript("window.localStorage.clear();"); // Clear local storage
        driver.get(domain);
        executeSetUp = true;
        nespressoHomePage.acceptCookie();

    }
    @Test(dataProvider = "getCapsulesAndValidQuantity", dataProviderClass = ReadCapsuleData.class)
    public void addProductToCartWithValidQuantityOneByOne(String productName, String quantity) throws IOException {
//        if(!executeSetUp) {
//            setUp();
//        }
//        //remove beforeTest
//        nespressoHomePage.acceptCookie();
        // reinitialize session(new solution to reduce ressources using @BeforeMethod that clear local storage)
        nespressoHomePage.goToCapsulesPage();
        nesspressoCapsulesPage.addProductToCartWithValidQuantity(productName,quantity);
        if(nesspressoCapsulesPage.isCapsuleExist()){
            nesspressoCapsulesPage.clickOnCart();
            String quantityInSpan = nesspressoCapsulesPage.getQuantityOfSelectedProductInCartSpan(productName);
            Assert.assertEquals(quantity, quantityInSpan,"Quantity in cart does not match expected value.");
            nesspressoCapsulesPage.closeCart();
//        executeSetUp = false;
//        shutDown();
        }
    }
    @Test
    public void addProductToCartWithValidQuantityWithHardCoding(){
        nespressoHomePage.goToCapsulesPage();
        nesspressoCapsulesPage.addProductToCartWithValidQuantity("Ristretto","30");
        nesspressoCapsulesPage.clickOnCart();
        String quantityInSpan = nesspressoCapsulesPage.getQuantityOfSelectedProductInCartSpan("Ristretto");
        Assert.assertEquals("30", quantityInSpan );
        nesspressoCapsulesPage.closeCart();
    }
    public void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
