package com.sqli.testauto.products.machines;

import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NespressoMachinesPage;
import com.sqli.testauto.nespresso.nespressopages.NespressoCapsulesPage;
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

public class MachineTest {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NespressoCapsulesPage nespressoCapsulesPage;
    private NespressoMachinesPage nespressoMachinesPage;

    private boolean executeSetUp = true;
    public void setExecuteSetUp(boolean executeSetUp) {
        this.executeSetUp = executeSetUp;
    }
    @BeforeTest
    public void setUp(){
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
        driver.manage().window().maximize();
        String domain = "https://www.nespresso.com/fr/en";
        driver.get(domain);
        executeSetUp = true;
        nespressoHomePage.acceptCookie();
    }
    //    @AfterTest
    public void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test(dataProvider = "getMachinesAndValidQuantity", dataProviderClass = ReadMachineData.class)
    public void addMachineToCartWithValidQuantity(String machineName, String quantity) throws IOException {
        if(!executeSetUp){
            setUp();
        }
        nespressoHomePage.goToMachinesPage();
        nespressoMachinesPage.addMachineToCartWithValidQuantity(machineName,quantity);
        if(nespressoMachinesPage.isMachineAvailable) {
            String quantityInSpan = nespressoMachinesPage.getQuantityOfSelectedProductInCartSpan(machineName);
            Assert.assertEquals(quantity, quantityInSpan, "Quantity in cart does not match expected value.");
        }
//      nesspressoCapsulesPage.closeCart();
        executeSetUp = false;
        shutDown();
    }
    @Test(dataProvider = "getMachinesAndValidQuantity", dataProviderClass = ReadMachineData.class)
    public void addMultipleMachineToCartWithValidQuantity(String machineName, String quantity){
        nespressoHomePage.goToMachinesPage();
        nespressoMachinesPage.addMachineToCartWithValidQuantity(machineName,quantity);
        if(nespressoMachinesPage.isMachineAvailable){
            String quantityInSpan = nespressoMachinesPage.getQuantityOfSelectedProductInCartSpan(machineName);
            Assert.assertEquals(quantity, quantityInSpan,"Quantity in cart does not match expected value.");
            nespressoCapsulesPage.closeCart();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
