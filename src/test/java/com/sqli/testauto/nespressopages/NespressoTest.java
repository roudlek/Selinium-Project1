package com.sqli.testauto.nespressopages;

import com.sqli.testauto.handlers.CookieHandler;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NespressoTest {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NesspressoProductsPage nesspressoProductsPage;
    private CookieHandler cookieHandler;


    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-blink-features=AutomationControlled");
        option.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        nespressoHomePage = new NespressoHomePage(driver);
        nesspressoProductsPage = new NesspressoProductsPage(driver);
        cookieHandler = new CookieHandler(driver);
    }


    @Test
    public void addToCartAndCheckoutInFR(){
        driver.manage().window().maximize();
        driver.get("https://www.nespresso.com/fr/en");
        cookieHandler.acceptCookies(nespressoHomePage.cookieAcceptButtonFR,6);

        nespressoHomePage.hoverOverNavigationMenuLink();
        nespressoHomePage.clickOnLink();

        nesspressoProductsPage.clickOnAddToCartButtonOfSpecifiedProduct("Coconut Flavour Over Ice");
        nesspressoProductsPage.setQuantity("20");
        nesspressoProductsPage.clickOnOKButton();
        nesspressoProductsPage.clickOnFilledCart();
        nesspressoProductsPage.verifyItemCountOfSelectedProduct("Coconut Flavour Over Ice");

        nesspressoProductsPage.proceedToCheckout();



//        nesspressoProductsPage.clickOnAddToCartButton();
//        nesspressoProductsPage.clickOnAddToCartButtonOfSpecifiedProduct("Jamaica Blue Mountain");
//        nesspressoProductsPage.clickOnAddToCartButton();
//        nesspressoProductsPage.setQuantity("100");
//        nesspressoProductsPage.clickOnOKButton();
//        nesspressoProductsPage.verifyItemCountOfSelectedProduct("Jamaica Blue Mountain");
//        nesspressoProductsPage.verifyItemCountOfSelectedProduct("Ristretto");
////      nesspressoProductsPage.verifyItemCount();
////      nesspressoProductsPage.getCartItemCount();

    }
}
