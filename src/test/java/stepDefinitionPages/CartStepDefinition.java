package stepDefinitionPages;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import nespressoPages.NespressoCapsulesPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CartStepDefinition {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoCapsulesPage nespressoCapsulesPage;
    String quantityInString;
    int ValidQuantityInExcelFile;
    //    int InvalidQuantityInExcelFile;
    int quantityOfSelectedProduct;
    static final String filePath = "src/test/java/com/sqli/testauto/products/productslist.xlsx";



    @Then("^Mini cart contains (.+) units of (.+) capsule$")
    public void miniCartContainsUnitsOfProductCapsule(int quantity, String product) {
        // Add code to verify that the mini cart contains the specified quantity of the product capsule
        // For example, you can get the mini cart details and assert the quantity of the product in the cart.
        // Replace the assertions below with the appropriate code for your application.
//        nespressoCapsulesPage.clickOnAddToCartButtonOfSpecifiedProduct(product);
//        nespressoCapsulesPage.pickQuantity(quantity);
//        nespressoCapsulesPage.waitForCartToBeUpdated();
        System.out.println("from mini");
//        int actualQuantity = getMiniCartQuantity(product);
//        Assert.assertEquals(quantity, actualQuantity);
    }

    // Replace this method with the actual implementation to get the quantity of the product in the mini cart.
//    private int getMiniCartQuantity(String product) {
        // Add code to fetch the quantity of the specified product in the mini cart
        // For example, you might interact with your application to get this information.
        // For demonstration purposes, let's assume the mini cart quantity is hardcoded.
//        if (product.equalsIgnoreCase("TOKYO VIVALTO LUNGO")) {
//            return 10;
//        } else {
//            return 0;
//        }
//    }

//    @AfterAll
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

}
