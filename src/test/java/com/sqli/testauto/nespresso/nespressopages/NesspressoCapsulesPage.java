package com.sqli.testauto.nespresso.nespressopages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NesspressoCapsulesPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//button[contains(@class,'AddToBagButton')]")
    WebElement addToCartButton;

    @FindBy(id = "ta-quantity-selector__custom-field")
    WebElement quantitySelector;
    @FindBy(xpath = "//button[@class='QuantitySelectorCustomField__button-ok'")
    WebElement okButton;
    @FindBy(xpath = "//button[contains(@class,'MiniBasketButton--not-empty')]")
    WebElement filledCart;
    @FindBy(xpath = "//button[contains(@class,'MiniBasketButton')]")
    WebElement Emptycart;
    @FindBy(className = "MiniBasketItemCategory__item-count']")
    WebElement cartCountElement;
    @FindBy(id = "ta-mini-basket__checkout")
    WebElement proceedToCheckoutButton;



    //not strong
    @FindBy(xpath = "//div[@class='AddToBagButtonSmall__quantity'][1]")
    WebElement numberOfItemsInButtonDiv;
    //not strong
    @FindBy(xpath = "//span[@class='MiniBasketItemPriceAndName__price-calc'][1]")
    WebElement numberOfItemsInCartSpan;


    public NesspressoCapsulesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }
    public void addProductToCartWithValidQuantity(String productName, String quantity){
        scrollToCapsules(productName);
        clickOnAddToCartButtonOfSpecifiedProduct(productName);
        setCapsuleQuantity(quantity);
        clickOnOKButton();
        WaitForCartToBeUpdated();
    }
    public void addProductToCartWithInvalidQuantityAndStop(String productName, String quantity){
        clickOnAddToCartButtonOfSpecifiedProduct(productName);
        setCapsuleQuantity(quantity);
        clickOnOKButton();
    }
    public void scrollToCapsules(String productName){
        String xpathOfScrollToTheSpecifiedProduct = "//h3[contains(text(),'" + productName + "')]//ancestor::article";

        // waiting for the article that has the specified title to be present in the page
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfScrollToTheSpecifiedProduct)));
        }
        catch (Exception ignored){
            driver.findElement(By.xpath(xpathOfScrollToTheSpecifiedProduct));
        }

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        // Scrolling down the page till the element is found
        WebElement productToScrollTo = driver.findElement(By.xpath(xpathOfScrollToTheSpecifiedProduct));
        jse.executeScript("arguments[0].scrollIntoView();", productToScrollTo);
        jse.executeScript("window.scrollBy(0,-100)", "");
    }

    public void addProductToCartWithEditedQuantity(String productName, String quantity){
        clickOnAddToCartButtonOfSpecifiedProduct(productName);
        setCapsuleQuantity(quantity);
        clickOnOKButton();
        clickOnOKButton();
        WaitForCartToBeUpdated();
    }

    public void clickOnAddToCartButtonOfSpecifiedProduct(String productName) {
        //scroll to bottom
//        ((JavascriptExecutor) driver)
//                .executeScript("window.scrollTo(0, document.body.scrollHeight)");



        String AddToBagButtonXpath = "//h3[contains(text(),'" + productName + "')]//ancestor::article//button[contains(@class,'AddToBagButton')]";
//        String AddToBagButtonXpath = "//h3[contains(text(),'" + productName + "')]//ancestor::article//button[contains(@class,'AddToBagButton AddToBagButtonSmall')]";
        WebElement addToCartButtonOfSpecifiedProduct =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AddToBagButtonXpath)));
        addToCartButtonOfSpecifiedProduct.click();

