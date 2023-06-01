package com.sqli.testauto.nespressoSetUp;

import com.sqli.testauto.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespressopages.NesspressoProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NespressoSetUp {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NesspressoProductsPage nesspressoProductsPage;
//    private CookieHandler cookieHandler;
    @BeforeTest
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
//        cookieHandler = new CookieHandler(driver);
        driver.manage().window().maximize();
        driver.get("https://www.nespresso.com/fr/en");
    }
    @Test
    public void addToCartAndCheckoutInFR(){

//        cookieHandler.acceptCookies(nespressoHomePage.cookieAcceptButtonFR,6);
        nespressoHomePage.acceptCookie();

        nespressoHomePage.goToProductsPage();

        nesspressoProductsPage.clickOnAddToCartButtonOfSpecifiedProduct("Coconut Flavour Over Ice");
        nesspressoProductsPage.setQuantity("50");
        nesspressoProductsPage.clickOnOKButton();
        nesspressoProductsPage.clickOnFilledCart();

        Assert.assertEquals("50",nesspressoProductsPage.verifyQuantityOfSelectedProduct("Coconut Flavour Over Ice"));

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
