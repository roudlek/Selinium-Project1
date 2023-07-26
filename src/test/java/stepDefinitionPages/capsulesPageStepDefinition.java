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

public class capsulesPageStepDefinition {
    private WebDriver driver;
    private ChromeOptions option;
    private NespressoCapsulesPage nespressoCapsulesPage;
    String quantityInString;
    int ValidQuantityInExcelFile;
    //    int InvalidQuantityInExcelFile;
    int quantityOfSelectedProduct;
    static final String filePath = "src/test/java/com/sqli/testauto/products/productslist.xlsx";
    @Before
    public void before_or_after_all() throws IOException {
        WebDriverManager.chromedriver().setup();
        option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-blink-features=AutomationControlled");
        option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        nespressoCapsulesPage = new NespressoCapsulesPage(driver);
        String domain = "https://ma.buynespresso.com/ma_fr/cafe.html";
        driver.manage().window().maximize();
        driver.get(domain);
    }

    @Given("^User opens the url (.+)$")
    public void userOpensTheUrl(String url) {
        // Add code to open the given URL
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

// wait until a class appear like filled cart


        // Add code to add the specified quantity of the product capsule to the cart
    }


    @After
    public void reinitializeSession() {
        driver.manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.sessionStorage.clear();"); // Clear session storage
        js.executeScript("window.localStorage.clear();"); // Clear local storage
        System.out.println("after was executed");
    }
//    @AfterAll
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

}
