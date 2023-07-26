package nespressoPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class NespressoCart {
    private WebDriver driver;
    private WebDriverWait wait;
    private boolean capsuleExist = false;
    @FindBy(xpath = "//button[contains(@class,'AddToBagButton')]")
    WebElement addToCartButton;



    @FindBy(id = "ta-quantity-selector__custom-field")
    WebElement quantitySelector;
    @FindBy(xpath = "//button[@class='QuantitySelectorCustomField__button-ok'")
    WebElement okButton;
    @FindBy(xpath = "//button[contains(@class,'MiniBasketButton--not-empty')]")
    WebElement filledCart;
    @FindBy(xpath = "//div[@data-block='minicart']//span[@class='items qty']//span[@class='counter-number']")
    WebElement emptyCart;
    @FindBy(className = "MiniBasketItemCategory__item-count']")
    WebElement cartCountElement;
    @FindBy(id = "ta-mini-basket__checkout")
    WebElement proceedToCheckoutButton;
    @FindBy(xpath = "//div[@class='AddToBagButtonSmall__quantity'][1]")
    WebElement numberOfItemsInButtonDiv;
    //not strong
    @FindBy(xpath = "//span[@class='MiniBasketItemPriceAndName__price-calc'][1]")
    WebElement numberOfItemsInCartSpan;


    public NespressoCart(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }


    public String getQuantityOfSelectedProductInCartSpanOfCart(String productName) {
        //this works as well
        //article[.//h3[contains(text(),'Caramello')]]//div[@class='AddToBagButtonSmall__quantity']

//        String DivOfAddToBagButtonXpath = "//h3[contains(text(),'" +
//        productName + "')]//ancestor::article//div[@class='AddToBagButtonSmall__quantity']";
//        String stringNumberOfItemsInButtonDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(DivOfAddToBagButtonXpath))).getText();

        // this works as well
        //span[text()='Jamaica Blue Mountain']//ancestor::td//span[@class='MiniBasketItemPriceAndName__price-calc']
        String SpanOfMiniBasketOfSpecifiedProduct = "//td[.//span[contains(text(),'" + productName + "')]]//span[@class='MiniBasketItemPriceAndName__price-calc']";
        String stringNumberOfItemsInCartSpan;
        try {
            stringNumberOfItemsInCartSpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SpanOfMiniBasketOfSpecifiedProduct))).getText();
        } catch (Exception e) {
            stringNumberOfItemsInCartSpan = driver.findElement(By.xpath(SpanOfMiniBasketOfSpecifiedProduct)).getText();
        }

        ////
        String substringInCartSpan = stringNumberOfItemsInCartSpan.substring(1, stringNumberOfItemsInCartSpan.indexOf(" "));
//        Assert.assertEquals(stringNumberOfItemsInButtonDiv, substringInCartSpan);

        System.out.println("stringNumberOfItemsInCartSpan is " + stringNumberOfItemsInCartSpan);
        System.out.println("substringInCartSpan is " + substringInCartSpan);
//        System.out.println("stringNumberOfItemsInButtonDiv is " + stringNumberOfItemsInButtonDiv);

        ////
//        Assert.assertTrue(stringNumberOfItemsInCartSpan.contains(stringNumberOfItemsInButtonDiv));

//        Assert.assertTrue(stringNumberOfItemsInCartSpan.contains(stringNumberOfItemsInButtonDiv));
//        System.out.println("stringNumberOfItemsInCartSpan is " + stringNumberOfItemsInCartSpan);
        return substringInCartSpan;
    }


    public void clickOnCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(filledCart)).click();
        } catch (Exception Ignored) {
            driver.findElement((By) filledCart).click();
        }
    }

    public void proceedToCheckout() {
        try {
            proceedToCheckoutButton.click();
        } catch (Exception ignored) {
            wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();
        }
    }
//    public void enterOrPickQuantity(final String quantity) {
//        try {
////            this.wait = new WebDriverWait(driver, 2);
//            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//li/span[contains(@class,'qty-item-btn') and (text()=" + quantity + ")]")));
//            button.click();
//        } catch (Exception e) {
//            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(quantitySelector));
//            input.sendKeys(quantity);
//        }
//        WebElement ok = driver.findElement(By.id("ta-quantity-selector__custom-ok"));
//        ok.click();
//    }

    public void closeCart() {
        try {
            driver.findElement(By.xpath("//button[@id='ta-mini-basket__close']")).click();
        } catch (Exception Ignored) {
            wait.until(ExpectedConditions.elementToBeClickable(By.
                    xpath("//button[@id='ta-mini-basket__close']"))).click();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
