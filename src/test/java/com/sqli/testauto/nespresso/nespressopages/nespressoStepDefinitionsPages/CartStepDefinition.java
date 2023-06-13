package com.sqli.testauto.nespresso.nespressopages.nespressoStepDefinitionsPages;

import com.sqli.testauto.nespresso.manageExcelData.ReadExcel;
import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NesspressoProductsPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import io.cucumber.testng.AbstractTestNGCucumberTests;
@CucumberOptions(features = "Features/ ",tags ="@Ready" ,glue = "nespressoStepDefinitionsPages", plugin = {"pretty", "html:target/htmlreports.html","json:target/htmlreports.json"})
public class CartStepDefinition extends AbstractTestNGCucumberTests {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NesspressoProductsPage nesspressoProductsPage;
    private String filePath = "src/test/java/com/sqli/testauto/products/productslist.xlsx";


    @Before
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

    }

    @Given("^user is in home page$")
    public void user_is_in_home_page() {
        String domain = "https://www.nespresso.com/fr/en";
        driver.get(domain);
    }

    @When("^user typed valid quantity (.+)$")
    public void user_typed_valid_quantity(String quantity_Typed) throws IOException {
        nesspressoProductsPage.setQuantity(ReadExcel.readProductDataFromExcel(filePath,"sheet1",1,0));
    }
    @When("^user typed invalid quantity (.+)$")
    public void user_typed_invalid_quantity(String quantity) {
        nesspressoProductsPage.setQuantity(quantity);
    }

    @Then("^Assert that actual (.+) of specified product equal to (.+)$")
    public void assert_that_actual_of_specified_product_equal_to(String quantitytyped) throws Throwable {
        String productName = ReadExcel.readProductDataFromExcel(filePath, "sheet1",1,0);
        String finalQuantity = nesspressoProductsPage.GetQuantityOfSelectedProductInCartSpan(productName);
        Assert.assertEquals(quantitytyped,finalQuantity);
    }

    @And("^accept cookie$")
    public void accept_cookie(){
        nespressoHomePage.acceptCookie();
    }

    @And("^user navigates to products page$")
    public void user_navigates_to_products_page(){
        nespressoHomePage.goToProductsPage();
    }

    @And("^user clicked on add to cart button of specified product$")
    public void user_clicked_on_add_to_cart_button_of_specified_product() throws IOException {
        String productName = ReadExcel.readProductDataFromExcel(filePath, "sheet1",0,0);
        nesspressoProductsPage.addProductToCartWithValidQuantity(productName,"130");
    }

    @And("^clicked on ok button$")
    public void clicked_on_ok_button(){
        nesspressoProductsPage.clickOnOKButton();
    }

    @And("^Wait for cart to be updated$")
    public void wait_for_cart_to_be_updated(){
        nesspressoProductsPage.WaitForCartToBeUpdated();
    }

    @And("^opened filled cart$")
    public void opened_filled_cart(){
        nesspressoProductsPage.clickOnFilledCart();
    }




}