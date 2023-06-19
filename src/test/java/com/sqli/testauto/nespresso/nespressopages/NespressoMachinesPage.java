package com.sqli.testauto.nespresso.nespressopages;

import com.sqli.testauto.products.machines.MachineTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NespressoMachinesPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private MachineTest machineTest;
    public Boolean isMachineAvailable = false;
    @FindBy(id = "ta-quantity-selector__custom-field")
    WebElement quantitySelector;
    @FindBy(xpath = "//button[contains(@class,'MiniBasketButton')]")
    WebElement Emptycart;
    @FindBy(xpath = "//button[@class='QuantitySelectorCustomField__button-ok'")
    WebElement okButton;
    @FindBy(xpath = "//button[contains(@class,'MiniBasketButton--not-empty')]")
    WebElement filledCart;
    public NespressoMachinesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
        this.machineTest = new MachineTest();
        PageFactory.initElements(driver, this);
    }

    public void scrollToMachine(String productName){
        String xpathOfScrollToTheSpecifiedProduct = "//a[contains(text(),'" + productName + "')]//ancestor::article";
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
    public void selectMachine(String machineName) {
        try {
            driver.findElement(By.xpath("//article//a[contains(text(),'" + machineName + "')]")).click();
        }
        catch (Exception ignored){
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//article//a[contains(text(),'" + machineName + "')]"))).click();
        }
    }

    public boolean isAddToCartButtonEnabled(String machineName){
        String addToCartButtonXpath = "//h1[contains(text(),'" + machineName + "')]//ancestor::div//button[contains(@class,'AddToBagButton')]";
        WebElement addToCartButton = driver.findElement(By.xpath(addToCartButtonXpath));
        boolean isAddToCartButtonEnabled = addToCartButton.isDisplayed() && addToCartButton.isEnabled();
        isMachineAvailable = isAddToCartButtonEnabled;
        System.out.println("Add to Bag button " + (isAddToCartButtonEnabled ? "found and enabled" : "not found or not enabled, probably item out of stock"));

        return isAddToCartButtonEnabled;
    }
    public void tryAddProductToCart(String machineName, String quantity){
        String AddToCartButtonXpath = "//h1[contains(text(),'" + machineName + "')]//ancestor::div//button[contains(@class,'AddToBagButton')]";
        if(isAddToCartButtonEnabled(machineName)){
            driver.findElement(By.xpath(AddToCartButtonXpath)).click();
            setMachineQuantity(quantity);
            clickOnOKButton();
            WaitForCartToBeUpdated();
            clickOnCart();
        }
        else{
            System.out.println("add to bag button not found");
        }
    }
    public void setMachineQuantity(String quantity){
        wait.until(ExpectedConditions.elementToBeClickable(quantitySelector)).sendKeys(quantity);
    }

    public void clickOnOKButton(){
        try {
            driver.findElement(By.xpath("//button[@class='QuantitySelectorCustomField__button-ok']")).click();
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
    public void clickOnCart(){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(filledCart)).click();
        }
        catch (Exception Ignored){
            driver.findElement((By) filledCart).click();
        }
    }
    public String getQuantityOfSelectedProductInCartSpan(String machineName){
        String xpathOfSpanOfMiniBasketOfSpecifiedProduct = "//td[contains(@headers,'machines')][.//span[contains(text(),'" + machineName + "')]]//span[@class='MiniBasketItemPriceAndName__price-calc']";
        String stringNumberOfItemsInCartSpan;
        try {
            stringNumberOfItemsInCartSpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfSpanOfMiniBasketOfSpecifiedProduct))).getText();
        }
        catch (Exception e){
            stringNumberOfItemsInCartSpan = driver.findElement(By.xpath(xpathOfSpanOfMiniBasketOfSpecifiedProduct)).getText();
        }
        String substringInCartSpan = stringNumberOfItemsInCartSpan.substring(1, stringNumberOfItemsInCartSpan.indexOf(" "));
        System.out.println("xpathOfSpanOfMiniBasketOfSpecifiedProduct is " + xpathOfSpanOfMiniBasketOfSpecifiedProduct);
        System.out.println("substringInCartSpan is " + substringInCartSpan);
        return substringInCartSpan;
    }
    public void addMachineToCartWithValidQuantity(String machineName, String quantity){
        scrollToMachine(machineName);
        selectMachine(machineName);
        tryAddProductToCart(machineName,quantity);
    }
}
