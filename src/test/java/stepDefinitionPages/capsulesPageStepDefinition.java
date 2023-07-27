package stepDefinitionPages;

import configuration.MyDriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import nespressoPages.NespressoCapsulesPage;
import java.io.IOException;

public class capsulesPageStepDefinition {
    private NespressoCapsulesPage nespressoCapsulesPage;
    @Before
    public void before_or_after_all() throws IOException {
        nespressoCapsulesPage = new NespressoCapsulesPage(MyDriverManager.getDriver());
    }

    @Given("^User opens the url (.+)$")
    public void userOpensTheUrl(String url) {
        String domain = "https://ma.buynespresso.com/ma_fr/cafe.html";
        MyDriverManager.getDriver().manage().window().maximize();
        MyDriverManager.getDriver().get(domain);
    }

    @When("^User scrolls to (.+) capsule$")
    public void userScrollsToProductCapsule(String product) {
        nespressoCapsulesPage.scrollToCapsules(product);
    }

    @When("^User adds (.+) units of (.+) capsule to cart$")
    public void userAddsUnitsOfProductCapsuleToCart(int quantity, String product) {
        nespressoCapsulesPage.clickOnAddToCartButtonOfSpecifiedProduct(product);
        nespressoCapsulesPage.pickQuantity(quantity);
        nespressoCapsulesPage.waitForCartToBeUpdated();
    }


//    @After
//    public void reinitializeSession() {
//        MyDriverManager.getDriver().manage().deleteAllCookies();
//        JavascriptExecutor js = (JavascriptExecutor) MyDriverManager.getDriver();
////        js.executeScript("window.sessionStorage.clear();"); // Clear session storage
//        js.executeScript("window.localStorage.clear();"); // Clear local storage
//        System.out.println("after was executed");
//    }

//    @AfterAll
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

}
