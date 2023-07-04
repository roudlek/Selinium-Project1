package com.sqli.testauto.products.machines;

import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NespressoMachinesPage;
import com.sqli.testauto.nespresso.nespressopages.NespressoCapsulesPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class MachineTest {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NespressoCapsulesPage nespressoCapsulesPage;
    private NespressoMachinesPage nespressoMachinesPage;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-blink-features=AutomationControlled");
        option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        nespressoHomePage = new NespressoHomePage(driver);
        nespressoCapsulesPage = new NespressoCapsulesPage(driver);
        nespressoMachinesPage = new NespressoMachinesPage(driver);
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
        nespressoHomePage.acceptCookie();
    }

    @Test(dataProvider = "getMachinesAndValidQuantity", dataProviderClass = ReadMachineData.class)
    public void addMachineToCartWithValidQuantity(String machineName, String quantity) throws IOException {
        nespressoHomePage.goToMachinesPage();
        nespressoMachinesPage.addMachineToCartWithValidQuantity(machineName, quantity);
        String quantityInSpan = nespressoMachinesPage.getQuantityOfSelectedProductInCartSpan(machineName);
        Assert.assertEquals(quantity, quantityInSpan, "Quantity in cart does not match expected value.");
    }

    @Test(dataProvider = "getMachinesAndValidQuantity", dataProviderClass = ReadMachineData.class)
    public void addMultipleMachineToCartWithValidQuantity(String machineName, String quantity) {
        nespressoHomePage.goToMachinesPage();
        nespressoMachinesPage.addMachineToCartWithValidQuantity(machineName, quantity);
        String quantityInSpan = nespressoMachinesPage.getQuantityOfSelectedProductInCartSpan(machineName);
        Assert.assertEquals(quantity, quantityInSpan, "Quantity in cart does not match expected value.");
        nespressoCapsulesPage.closeCart();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterClass
    public void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
