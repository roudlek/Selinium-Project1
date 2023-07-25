package nespressoPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class NespressoCapsulesPage {
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
    WebElement Emptycart;
    @FindBy(className = "MiniBasketItemCategory__item-count']")
    WebElement cartCountElement;
    @FindBy(id = "ta-mini-basket__checkout")
    WebElement proceedToCheckoutButton;
    @FindBy(xpath = "//div[@class='AddToBagButtonSmall__quantity'][1]")
    WebElement numberOfItemsInButtonDiv;
    //not strong
    @FindBy(xpath = "//span[@class='MiniBasketItemPriceAndName__price-calc'][1]")
    WebElement numberOfItemsInCartSpan;


    public NespressoCapsulesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public void addProductToCartWithValidQuantity(String productName, String quantity) {
        scrollToCapsules(productName);
        clickOnAddToCartButtonOfSpecifiedProduct(productName);
        setCapsuleQuantity(quantity);
        clickOnOKButton();
        waitForCartToBeUpdated();
    }

    public void addProductToCartWithInvalidQuantityAndStop(String productName, String quantity) {
        clickOnAddToCartButtonOfSpecifiedProduct(productName);
        setCapsuleQuantity(quantity);
        clickOnOKButton();
    }


    public void scrollToCapsules(String product) {
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//button[@id='_evidon-accept-button']")))).click();
        String capitalizedProduct = UsefullFonctions.capitalizeEachWordInString(product);
        String xpathOfScrollToTheSpecifiedProduct = "//a[contains(text(),'" + capitalizedProduct + "')]";
//        String xpathOfScrollToTheSpecifiedProduct = "//a[contains(text(),'" + product + "')]//ancestor::strong";
        // waiting for the article that has the specified title to be present in the page
        try {
            driver.findElement(By.xpath(xpathOfScrollToTheSpecifiedProduct));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            // Scrolling down the page till the element is found
            WebElement productToScrollTo = driver.findElement(By.xpath(xpathOfScrollToTheSpecifiedProduct));
            jse.executeScript("arguments[0].scrollIntoView();", productToScrollTo);
            jse.executeScript("window.scrollBy(0,-700)", "");
        } catch (Exception ignored) {
            Assert.fail("capsule with name: " + capitalizedProduct + " does not exist in page");
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfScrollToTheSpecifiedProduct)));
        }
    }

    public void addProductToCartWithEditedQuantity(String productName, String quantity) {
        clickOnAddToCartButtonOfSpecifiedProduct(productName);
        setCapsuleQuantity(quantity);
        clickOnOKButton();
        clickOnOKButton();
        waitForCartToBeUpdated();
    }

    public void clickOnAddToCartButtonOfSpecifiedProduct(String product) {
        String capitalizedProduct = UsefullFonctions.capitalizeEachWordInString(product);
        String addToBagButtonXpath =
                "//a[contains(text(),'" + capitalizedProduct + "')]//ancestor::div[contains(@class,'product-item-details')]//button";
        try {
            WebElement addToBagButton = driver.findElement(By.xpath(addToBagButtonXpath));
            addToBagButton.click();
        } catch (NoSuchElementException e) {
            Assert.fail("Add to bag button of" + capitalizedProduct + " not found ");
        }
//        WebElement addToCartButtonOfSpecifiedProduct =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AddToBagButtonXpath)));
//        addToCartButtonOfSpecifiedProduct.click();
    }

    public String getQuantityOfSelectedProductInCartSpan(String productName) {
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

    public void clickOnAddToCartButton() {
        try {
            addToCartButton.click();
        } catch (NoSuchElementException e) {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        }
    }

    public void setCapsuleQuantity(String quantity) {
        wait.until(ExpectedConditions.elementToBeClickable(quantitySelector)).sendKeys(quantity);
    }

    public void clickOnOKButton() {
        try {
            driver.findElement(By.xpath("//button[@class='QuantitySelectorCustomField__button-ok']")).click();
//            driver.findElement((By) okButton).click(); // this does not work
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
        }
    }

//    public void waitForCartToBeUpdated() {
//        //wait until value of filled cart change
//        String oldValueOfCartButton = Emptycart.getText();
//        System.out.println(oldValueOfCartButton);
//        new WebDriverWait(driver, 8).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(Emptycart, oldValueOfCartButton)));
//        String newValueOfCartButton = Emptycart.getText();
//        System.out.println(newValueOfCartButton);
//    }

    ////

    public void waitForCartToBeUpdated() {
        // Wait for the value of the cart to change
        String oldValueOfCartButton = getInnerElementText(Emptycart).trim();
        System.out.println("the old value: " + oldValueOfCartButton);

        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(Emptycart, oldValueOfCartButton)));

        String newValueOfCartButton = getInnerElementText(Emptycart).trim();
        System.out.println("the new value: " + newValueOfCartButton);
    }

    // Helper method to get the inner text of an element using JavaScriptExecutor
    private String getInnerElementText(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].innerText", element);
    }

    // Helper method to get the inner text of an element using JavaScriptExecutor


    ///////

    public void WaitForTextToBeChanged(final WebElement element) {
        try {
            String oldValueOfCTextOfWebElement = element.getText();
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, oldValueOfCTextOfWebElement)));
        } catch (Exception e) {
            System.out.println("Text of element didn't change");
        }
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

    public void pickQuantity(int quantity) {
        this.wait = new WebDriverWait(driver, 2);
//        $x("//ul[@class='qty-list']//li[@class='qty-item']/span[@class='qty-item-btn ' and @data-qtyitem = '1']"); // 42 result
//        "//div[@class='qty-box coffee-label-block-parent body-popup-overlay active']//span[text()='1'] // 1 is result
//        //div[contains(@class,'body-popup-overlay active')]//span[text()='1']
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(
                        "//div[contains(@class,'body-popup-overlay active')]//span[text()='" + quantity + "']")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        button.click();


    }

    public void enterOrPickQuantity(final String quantity) {
        try {
//            this.wait = new WebDriverWait(driver, 2);
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li/span[contains(@class,'qty-item-btn') and (text()=" + quantity + ")]")));
            button.click();
        } catch (Exception e) {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(quantitySelector));
            input.sendKeys(quantity);
        }
        WebElement ok = driver.findElement(By.id("ta-quantity-selector__custom-ok"));
        ok.click();
    }

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