//        jse.executeScript("window.scrollTo(0, document.documentElement.scrollTop || document.body.scrollTop);");
    }

    public String getQuantityOfSelectedProductInCartSpan(String productName){
        //this works as well
        //article[.//h3[contains(text(),'Caramello')]]//div[@class='AddToBagButtonSmall__quantity']

//        String DivOfAddToBagButtonXpath = "//h3[contains(text(),'" +
//        productName + "')]//ancestor::article//div[@class='AddToBagButtonSmall__quantity']";
//        String stringNumberOfItemsInButtonDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(DivOfAddToBagButtonXpath))).getText();

        // this works as well
        //span[text()='Jamaica Blue Mountain']//ancestor::td//span[@class='MiniBasketItemPriceAndName__price-calc']
        String SpanOfMiniBasketOfSpecifiedProduct = "//td[.//span[text()='"+ productName + "']]//span[@class='MiniBasketItemPriceAndName__price-calc']";
        String stringNumberOfItemsInCartSpan;
        try {
            stringNumberOfItemsInCartSpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SpanOfMiniBasketOfSpecifiedProduct))).getText();
        }
        catch (Exception e){
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

    public void clickOnAddToCartButton(){
        try {
            addToCartButton.click();
        }
        catch (NoSuchElementException e){
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        }
    }
    public void setCapsuleQuantity(String quantity){
        wait.until(ExpectedConditions.elementToBeClickable(quantitySelector)).sendKeys(quantity);
    }
    public void clickOnOKButton(){
        try {
            driver.findElement(By.xpath("//button[@class='QuantitySelectorCustomField__button-ok']")).click();
//            driver.findElement((By) okButton).click(); // this does not work
        }
        catch (Exception e){
            wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
        }
    }
    public void WaitForCartToBeUpdated(){
        //wait until value of filled cart change
        String oldValueOfCartButton = Emptycart.getText();
        System.out.println(oldValueOfCartButton);
        new WebDriverWait(driver,8).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(Emptycart,oldValueOfCartButton)));
        String newValueOfCartButton = Emptycart.getText();
        System.out.println(newValueOfCartButton);
    }
    public void WaitForTextToBeChanged(final WebElement element){
        try {
            String oldValueOfCTextOfWebElement = element.getText();
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element,oldValueOfCTextOfWebElement)));
        }
        catch (Exception e){
            System.out.println("Text of element didn't change");
        }
    }

    public void clickOnCart(){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(filledCart)).click();
        }
        catch (Exception Ignored){
            driver.findElement((By) filledCart).click();
        }
    }

    public void verifyItemCount(){
    wait.until(ExpectedConditions.elementToBeClickable(numberOfItemsInButtonDiv));
    wait.until(ExpectedConditions.elementToBeClickable(numberOfItemsInCartSpan));

    String stringNumberOfItemsInButtonDiv = numberOfItemsInButtonDiv.getText();
    String stringNumberOfItemsInCartSpan = numberOfItemsInCartSpan.getText();

//        String substringInCartSpan = stringNumberOfItemsInCartSpan.substring(1, stringNumberOfItemsInCartSpan.indexOf(" "));
//        Assert.assertEquals(stringNumberOfItemsInButtonDiv, substringInCartSpan);

    Assert.assertTrue(stringNumberOfItemsInCartSpan.contains(stringNumberOfItemsInButtonDiv));
//        System.out.println("substringInCartSpan is " + substringInCartSpan);
    System.out.println("stringNumberOfItemsInButtonDiv is " + stringNumberOfItemsInButtonDiv);
    System.out.println("stringNumberOfItemsInCartSpan is " + stringNumberOfItemsInCartSpan);
    }

//        public void getCartItemCount() {
//            clickOnFilledCart();
//            //find span text
//            String itemCountText = wait.until(ExpectedConditions.elementToBeClickable(cartCountElement)).getText();
//            String itemCountValue = itemCountText.replaceAll("[^0-9]", "");
//            int itemCount = Integer.parseInt(itemCountValue);
//            System.out.println(itemCount);
//        }


    public void proceedToCheckout(){
        try {
            proceedToCheckoutButton.click();
        }
        catch (Exception ignored){
            wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();
        }
    }



    public void enterOrPickQuantity(final String quantity){
        try {
            this.wait = new WebDriverWait(driver, 2);
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//[@id='ta-quantity-selector__predefined-" + quantity + "']")));
            button.click();
        }
        catch (Exception e){
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(quantitySelector));
            input.sendKeys(quantity);
        }
        WebElement ok = driver.findElement(By.id("ta-quantity-selector__custom-ok"));
        ok.click();
    }

    public void closeCart() {
        try {
            driver.findElement(By.xpath("//button[@id='ta-mini-basket__close']")).click();
        }
        catch (Exception Ignored){
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
