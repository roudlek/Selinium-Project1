package com.sqli.testauto.nespresso.nespressopages.nespressoStepDefinitionsPages;

import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NesspressoCapsulesPage;
import io.cucumber.java.After;
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

import java.util.concurrent.TimeUnit;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.Assert;

@CucumberOptions(features = "Features",tags ="@Ready" ,glue = "com/sqli/testauto/nespresso/nespressopages/nespressoStepDefinitionsPages", plugin = {"pretty", "html:target/htmlreports.html","json:target/htmlreports.json"})
public class CartStepDefinition {
//public class CartStepDefinition extends AbstractTestNGCucumberTests {
    private WebDriver driver;
    private NespressoHomePage nespressoHomePage;
    private NesspressoCapsulesPage nesspressoCapsulesPage;
    private final String filePath = "src/test/java/com/sqli/testauto/products/productslist.xlsx";


    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-blink-features=AutomationControlled");
        option.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        nespressoHomePage = new NespressoHomePage(driver);
        nesspressoCapsulesPage = new NesspressoCapsulesPage(driver);
    }


    @Given("^User is on the home page$")
    public void user_is_on_the_home_page() throws Throwable {
        driver.manage().window().maximize();
        String domain = "https://www.nespresso.com/fr/en";

        driver.get(domain);
    }
//    @Given("User is on the home page")
//    public void user_is_on_the_home_page() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
    @And("^User navigates to the capsules page$")
    public void user_navigates_to_the_capsules_page() throws Throwable {
        nespressoHomePage.acceptCookie();
        nespressoHomePage.goToCapsulesPage();
    }
    @When("^User adds product (.+) to the cart with a valid quantity (.+)$")
    public void user_adds_product_to_the_cart_with_a_valid_quantity(String productName, String quantity) throws Throwable {
        nesspressoCapsulesPage.addProductToCartWithValidQuantity(productName,quantity);
    }
    @And("^User opens the shopping cart$")
    public void user_opens_the_shopping_cart() throws Throwable {
        nesspressoCapsulesPage.clickOnCart();
    }
    @Then("^the quantity of the selected product (.+) in the cart should be (.+)$")
    public void the_quantity_of_the_selected_product_in_the_cart_should_be(String productname, String quantity) throws Throwable {
        String quantityInSpan = nesspressoCapsulesPage.getQuantityOfSelectedProductInCartSpan(productname);
        Assert.assertEquals(quantity, quantityInSpan,"Quantity in cart does not match expected value.");
    }
    @And("^the user closes the shopping cart$")
    public void the_user_closes_the_shopping_cart() throws Throwable {
        nesspressoCapsulesPage.closeCart();
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }



}