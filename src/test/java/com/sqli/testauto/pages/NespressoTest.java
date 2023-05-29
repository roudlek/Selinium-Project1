package com.sqli.testauto.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NespressoTest {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NesspressoProductsPage nesspressoProductsPage;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-blink-features=AutomationControlled");
        option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(option);

        nespressoHomePage = new NespressoHomePage(driver);
        nesspressoProductsPage = new NesspressoProductsPage(driver);
    }

    @Test
    public void addToCartAndCheckout(){
        driver.manage().window().maximize();
        driver.get("https://www.nespresso.com/fr/en");

        WebDriverWait wait = new WebDriverWait(driver, 40);

        nespressoHomePage.AcceptCookieNespressoFR();
        nespressoHomePage.hoverOverNavigationMenuLink();
        nespressoHomePage.clickOnLink();
        nesspressoProductsPage.clickOnAddToCartButton();
        nesspressoProductsPage.setQuantity("20");
        nesspressoProductsPage.clickOnOKButton();
        nesspressoProductsPage.clickOnFilledCart();
        nesspressoProductsPage.verifyItemCount();

//        nesspressoProductsPage.getCartItemCount();
        nesspressoProductsPage.proceedToCheckout();
    }
}
