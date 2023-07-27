package stepDefinitionPages;

import configuration.MyDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import nespressoPages.NespressoCapsulesPage;
import nespressoPages.NespressoCart;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CartStepDefinition {
    private NespressoCart nespressoCart;

    @Before
    public void before_or_after_all() throws IOException {
        nespressoCart = new NespressoCart(MyDriverManager.getDriver());
    }

    @Then("^Mini cart contains (.+) units of (.+) capsule$")
    public void miniCartContainsUnitsOfProductCapsule(int quantity, String product) {
        nespressoCart.clickOnCart();
        int actualQuantity = nespressoCart.getQuantityOfSelectedProductInCartSpan(product);
        Assert.assertEquals(quantity, actualQuantity, "the given quantity is equal to the actual quantity");
    }


//    @AfterAll
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

}
