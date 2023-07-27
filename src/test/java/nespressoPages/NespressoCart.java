package nespressoPages;

import configuration.MyDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class NespressoCart {
    private WebDriver driver;
    private WebDriverWait wait;
    public NespressoCart(WebDriver driver) {
        this.driver = MyDriverManager.getDriver();
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public void clickOnCart() {
        try {
            driver.findElement(By.cssSelector(".showcart-wrapper")).click();
        } catch (NoSuchElementException e) {
            Assert.fail("unable to locate cart, message: " + e.getMessage());
        }
    }

    public int getQuantityOfSelectedProductInCartSpan(String productName) {
        String capitalizedProduct = UsefullFonctions.capitalizeEachWordInString(productName);
        String xpathOfInputOfMiniBasketOfSpecifiedProduct =
                "//a[contains(text(),'" + capitalizedProduct + "')]//ancestor::div[@class='product-item-details']//input";
        WebElement inputOfMiniBasketOfSpecifiedProduct =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfInputOfMiniBasketOfSpecifiedProduct)));
        String stringNumberOfItemsInCartInputAttribute = inputOfMiniBasketOfSpecifiedProduct.getAttribute("data-item-qty");
        return Integer.parseInt(stringNumberOfItemsInCartInputAttribute);
    }
}
