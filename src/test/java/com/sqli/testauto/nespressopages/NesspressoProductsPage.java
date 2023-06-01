package com.sqli.testauto.nespressopages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NesspressoProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//button[contains(@class,'AddToBagButton')]")
    WebElement addToCartButton;

    @FindBy(id = "ta-quantity-selector__custom-field")
    WebElement quantitySelector;
    @FindBy(id = "ta-quantity-selector__custom-ok")
    WebElement okButton;
    @FindBy(xpath = "//button[contains(@class,'MiniBasketButton--not-empty')]")
    WebElement filledCart;
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




    public NesspressoProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void clickOnAddToCartButtonOfSpecifiedProduct(String productName) {
        //scroll to bottom
//        ((JavascriptExecutor) driver)
//                .executeScript("window.scrollTo(0, document.body.scrollHeight)");

        String xpathOfScrollToTheSpecifiedProduct = "//h3[contains(text(),'" + productName + "')]//ancestor::article";

        // waiting for the article that has the specified title to be present in the page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfScrollToTheSpecifiedProduct)));

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        // Scrolling down the page till the element is found
        WebElement productToScrollTo = driver.findElement(By.xpath(xpathOfScrollToTheSpecifiedProduct));

        jse.executeScript("arguments[0].scrollIntoView();", productToScrollTo);
        String AddToBagButtonXpath = "//h3[contains(text(),'" + productName + "')]//ancestor::article//button[contains(@class,'AddToBagButton AddToBagButtonSmall')]";
        WebElement addToCartButtonOfSpecifiedProduct =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AddToBagButtonXpath)));
        addToCartButtonOfSpecifiedProduct.click();

//        jse.executeScript("window.scrollTo(0, document.documentElement.scrollTop || document.body.scrollTop);");
    }

    public String verifyQuantityOfSelectedProduct(String productName){
        //this works as well
        //article[.//h3[contains(text(),'Caramello')]]//div[@class='AddToBagButtonSmall__quantity']

//        String DivOfAddToBagButtonXpath = "//h3[contains(text(),'" +
//        productName + "')]//ancestor::article//div[@class='AddToBagButtonSmall__quantity']";
//        String stringNumberOfItemsInButtonDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(DivOfAddToBagButtonXpath))).getText();

        // this works as well
        //span[text()='Jamaica Blue Mountain']//ancestor::td//span[@class='MiniBasketItemPriceAndName__price-calc']
        String SpanOfMiniBasketOfSpecifiedProduct = "//td[.//span[text()='"+ productName + "']]//span[@class='MiniBasketItemPriceAndName__price-calc']";
        String stringNumberOfItemsInCartSpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SpanOfMiniBasketOfSpecifiedProduct))).getText();

        ////
        String substringInCartSpan = stringNumberOfItemsInCartSpan.substring(1, stringNumberOfItemsInCartSpan.indexOf(" "));
//        Assert.assertEquals(stringNumberOfItemsInButtonDiv, substringInCartSpan);
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

    public void setQuantity(String quantity){
        wait.until(ExpectedConditions.elementToBeClickable(quantitySelector)).sendKeys(quantity);
    }

    public void clickOnOKButton(){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
        }
        catch (Exception e){
            wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
        }
    }

    public void clickOnFilledCart(){
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        wait.until(ExpectedConditions.elementToBeClickable(filledCart)).click();
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
        proceedToCheckoutButton.click();
    }
}
