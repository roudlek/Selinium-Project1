package nespressoStepDefinitionsPages;

import com.sqli.testauto.nespresso.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespresso.nespressopages.NesspressoCapsulesPage;
//import cucumber.api.CucumberOptions;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.BeforeAll;
//import io.cucumber.java.BeforeStep;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.cucumber.testng.CucumberOptions;
//import io.github.bonigarcia.wdm.WebDriverManager;

//import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


//import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.Assert;


//@CucumberOptions(features = "Features",tags ="@Ready" ,glue = "com/sqli/testauto/nespresso/nespressopages/nespressoStepDefinitionsPages", plugin = {"pretty", "html:target/htmlreports.html","json:target/htmlreports.json"})
//@CucumberOptions(features = "/src/test/resources", glue = "src/test/javanespressoStepDefinitionsPages")
public class CartStepDefinition{
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoHomePage nespressoHomePage;
    private NesspressoCapsulesPage nesspressoCapsulesPage;

    @Before
    public void before_or_after_all() throws IOException {
//        WebDriverManager.chromedriver().setup();
//        option = new ChromeOptions();
//        option.addArguments("--remote-allow-origins=*");
//        option.addArguments("--disable-blink-features=AutomationControlled");
//        option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        driver = new ChromeDriver(option);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        nespressoHomePage = new NespressoHomePage(driver);
//        nesspressoCapsulesPage = new NesspressoCapsulesPage(driver);
//        String domain = "https://www.google.com";
//        driver.manage().window().maximize();
//        driver.get(domain);
//        System.out.println("hello its working finally");
//        System.out.println("hello its working finally");
//        System.out.println("hello its working finally");
    }

    @After
    public void reinitializeSession() {
//        driver.manage().deleteAllCookies();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
////        js.executeScript("window.sessionStorage.clear();"); // Clear session storage
//        js.executeScript("window.localStorage.clear();"); // Clear local storage
//        System.out.println("after was executed");
    }


    @Given("User is on the home page")
    public void user_is_on_the_home_page() throws Throwable {
//        String domain = "https://www.nespresso.com/fr/en";
//        driver.get(domain);
//        nespressoHomePage.acceptCookie();
    }

    //    @Given("User is on the home page")
//    public void user_is_on_the_home_page() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
    @And("^User navigates to the capsules page$")
    public void user_navigates_to_the_capsules_page() throws Throwable {
//        nespressoHomePage.goToCapsulesPage();
    }

    @When("^User adds product (.+) to the cart with a valid quantity (.+)$")
    public void user_adds_product_to_the_cart_with_a_valid_quantity(String productName, String quantity) throws Throwable {
//        nesspressoCapsulesPage.addProductToCartWithValidQuantity(productName, quantity);
    }

    @And("^User opens the shopping cart$")
    public void user_opens_the_shopping_cart() throws Throwable {
//        nesspressoCapsulesPage.clickOnCart();
    }

    @Then("^the quantity of the selected product (.+) in the cart should be (.+)$")
    public void the_quantity_of_the_selected_product_in_the_cart_should_be(String productname, String quantity) throws Throwable {
//        String quantityInSpan = nesspressoCapsulesPage.getQuantityOfSelectedProductInCartSpan(productname);
//        Assert.assertEquals(quantity, quantityInSpan, "Quantity in cart does not match expected value.");
    }

    @And("^the user closes the shopping cart$")
    public void the_user_closes_the_shopping_cart() throws Throwable {
//        nesspressoCapsulesPage.closeCart();
    }
//    @After
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}