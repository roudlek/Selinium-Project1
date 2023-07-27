package nespressoPages;

import configuration.MyDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class NespressoCapsulesPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//div[@data-block='minicart']//span[@class='items qty']//span[@class='counter-number']")
    WebElement cartSpanValue;

    public NespressoCapsulesPage(WebDriver driver) {
        this.driver = MyDriverManager.getDriver();
        ;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public void scrollToCapsules(String product) {
        String capitalizedProduct = UsefullFonctions.capitalizeEachWordInString(product);
        String xpathOfScrollToTheSpecifiedProduct = "//a[contains(text(),'" + capitalizedProduct + "')]";
        try {
            driver.findElement(By.xpath(xpathOfScrollToTheSpecifiedProduct));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            // Scrolling down the page till the element is found
            WebElement productToScrollTo = driver.findElement(By.xpath(xpathOfScrollToTheSpecifiedProduct));
            jse.executeScript("arguments[0].scrollIntoView();", productToScrollTo);
            jse.executeScript("window.scrollBy(0,-700)", "");
        } catch (Exception ignored) {
            Assert.fail("capsule with name: " + capitalizedProduct + " does not exist in page");
        }
    }

    public void clickOnAddToCartButtonOfSpecifiedProduct(String product) {
        String capitalizedProduct = UsefullFonctions.capitalizeEachWordInString(product);
        String addToBagButtonXpath =
                "//a[contains(text(),'" + capitalizedProduct +
                        "')]//ancestor::div[contains(@class,'product-item-details')]//button[contains(@class,'action')]";
        try {
            WebElement addToBagButton = driver.findElement(By.xpath(addToBagButtonXpath));
            addToBagButton.click();
        } catch (NoSuchElementException e) {
            Assert.fail("Add to bag button of" + capitalizedProduct + " not found ");
        }
    }

    public void waitForCartToBeUpdated() {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@data-block='minicart']//span[@class='items qty']//span[@class='counter-number']")));
        String oldValueOfCartButton = getInnerElementText(cartSpanValue).trim();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(cartSpanValue, oldValueOfCartButton)));
    }

    // Helper method to get the inner text of an element using JavaScriptExecutor
    private String getInnerElementText(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].innerText", element);
    }


    public void pickQuantity(int quantity) {
        this.wait = new WebDriverWait(driver, 2);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(
                        "//div[contains(@class,'body-popup-overlay active')]//span[text()='" + quantity + "']")));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        button.click();
    }
}
