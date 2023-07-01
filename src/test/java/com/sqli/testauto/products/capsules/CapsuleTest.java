package com.sqli.testauto.products.capsules;

import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NesspressoCapsulesPage;
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

public class CapsuleTest {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NesspressoCapsulesPage nesspressoCapsulesPage;
    private boolean executeSetUp = true;
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
        driver.manage().window().maximize();
        String domain = "https://www.nespresso.com/fr/en";
        executeSetUp = true;
        driver.get(domain);
        nespressoHomePage.acceptCookie();
        nespressoHomePage.goToCapsulesPage();
    }
    @Test(dataProvider = "getCapsulesAndValidQuantity", dataProviderClass = ReadCapsuleData.class)
    public void addMultipleProductsToCartWithValidQuantityInOneTest(String productName, String quantity) throws IOException {
        // reinistialiser la session(new solution to reduce ressources)
        nesspressoCapsulesPage.addProductToCartWithValidQuantity(productName,quantity);
        nesspressoCapsulesPage.clickOnCart();
        String quantityInSpan = nesspressoCapsulesPage.getQuantityOfSelectedProductInCartSpan(productName);
        Assert.assertEquals(quantity, quantityInSpan, "Quantity in cart does not match expected value.");
        nesspressoCapsulesPage.closeCart();
    }
    @Test
    public void sayHi(){
        System.out.println("hi from sayHi method");
    }
    //    @AfterTest
    public void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
