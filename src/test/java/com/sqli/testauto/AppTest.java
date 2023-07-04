package com.sqli.testauto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    public WebDriver driver ;
//    public static final String path = "/home/elidrissiabdelaziz/Desktop/chromedriver_linux64/chromedriver";

    public static void main(String[] args) {

    }
    // executed once before and after all the test methods in a test class.
    //initializing resources, establishing database connections, or starting a web server.
    @BeforeClass
    public void setUpDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
//        System.setProperty("webdriver.chrome.driver", path);

//        DesiredCapabilities caps = new DesiredCapabilities();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("incognito");  // ChromeOptions for starting chrome in incognito mode
//
//        caps.setCapability(ChromeOptions.CAPABILITY, options);
//// other capability declarations
//        caps.setCapability("browser", "Chrome");
//        caps.setCapability("browser_version", "latest");


        option.addArguments("--disable-blink-features=AutomationControlled");
        option.setPageLoadStrategy(PageLoadStrategy.EAGER);


        driver = new ChromeDriver(option);
        // navigate using, method is handled by the browser as a fragment identifier and JavaScript variables are retained from the previous URL.:
//        driver.get("https://google.com/");
//        WebElement searchInput = driver.findElement(By.name("q"));
//        searchInput.sendKeys("y");
//
//        // Simulate search button click
//        searchInput.submit();
//        // Validate search results page
//        WebElement searchResults = driver.findElement(By.id("search"));
//        Assert.assertTrue(searchResults.isDisplayed(), "Search results not displayed");

//        // navigate using,method is handled by the browser as a address/location/URL bar input and JavaScript variables are not retained from the previous URL.:
//        driver.navigate().to("https://instagram.com/");
//        //refresh instagram page:
//        driver.navigate().refresh();
//
//
//        //Use the ChromeDriver to locate the search input element and enter a search query.
//        driver.findElement(By.name("input"));


    }

    @Test
    public void getNespresso() {
        driver.manage().window().maximize();
        driver.get("https://www.nespresso.com/fr/en");

        WebDriverWait wait = new WebDriverWait(driver, 40);

//         Accept cookie
        WebElement cookieAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("_evidon-banner-acceptbutton")));
        cookieAcceptButton.click();

        // Accept cookie only if it exist
//        List<WebElement> cookieElements = driver.findElements(By.id("_evidon-banner-acceptbutton"));
//
//        if (!cookieElements.isEmpty()) {
//            WebElement cookieAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(cookieElements.get(0)));
//            cookieAcceptButton.click();
//        }

        //in ch close cookie, coockie not closing whyyy
//        WebElement closeCookie = driver.findElement(By.id("nes_cookie_banner"));
//        closeCookie.click();

        // Hover over the navigation menu
        WebElement navigationMenuLink = wait.until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//a[contains(@class,'AccessibleLink HeaderNavigationBarItem__anchor') and contains(@href,'/capsules')]")));
        Actions action = new Actions(driver);
        action.moveToElement(navigationMenuLink).perform();

        // Click on the order link
        WebElement orderLink = wait.until(ExpectedConditions.elementToBeClickable(By.
                xpath("//a[@class='AccessibleLink HeaderNavigationBarDropdown__medium-link' and contains(@href,'capsules/')]")));
        orderLink.click();

        // Click on first product that appears
        // don't delete this, it's working fine
//            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By
//                    .xpath("(//article[@data-product-item-id]/a[contains(@href,'/capsules/')])[1]")));
//        productLink.click();


        //(//article[contains(@data-product-item-id,'')]/a[contains(@href,'/capsules/')])[1]
// Find all product links
//        List<WebElement> productLinks = driver.findElements(By.xpath("//article[@class='ProductCard']/a[contains(@href,'/order/capsules/')]"));
//

//// Click on the first product link if it exists
//        if (!productLinks.isEmpty()) {
//            WebElement firstProductLink = productLinks.get(0);
//            firstProductLink.click();
//        }



        // Add to cart
//        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ta-product-details__add-to-bag-button")));
//        addToCartButton.click();

        //add to cart for ch and fr from products page
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//button[contains(@class,'AddToBagButton')]")));
        addToCartButton.click();



        // Set quantity using input
        WebElement quantitySelector = wait.until(ExpectedConditions.elementToBeClickable(By.id("ta-quantity-selector__custom-field")));


        quantitySelector.sendKeys("20");

        // click on ok button
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ta-quantity-selector__custom-ok")));
        okButton.click();

        // difference between empty cart and full cart is in class MiniBasketButton--not-empty
        // open cart
        WebElement notEmptyCart = wait.until(ExpectedConditions.elementToBeClickable(By.
                xpath("//button[@class='MiniBasketButton MiniBasketButton--not-empty']")));
        notEmptyCart.click();

        // find span text
        WebElement cartCountElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='MiniBasketItemCategory__item-count']")));

        // Get the text content of the element
        String itemCountText = cartCountElement.getText();

        // Extract the number from the text
        String itemCountValue = itemCountText.replaceAll("[^0-9]", "");

        // Convert the extracted value to an integer
        int itemCount = Integer.parseInt(itemCountValue);

        System.out.println(itemCount);

        //proceed to checkout
        WebElement checkout = driver.findElement(By.id("ta-mini-basket__checkout"));
        checkout.click();

    }


    @Test(priority = 0)
    public void gptExercice(){

    }

    @Test(priority = 1)
    public void getTitle(){
        String title = driver.getTitle();
        System.out.println(driver.getTitle());
        Assert.assertEquals(title,"Login | Le Club | Nespresso");
    }
    @Test(priority = 2)
    public void maximizeWindowThenMinimize() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(3000);
//        driver.manage().window().minimize();
    }
    @Test(priority = 3)
    public void navigateTo(){
        try {
            Thread.sleep(7000);
            driver.navigate().to("https://google.com");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @AfterClass
    public void exit(){
//        driver.quit();
    }


}
