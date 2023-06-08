package com.sqli.testauto.nespresso.nespressopages.nespressoSetUp;

import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NesspressoProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
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
    private NesspressoProductsPage nesspressoProductsPage;
//    private CookieHandler cookieHandler;

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
    @Test(dataProvider = "testdata")
    public void addToCartAndCheckoutInFR(String productName) throws IOException {
//        cookieHandler.acceptCookies(nespressoHomePage.cookieAcceptButtonFR,6);
        nespressoHomePage.acceptCookie();
        nespressoHomePage.goToProductsPage();
        nesspressoProductsPage.addProductToCart(productName,"50");
        nesspressoProductsPage.clickOnFilledCart();

//        nesspressoProductsPage.addProductToCart("Coconut Flavour Over Ice","30");
//        nesspressoProductsPage.clickOnFilledCart();
//        Assert.assertEquals("30",nesspressoProductsPage.verifyQuantityOfSelectedProduct("Coconut Flavour Over Ice"));
        Assert.assertEquals("50",nesspressoProductsPage.verifyQuantityOfSelectedProduct(productName));

        nesspressoProductsPage.proceedToCheckout();
    }
    @DataProvider(name = "testdata")
    public Object[][] provideTestData() throws IOException {
        String filePath = "src/test/java/com/sqli/testauto/products/productslist.xlsx";
        String sheetName = "sheet1";

        String productName = nesspressoProductsPage.readProductDataFromExcel(filePath, sheetName,1,0);

        return new Object[][]{{productName}};
    }


}
